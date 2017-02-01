package com.jaydevelopment.popkovanton.vietnamesephrasebook;

import android.annotation.TargetApi;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.content.res.AssetManager;
import android.content.res.AssetFileDescriptor;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StandartphraseActivity extends AppCompatActivity {

    private SoundPool mSoundPool;
    private AssetManager mAssetManager;

    private int mStreamID;

    private Map<String, Integer> map = new HashMap<String, Integer>();

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.standartphrase);


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
            case R.id.standartphraseButton:
                playSound(map.get("yes"));
                break;
            case R.id.standartphraseButton2:
                playSound(map.get("no"));
                break;
            case R.id.standartphraseButton3:
                playSound(map.get("thanks"));
                break;
            case R.id.standartphraseButton4:
                playSound(map.get("welcome"));
                break;
            case R.id.standartphraseButton5:
                playSound(map.get("sorry"));
                break;
            case R.id.standartphraseButton6:
                playSound(map.get("are_you_speak_russian"));
                break;
            case R.id.standartphraseButton7:
                playSound(map.get("are_you_speak_english"));
                break;
            case R.id.standartphraseButton8:
                playSound(map.get("are_you_speak_french"));
                break;
            case R.id.standartphraseButton9:
                playSound(map.get("are_you_speak_germany"));
                break;
            case R.id.standartphraseButton10:
                playSound(map.get("i"));
                break;
            case R.id.standartphraseButton11:
                playSound(map.get("we"));
                break;
            case R.id.standartphraseButton12:
                playSound(map.get("you"));
                break;
            case R.id.standartphraseButton13:
                playSound(map.get("they"));
                break;
            case R.id.standartphraseButton14:
                playSound(map.get("good"));
                break;
            case R.id.standartphraseButton15:
                playSound(map.get("so_so"));
                break;
            case R.id.standartphraseButton16:
                playSound(map.get("wife"));
                break;
            case R.id.standartphraseButton17:
                playSound(map.get("husband"));
                break;
            case R.id.standartphraseButton18:
                playSound(map.get("daughter"));
                break;
            case R.id.standartphraseButton19:
                playSound(map.get("son"));
                break;
            case R.id.standartphraseButton20:
                playSound(map.get("mother"));
                break;
            case R.id.standartphraseButton21:
                playSound(map.get("father"));
                break;
            case R.id.standartphraseButton22:
                playSound(map.get("friend"));
                break;
            case R.id.standartphraseButton23:
                playSound(map.get("whats_your_name"));
                break;
            case R.id.standartphraseButton24:
                playSound(map.get("my_name_is"));
                break;
            case R.id.standartphraseButton25:
                playSound(map.get("russia"));
                break;
            case R.id.standartphraseButton26:
                playSound(map.get("dont_understand"));
                break;
            case R.id.standartphraseButton27:
                playSound(map.get("understand"));
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
        map.put("yes", loadSound("standartphrase/yes.ogg"));
        map.put("no", loadSound("standartphrase/no.ogg"));
        map.put("thanks", loadSound("standartphrase/thanks.ogg"));
        map.put("welcome", loadSound("standartphrase/welcome.ogg"));
        map.put("sorry", loadSound("standartphrase/sorry.ogg"));
        map.put("are_you_speak_russian", loadSound("standartphrase/are_you_speak_russian.ogg"));
        map.put("are_you_speak_english", loadSound("standartphrase/are_you_speak_english.ogg"));
        map.put("are_you_speak_french", loadSound("standartphrase/are_you_speak_french.ogg"));
        map.put("are_you_speak_germany", loadSound("standartphrase/are_you_speak_germany.ogg"));
        map.put("i", loadSound("standartphrase/i.ogg"));
        map.put("we", loadSound("standartphrase/we.ogg"));
        map.put("you", loadSound("standartphrase/you.ogg"));
        map.put("they", loadSound("standartphrase/they.ogg"));
        map.put("good", loadSound("standartphrase/good.ogg"));
        map.put("so_so", loadSound("standartphrase/so_so.ogg"));
        map.put("wife", loadSound("standartphrase/wife.ogg"));
        map.put("husband", loadSound("standartphrase/husband.ogg"));
        map.put("daughter", loadSound("standartphrase/daughter.ogg"));
        map.put("son", loadSound("standartphrase/son.ogg"));
        map.put("mother", loadSound("standartphrase/mother.ogg"));
        map.put("father", loadSound("standartphrase/father.ogg"));
        map.put("friend", loadSound("standartphrase/friend.ogg"));
        map.put("whats_your_name", loadSound("standartphrase/whats_your_name.ogg"));
        map.put("my_name_is", loadSound("standartphrase/my_name_is.ogg"));
        map.put("russia", loadSound("standartphrase/russia.ogg"));
        map.put("dont_understand", loadSound("standartphrase/dont_understand.ogg"));
        map.put("understand", loadSound("standartphrase/understand.ogg"));

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
