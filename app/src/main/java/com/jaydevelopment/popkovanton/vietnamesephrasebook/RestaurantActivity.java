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

public class RestaurantActivity extends AppCompatActivity {

    private SoundPool mSoundPool;
    private AssetManager mAssetManager;

    private int mStreamID;

    private Map<String, Integer> map = new HashMap<String, Integer>();

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant);

        mAdView = (AdView) findViewById(R.id.adView12);
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
            case R.id.restaurantButton:
                playSound(map.get("breakfast"));
                break;
            case R.id.restaurantButton2:
                playSound(map.get("lunch"));
                break;
            case R.id.restaurantButton3:
                playSound(map.get("dinner"));
                break;
            case R.id.restaurantButton4:
                playSound(map.get("bread"));
                break;
            case R.id.restaurantButton5:
                playSound(map.get("coffee"));
                break;
            case R.id.restaurantButton6:
                playSound(map.get("juice"));
                break;
            case R.id.restaurantButton7:
                playSound(map.get("water"));
                break;
            case R.id.restaurantButton8:
                playSound(map.get("beer"));
                break;
            case R.id.restaurantButton9:
                playSound(map.get("wine"));
                break;
            case R.id.restaurantButton10:
                playSound(map.get("meat"));
                break;
            case R.id.restaurantButton11:
                playSound(map.get("vegetables"));
                break;
            case R.id.restaurantButton12:
                playSound(map.get("fruit"));
                break;
            case R.id.restaurantButton13:
                playSound(map.get("spoon"));
                break;
            case R.id.restaurantButton14:
                playSound(map.get("knife"));
                break;
            case R.id.restaurantButton15:
                playSound(map.get("fork"));
                break;
            case R.id.restaurantButton16:
                playSound(map.get("theres_no_one_siting"));
                break;
            case R.id.restaurantButton17:
                playSound(map.get("waiter"));
                break;
            case R.id.restaurantButton18:
                playSound(map.get("thanks"));
                break;
            case R.id.restaurantButton19:
                playSound(map.get("very_tasty"));
                break;
            case R.id.restaurantButton20:
                playSound(map.get("bill"));
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
        map.put("breakfast", loadSound("restaurant/breakfast.ogg"));
        map.put("lunch", loadSound("restaurant/lunch.ogg"));
        map.put("dinner", loadSound("restaurant/dinner.ogg"));
        map.put("bread", loadSound("restaurant/bread.ogg"));
        map.put("coffee", loadSound("restaurant/coffee.ogg"));
        map.put("juice", loadSound("restaurant/juice.ogg"));
        map.put("water", loadSound("restaurant/water.ogg"));
        map.put("beer", loadSound("restaurant/beer.ogg"));
        map.put("wine", loadSound("restaurant/wine.ogg"));
        map.put("meat", loadSound("restaurant/meat.ogg"));
        map.put("vegetables", loadSound("restaurant/vegetables.ogg"));
        map.put("fruit", loadSound("restaurant/fruit.ogg"));
        map.put("spoon", loadSound("restaurant/spoon.ogg"));
        map.put("knife", loadSound("restaurant/knife.ogg"));
        map.put("fork", loadSound("restaurant/fork.ogg"));
        map.put("theres_no_one_siting", loadSound("restaurant/theres_no_one_siting.ogg"));
        map.put("waiter", loadSound("restaurant/waiter.ogg"));
        map.put("thanks", loadSound("restaurant/thanks.ogg"));
        map.put("very_tasty", loadSound("restaurant/very_tasty.ogg"));
        map.put("bill", loadSound("restaurant/bill.ogg"));
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
