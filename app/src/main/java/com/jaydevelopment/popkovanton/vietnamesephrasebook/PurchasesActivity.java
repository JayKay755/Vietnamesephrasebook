package com.jaydevelopment.popkovanton.vietnamesephrasebook;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PurchasesActivity extends AppCompatActivity {

    private SoundPool mSoundPool;
    private AssetManager mAssetManager;

    private int mStreamID;

    private Map<String, Integer> map = new HashMap<String, Integer>();

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.purchases);

        mAdView = (AdView) findViewById(R.id.adView11);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        ActionBar actionBar = getSupportActionBar();///////////////////
        actionBar.setHomeButtonEnabled(true);//////////////////////////
        actionBar.setDisplayHomeAsUpEnabled(true);/////////////////////
    }//////////////////////////////////////////////////////////////////

    @Override//////////////////////////////////////////////////////////КНОПКА
    public boolean onOptionsItemSelected(MenuItem item) {//////////////ДОМОЙ
        switch (item.getItemId()) {////////////////////////////////////
            case android.R.id.home:////////////////////////////////////
                finish();
            default:///////////////////////////////////////////////////
                return super.onOptionsItemSelected(item);//////////////
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.purchasesButton:
                playSound(map.get("menswear"));
                break;
            case R.id.purchasesButton2:
                playSound(map.get("women_clothing"));
                break;
            case R.id.purchasesButton3:
                playSound(map.get("baby_clothes"));
                break;
            case R.id.purchasesButton4:
                playSound(map.get("how_much"));
                break;
            case R.id.purchasesButton5:
                playSound(map.get("i_dont_like"));
                break;
            case R.id.purchasesButton6:
                playSound(map.get("i_buy_it"));
                break;
            case R.id.purchasesButton7:
                playSound(map.get("cheap"));
                break;
            case R.id.purchasesButton8:
                playSound(map.get("expensive"));
                break;
            case R.id.purchasesButton9:
                playSound(map.get("shop"));
                break;
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void createNewSoundPool() {
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        mSoundPool = new SoundPool.Builder()
                .setAudioAttributes(attributes)
                .setMaxStreams(1)
                .build();
    }

    @SuppressWarnings("deprecation")
    private void createOldSoundPool() {
        mSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
    }

    private int playSound(int sound) {
        if (sound > 0) {
            mStreamID = mSoundPool.play(sound, 1, 1, 0, 0, 1);
        }
        return mStreamID;
    }

    private int loadSound(String fileName) {
        AssetFileDescriptor afd;
        try {
            afd = mAssetManager.openFd(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
        return mSoundPool.load(afd, 1);
    }

    @Override
    protected void onResume() {
        mAdView.resume();

        super.onResume();

        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            // Для устройств до Android 5
            createOldSoundPool();
        } else {
            // Для новых устройств
            createNewSoundPool();
        }
        mAssetManager = getAssets();
        map.put("menswear", loadSound("purchases/menswear.ogg"));
        map.put("women_clothing", loadSound("purchases/women_clothing.ogg"));
        map.put("baby_clothes", loadSound("purchases/baby_clothes.ogg"));
        map.put("how_much", loadSound("purchases/how_much.ogg"));
        map.put("i_dont_like", loadSound("purchases/i_dont_like.ogg"));
        map.put("i_buy_it", loadSound("purchases/i_buy_it.ogg"));
        map.put("cheap", loadSound("purchases/cheap.ogg"));
        map.put("expensive", loadSound("purchases/expensive.ogg"));
        map.put("shop", loadSound("purchases/shop.ogg"));
    }

    @Override
    protected void onPause() {
        mAdView.pause();
        super.onPause();
        mSoundPool.release();
        mSoundPool = null;
    }

    @Override
    protected void onDestroy() {
        mAdView.destroy();
        super.onDestroy();
    }
}
