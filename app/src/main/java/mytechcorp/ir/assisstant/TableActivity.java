package mytechcorp.ir.assisstant;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.chrisbanes.photoview.PhotoView;
import com.github.chrisbanes.photoview.PhotoViewAttacher;

import java.lang.reflect.Field;

import Models.Answers;
import Models.Questions;
import Models.Scores;

public class TableActivity extends Activity {

    PhotoView ivTable;
    Button btnEnter, btnSelect, btnSave, btnShowAnswers,btnBackToMain;
    ImageButton btnHelp;
    EditText txtAnswer, txtQues;
    TextViewPlus tvQuestion, questionCount;
    Activity ta;
    String Game;
    private DBHandler dbHandler;
    private int qID,Count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        ivTable = findViewById(R.id.ivTable);
        PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(ivTable);
        photoViewAttacher.update();
        dbHandler =new DBHandler(this);
        ta= this;
        Count = Integer.parseInt(this.getString(R.string.QCount));
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/IRANSansMobile_Light.ttf");
        btnEnter = findViewById(R.id.btnEnter3);
        btnSelect = findViewById(R.id.btnSelect);
        btnHelp = findViewById(R.id.btnHelp);
        btnSave = findViewById(R.id.btnSave);
        btnBackToMain = findViewById(R.id.btnBackToMain);
        btnShowAnswers = findViewById(R.id.btnShowAnswers);
        txtAnswer = findViewById(R.id.txtAnswer);
        txtQues = findViewById(R.id.txtQues);
        tvQuestion = findViewById(R.id.tvQuestion);
        questionCount = findViewById(R.id.questionCount);

        questionCount.setText("تعداد سوالات: " + this.getString(R.string.QCount));
        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
        {
            Game = bundle.getString("Game");
            Log.d("Game Value ", Game);
        }
        btnEnter.setTypeface(tf);
        btnSelect.setTypeface(tf);
        btnSave.setTypeface(tf);
        txtAnswer.setTypeface(tf);
        btnShowAnswers.setTypeface(tf);
        btnBackToMain.setTypeface(tf);

