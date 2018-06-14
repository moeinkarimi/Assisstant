package mytechcorp.ir.assisstant;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.TimeUnit;

public class ReadingActivity extends AppCompatActivity {

    TextViewPlus mTextField, tvReading, tvHeader;
    Button btnEnter;

    String Game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/IRANSansMobile_Light.ttf");

        btnEnter = findViewById(R.id.btnEnter);
        mTextField = findViewById(R.id.mTextField);
        tvReading = findViewById(R.id.tvReading);
        tvHeader = findViewById(R.id.tvHeader);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
        {
            Game = bundle.getString("Game");
        }

        tvHeader.setText(R.string.reading);
        tvReading.setText(R.string.readingDesc);

        btnEnter.setTypeface(tf);
        startReading();
    }

    void startReading(){
        new CountDownTimer(420000, 1000) {

            @SuppressLint({"DefaultLocale","SetTextI18n"})
            public void onTick(long millisUntilFinished) {

                mTextField.setText("زمان باقی مانده : " + String.format("%d دقیقه و  %d ثانیه",
                        TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            public void onFinish() {
                btnEnter.setEnabled(true);

            }
        }.start();
    }

    public void setBtnEnterOnClickListener(View v){
        Intent intent = new Intent(this, CodeActivity.class);
        intent.putExtra("Game",Game);
        startActivity(intent);
        this.finish();
    }
}
