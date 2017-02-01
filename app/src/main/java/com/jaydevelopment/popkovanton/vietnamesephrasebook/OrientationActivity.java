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

public class OrientationActivity extends AppCompatActivity {

    private SoundPool mSoundPool;
    private AssetManager mAssetManager;

    private int mStreamID;

    private Map<String, Integer> map = new HashMap<String, Integer>();

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orientation);

        mAdView = (AdView) findViewById(R.id.adView6);
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
            case R.id.orientationButton:
                playSound(map.get("left"));
                break;
            case R.id.orientationButton2:
                playSound(map.get("right"));
                break;
            case R.id.orientationButton3:
                playSound(map.get("forward"));
                break;
            case R.id.orientationButton4:
                playSound(map.get("up"));
                break;
            case R.id.orientationButton5:
                playSound(map.get("down"));
                break;
            case R.id.orientationButton6:
                playSound(map.get("long_away"));
                break;
            case R.id.orientationButton7:
                playSound(map.get("close"));
                break;
            case R.id.orientationButton8:
                playSound(map.get("map"));
                break;
            case R.id.orientationButton9:
                playSound(map.get("post_office"));
                break;
            case R.id.orientationButton10:
                playSound(map.get("museum"));
                break;
            case R.id.orientationButton11:
                playSound(map.get("bank"));
                break;
            case R.id.orientationButton12:
                playSound(map.get("police"));
                break;
            case R.id.orientationButton13:
                playSound(map.get("hospital"));
                break;
            case R.id.orientationButton14:
                playSound(map.get("pharmacy"));
                break;
            case R.id.orientationButton15:
                playSound(map.get("shop"));
                break;
            case R.id.orientationButton16:
                playSound(map.get("restaurant"));
                break;
            case R.id.orientationButton17:
                playSound(map.get("street"));
                break;
            case R.id.orientationButton18:
                playSound(map.get("square"));
                break;
            case R.id.orientationButton19:
                playSound(map.get("which_address_here"));
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
        map.put("left", loadSound("orientation/left.ogg"));
        map.put("right", loadSound("orientation/right.ogg"));
        map.put("forward", loadSound("orientation/forward.ogg"));
        map.put("up", loadSound("orientation/up.ogg"));
        map.put("down", loadSound("orientation/down.ogg"));
        map.put("long_away", loadSound("orientation/long_away.ogg"));
        map.put("close", loadSound("orientation/close.ogg"));
        map.put("map", loadSound("orientation/map.ogg"));
        map.put("post_office", loadSound("orientation/post_office.ogg"));
        map.put("museum", loadSound("orientation/museum.ogg"));
        map.put("bank", loadSound("orientation/bank.ogg"));
        map.put("police", loadSound("orientation/police.ogg"));
        map.put("hospital", loadSound("orientation/hospital.ogg"));
        map.put("pharmacy", loadSound("orientation/pharmacy.ogg"));
        map.put("shop", loadSound("orientation/shop.ogg"));
        map.put("restaurant", loadSound("orientation/restaurant.ogg"));
        map.put("street", loadSound("orientation/street.ogg"));
        map.put("square", loadSound("orientation/square.ogg"));
        map.put("which_address_here", loadSound("orientation/which_address_here.ogg"));
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