package id.jody.verie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            //switching intent
            SharedPreferences sPrefs = getSharedPreferences("id.jody.verie", Context.MODE_PRIVATE);
            String calorie = sPrefs.getString("user_calorie", null);
            if (calorie != null){
                Intent intent = new Intent(SplashActivity.this, StatusActivity.class);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
            }
        //delay timeout
        }, 3000);
    }
}