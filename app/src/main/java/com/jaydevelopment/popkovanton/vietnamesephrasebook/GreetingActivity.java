package com.jaydevelopment.popkovanton.vietnamesephrasebook;

import android.annotation.TargetApi;
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

public class GreetingActivity extends AppCompatActivity {

    private SoundPool mSoundPool;
    private AssetManager mAssetManager;

    private int mStreamID;

    private Map<String, Integer> map = new HashMap<String, Integer>();

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.greeting);


        mAdView = (AdView) findViewById(R.id.adView2);
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
            case R.id.greetingButton:
                playSound(map.get("hello"));
                break;
            case R.id.greetingButton2:
                playSound(map.get("hello_sir"));
                break;
            case R.id.greetingButton3:
                playSound(map.get("dear_friends"));
                break;
            case R.id.greetingButton4:
                playSound(map.get("goodbuy"));
                break;
            case R.id.greetingButton5:
                playSound(map.get("wen_we_will_meet"));
                break;
            case R.id.greetingButton6:
                playSound(map.get("where_are_we_gona_meet"));
                break;
            case R.id.greetingButton7:
                playSound(map.get("good_night"));
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
        map.put("hello", loadSound("greeting/hello.ogg"));
        map.put("hello_sir", loadSound("greeting/hello_sir.ogg"));
        map.put("dear_friends", loadSound("greeting/dear_friends.ogg"));
        map.put("goodbuy", loadSound("greeting/goodbuy.ogg"));
        map.put("wen_we_will_meet", loadSound("greeting/wen_we_will_meet.ogg"));
        map.put("where_are_we_gona_meet", loadSound("greeting/where_are_we_gona_meet.ogg"));
        map.put("good_night", loadSound("greeting/good_night.ogg"));
    }

    @Override
    protected void onPause() {
        mAdView.pause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mAdView.destroy();
        super.onDestroy();
    }
}
