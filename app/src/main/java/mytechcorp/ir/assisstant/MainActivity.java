package mytechcorp.ir.assisstant;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class MainActivity extends Activity {

    SQLiteDatabase db;
    DBHandler dbHandler;

    LinearLayout btnGroup, btnRecord, btnBuild, btnTable, btnReading, btnShowPerson, btnDoc, btnEnd, btnFinallyCode, btnMystery, btnPuzzle, btnShowScores;
    ImageView imageView, imageView8, imageView3, imageView4, imageView5, imageView6, imageView7, iVDoc;
    public static Activity fa;

    TextViewPlus lblPersonCount, lblAllScores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fa = this;
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/IRANSansMobile_Light.ttf");

        dbHandler =new DBHandler(this);

        imageView = findViewById(R.id.imageView);
        imageView8 = findViewById(R.id.imageView8);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        iVDoc = findViewById(R.id.iVDoc);

        btnBuild = findViewById(R.id.btnBuild);
        btnRecord = findViewById(R.id.btnRecord);
        btnGroup = findViewById(R.id.btnGroup);
        btnTable = findViewById(R.id.btnTable);
        btnReading = findViewById(R.id.btnReading);
        btnMystery = findViewById(R.id.btnMystery);
        btnPuzzle = findViewById(R.id.btnPuzzle);
        btnShowPerson = findViewById(R.id.btnShowPerson);
        btnDoc = findViewById(R.id.btnDoc);
        btnEnd = findViewById(R.id.btnEnd);
        btnFinallyCode = findViewById(R.id.btnFinallyCode);
        btnShowScores = findViewById(R.id.btnShowScores);

        lblAllScores = findViewById(R.id.lblAllScores);
        lblPersonCount = findViewById(R.id.lblPersonCount);
        lblPersonCount.setText(String.valueOf(dbHandler.GetPersonCount())+ " نفر");
        lblAllScores.setText(String.valueOf(dbHandler.GetSumOfScores()));
        checkImageViewVisibility();

    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom));
        builder.setTitle("خروج");
        builder.setMessage("آیا مطمئنید ؟");
        builder.setPositiveButton("بله", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                System.exit(0);
            }
        }).setNegativeButton("خیر",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setIcon(R.mipmap.ic_close_web);
        builder.create().show();
    }

    public void setBtnShowScoresOnClickListener(View v) {
        Intent intent = new Intent(this, ShowScoresActivity.class);
        startActivity(intent);
    }

    public void setBtnTableOnClickListener(View v) {
        Intent intent = new Intent(this, TableActivity.class);
        intent.putExtra("Game","1");
        startActivity(intent);
    }

    public void setBtnReadingOnClickListener(View v) {
        Intent intent = new Intent(this, ReadingActivity.class);
        intent.putExtra("Game","2");
        startActivity(intent);
    }

    public void setBtnMysteryOnClickListener(View v) {
        Intent intent = new Intent(this, MysteryActivity.class);
        intent.putExtra("Game","3");
        startActivity(intent);
    }

    public void setBtnPuzzleOnClickListener(View v) {
        Intent intent = new Intent(this, DescriptionActivity.class);
        intent.putExtra("Game","4");
        startActivity(intent);
    }

    public void setBtnBuildOnClickListener(View v) {
        Intent intent = new Intent(this, DescriptionActivity.class);
        intent.putExtra("Game","5");
        startActivity(intent);
    }

    public void setBtnRecordOnClickListener(View v) {
        Intent intent = new Intent(this, DescriptionActivity.class);
        intent.putExtra("Game","6");
        startActivity(intent);
    }

    public void setBtnGroupOnClickListener(View v) {
        if(dbHandler.getCodeState(2)){
            Intent intent = new Intent(this, DescriptionActivity.class);
            intent.putExtra("Game","7");
            startActivity(intent);
        }
        else {
            GCodeActivity gCodeActivity = new GCodeActivity(this,2);
            gCodeActivity.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            gCodeActivity.show();
        }
    }

    public void setBtnDocOnClickListener(View v) {
        Intent intent = new Intent(this, DescriptionActivity.class);
        intent.putExtra("Game","8");
        startActivity(intent);
    }

    public void setBtnShowPersonOnClickListener(View v){
        Intent intent = new Intent(this, ShowPersonActivity.class);
        startActivity(intent);
    }

    public void setBtnEndOnClickListener(View v){
        btnEnd.setVisibility(View.GONE);
        btnFinallyCode.setVisibility(View.VISIBLE);
    }

    public void setBtnAboutUsOnClickListener(View v){
        Intent intent = new Intent(this, AboutUsActivity.class);
        startActivity(intent);
    }

    private void checkImageViewVisibility(){
            Log.d("rows ", String.valueOf(dbHandler.getProfilesCount()));

        if(dbHandler.GetStateData(1).equals("1")){
            imageView.setVisibility(View.VISIBLE);
        }
        if(dbHandler.GetStateData(2).equals("1")){
            imageView3.setVisibility(View.VISIBLE);
        }
        if(dbHandler.GetStateData(3).equals("1")){
            imageView4.setVisibility(View.VISIBLE);
        }
        if(dbHandler.GetStateData(4).equals("1")){
            imageView5.setVisibility(View.VISIBLE);
        }
        if(dbHandler.GetStateData(5).equals("1")){
            imageView6.setVisibility(View.VISIBLE);
        }
        if(dbHandler.GetStateData(6).equals("1")){
            imageView7.setVisibility(View.VISIBLE);
        }
        if(dbHandler.GetStateData(7).equals("1")){
            imageView8.setVisibility(View.VISIBLE);
        }
        if(dbHandler.GetStateData(8).equals("1")){
            iVDoc.setVisibility(View.VISIBLE);
        }
    }



}
