package mytechcorp.ir.assisstant;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.LinearLayout;

public class ShowScoresActivity extends Activity {

    private DBHandler dbHandler;
    LinearLayout btnGroup, btnRecord, btnBuild, btnTable, btnReading, btnDoc, btnMystery, btnPuzzle;
    TextViewPlus lblTScore, lblRScore, lblMScore, lblPScore, lblMakeScore, lblRecordScore, lblGGScore, lblDocScore, lblAllScores2, lblGpCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_scores);
        dbHandler = new DBHandler(this);
        btnBuild = findViewById(R.id.btnBuild);
        btnRecord = findViewById(R.id.btnRecord);
        btnGroup = findViewById(R.id.btnGroup);
        btnTable = findViewById(R.id.btnTable);
        btnReading = findViewById(R.id.btnReading);
        btnMystery = findViewById(R.id.btnMystery);
        btnPuzzle = findViewById(R.id.btnPuzzle);
        btnDoc = findViewById(R.id.btnDoc);

        lblAllScores2 = findViewById(R.id.lblAllScores2);
        lblTScore = findViewById(R.id.lblTScore);
        lblRScore = findViewById(R.id.lblRScore);
        lblMScore = findViewById(R.id.lblMScore);
        lblPScore = findViewById(R.id.lblPScore);
        lblMakeScore = findViewById(R.id.lblMakeScore);
        lblRecordScore = findViewById(R.id.lblRecordScore);
        lblGGScore = findViewById(R.id.lblGGScore);
        lblDocScore = findViewById(R.id.lblDocScore);
        lblGpCode = findViewById(R.id.lblGpCode);


        lblAllScores2.setText("مجموع امتیازات : " + String.valueOf(dbHandler.GetSumOfScores()));
        lblTScore.setText(String.valueOf(dbHandler.GetSumOfScore(1)));
        lblRScore.setText(String.valueOf(dbHandler.GetSumOfScore(2)));
        lblMScore.setText(String.valueOf(dbHandler.GetSumOfScore(3)));
        lblPScore.setText(String.valueOf(dbHandler.GetSumOfScore(4)));
        lblMakeScore.setText(String.valueOf(dbHandler.GetSumOfScore(5)));
        lblRecordScore.setText(String.valueOf(dbHandler.GetSumOfScore(6)));
        lblGGScore.setText(String.valueOf(dbHandler.GetSumOfScore(7)));
        lblDocScore.setText(String.valueOf(dbHandler.GetSumOfScore(8)));
        lblGpCode.setText(dbHandler.GetGCode(1));
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        MainActivity.fa.finish();
        finish();
    }

    public void setBtnTableOnClickListener(View v) {
        if (dbHandler.GetScoreState(1)){
            AlertDialog.Builder dialog = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom)).setMessage("1- ف").setTitle("حروف رمز");
            dialog.setNeutralButton("باشه", null);
            dialog.show();
        }
    }

    public void setBtnReadingOnClickListener(View v) {
        if (dbHandler.GetScoreState(2)){
            AlertDialog.Builder dialog = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom)).setMessage("2-\tر").setTitle("حروف رمز");
            dialog.setNeutralButton("باشه", null);
            dialog.show();
        }
    }

    public void setBtnMysteryOnClickListener(View v) {
        if (dbHandler.GetScoreState(3)){
            AlertDialog.Builder dialog = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom)).setMessage("3-\tا د").setTitle("حروف رمز");
            dialog.setNeutralButton("باشه", null);
            dialog.show();
        }
    }

    public void setBtnPuzzleOnClickListener(View v) {
        if (dbHandler.GetScoreState(4)){
            AlertDialog.Builder dialog = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom)).setMessage("4-\tز ا").setTitle("حروف رمز");
            dialog.setNeutralButton("باشه", null);
            dialog.show();
        }
    }

    public void setBtnBuildOnClickListener(View v) {
        if (dbHandler.GetScoreState(5)){
            AlertDialog.Builder dialog = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom)).setMessage("5-\tا").setTitle("حروف رمز");
            dialog.setNeutralButton("باشه", null);
            dialog.show();
        }
    }

    public void setBtnRecordOnClickListener(View v) {
        if (dbHandler.GetScoreState(6)){
            AlertDialog.Builder dialog = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom)).setMessage("6-\tن").setTitle("حروف رمز");
            dialog.setNeutralButton("باشه", null);
            dialog.show();
        }
    }

    public void setBtnGroupOnClickListener(View v) {
        if (dbHandler.GetScoreState(7)){
            AlertDialog.Builder dialog = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom)).setMessage("7-\tا م").setTitle("حروف رمز");
            dialog.setNeutralButton("باشه", null);
            dialog.show();
        }
    }

    public void setBtnDocOnClickListener(View v) {
        if (dbHandler.GetScoreState(8)){
            AlertDialog.Builder dialog = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom)).setMessage("8-\tت س").setTitle("حروف رمز");
            dialog.setNeutralButton("باشه", null);
            dialog.show();
        }
    }
}
