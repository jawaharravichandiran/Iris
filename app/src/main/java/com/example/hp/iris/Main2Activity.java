package com.example.hp.iris;

import android.content.Intent;
import android.os.Handler;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.telephony.SmsManager;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.HashMap;

import static com.example.hp.iris.MainActivity.c;

public class Main2Activity extends AppCompatActivity implements TextToSpeech.OnInitListener,TextToSpeech.OnUtteranceCompletedListener{
    CardView barcode,textscanner,facedetect,call,vision,message;
    Button vibe;
    TextToSpeech tvvs;
    Boolean a = false;
    Boolean ts = true,qr=true,ms=true,vi=true,cl=true,fd=true;
    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main2);
        textscanner=(CardView)findViewById(R.id.textscanner);
        barcode=(CardView)findViewById(R.id.qr);
        facedetect=(CardView)findViewById(R.id.face);
        call=(CardView)findViewById(R.id.call);
        vision=(CardView)findViewById(R.id.vision);
        message=(CardView)findViewById(R.id.msg);
        vibe=(Button)findViewById(R.id.vibrate) ;

        vibrator=(Vibrator)getSystemService(VIBRATOR_SERVICE);
        tvvs=new TextToSpeech(Main2Activity.this,Main2Activity.this);

        vibe.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                vibrator.vibrate(150);
                a =true;
                ts=qr=ms=cl=vi=fd=true;
                return false;
            }
        });

        textscanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                    if((a)&&(ts))
                    {
                        vi=qr=ms=cl=fd=false;
                        if (!tvvs.isSpeaking()) {
                            HashMap<String, String> params = new HashMap<String, String>();
                            params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "sampleText");
                            tvvs.speak("you have selected textscanner scanning Option, to enter long press in the same place", TextToSpeech.QUEUE_ADD, params);
                        } else {
                            tvvs.stop();
                        }
                }
                else
                {
                    if (!tvvs.isSpeaking()) {
                        HashMap<String, String> params = new HashMap<String, String>();
                        params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "sampleText");
                        tvvs.speak("Text Scanner", TextToSpeech.QUEUE_ADD, params);
                    } else {
                        tvvs.stop();
                    }
                }
            }
        });
        textscanner.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(a)
                {
                    a = false;
                    if (!tvvs.isSpeaking()) {
                        HashMap<String, String> params = new HashMap<String, String>();
                        params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "sampleText");
                        tvvs.speak("Entered Camera", TextToSpeech.QUEUE_ADD, params);
                    } else {
                        tvvs.stop();
                    }
                    startActivity(new Intent(Main2Activity.this, textscanner.class));
                }
                return false;
            }
        });

        facedetect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if((a)&&(fd))
                {
                    ts=ms=cl=vi=qr=false;
                    if (!tvvs.isSpeaking()) {
                        HashMap<String, String> params = new HashMap<String, String>();
                        params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "sampleText");
                        tvvs.speak("you have selected FaceDetection Option, to enter long press in the same place", TextToSpeech.QUEUE_ADD, params);
                    } else {
                        tvvs.stop();
                    }
                }
                else
                {
                    if (!tvvs.isSpeaking()) {
                        HashMap<String, String> params = new HashMap<String, String>();
                        params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "sampleText");
                        tvvs.speak("Face Detection", TextToSpeech.QUEUE_ADD, params);
                    } else {
                        tvvs.stop();
                    }
                }
            }
        });
        facedetect.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(a) {
                    a = false;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (!tvvs.isSpeaking()) {
                                HashMap<String, String> params = new HashMap<String, String>();
                                params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "sampleText");
                                tvvs.speak("Opening Facedetection Camera click to take picute and long click to enable face detection", TextToSpeech.QUEUE_ADD, params);
                            } else {
                                tvvs.stop();
                            }
                            startActivity(new Intent(Main2Activity.this, camera1.class));
                        }
                    });
                }
                return false;
            }
        });

        vision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if((a)&&(vi))
                {
                    ts=qr=ms=cl=fd=false;
                    if(!tvvs.isSpeaking()){
                        HashMap<String,String> params=new HashMap<String, String>();
                        params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID,"sampleText");
                        tvvs.speak("you have selected Vision Option, to enter long press in the same place",TextToSpeech.QUEUE_ADD,params);
                    }
                    else{
                        tvvs.stop();
                    }
                }
                else
                {
                    if(!tvvs.isSpeaking()){
                        HashMap<String,String> params=new HashMap<String, String>();
                        params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID,"sampleText");
                        tvvs.speak("Vision",TextToSpeech.QUEUE_ADD,params);
                    }
                    else{
                        tvvs.stop();
                    }
                }
            }
        });
        vision.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(a)
                {
                    a = false;
                    if (!tvvs.isSpeaking()) {
                        HashMap<String, String> params = new HashMap<String, String>();
                        params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "sampleText");
                        tvvs.speak("Opening vision Camera", TextToSpeech.QUEUE_ADD, params);
                    } else {
                        tvvs.stop();
                    }
                    startActivity(new Intent(Main2Activity.this, cloudcamera.class));
                }
                return false;
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if((a)&&(cl))
                {
                    vi=qr=ms=ts=fd=false;
                    if(!tvvs.isSpeaking()){
                        HashMap<String,String> params=new HashMap<String, String>();
                        params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID,"sampleText");
                        tvvs.speak("you have selected Call Option, to enter long press in the same place",TextToSpeech.QUEUE_ADD,params);
                    }
                    else{
                        tvvs.stop();
                    }
                }
                else
                {
                    if(!tvvs.isSpeaking()){
                        HashMap<String,String> params=new HashMap<String, String>();
                        params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID,"sampleText");
                        tvvs.speak("Caller",TextToSpeech.QUEUE_ADD,params);
                    }
                    else{
                        tvvs.stop();
                    }
                }
            }
        });
        call.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(a) {
                    a = false;
//                    if (!tvvs.isSpeaking()) {
//                        HashMap<String, String> params = new HashMap<String, String>();
//                        params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "sampleText");
//                        tvvs.speak("Click on the contact to hear the name .", TextToSpeech.QUEUE_ADD, params);
//                        tvvs.speak("Long press on the contact to call the name ", TextToSpeech.QUEUE_ADD, params);
//                    } else {
//                        tvvs.stop();
//                    }
                    Intent intent = new Intent(Main2Activity.this, BeforeMessage.class);
                    intent.putExtra("Activity","Call");
                    startActivity(intent);
                    finish();
                }
                return false;
            }
        });

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if((a)&&(ms))
                {
                    vi=qr=ts=cl=fd=false;
                    if(!tvvs.isSpeaking()){
                        HashMap<String,String> params=new HashMap<String, String>();
                        params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID,"sampleText");
                        tvvs.speak("you have selected Message Option, to enter long press in the same place",TextToSpeech.QUEUE_ADD,params);
                    }
                    else{
                        tvvs.stop();
                    }
                }
                else
                {
                    if(!tvvs.isSpeaking()){
                        HashMap<String,String> params=new HashMap<String, String>();
                        params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID,"sampleText");
                        tvvs.speak("Message",TextToSpeech.QUEUE_ADD,params);
                    }
                    else{
                        tvvs.stop();
                    }

                }
            }
        });
        message.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(a) {
                    a = false;
//                    if (!tvvs.isSpeaking()) {
//                        HashMap<String, String> params = new HashMap<String, String>();
//                        params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "sampleText");
//                        tvvs.speak("Click on the contact to hear the name .", TextToSpeech.QUEUE_ADD, params);
//                        tvvs.speak("Long press on the contact to Message the name ", TextToSpeech.QUEUE_ADD, params);
//                    } else {
//                        tvvs.stop();
//                    }
                    Intent intent = new Intent(Main2Activity.this, BeforeMessage.class);
                    intent.putExtra("Activity","Message");
                    startActivity(intent);
                    finish();
                }
                return false;
            }
        });

        barcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if((a)&&(qr))
                {
                    vi=ts=ms=cl=fd=false;
                    if(!tvvs.isSpeaking()){
                        HashMap<String,String> params=new HashMap<String, String>();
                        params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID,"sampleText");
                        tvvs.speak("You have selected barcode option please long press to open",TextToSpeech.QUEUE_ADD,params);
                    }
                    else{
                        tvvs.stop();
                    }
                }
                else
                {
                    if(!tvvs.isSpeaking()){
                        HashMap<String,String> params=new HashMap<String, String>();
                        params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID,"sampleText");
                        tvvs.speak("QR Scanner",TextToSpeech.QUEUE_ADD,params);
                    }
                    else{
                        tvvs.stop();
                    }
                }
            }
        });
        barcode.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(a) {
                    a = false;
                    if (!tvvs.isSpeaking()) {
                        HashMap<String, String> params = new HashMap<String, String>();
                        params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "sampleText");
                        tvvs.speak("Entered Camera", TextToSpeech.QUEUE_ADD, params);
                    } else {
                        tvvs.stop();
                    }
                    startActivity(new Intent(Main2Activity.this, com.example.hp.iris.barcode.class));
                }
                return false;
            }
        });
    }
    @Override
    protected void onDestroy() {
        if(tvvs!=null)
        {
            tvvs.stop();
            tvvs.shutdown();

            tvvs=null;
        }


        super.onDestroy();
    }

    @Override
    public void onInit(int status) {

    }

    @Override
    public void onUtteranceCompleted(String utteranceId) {

    }
    public  boolean onKeyDown(int keyCode,KeyEvent event)
    {
        if(keyCode==KeyEvent.KEYCODE_VOLUME_UP){
            event.startTracking();



            return true;
        }
        return super.onKeyDown(keyCode,event);

    }
    public boolean onKeyLongPress(int keycode,KeyEvent event){
        if(keycode==KeyEvent.KEYCODE_VOLUME_UP){
            startActivity(new Intent(Main2Activity.this,MyLocationGetter.class));
            return true;
        }
        return onKeyLongPress(keycode, event);
    }

}
