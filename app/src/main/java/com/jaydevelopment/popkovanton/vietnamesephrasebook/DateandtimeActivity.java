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


public class DateandtimeActivity extends AppCompatActivity {

    private SoundPool mSoundPool;
    private AssetManager mAssetManager;

    private int mStreamID;

    private Map<String, Integer> map = new HashMap<String, Integer>();
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dateandtime);

        mAdView = (AdView) findViewById(R.id.adView10);
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
            case R.id.dateandtimeButton:
                playSound(map.get("whats_time_is_it"));
                break;
            case R.id.dateandtimeButton2:
                playSound(map.get("day"));
                break;
            case R.id.dateandtimeButton3:
                playSound(map.get("week"));
                break;
            case R.id.dateandtimeButton4:
                playSound(map.get("monday"));
                break;
            case R.id.dateandtimeButton5:
                playSound(map.get("tuesday"));
                break;
            case R.id.dateandtimeButton6:
                playSound(map.get("wednesday"));
                break;
            case R.id.dateandtimeButton7:
                playSound(map.get("thursday"));
                break;
            case R.id.dateandtimeButton8:
                playSound(map.get("friday"));
                break;
            case R.id.dateandtimeButton9:
                playSound(map.get("saturday"));
                break;
            case R.id.dateandtimeButton10:
                playSound(map.get("sunday"));
                break;
            case R.id.dateandtimeButton11:
                playSound(map.get("spring"));
                break;
            case R.id.dateandtimeButton12:
                playSound(map.get("summer"));
                break;
            case R.id.dateandtimeButton13:
                playSound(map.get("autumn"));
                break;
            case R.id.dateandtimeButton14:
                playSound(map.get("winter"));
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
        map.put("whats_time_is_it", loadSound("dateandtime/whats_time_is_it.ogg"));
        map.put("day", loadSound("dateandtime/day.ogg"));
        map.put("week", loadSound("dateandtime/week.ogg"));
        map.put("monday", loadSound("dateandtime/monday.ogg"));
        map.put("tuesday", loadSound("dateandtime/tuesday.ogg"));
        map.put("wednesday", loadSound("dateandtime/wednesday.ogg"));
        map.put("thursday", loadSound("dateandtime/thursday.ogg"));
        map.put("friday", loadSound("dateandtime/friday.ogg"));
        map.put("saturday", loadSound("dateandtime/saturday.ogg"));
        map.put("sunday", loadSound("dateandtime/sunday.ogg"));
        map.put("spring", loadSound("dateandtime/spring.ogg"));
        map.put("summer", loadSound("dateandtime/summer.ogg"));
        map.put("autumn", loadSound("dateandtime/autumn.ogg"));
        map.put("winter", loadSound("dateandtime/winter.ogg"));
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
