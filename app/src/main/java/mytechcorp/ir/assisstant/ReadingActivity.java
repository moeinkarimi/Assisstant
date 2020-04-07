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
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

public class ReadingActivity extends Activity {

    TextViewPlus mTextField, tvReading, tvHeader, mTextPage;
    Button btnEnter,btnNext,btnPrev;
    ImageButton btnHelp;
    private DBHandler dbHandler;

    public static Activity ca;
    String Game, page;
    int pageID = 1,totalPage =0;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/IRANSansMobile_Light.ttf");
        ca = this;
        dbHandler = new DBHandler(this);
        btnEnter = findViewById(R.id.btnEnter);
        btnHelp = findViewById(R.id.btnHelp);
        btnPrev = findViewById(R.id.btnPrev);
        btnNext = findViewById(R.id.btnNext);
        mTextField = findViewById(R.id.mTextField);
        tvReading = findViewById(R.id.tvReading);
        tvHeader = findViewById(R.id.tvHeader);
        mTextPage = findViewById(R.id.mTextPage);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
        {
            Game = bundle.getString("Game");
        }
        totalPage = Integer.parseInt(this.getString(R.string.pageCount));
        tvHeader.setText(R.string.reading);
        btnEnter.setTypeface(tf);
        btnPrev.setTypeface(tf);
        btnNext.setTypeface(tf);
        btnEnter.setEnabled(true);
        mTextField.setText("تعداد کل صفحات: " + this.getString(R.string.pageCount));
        getPage(pageID);
        //startReading();
    }

    private void getPage(int pageId) {
        try {
            Field resourceField = R.string.class.getDeclaredField("RDP"+String.valueOf(pageId));
            int resourceId = resourceField.getInt(resourceField);
            page = this.getString(resourceId);
            tvReading.setText(page);
            mTextPage.setText("صفحه: " + pageID);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }

    public void setBtnHelpOnClickListener(View v){
        HelpActivity cdd = new HelpActivity(this, 3);
        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cdd.show();
    }

    public void setBtnNextOnClickListener(View v){
        if (pageID<totalPage) {
            pageID++;
            getPage(pageID);}
        else {
            Toast.makeText(this,"شما در صفحه آخر هستید.",Toast.LENGTH_LONG).show();
        }
    }

    public void setBtnPrevOnClickListener(View v){
        if (pageID>1) {
            pageID--;
            getPage(pageID);
        }
        else {
            Toast.makeText(this,"شما در صفحه اول هستید.",Toast.LENGTH_LONG).show();
        }
    }


/*    @Override
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
    }*/

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
