package com.duckbox.pequerisegura;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class SplashActivity extends AppCompatActivity {
    ProgressBar pb_;
    ConnectionDetector CD;
    Animation animFadein;
    ImageView img1 , img2 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        Handler handler2 = new Handler();
        pb_ = (ProgressBar) findViewById(R.id.pb);
       /* animFadein = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.anim_um);

        // set animation listener
        img1.startAnimation(animFadein);
        img2.startAnimation(animFadein);
*/
        CD = new ConnectionDetector();
        if (CD.isConected(this)) {
            handler.postDelayed(new Runnable() {
                public void run() {
                    Intent ints = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(ints);

                }
            }, 2000);
        } else {
            handler.postDelayed(new Runnable() {
                public void run() {
                  //  Intent ints2 = new Intent(SplashActivity.this, Error_n.class);
                  //  startActivity(ints2);
                }
            }, 2000);
        }



        handler2.postDelayed(new Runnable() {
            public void run() {
                pb_.setVisibility(true ? View.VISIBLE : View.GONE);}
        }, 1000);



    }
}
