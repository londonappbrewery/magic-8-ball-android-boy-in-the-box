package com.londonappbrewery.magiceightball;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    // The following are used for the shake detection
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView answergiver=findViewById(R.id.answergiver);
        final TextView sarcasm=findViewById(R.id.oneliner);
        final String[] oneliner = {"A conscience is what hurts when all your other parts feel so good.",
                "I’d kill for a Nobel Peace Prize.",
                "Just because I don’t care doesn’t mean I don’t understand.Oops",
        "A conclusion is the part where you got tired of thinking.",
                "Depression is merely anger without enthusiasm.",
                "Two wrongs don’t make a right, take your parents as an example.",
                "If I could kill myself I’d climb your ego and jump to your IQ.",
                "Accept that some days you are the pigeon, and some days you are the statue.",
                "Always remember that you are absolutely unique... Just like everyone else...",
                "As the joker said, if you are good at something why do it for free.I do anyway.",
        "Avoid arguments about the toilet seat...use the sink...",
                "don’t regret doing things, regret getting caught,Capiche",
        "I like you. You remind me of when I was young and stupid.",
        "don’t think of me as your boss, think of me as your friend who can fire you.",
         "Congratulations, If you press the elevator button three times it goes into hurry mode – really",
        "I created a bug in your software...it’s called #Monday",
                "I wasn’t lying, I was just writing fiction with my mouth",
        "I wonder where my subject is, his lunch is getting all cold",
        "I’m not your type. I’m not inflatable.",
                "If a stranger offers you a piece of candy.take two.Its godsend",
        "I didn’t say it was your fault, I said I was blaming you.",
                "I can totally keep secrets. It’s the people I tell them to that can’t.",
                "If I promise to miss you, will you go away?",
                "If something goes wrong at the office, blame the guy who can’t speak English...",
        "If you can smile when things go wrong, you have someone in mind to blame."};












        final int[] diceArray={R.drawable.ball1,
                R.drawable.ball2,
                R.drawable.ball3,
                R.drawable.ball4,
                R.drawable.ball5};



        // ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake(int count) {

                Log.d("Shake","Shake");

                Random randomnumberGenerator = new Random();
                int randnum= randomnumberGenerator.nextInt(5);
                answergiver.setImageResource(diceArray[randnum]);

                int onecount=randomnumberGenerator.nextInt(oneliner.length);
                sarcasm.setText(oneliner[onecount]);

            }
        });





    }
    @Override
    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }

}
