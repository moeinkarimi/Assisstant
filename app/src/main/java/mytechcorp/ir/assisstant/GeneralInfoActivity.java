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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import Models.Answers;
import Models.Scores;

public class GeneralInfoActivity extends AppCompatActivity {

    Button btnCheckAnswer, btnBack1;
    Spinner spQues1;
    TextViewPlus tvQDesc;
    RadioButton rbJ1, rbJ2, rbJ3, rbJ4;
    private DBHandler dbHandler;

    Activity rq;
    String Game;
    int qID =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_info);
        dbHandler = new DBHandler(this);
        rq = this;
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/IRANSansMobile_Light.ttf");

        spQues1 = findViewById(R.id.spQues1);
        tvQDesc = findViewById(R.id.tvQDesc);
        btnCheckAnswer = findViewById(R.id.btnCheckAnswer);
        btnBack1 = findViewById(R.id.btnBack1);
        rbJ1 = findViewById(R.id.rbJ1);
        rbJ2 = findViewById(R.id.rbJ2);
        rbJ3 = findViewById(R.id.rbJ3);
        rbJ4 = findViewById(R.id.rbJ4);
        rbJ1.setTypeface(tf);
        rbJ2.setTypeface(tf);
        rbJ3.setTypeface(tf);
        rbJ4.setTypeface(tf);
        btnCheckAnswer.setTypeface(tf);
        btnBack1.setTypeface(tf);


        String[] QuesName = new String[]{"سوال 1","سوال 2","سوال 3","سوال 4","سوال 5","سوال 6","سوال 7","سوال 8","سوال 9","سوال 10" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, QuesName);
        spQues1.setAdapter(adapter);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
        {
            Game = bundle.getString("Game");
        }

        if (dbHandler.GetAnswerCount(9) == 10){
            btnCheckAnswer.setText("رفتن به صفحه اصلی");
        }

        spQues1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                GetQuestionsAndOptions(position);
                qID = position+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void setBtnGIHelpOnClickListener(View v){
        HelpActivity cdd = new HelpActivity(this, 7);
        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cdd.show();
    }

    public void setBtnBack1OnClickListener(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        MainActivity.fa.finish();
        rq.finish();
    }


    public void setBtnCheckOnClickListener(View v) {
        if (dbHandler.GetAnswerCount(9) != 10){

            if (!dbHandler.GetQuestionState(qID,9)) {
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
                Toast.makeText(this,"قبلا به این سوال پاسخ داده اید",Toast.LENGTH_SHORT).show();
            }
        }
        else if (dbHandler.GetAnswerCount(9) == 10){
            dbHandler.UpdateState(9);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            MainActivity.fa.finish();
            rq.finish();
        }
    }


    private void GetQuestionsAndOptions(int id) {
        rbJ1.setChecked(false);
        rbJ2.setChecked(false);
        rbJ3.setChecked(false);
        rbJ4.setChecked(false);
        if (dbHandler.GetAnswerCount(9) == 10){
            btnCheckAnswer.setText("رفتن به صفحه اصلی");
        }
        try {
            Field resourceField = R.string.class.getDeclaredField("gis"+String.valueOf(id+1));
            Field rbjtext1 = R.string.class.getDeclaredField("gisj1"+String.valueOf(id+1));
            Field rbjtext2 = R.string.class.getDeclaredField("gisj2"+String.valueOf(id+1));
            Field rbjtext3 = R.string.class.getDeclaredField("gisj3"+String.valueOf(id+1));
            Field rbjtext4 = R.string.class.getDeclaredField("gisj4"+String.valueOf(id+1));
            int resourceId = resourceField.getInt(resourceField);

            tvQDesc.setText(this.getString(resourceId));
            rbJ1.setText(this.getString(rbjtext1.getInt(resourceField)));
            rbJ2.setText(this.getString(rbjtext2.getInt(resourceField)));
            rbJ3.setText(this.getString(rbjtext3.getInt(resourceField)));
            rbJ4.setText(this.getString(rbjtext4.getInt(resourceField)));
            CheckAnswer(id+1);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private void Answer(){
        if (!dbHandler.GetQuestionState(qID,9)) {
            try {

                Field resourceField = R.string.class.getDeclaredField("TQanswer" + String.valueOf(qID));
                int resourceId = resourceField.getInt(resourceField);
                String answer = this.getString(resourceId);
                RadioGroup radioGroup = findViewById(R.id.radio_group);
                int radioButtonID = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = radioGroup.findViewById(radioButtonID);
                String selectedtext = radioButton.getText().toString();
                Log.d("rb Text: ", selectedtext);
                if (selectedtext.equals(answer)) {
                    dbHandler.AddScore(
                        new Scores(
                                9,
                                3,
                                qID
                        )
                    );

                    Toast.makeText(this, "پاسخ صحیح است", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "پاسخ غلط است", Toast.LENGTH_LONG).show();
                }
                dbHandler.AddAnswer(
                        new Answers(
                                selectedtext,
                                9,
                                qID
                        )
                );
                dbHandler.UpdateQuestionState(qID, 9);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        else {
            Toast.makeText(this,"قبلا به این سوال پاسخ داده اید",Toast.LENGTH_SHORT).show();
        }
        if (dbHandler.GetAnswerCount(9) == 10) {
            dbHandler.UpdateState(9);
        }
    }

    private void CheckAnswer(int id){
        if (dbHandler.GetQuestionState(id,9)) {
            if (rbJ1.getText().toString().equals(dbHandler.GetAnswer(9, id))) {
                rbJ1.setChecked(true);
            }
            else if (rbJ2.getText().toString().equals(dbHandler.GetAnswer(9, id)))
                rbJ2.setChecked(true);
            else if (rbJ3.getText().toString().equals(dbHandler.GetAnswer(9, id)))
                rbJ3.setChecked(true);
            else if (rbJ4.getText().toString().equals(dbHandler.GetAnswer(9, id)))
                rbJ4.setChecked(true);
        }

    }

}
