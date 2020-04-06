package mytechcorp.ir.assisstant;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Toast;

import Models.Scores;

public class MysteryActivity extends Activity {

    CheckBox ans, ans1, ans2, ans3, ans4;
    Button btnEnter2;
    ImageButton btnHelp;
    private DBHandler dbHandler;
    private Activity ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mystery);
        btnHelp = findViewById(R.id.btnHelp);

        ans = findViewById(R.id.ans);
        ans1 = findViewById(R.id.ans1);
        ans2 = findViewById(R.id.ans2);
        ans3 = findViewById(R.id.ans3);
        ans4 = findViewById(R.id.ans4);
        btnEnter2 = findViewById(R.id.btnEnter2);
        dbHandler = new DBHandler(this);
        ma = this;
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/IRANSansMobile_Light.ttf");
        ans.setTypeface(tf);
        ans1.setTypeface(tf);
        ans2.setTypeface(tf);
        ans3.setTypeface(tf);
        ans4.setTypeface(tf);
        btnEnter2.setTypeface(tf);

        if(dbHandler.GetScoreState(3)) {
            //Namayesh Javab haye doroste Moama QorAni
            ans.toggle();
            ans3.toggle();
        }
    }


    public void setBtnHelpOnClickListener(View v){
        HelpActivity cdd = new HelpActivity(this, 2);
        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cdd.show();
    }

    public void setBtnEnterOnClickListener(View v){
        final android.app.AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom));
        builder.setTitle("ثبت پاسخ");
        builder.setMessage("آیا مطمئنید ؟");
        builder.setPositiveButton("بله", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Answer();
                dbHandler.UpdateState(3);
            }
        }).setNegativeButton("خیر", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setIcon(R.mipmap.ic_tick);
        builder.create().show();
    }

    private void Answer(){
        // ! => ghalat
        if (ans.isChecked()
                && !ans1.isChecked()
                && !ans2.isChecked()
                && ans3.isChecked()
                && !ans4.isChecked()){
            if(!dbHandler.GetScoreState(3)) {
                dbHandler.AddScore(
                        new Scores(
                                3,
                                30,
                                1
                        )
                );
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        MainActivity.fa.finish();
        finish();
    }
}
