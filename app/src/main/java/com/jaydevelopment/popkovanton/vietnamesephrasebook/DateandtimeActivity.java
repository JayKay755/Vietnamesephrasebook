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


public class DateandtimeActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton button;

    final int MAX_STREAMS = 5;


    private SoundPool sp;
    private int soundWhatsTimeIsIt;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dateandtime);

        sp = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0); //создаем класс SoundPool

        try {                                                                     //
            soundWhatsTimeIsIt = sp.load(getAssets().openFd("dateandtime/whats_time_is_it.wav"), 1);   //
        } catch (IOException e) {                                                 //
            e.printStackTrace();                                                  //  загрузка из assets
        }                                                                         //

        button = (ImageButton) findViewById(R.id.imageButton);
        button.setOnClickListener(this);

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
                startActivity(new Intent(this, MainActivity.class));///
                return true;///////////////////////////////////////////
            default:///////////////////////////////////////////////////
                return super.onOptionsItemSelected(item);//////////////
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageButton:
                sp.play(soundWhatsTimeIsIt, 1, 1, 0, 0, 1);
                break;
        }
    }

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
