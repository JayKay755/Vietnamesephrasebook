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

public class EmergenciesActivity extends AppCompatActivity {

    private SoundPool mSoundPool;
    private AssetManager mAssetManager;

    private int mStreamID;

    private Map<String, Integer> map = new HashMap<String, Integer>();

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emergencies);

        mAdView = (AdView) findViewById(R.id.adView9);
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
            case R.id.emergenciesButton:
                playSound(map.get("fire_department"));
                break;
            case R.id.emergenciesButton2:
                playSound(map.get("police"));
                break;
            case R.id.emergenciesButton3:
                playSound(map.get("ambulance"));
                break;
            case R.id.emergenciesButton4:
                playSound(map.get("hospital"));
                break;
            case R.id.emergenciesButton5:
                playSound(map.get("pain"));
                break;
            case R.id.emergenciesButton6:
                playSound(map.get("pharmacy"));
                break;
            case R.id.emergenciesButton7:
                playSound(map.get("doctor"));
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
        map.put("fire_department", loadSound("emergencies/fire_department.ogg"));
        map.put("police", loadSound("emergencies/police.ogg"));
        map.put("ambulance", loadSound("emergencies/ambulance.ogg"));
        map.put("hospital", loadSound("emergencies/hospital.ogg"));
        map.put("pain", loadSound("emergencies/pain.ogg"));
        map.put("pharmacy", loadSound("emergencies/pharmacy.ogg"));
        map.put("doctor", loadSound("emergencies/doctor.ogg"));
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
