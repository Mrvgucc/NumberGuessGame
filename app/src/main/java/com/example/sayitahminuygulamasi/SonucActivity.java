package com.example.sayitahminuygulamasi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SonucActivity extends AppCompatActivity {
    private TextView textViewSonuc,textViewDogruSayi;
    private ImageView imageViewSonucGorseli;
    private Button buttonTekrarOyna;

    private Boolean sonuc; // veri transferi icin tutuldu
    private int dogruSayi; // veri transferi icin tutuldu

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sonuc);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textViewSonuc = findViewById(R.id.textViewSonuc);
        imageViewSonucGorseli = findViewById(R.id.imageViewSonucGorseli);
        buttonTekrarOyna = findViewById(R.id.buttonTekrarOyna);
        textViewDogruSayi = findViewById(R.id.textViewDogruSayi);

        sonuc = getIntent().getBooleanExtra("sonuc", false);
        dogruSayi = getIntent().getIntExtra("dogruSayi" ,0); // dogruSayi TahminActivity'den cekildi

        if (sonuc){
            imageViewSonucGorseli.setImageResource(R.drawable.baseline_sentiment_satisfied_alt_24);
            textViewSonuc.setText("KAZANDINIZ !");
        }
        else {
            imageViewSonucGorseli.setImageResource(R.drawable.baseline_sentiment_very_dissatisfied_24);
            textViewSonuc.setText("KAYBETTİNİZ !");
            textViewDogruSayi.setText("Doğru Sayı: " + dogruSayi);
        }

        buttonTekrarOyna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SonucActivity.this, TahminActivity.class);
                startActivity(intent);
                finish(); // burayi da backStack ten silerek tekrar olarak kendini acmasi engellenir
            }
        });
    }
}