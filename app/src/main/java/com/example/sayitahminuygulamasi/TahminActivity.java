package com.example.sayitahminuygulamasi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class TahminActivity extends AppCompatActivity {
    private TextView kalanHak, arttirAzalt;
    private EditText editText;
    private Button buttonTahminEt;

    private int uretilenSayi;
    private int kalanHakSayisi = 7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tahmin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        kalanHak = findViewById(R.id.kalanHak);
        arttirAzalt = findViewById(R.id.arttirAzalt);
        buttonTahminEt = findViewById(R.id.buttonTahminEt);
        editText = findViewById(R.id.editText);

        Random r = new Random();
        uretilenSayi = r.nextInt(101);
        Log.e("URETILEN SAYI " , String.valueOf(uretilenSayi));
        kalanHak.setText("Kalan Tahmin Hakkı : " + kalanHakSayisi);


        buttonTahminEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(editText.getText().toString().isEmpty()){
                    Toast toast = Toast.makeText(getApplicationContext(),"Lütfen Bir Sayı Giriniz!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 100);
                    toast.show();
                    return; // islemi durdur ve ekranda kal
                }

                int tahminEdilenSayi = Integer.parseInt(editText.getText().toString());


                if (tahminEdilenSayi == uretilenSayi){
                    Intent intent = new Intent(TahminActivity.this, SonucActivity.class);
                    intent.putExtra("sonuc" , true); // tahminin dogru olmasi sonucunda veri transferi gerceklesir.
                    startActivity(intent);
                    finish(); // backStack ten silme islemi yapilmalidir. Yoksa sonuc ekranindayken geri tusuna basildiginda ana sayfaya degilde oyun ekranina geri atar
                    kalanHakSayisi -= 1;
                    return; // tahmin dogru ise buton calismasi durur ve sonuc sayfasina gecilir.
                }
                if (tahminEdilenSayi > uretilenSayi){
                    arttirAzalt.setText("Tahmini Azalt");
                    kalanHakSayisi -= 1;
                    kalanHak.setText("Kalan Tahmin Hakkı : " + kalanHakSayisi);
                }
                if (tahminEdilenSayi < uretilenSayi){
                    arttirAzalt.setText("Tahmini Arttir");
                    kalanHakSayisi -= 1;
                    kalanHak.setText("Kalan Tahmin Hakkı : " + kalanHakSayisi);
                }
                if (kalanHakSayisi == 0) {
                    Intent intent = new Intent(TahminActivity.this, SonucActivity.class);
                    intent.putExtra("sonuc" , false);
                    intent.putExtra("dogruSayi", uretilenSayi);
                    startActivity(intent);
                    kalanHakSayisi -= 1;
                    finish();
                }

                editText.setText(""); // tahminler bittikten sonra edittext sifirlansin


            }
        });
    }
}