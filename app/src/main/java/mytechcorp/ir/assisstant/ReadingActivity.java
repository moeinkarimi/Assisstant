package mytechcorp.ir.assisstant;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ReadingActivity extends AppCompatActivity {

    TextViewPlus mTextField;
    Button btnEnter;

    String Game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/IRANSansMobile_Light.ttf");
        btnEnter = findViewById(R.id.btnEnter);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
        {
            Game = bundle.getString("Game");
            Log.d("Game Value ", Game);
        }
        btnEnter.setTypeface(tf);
        mTextField = findViewById(R.id.mTextField);
        startReading();
    }

    void startReading(){
        new CountDownTimer(35000, 1000) {

            public void onTick(long millisUntilFinished) {
                mTextField.setText("زمان باقی مانده : " + (millisUntilFinished / 1000) + " ثانیه");
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
