package com.jaydevelopment.popkovanton.vietnamesephrasebook;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.IOException;


public class DateandtimeActivity extends AppCompatActivity {

    final int MAX_STREAMS = 5;


    private SoundPool sp;
    private int soundWhatsTimeIsIt, soundDay, soundWeek, soundMonday,
            soundTuesday, soundWednesday, soundThursday, soundFriday,
            soundSaturday, soundSunday, soundSpring, soundSummer, soundAutumn, soundWinter;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dateandtime);

        sp = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0); //создаем класс SoundPool

        try {                                                                                        //загрузка из assets
            soundWhatsTimeIsIt = sp.load(getAssets().openFd("dateandtime/whats_time_is_it.ogg"), 1);
            soundDay = sp.load(getAssets().openFd("dateandtime/day.ogg"), 1);
            soundWeek = sp.load(getAssets().openFd("dateandtime/week.ogg"), 1);
            soundMonday = sp.load(getAssets().openFd("dateandtime/monday.ogg"), 1);
            soundTuesday = sp.load(getAssets().openFd("dateandtime/tuesday.ogg"), 1);
            soundWednesday = sp.load(getAssets().openFd("dateandtime/wednesday.ogg"), 1);
            soundThursday = sp.load(getAssets().openFd("dateandtime/thursday.ogg"), 1);
            soundFriday = sp.load(getAssets().openFd("dateandtime/friday.ogg"), 1);
            soundSaturday = sp.load(getAssets().openFd("dateandtime/saturday.ogg"), 1);
            soundSunday = sp.load(getAssets().openFd("dateandtime/sunday.ogg"), 1);
            soundSpring = sp.load(getAssets().openFd("dateandtime/spring.ogg"), 1);
            soundSummer = sp.load(getAssets().openFd("dateandtime/summer.ogg"), 1);
            soundAutumn = sp.load(getAssets().openFd("dateandtime/autumn.ogg"), 1);
            soundWinter = sp.load(getAssets().openFd("dateandtime/winter.ogg"), 1);
        } catch (IOException e) {
            e.printStackTrace();
        }


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
                //startActivity(new Intent(this, MainActivity.class));///
                finish();
                //return true;
            default:///////////////////////////////////////////////////
                return super.onOptionsItemSelected(item);//////////////
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageButton:
                sp.play(soundWhatsTimeIsIt, 1, 1, 0, 0, 1);
                break;
            case R.id.imageButton2:
                sp.play(soundDay, 1, 1, 0, 0, 1);
                break;
            case R.id.imageButton3:
                sp.play(soundWeek, 1, 1, 0, 0, 1);
                break;
            case R.id.imageButton4:
                sp.play(soundMonday, 1, 1, 0, 0, 1);
                break;
            case R.id.imageButton5:
                sp.play(soundTuesday, 1, 1, 0, 0, 1);
                break;
            case R.id.imageButton6:
                sp.play(soundWednesday, 1, 1, 0, 0, 1);
                break;
            case R.id.imageButton7:
                sp.play(soundThursday, 1, 1, 0, 0, 1);
                break;
            case R.id.imageButton8:
                sp.play(soundFriday, 1, 1, 0, 0, 1);
                break;
            case R.id.imageButton9:
                sp.play(soundSaturday, 1, 1, 0, 0, 1);
                break;
            case R.id.imageButton10:
                sp.play(soundSunday, 1, 1, 0, 0, 1);
                break;
            case R.id.imageButton11:
                sp.play(soundSpring, 1, 1, 0, 0, 1);
                break;
            case R.id.imageButton12:
                sp.play(soundSummer, 1, 1, 0, 0, 1);
                break;
            case R.id.imageButton13:
                sp.play(soundAutumn, 1, 1, 0, 0, 1);
                break;
            case R.id.imageButton14:
                sp.play(soundWinter, 1, 1, 0, 0, 1);
                break;
        }
    }

    @Override
    protected void onResume() {
        mAdView.resume();

        super.onResume();
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
