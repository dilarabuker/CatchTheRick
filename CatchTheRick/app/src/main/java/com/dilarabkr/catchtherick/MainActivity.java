package com.dilarabkr.catchtherick;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView scoreText;
    TextView timeText;

    ImageView imageView;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView3;
    ImageView [] imageArray;

    Handler handler; //runnable'ı kullanabilmemiz için gerekli olan sınıf.
    Runnable runnable; //Bir işlemin birden fazla kez belli periyotlarla çalışmasını istersek Runnable Kullanabiliriz

    int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize: başlatmak(yukarıda tanımladığımız textViewları buurda başlattık
        timeText= findViewById(R.id.timeText);
        scoreText= findViewById(R.id.skorText);
        imageView = findViewById(R.id.imageView);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);

        imageArray = new  ImageView[] {imageView,imageView1,imageView2,imageView3, imageView4,imageView5,imageView6,imageView7,imageView8};
        //bunu bi loopa alıp imageViewları görünmez yapacağız

        görseliSakla(); //program açılır açılmaz saklasın..

        score=0;

        new CountDownTimer(10000, 1000)
    //birinci parametre: kaçtan geriye sayacağını, ikinci parametre: kaç aralıkla sayacağını belirtir (milisaniye)
        {
            @Override
            public void onTick(long l) //her bir saniyede ne yapılacak
            {
                timeText.setText("Time: "+l/1000);
            }

            @Override
            public void onFinish() // süre bittiğinde ne olacak
            {
                timeText.setText("Time Off");
                handler.removeCallbacks(runnable);
                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);}

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);

                alert.setTitle("Restart?");
                alert.setMessage("Are you sure to restart game?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //restart

                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Game Over!", Toast.LENGTH_SHORT).show();
                    }
                });

                alert.show();

            }

        }.start();//CountDownTimerı başlat..

    }

    public void scoreArttir(View view) {
    //bu onClick methodunu bütün imageVewlara yazdık ve bu sayede hangi imageViewa tıklanırsa tıklansın aynı method çağırılacak
        score++;//her tıklamada score değişkenini 1 artır.
        scoreText.setText("Skor: "+ score);

    }

    public void görseliSakla() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView image: imageArray) {
                    image.setVisibility(View.INVISIBLE);
                    //image'ları sakladık..
                }
                    Random random = new Random();
                    int i = random.nextInt(9);
                    imageArray[i].setVisibility(View.VISIBLE);
                    //0'dan 9'a rastgele sayı atadık ve bu sayıları imgae dizimize atadık

                    handler.postDelayed(runnable, 1000);

            }
        };
            handler.post(runnable);
    }
}
