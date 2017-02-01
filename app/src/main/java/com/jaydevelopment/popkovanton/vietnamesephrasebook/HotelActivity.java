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

public class HotelActivity extends AppCompatActivity {

    private SoundPool mSoundPool;
    private AssetManager mAssetManager;

    private int mStreamID;

    private Map<String, Integer> map = new HashMap<String, Integer>();

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotel);

        mAdView = (AdView) findViewById(R.id.adView8);
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
                return super.onOptionsItemSelected(item);///////////////
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.hotelButton:
                playSound(map.get("for_one_person"));
                break;
            case R.id.hotelButton2:
                playSound(map.get("for_two_person"));
                break;
            case R.id.hotelButton3:
                playSound(map.get("large"));
                break;
            case R.id.hotelButton4:
                playSound(map.get("smaller"));
                break;
            case R.id.hotelButton5:
                playSound(map.get("cheap"));
                break;
            case R.id.hotelButton6:
                playSound(map.get("tv"));
                break;
            case R.id.hotelButton7:
                playSound(map.get("reserved_for_me"));
                break;
            case R.id.hotelButton8:
                playSound(map.get("heres_my_passport"));
                break;
            case R.id.hotelButton9:
                playSound(map.get("how_much_room"));
                break;
            case R.id.hotelButton10:
                playSound(map.get("give_me_key"));
                break;
            case R.id.hotelButton11:
                playSound(map.get("mirror"));
                break;
            case R.id.hotelButton12:
                playSound(map.get("bed"));
                break;
            case R.id.hotelButton13:
                playSound(map.get("table"));
                break;
            case R.id.hotelButton14:
                playSound(map.get("chair"));
                break;
            case R.id.hotelButton15:
                playSound(map.get("toilet_paper"));
                break;
            case R.id.hotelButton16:
                playSound(map.get("one_day"));
                break;
            case R.id.hotelButton17:
                playSound(map.get("one_week"));
                break;
            case R.id.hotelButton18:
                playSound(map.get("two_week"));
                break;
            case R.id.hotelButton19:
                playSound(map.get("show_me_room"));
                break;
            case R.id.hotelButton20:
                playSound(map.get("wheres_exchange_office"));
                break;
            case R.id.hotelButton21:
                playSound(map.get("who_here"));
                break;
            case R.id.hotelButton22:
                playSound(map.get("please_go"));
                break;
            case R.id.hotelButton23:
                playSound(map.get("leaving_today"));
                break;
            case R.id.hotelButton24:
                playSound(map.get("leaving_tomorrow"));
                break;
            case R.id.hotelButton25:
                playSound(map.get("call_taxi"));
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
        map.put("for_one_person", loadSound("hotel/for_one_person.ogg"));
        map.put("for_two_person", loadSound("hotel/for_two_person.ogg"));
        map.put("large", loadSound("hotel/large.ogg"));
        map.put("smaller", loadSound("hotel/smaller.ogg"));
        map.put("cheap", loadSound("hotel/cheap.ogg"));
        map.put("tv", loadSound("hotel/tv.ogg"));
        map.put("reserved_for_me", loadSound("hotel/reserved_for_me.ogg"));
        map.put("heres_my_passport", loadSound("hotel/heres_my_passport.ogg"));
        map.put("how_much_room", loadSound("hotel/how_much_room.ogg"));
        map.put("give_me_key", loadSound("hotel/give_me_key.ogg"));
        map.put("mirror", loadSound("hotel/mirror.ogg"));
        map.put("bed", loadSound("hotel/bed.ogg"));
        map.put("table", loadSound("hotel/table.ogg"));
        map.put("chair", loadSound("hotel/chair.ogg"));
        map.put("toilet_paper", loadSound("hotel/toilet_paper.ogg"));
        map.put("one_day", loadSound("hotel/one_day.ogg"));
        map.put("one_week", loadSound("hotel/one_week.ogg"));
        map.put("two_week", loadSound("hotel/two_week.ogg"));
        map.put("show_me_room", loadSound("hotel/show_me_room.ogg"));
        map.put("wheres_exchange_office", loadSound("hotel/wheres_exchange_office.ogg"));
        map.put("who_here", loadSound("hotel/who_here.ogg"));
        map.put("please_go", loadSound("hotel/please_go.ogg"));
        map.put("leaving_today", loadSound("hotel/leaving_today.ogg"));
        map.put("leaving_tomorrow", loadSound("hotel/leaving_tomorrow.ogg"));
        map.put("call_taxi", loadSound("hotel/call_taxi.ogg"));
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
