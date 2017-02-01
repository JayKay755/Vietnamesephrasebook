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

public class FoodActivity extends AppCompatActivity {

    private SoundPool mSoundPool;
    private AssetManager mAssetManager;

    private int mStreamID;

    private Map<String, Integer> map = new HashMap<String, Integer>();

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food);


        mAdView = (AdView) findViewById(R.id.adView14);
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
            case R.id.foodButton:
                playSound(map.get("chicken"));
                break;
            case R.id.foodButton2:
                playSound(map.get("beef"));
                break;
            case R.id.foodButton3:
                playSound(map.get("pork"));
                break;
            case R.id.foodButton4:
                playSound(map.get("rice"));
                break;
            case R.id.foodButton5:
                playSound(map.get("noodle"));
                break;
            case R.id.foodButton6:
                playSound(map.get("soup"));
                break;
            case R.id.foodButton7:
                playSound(map.get("mango"));
                break;
            case R.id.foodButton8:
                playSound(map.get("banana"));
                break;
            case R.id.foodButton9:
                playSound(map.get("watermelon"));
                break;
            case R.id.foodButton10:
                playSound(map.get("durian"));
                break;
            case R.id.foodButton11:
                playSound(map.get("sapodilla"));
                break;
            case R.id.foodButton12:
                playSound(map.get("mangosteen"));
                break;
            case R.id.foodButton13:
                playSound(map.get("papaya"));
                break;
            case R.id.foodButton14:
                playSound(map.get("passion_fruit"));
                break;
            case R.id.foodButton15:
                playSound(map.get("guava"));
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
        map.put("chicken", loadSound("food/chicken.ogg"));
        map.put("beef", loadSound("food/beef.ogg"));
        map.put("pork", loadSound("food/pork.ogg"));
        map.put("rice", loadSound("food/rice.ogg"));
        map.put("noodle", loadSound("food/noodle.ogg"));
        map.put("soup", loadSound("food/soup.ogg"));
        map.put("mango", loadSound("food/mango.ogg"));
        map.put("banana", loadSound("food/banana.ogg"));
        map.put("watermelon", loadSound("food/watermelon.ogg"));
        map.put("durian", loadSound("food/durian.ogg"));
        map.put("sapodilla", loadSound("food/sapodilla.ogg"));
        map.put("mangosteen", loadSound("food/mangosteen.ogg"));
        map.put("papaya", loadSound("food/papaya.ogg"));
        map.put("passion_fruit", loadSound("food/passion_fruit.ogg"));
        map.put("guava", loadSound("food/guava.ogg"));
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
