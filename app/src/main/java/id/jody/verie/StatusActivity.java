package id.jody.verie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import id.jody.verie.databinding.ActivityMainBinding;
import id.jody.verie.databinding.ActivityStatusBinding;

public class StatusActivity extends AppCompatActivity {

    ActivityStatusBinding binding;
    String calorie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityStatusBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        SharedPreferences sPrefs = getSharedPreferences("id.jody.verie", Context.MODE_PRIVATE);
        calorie = sPrefs.getString("user_calorie", "");
        binding.tvCalorie.setText(calorie);

    }
}