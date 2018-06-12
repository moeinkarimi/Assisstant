package mytechcorp.ir.assisstant;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    DBHandler dbHandler;

    LinearLayout btnGroup, btnRecord, btnBuild, btnTable, btnReading, btnShowPerson, btnDoc, btnEnd, btnFinallyCode;
    ImageView imageView, imageView8, imageView3, imageView4, imageView5, imageView6, imageView7, iVDoc;
    public static Activity fa;

    TextViewPlus lblPersonCount;

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
        btnShowPerson = findViewById(R.id.btnShowPerson);
        btnDoc = findViewById(R.id.btnDoc);
        btnEnd = findViewById(R.id.btnEnd);
        btnFinallyCode = findViewById(R.id.btnFinallyCode);

        lblPersonCount = findViewById(R.id.lblPersonCount);
        lblPersonCount.setText(String.valueOf(dbHandler.GetPersonCount())+ " نفر");
        checkImageViewVisibility();

    }

    public void setBtnBuildOnClickListener(View v) {
        Intent intent = new Intent(this, DescriptionActivity.class);
        intent.putExtra("Game","5");
        startActivity(intent);
        //this.finish();
    }

    public void setBtnRecordOnClickListener(View v) {
        Intent intent = new Intent(this, DescriptionActivity.class);
        intent.putExtra("Game","6");
        startActivity(intent);
        //this.finish();
    }

    public void setBtnGroupOnClickListener(View v) {
        Intent intent = new Intent(this, DescriptionActivity.class);
        intent.putExtra("Game","7");
        startActivity(intent);
        //this.finish();
    }

    public void setBtnTableOnClickListener(View v) {
        Intent intent = new Intent(this, TableActivity.class);
        intent.putExtra("Game","1");
        startActivity(intent);
        //this.finish();
    }

    public void setBtnReadingOnClickListener(View v) {
        Intent intent = new Intent(this, ReadingActivity.class);
        intent.putExtra("Game","2");
        startActivity(intent);
        //this.finish();
    }

    public void setBtnDocOnClickListener(View v) {
        Intent intent = new Intent(this, DescriptionActivity.class);
        intent.putExtra("Game","8");
        startActivity(intent);
        //this.finish();
    }

    public void setBtnShowPersonOnClickListener(View v){
        Intent intent = new Intent(this, ShowPersonActivity.class);
        startActivity(intent);
    }

    public void setBtnEndOnClickListener(View v){
        btnEnd.setVisibility(View.INVISIBLE);
        btnFinallyCode.setVisibility(View.VISIBLE);
        btnEnd.getLayoutParams().height = 0;
        btnFinallyCode.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
        
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
            imageView8.setVisibility(View.VISIBLE);
        }
    }



}
