package id.jody.verie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Objects;
import java.util.stream.Stream;

import id.jody.verie.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    double usia, berat_badan, tinggi_badan, jlOlahraga, hasilKalori, roundKalori;
    String jenis_kelamin, freqOlahraga, stKalori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //array gender
        String[] genders = new String[] {"Laki-Laki", "Perempuan"};
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(
                this,
                R.layout.drop_down_gender,
                genders
        );

        //array frekuensi olahraga
        String[] frekuensiOlahraga = new String[] {"Tidak Berolahraga", "Jarang Berolahraga", "Sering Berolahraga"};
        ArrayAdapter<String> olahragaAdapter = new ArrayAdapter<>(
                this,
                R.layout.drop_down_olahraga,
                frekuensiOlahraga
        );

        //memasukkan array ke list dropdown
        binding.genderFillExposed.setAdapter(genderAdapter);
        binding.olahragaFillExposed.setAdapter(olahragaAdapter);

        binding.btnHitungKalori.setOnClickListener(v -> {
            jenis_kelamin = binding.genderFillExposed.getText().toString();
            freqOlahraga = binding.olahragaFillExposed.getText().toString();

            if(freqOlahraga.equals("Tidak Berolahraga")){
                jlOlahraga = 1.2;
                binding.tILOlahraga.setError(null);
            }
            else if(freqOlahraga.equals("Jarang Berolahraga")){
                jlOlahraga = 1.3;
                binding.tILOlahraga.setError(null);
            }
            else if(freqOlahraga.equals("Sering Berolahraga")){
                jlOlahraga = 1.4;
                binding.tILOlahraga.setError(null);
            } else {
                binding.tILOlahraga.setError("Pilih frekuensi berolahraga");
            }

            if (jenis_kelamin.equals("Laki-Laki")){
                binding.tILGender.setError(null);
                if(TextUtils.isEmpty(binding.tIEUsia.getText().toString())){
                    binding.tILUsia.setError("Harap masukkan usia");
                } else {
                    binding.tILUsia.setError(null);
                    usia = Double.parseDouble(binding.tIEUsia.getText().toString());
                }
                if(TextUtils.isEmpty(binding.tIEBB.getText().toString())){
                    binding.tILBB.setError("Harap masukkan berat badann");
                } else {
                    binding.tILBB.setError(null);
                    berat_badan = Double.parseDouble(binding.tIEBB.getText().toString());
                }
                if(TextUtils.isEmpty(binding.tIETB.getText().toString())){
                    binding.tILTB.setError("Harap masukkan tinggi badan");
                } else {
                    binding.tILTB.setError(null);
                    tinggi_badan = Double.parseDouble(binding.tIETB.getText().toString());
                }
                if(Stream.of(usia, berat_badan, tinggi_badan, jlOlahraga).allMatch(Objects::nonNull)){
                    hasilKalori = 66.5 + (13.7 * berat_badan) + (5 * tinggi_badan) - (6.8 * usia);
                    hasilKalori = hasilKalori * jlOlahraga;
                    roundKalori = Math.round(hasilKalori * 100.0) / 100.0;
                    stKalori = String.valueOf(roundKalori);
                    binding.tvKalori.setText(stKalori);
                    SharedPreferences sPrefs = getApplicationContext().getSharedPreferences("id.jody.verie", Context.MODE_PRIVATE);
                    sPrefs.edit().putString("user_calorie", stKalori).apply();
                    Intent intent = new Intent(MainActivity.this, StatusActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    ;
                }
            }
            else if (jenis_kelamin.equals("Perempuan")){
                binding.tILGender.setError(null);
                if(TextUtils.isEmpty(binding.tIEUsia.getText().toString())){
                    binding.tILUsia.setError("Harap masukkan usia");
                } else {
                    binding.tILUsia.setError(null);
                    usia = Double.parseDouble(binding.tIEUsia.getText().toString());
                }
                if(TextUtils.isEmpty(binding.tIEBB.getText().toString())){
                    binding.tILBB.setError("Harap masukkan berat badann");
                } else {
                    binding.tILBB.setError(null);
                    berat_badan = Double.parseDouble(binding.tIEBB.getText().toString());
                }
                if(TextUtils.isEmpty(binding.tIETB.getText().toString())){
                    binding.tILTB.setError("Harap masukkan tinggi badan");
                } else {
                    binding.tILTB.setError(null);
                    tinggi_badan = Double.parseDouble(binding.tIETB.getText().toString());
                }
                if(Stream.of(usia, berat_badan, tinggi_badan, jlOlahraga).allMatch(Objects::nonNull)){
                    hasilKalori = 655 + (9.6 * berat_badan) + (1.8 * tinggi_badan) - (4.7 * usia);
                    hasilKalori = hasilKalori * jlOlahraga;
                    roundKalori = Math.round(hasilKalori * 100.0) / 100.0;
                    stKalori = String.valueOf(roundKalori);
                    binding.tvKalori.setText(stKalori);
                    SharedPreferences sPrefs = getApplicationContext().getSharedPreferences("id.jody.verie", Context.MODE_PRIVATE);
                    sPrefs.edit().putString("user_calorie", stKalori).apply();
                    Intent intent = new Intent(MainActivity.this, StatusActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    ;
                }
            } else {
                binding.tILGender.setError("Pilih Gender");
            }
        });
    }
}