        if(dbHandler.GetAnswerCount(1) != Count) {
            btnEnter.setEnabled(false);
        }

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(TableActivity.this,MainActivity.class);
        startActivity(intent);
        MainActivity.fa.finish();
        finish();
    }
    public void setBtnBackOnClickListener(View v) {
        Intent intent = new Intent(TableActivity.this,MainActivity.class);
        startActivity(intent);
        MainActivity.fa.finish();
        finish();
    }

    public void setBtnEnterOnClickListener(View v){
        if(dbHandler.GetAnswerCount(1) != Count) {
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra("Game",Game);
            startActivity(intent);
            this.finish();
        }
        else if (dbHandler.GetAnswerCount(1) == Count){
//            //dbHandler.UpdateState(Integer.parseInt(Game));
//            AlertDialog.Builder dialog = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom)).setMessage("1- ف").setTitle("حروف رمز");
//            dialog.setNeutralButton("باشه",new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface,int i) {
                    Intent intent = new Intent(TableActivity.this, CodeActivity.class);
                    intent.putExtra("Game",Game);
                    startActivity(intent);
                    ta.finish();
//                }
//            });
//            dialog.show();
        }
    }

    public void setBtnSelectOnClickListener(View v){
        if (!txtQues.getText().toString().equals("")) {
            qID = Integer.parseInt(txtQues.getText().toString());
            if (qID > 0 && qID < Count+1) {
                Questions q = dbHandler.GetQuestion(qID,1);
                tvQuestion.setText(q.getQuestion());
            } else {
                AlertDialog.Builder dialog = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom)).setMessage("لطفا یک عدد بین 1 تا "+Count+" وارد نمایید").setTitle("خطا").setIcon(R.mipmap.ic_close_web);
                dialog.setNeutralButton("باشه", null);
                dialog.show();
            }
        }
        else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom)).setMessage("لطفا یک عدد بین 1 تا "+Count+" وارد نمایید").setTitle("خطا").setIcon(R.mipmap.ic_close_web);
            dialog.setNeutralButton("باشه", null);
            dialog.show();
        }
    }

    public void setBtnHelpOnClickListener(View v){
        HelpActivity cdd = new HelpActivity(TableActivity.this, 1);
        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cdd.show();
    }

    public void setBtnSaveOnClickLisetener(View v){
        try{
            Field resourceField = R.string.class.getDeclaredField("sj"+String.valueOf(qID));
            int resourceId = resourceField.getInt(resourceField);
            String Question = this.getString(resourceId).replace(" ", "");
            if (txtAnswer.getText().toString().replace(" ", "").equals(Question)){
                if(!dbHandler.GetQuestionState(qID,1)) {
                    dbHandler.AddAnswer(
                            new Answers(
                                    txtAnswer.getText().toString(),
                                    1,
                                    qID
                            )
                    );
                    dbHandler.AddScore(
                            new Scores(
                                    1,
                                    1,
                                    qID
                            )
                    );
                    Toast.makeText(this, "پاسخ صحیح است", Toast.LENGTH_SHORT).show();
                    dbHandler.UpdateQuestionState(qID,1);

                }else
                {
                    Toast.makeText(this, "قبلا به این سوال پاسخ داده اید", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(this, "پاسخ غلط است", Toast.LENGTH_SHORT).show();
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }


        /*if ((qID==1 && txtAnswer.getText().toString().equals("ابن سبیل"))){

            if(!dbHandler.GetQuestionState(qID,1)){
                dbHandler.AddAnswer(
                        new Answers(
                                txtAnswer.getText().toString(),
                                1,
                                qID
                        )
                );
                dbHandler.AddScore(
                        new Scores(
                                1,
                                1,
                                qID
                        )
                );
                Toast.makeText(this, "پاسخ صحیح است", Toast.LENGTH_SHORT).show();
                dbHandler.UpdateQuestionState(qID,1);
            }
            else
            {
                Toast.makeText(this, "قبلا به این سوال پاسخ داده اید", Toast.LENGTH_SHORT).show();
            }
        }
        else if ((qID==2 && txtAnswer.getText().toString().equals("برکت"))){
            if(!dbHandler.GetQuestionState(qID,1)){
                dbHandler.AddAnswer(
                        new Answers(
                                txtAnswer.getText().toString(),
                                1,
                                qID
                        )
                );
                dbHandler.AddScore(
                        new Scores(
                                1,
                                1,
                                qID
                        )
                );
                Toast.makeText(this, "پاسخ صحیح است", Toast.LENGTH_SHORT).show();
                dbHandler.UpdateQuestionState(qID,1);
            }
            else
            {
                Toast.makeText(this, "قبلا به این سوال پاسخ داده اید", Toast.LENGTH_SHORT).show();
            }
        }
        else if ((qID==3 && txtAnswer.getText().toString().equals("تولی و تبری"))){
            if(!dbHandler.GetQuestionState(qID,1)){
                dbHandler.AddAnswer(
                        new Answers(
                                txtAnswer.getText().toString(),
                                1,
                                qID
                        )
                );
                dbHandler.AddScore(
                        new Scores(
                                1,
                                1,
                                qID
                        )
                );
                Toast.makeText(this, "پاسخ صحیح است", Toast.LENGTH_SHORT).show();
                dbHandler.UpdateQuestionState(qID,1);
            }
            else
            {
                Toast.makeText(this, "قبلا به این سوال پاسخ داده اید", Toast.LENGTH_SHORT).show();
            }
        }
        else if ((qID==4 && txtAnswer.getText().toString().equals("بندگی"))){
            if(!dbHandler.GetQuestionState(qID,1)){
                dbHandler.AddAnswer(
                        new Answers(
                                txtAnswer.getText().toString(),
                                1,
                                qID
                        )
                );
                dbHandler.AddScore(
                        new Scores(
                                1,
                                1,
                                qID
                        )
                );
                Toast.makeText(this, "پاسخ صحیح است", Toast.LENGTH_SHORT).show();
                dbHandler.UpdateQuestionState(qID,1);
            }
            else
            {
                Toast.makeText(this, "قبلا به این سوال پاسخ داده اید", Toast.LENGTH_SHORT).show();
            }
        }
        else if ((qID==5 && txtAnswer.getText().toString().equals("بدر"))){
            if(!dbHandler.GetQuestionState(qID,1)){
                dbHandler.AddAnswer(
                        new Answers(
                                txtAnswer.getText().toString(),
                                1,
                                qID
                        )
                );
                dbHandler.AddScore(
                        new Scores(
                                1,
                                1,
                                qID
                        )
                );
                Toast.makeText(this, "پاسخ صحیح است", Toast.LENGTH_SHORT).show();
                dbHandler.UpdateQuestionState(qID,1);
            }
            else
            {
                Toast.makeText(this, "قبلا به این سوال پاسخ داده اید", Toast.LENGTH_SHORT).show();
            }
        }
        else if ((qID==6 && txtAnswer.getText().toString().equals("رزق"))){
            if(!dbHandler.GetQuestionState(qID,1)){
                dbHandler.AddAnswer(
                        new Answers(
                                txtAnswer.getText().toString(),
                                1,
                                qID
                        )
                );
                dbHandler.AddScore(
                        new Scores(
                                1,
                                1,
                                qID
                        )
                );
                Toast.makeText(this, "پاسخ صحیح است", Toast.LENGTH_SHORT).show();
                dbHandler.UpdateQuestionState(qID,1);
            }
            else
            {
                Toast.makeText(this, "قبلا به این سوال پاسخ داده اید", Toast.LENGTH_SHORT).show();
            }
        }
        else if ((qID==7 && txtAnswer.getText().toString().equals("قصد"))){
            if(!dbHandler.GetQuestionState(qID,1)){
                dbHandler.AddAnswer(
                        new Answers(
                                txtAnswer.getText().toString(),
                                1,
                                qID
                        )
                );
                dbHandler.AddScore(
                        new Scores(
                                1,
                                1,
                                qID
                        )
                );
                Toast.makeText(this, "پاسخ صحیح است", Toast.LENGTH_SHORT).show();
                dbHandler.UpdateQuestionState(qID,1);
            }
            else
            {
                Toast.makeText(this, "قبلا به این سوال پاسخ داده اید", Toast.LENGTH_SHORT).show();
            }
        }
        else if ((qID==8 && txtAnswer.getText().toString().equals("وفاداری"))){
            if(!dbHandler.GetQuestionState(qID,1)){
                dbHandler.AddAnswer(
                        new Answers(
                                txtAnswer.getText().toString(),
                                1,
                                qID
                        )
                );
                dbHandler.AddScore(
                        new Scores(
                                1,
                                1,
                                qID
                        )
                );
                Toast.makeText(this, "پاسخ صحیح است", Toast.LENGTH_SHORT).show();
                dbHandler.UpdateQuestionState(qID,1);
            }
            else
            {
                Toast.makeText(this, "قبلا به این سوال پاسخ داده اید", Toast.LENGTH_SHORT).show();
            }
        }
        else if ((qID==9 && txtAnswer.getText().toString().equals("آب"))){
            if(!dbHandler.GetQuestionState(qID,1)){
                dbHandler.AddAnswer(
                        new Answers(
                                txtAnswer.getText().toString(),
                                1,
                                qID
                        )
                );
                dbHandler.AddScore(
                        new Scores(
                                1,
                                1,
                                qID
                        )
                );
                Toast.makeText(this, "پاسخ صحیح است", Toast.LENGTH_SHORT).show();
                dbHandler.UpdateQuestionState(qID,1);
            }
            else
            {
                Toast.makeText(this, "قبلا به این سوال پاسخ داده اید", Toast.LENGTH_SHORT).show();
            }
        }
        else if ((qID==10 && txtAnswer.getText().toString().equals("رستگاری"))){
            if(!dbHandler.GetQuestionState(qID,1)){
                dbHandler.AddAnswer(
                        new Answers(
                                txtAnswer.getText().toString(),
                                1,
                                qID
                        )
                );
                dbHandler.AddScore(
                        new Scores(
                                1,
                                1,
                                qID
                        )
                );
                Toast.makeText(this, "پاسخ صحیح است", Toast.LENGTH_SHORT).show();
                dbHandler.UpdateQuestionState(qID,1);
            }
            else
            {
                Toast.makeText(this, "قبلا به این سوال پاسخ داده اید", Toast.LENGTH_SHORT).show();
            }
        }
        else if ((qID==11 && txtAnswer.getText().toString().equals("نذر"))){
            if(!dbHandler.GetQuestionState(qID,1)){
                dbHandler.AddAnswer(
                        new Answers(
                                txtAnswer.getText().toString(),
                                1,
                                qID
                        )
                );
                dbHandler.AddScore(
                        new Scores(
                                1,
                                1,
                                qID
                        )
                );
                Toast.makeText(this, "پاسخ صحیح است", Toast.LENGTH_SHORT).show();
                dbHandler.UpdateQuestionState(qID,1);
            }
            else
            {
                Toast.makeText(this, "قبلا به این سوال پاسخ داده اید", Toast.LENGTH_SHORT).show();
            }
        }
        else if ((qID==12 && txtAnswer.getText().toString().equals("نان"))){
            if(!dbHandler.GetQuestionState(qID,1)){
                dbHandler.AddAnswer(
                        new Answers(
                                txtAnswer.getText().toString(),
                                1,
                                qID
                        )
                );
                dbHandler.AddScore(
                        new Scores(
                                1,
                                1,
                                qID
                        )
                );
                Toast.makeText(this, "پاسخ صحیح است", Toast.LENGTH_SHORT).show();
                dbHandler.UpdateQuestionState(qID,1);
            }
            else
            {
                Toast.makeText(this, "قبلا به این سوال پاسخ داده اید", Toast.LENGTH_SHORT).show();
            }
        }
        else if ((qID==13 && txtAnswer.getText().toString().equals("تین"))){
            if(!dbHandler.GetQuestionState(qID,1)){
                dbHandler.AddAnswer(
                        new Answers(
                                txtAnswer.getText().toString(),
                                1,
                                qID
                        )
                );
                dbHandler.AddScore(
                        new Scores(
                                1,
                                1,
                                qID
                        )
                );
                Toast.makeText(this, "پاسخ صحیح است", Toast.LENGTH_SHORT).show();
                dbHandler.UpdateQuestionState(qID,1);
            }
            else
            {
                Toast.makeText(this, "قبلا به این سوال پاسخ داده اید", Toast.LENGTH_SHORT).show();
            }
        }
        else if ((qID==14 && txtAnswer.getText().toString().equals("یعقوب"))){
            if(!dbHandler.GetQuestionState(qID,1)){
                dbHandler.AddAnswer(
                        new Answers(
                                txtAnswer.getText().toString(),
                                1,
                                qID
                        )
                );
                dbHandler.AddScore(
                        new Scores(
                                1,
                                1,
                                qID
                        )
                );
                Toast.makeText(this, "پاسخ صحیح است", Toast.LENGTH_SHORT).show();
                dbHandler.UpdateQuestionState(qID,1);
            }
            else
            {
                Toast.makeText(this, "قبلا به این سوال پاسخ داده اید", Toast.LENGTH_SHORT).show();
            }
        }
        else if ((qID==15 && txtAnswer.getText().toString().equals("بخشنده"))){
            if(!dbHandler.GetQuestionState(qID,1)){
                dbHandler.AddAnswer(
                        new Answers(
                                txtAnswer.getText().toString(),
                                1,
                                qID
                        )
                );
                dbHandler.AddScore(
                        new Scores(
                                1,
                                1,
                                qID
                        )
                );
                Toast.makeText(this, "پاسخ صحیح است", Toast.LENGTH_SHORT).show();
                dbHandler.UpdateQuestionState(qID,1);
            }
            else
            {
                Toast.makeText(this, "قبلا به این سوال پاسخ داده اید", Toast.LENGTH_SHORT).show();
            }
        }

        else {
            Toast.makeText(this, "پاسخ غلط است", Toast.LENGTH_SHORT).show();
        }*/
        txtAnswer.setText("");
        txtQues.setText("");
    }

    public void setBtnShowAnswersOnClickListener(View v){
        AnswerDialogActivity cdd = new AnswerDialogActivity(TableActivity.this);
        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cdd.show();
    }
}