package mytechcorp.ir.assisstant;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.concurrent.TimeUnit;

public class ReadingActivity extends Activity {

    TextViewPlus mTextField, tvReading, tvHeader;
    Button btnEnter;
    ImageButton btnHelp;
    private DBHandler dbHandler;

    public static Activity ca;
    String Game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/IRANSansMobile_Light.ttf");
        ca = this;
        dbHandler = new DBHandler(this);
        btnEnter = findViewById(R.id.btnEnter);
        btnHelp = findViewById(R.id.btnHelp);
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

    public void setBtnHelpOnClickListener(View v){
        HelpActivity cdd = new HelpActivity(this, 3);
        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cdd.show();
    }


    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom));
        builder.setTitle("خروج");
        builder.setMessage("در صورت خروج زمان مطالعه از ابتدا محاسبه خواهد شد.آیا مطمئنید ؟");
        builder.setPositiveButton("بله", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(ca, MainActivity.class);
                startActivity(intent);
                MainActivity.fa.finish();
                finish();

            }
        }).setNegativeButton("خیر",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setIcon(R.mipmap.ic_close_web);
        builder.create().show();
    }

    void startReading(){
        if (!dbHandler.GetScoreState(10)) {
            new CountDownTimer(300000,1000) {

                @SuppressLint({"DefaultLocale","SetTextI18n"})
                public void onTick(long millisUntilFinished) {

                    mTextField.setText("زمان باقی مانده : " + String.format("%d دقیقه و  %d ثانیه",
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                }

                public void onFinish() {
                    mTextField.setText("زمان باقی مانده : 0 دقیقه و  0 ثانیه");
                    btnEnter.setEnabled(true);
                    dbHandler.UpdateState(10);
                }
            }.start();
        }else {

            mTextField.setText("زمان باقی مانده : 0 دقیقه و  0 ثانیه");
            btnEnter.setEnabled(true);
        }
    }

    public void setBtnEnterOnClickListener(View v){
        Intent intent = new Intent(this, ReadingQuestionActivity.class);
        startActivity(intent);
        this.finish();
    }
}
