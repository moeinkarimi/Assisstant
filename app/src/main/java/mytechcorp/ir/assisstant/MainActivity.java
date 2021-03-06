package mytechcorp.ir.assisstant;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

public class MainActivity extends Activity {

    SQLiteDatabase db;
    DBHandler dbHandler;

    LinearLayout btnGroup, btnRecord, btnBuild, btnTable, btnReading, btnShowPerson, btnDoc, btnEnd, btnFinallyCode, btnMystery, btnPuzzle, btnShowScores;
    ImageView imageView, imageView8, imageView3, imageView4, imageView5, imageView6, imageView7, iVDoc, imageView13,imageView17;
    ImageButton btnAppDesc, btnScoreDesc, btnSpecialDesc;

    public static Activity fa;

    TextViewPlus lblPersonCount, lblAllScores, textViewPlus10;

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
        imageView6 = findViewById(R.id.btnSpecialDesc1);
        imageView7 = findViewById(R.id.imageView7);
        imageView13 = findViewById(R.id.imageView13);
        imageView17 = findViewById(R.id.imageView17);
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
        btnShowScores = findViewById(R.id.btnShowScores);

        btnAppDesc = findViewById(R.id.btnAppDesc);
        btnSpecialDesc = findViewById(R.id.btnSpecialDesc);
        btnSpecialDesc = findViewById(R.id.btnScoreDesc);

        lblAllScores = findViewById(R.id.lblAllScores);
        lblPersonCount = findViewById(R.id.lblPersonCount);
        textViewPlus10 = findViewById(R.id.textViewPlus10);

        lblPersonCount.setText(String.valueOf(dbHandler.GetPersonCount())+ " نفر");
        lblAllScores.setText(String.valueOf(dbHandler.GetSumOfScores(0)));
        checkImageViewVisibility();
        textViewPlus10.setText(dbHandler.GetFirstPerson());

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
        /*Intent intent = new Intent(this, ShowScoresActivity.class);
        startActivity(intent);*/

        lblAllScores.setText(String.valueOf(dbHandler.GetSumOfScores(0)));
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
//        if(dbHandler.getCodeState(2)){
            Intent intent = new Intent(this, DescriptionActivity.class);
            intent.putExtra("Game","7");
            startActivity(intent);
        /*}
        else {
            GCodeActivity gCodeActivity = new GCodeActivity(this,2);
            gCodeActivity.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            gCodeActivity.show();
        }*/
    }

    public void setBtnDocOnClickListener(View v) {
        Intent intent = new Intent(this, DescriptionActivity.class);
        intent.putExtra("Game","8");
        startActivity(intent);
    }

    public void setBtnGIOnClickListener(View v) {
        Intent intent = new Intent(this, GeneralInfoActivity.class);
        intent.putExtra("Game","9");
        startActivity(intent);
    }

    public void setBtnIntelliMysteryOnClickListener(View v) {
        Intent intent = new Intent(this, IntelligenceMysteryActivity.class);
        intent.putExtra("Game","10");
        startActivity(intent);
    }

    public void setBtnShowPersonOnClickListener(View v){
        Intent intent = new Intent(this, ShowPersonActivity.class);
        startActivity(intent);
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void setBtnEndOnClickListener(View v){
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                ).withListener(new MultiplePermissionsListener() {
            @Override public void onPermissionsChecked(MultiplePermissionsReport report) {
                FinalActivity Fad = new FinalActivity(fa);
                Fad.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                Fad.show();
            }
            @Override public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions,PermissionToken token) {

            }
        }).check();
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
        if(dbHandler.GetStateData(9).equals("1")){
            imageView13.setVisibility(View.VISIBLE);
        }
        if(dbHandler.GetStateData(10).equals("1")){
            imageView17.setVisibility(View.VISIBLE);
        }
    }

    public void setBtnAppDescOnClickListener(View v){
        HelpActivity cdd = new HelpActivity(this, 4);
        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cdd.show();
    }

    public void setBtnScoreDescOnClickListener(View v){
        HelpActivity cdd = new HelpActivity(this, 5);
        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cdd.show();
    }

    public void setBtnSpecialDescOnClickListener(View v){
        HelpActivity cdd = new HelpActivity(this, 6);
        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cdd.show();
    }



}
