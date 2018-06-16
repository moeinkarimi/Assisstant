package mytechcorp.ir.assisstant;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import Models.Scores;

public class MysteryActivity extends AppCompatActivity {

    CheckBox ans, ans1, ans2, ans3, ans4;
    Button btnEnter2;
    private DBHandler dbHandler;
    private Activity ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mystery);

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
            ans.toggle();
            ans1.toggle();
            ans3.toggle();
            ans4.toggle();
        }
    }

    public void setBtnEnterOnClickListener(View v){
        Intent intent = new Intent(this, MainActivity.class);
        if (ans.isChecked()
                && ans1.isChecked()
                && ans3.isChecked()
                && ans4.isChecked()){
            if(!dbHandler.GetScoreState(3)) {
                dbHandler.AddScore(
                        new Scores(
                                3,
                                20,
                                30
                        )
                );
            }
            dbHandler.UpdateState(3);
            AlertDialog.Builder dialog = new AlertDialog.Builder(this).setMessage("3-\tا د").setTitle("حروف رمز");
            dialog.setNeutralButton("باشه",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface,int i) {
                    Intent intent = new Intent(MysteryActivity.this, MainActivity.class);
                    startActivity(intent);
                    MainActivity.fa.finish();
                    ma.finish();
                }
            });
            dialog.show();
        }
        else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this).setMessage("لطفا تمام گزینه های صحیح را انتخاب نمایید").setTitle("خطا");
            dialog.setNeutralButton("باشه", null);
            dialog.show();
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
