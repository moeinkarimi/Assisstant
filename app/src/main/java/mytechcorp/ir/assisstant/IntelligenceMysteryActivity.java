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
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import Models.Answers;
import Models.Scores;

public class IntelligenceMysteryActivity extends AppCompatActivity {

    Button btnCheckAnswer2;
    ImageButton btnHelpMys2;
    EditText txtMAnswer2;
    DBHandler dbHandler;
    public static Activity ca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intelligence_mystery);
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/IRANSansMobile_Light.ttf");
        btnCheckAnswer2 = findViewById(R.id.btnCheckAnswer5);
        txtMAnswer2 = findViewById(R.id.txtMAnswer2);
        btnHelpMys2 = findViewById(R.id.btnHelpMys2);

        ca = this;
        dbHandler = new DBHandler(this);
        btnCheckAnswer2.setTypeface(tf);
        txtMAnswer2.setTypeface(tf);
        if (dbHandler.GetScoreState(10)) {
            btnCheckAnswer2.setText("صفحه بعد");
            txtMAnswer2.setText(this.getString(R.string.IntelligenceMysteryAnswer));
            txtMAnswer2.setEnabled(false);
        }
    }

    public void setBtnCheckAnswer2OnClickListener(View v){
        if (!dbHandler.GetScoreState(10)) {
            final android.app.AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom));
            builder.setTitle("ثبت پاسخ");
            builder.setMessage("آیا مطمئنید ؟");
            builder.setPositiveButton("بله", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Answer();
                }
            }).setNegativeButton("خیر", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).setIcon(R.mipmap.ic_tick);
            builder.create().show();
        }
        else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            MainActivity.fa.finish();
            ca.finish();
        }
    }

    private void Answer(){

        if (!dbHandler.GetScoreState(10)) {
            dbHandler.AddAnswer(
                    new Answers(
                            txtMAnswer2.getText().toString(),
                            10,
                            1
                    )
            );
            String trueAns = this.getString(R.string.IntelligenceMysteryAnswer).replace(" ", ""), txtAns = txtMAnswer2.getText().toString().replace(" ", "");
            if (txtAns.equals(trueAns)) {
                dbHandler.AddScore(
                        new Scores(
                                10,
                                30,
                                1
                        )
                );
                Toast.makeText(this, "پاسخ صحیح است", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "پاسخ غلط است", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(this, "قبلا به این سوال پاسخ داده اید", Toast.LENGTH_SHORT).show();
        }

        dbHandler.UpdateState(10);
    }

    public void setBtnHelpMysOnClickListener(View v){
        HelpActivity cdd = new HelpActivity(this, 8);
        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cdd.show();
    }
}
