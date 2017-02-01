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

public class NumberActivity extends AppCompatActivity {

    private SoundPool mSoundPool;
    private AssetManager mAssetManager;

    private int mStreamID;

    private Map<String, Integer> map = new HashMap<String, Integer>();

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.number);

        mAdView = (AdView) findViewById(R.id.adView13);
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
            case R.id.numberButton:
                playSound(map.get("zero"));
                break;
            case R.id.numberButton2:
                playSound(map.get("one"));
                break;
            case R.id.numberButton3:
                playSound(map.get("two"));
                break;
            case R.id.numberButton4:
                playSound(map.get("three"));
                break;
            case R.id.numberButton5:
                playSound(map.get("four"));
                break;
            case R.id.numberButton6:
                playSound(map.get("five"));
                break;
            case R.id.numberButton7:
                playSound(map.get("six"));
                break;
            case R.id.numberButton8:
                playSound(map.get("seven"));
                break;
            case R.id.numberButton9:
                playSound(map.get("eight"));
                break;
            case R.id.numberButton10:
                playSound(map.get("nine"));
                break;
            case R.id.numberButton11:
                playSound(map.get("ten"));
                break;
            case R.id.numberButton12:
                playSound(map.get("eleven"));
                break;
            case R.id.numberButton13:
                playSound(map.get("twenty"));
                break;
            case R.id.numberButton14:
                playSound(map.get("hundred"));
                break;
            case R.id.numberButton15:
                playSound(map.get("thousand"));
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
        map.put("zero", loadSound("number/zero.ogg"));
        map.put("one", loadSound("number/one.ogg"));
        map.put("two", loadSound("number/two.ogg"));
        map.put("three", loadSound("number/three.ogg"));
        map.put("four", loadSound("number/four.ogg"));
        map.put("five", loadSound("number/five.ogg"));
        map.put("six", loadSound("number/six.ogg"));
        map.put("seven", loadSound("number/seven.ogg"));
        map.put("eight", loadSound("number/eight.ogg"));
        map.put("nine", loadSound("number/nine.ogg"));
        map.put("ten", loadSound("number/ten.ogg"));
        map.put("eleven", loadSound("number/eleven.ogg"));
        map.put("twenty", loadSound("number/twenty.ogg"));
        map.put("hundred", loadSound("number/hundred.ogg"));
        map.put("thousand", loadSound("number/thousand.ogg"));
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
