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

import Models.Answers;
import Models.Questions;
import Models.Scores;

public class TableActivity extends Activity {

    PhotoView ivTable;
    Button btnEnter, btnSelect, btnSave, btnShowAnswers;
    ImageButton btnHelp;
    EditText txtAnswer, txtQues;
    TextViewPlus tvQuestion;
    Activity ta;
    String Game;
    private DBHandler dbHandler;
    private int qID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        ivTable = findViewById(R.id.ivTable);
        PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(ivTable);
        photoViewAttacher.update();
        dbHandler =new DBHandler(this);
        ta= this;
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/IRANSansMobile_Light.ttf");
        btnEnter = findViewById(R.id.btnEnter);
        btnSelect = findViewById(R.id.btnSelect);
        btnHelp = findViewById(R.id.btnHelp);
        btnSave = findViewById(R.id.btnSave);
        btnShowAnswers = findViewById(R.id.btnShowAnswers);
        txtAnswer = findViewById(R.id.txtAnswer);
        txtQues = findViewById(R.id.txtQues);
        tvQuestion = (TextViewPlus)findViewById(R.id.tvQuestion);

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
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(TableActivity.this,MainActivity.class);
        startActivity(intent);
        MainActivity.fa.finish();
        finish();
    }

    public void setBtnEnterOnClickListener(View v){
        if(dbHandler.GetAnswerCount(1) != 22) {
            Intent intent = new Intent(this,CodeActivity.class);
            intent.putExtra("Game",Game);
            startActivity(intent);
            this.finish();
        }
        else if (dbHandler.GetAnswerCount(1) == 22){
            //dbHandler.UpdateState(Integer.parseInt(Game));
            AlertDialog.Builder dialog = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom)).setMessage("1- ف").setTitle("حروف رمز");
            dialog.setNeutralButton("باشه",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface,int i) {
                    Intent intent = new Intent(TableActivity.this, CodeActivity.class);
                    intent.putExtra("Game",Game);
                    startActivity(intent);
                    ta.finish();
                }
            });
            dialog.show();
        }
    }

    public void setBtnSelectOnClickListener(View v){
        if (!txtQues.getText().toString().equals("")) {
            qID = Integer.parseInt(txtQues.getText().toString());
            if (qID > 0 && qID < 23) {
                Questions q = dbHandler.GetQuestion(qID,1);
                tvQuestion.setText(q.getQuestion());
            } else {
                AlertDialog.Builder dialog = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom)).setMessage("لطفا یک عدد بین 1 تا 22 وارد نمایید").setTitle("خطا").setIcon(R.mipmap.ic_close_web);
                dialog.setNeutralButton("باشه", null);
                dialog.show();
            }
        }
        else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom)).setMessage("لطفا یک عدد بین 1 تا 22 وارد نمایید").setTitle("خطا").setIcon(R.mipmap.ic_close_web);
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
        if ((qID==1 && txtAnswer.getText().toString().equals("ابن سبیل"))){

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
        else if ((qID==3 && txtAnswer.getText().toString().equals("تولی و تبری"))
                ||(qID==3 && txtAnswer.getText().toString().equals("تولی وتبری"))
                ){
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
        else if ((qID==6 && txtAnswer.getText().toString().equals("رزق"))
                /*||(qID==6 && txtAnswer.getText().toString().equals("شرم ساری"))*/){
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
        else if ((qID==8 && txtAnswer.getText().toString().equals("وفاداری"))
                || (qID==8 && txtAnswer.getText().toString().equals("وفا داری"))){
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
        else if ((qID==10 && txtAnswer.getText().toString().equals("رستگاری"))
                /*||(qID==10 && txtAnswer.getText().toString().equals("یاس"))*/){
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
        /*else if ((qID==16 && txtAnswer.getText().toString().equals("مادران آسمانی"))
                ||(qID==16 && txtAnswer.getText().toString().equals("مادران اسمانی"))){
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
        else if ((qID==17 && txtAnswer.getText().toString().equals("چادر"))){
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
        else if ((qID==18 && txtAnswer.getText().toString().equals("چشم"))){
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
        else if ((qID==19 && txtAnswer.getText().toString().equals("مار"))){
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
        else if ((qID==20 && txtAnswer.getText().toString().equals("دار"))){
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
        else if ((qID==21 && txtAnswer.getText().toString().equals("امید"))){
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
        else if ((qID==22 && txtAnswer.getText().toString().equals("یوسف"))){
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
        }*/
        else {
            Toast.makeText(this, "پاسخ غلط است", Toast.LENGTH_SHORT).show();
        }
        txtAnswer.setText("");
        txtQues.setText("");
    }

    public void setBtnShowAnswersOnClickListener(View v){
        AnswerDialogActivity cdd = new AnswerDialogActivity(TableActivity.this);
        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cdd.show();
    }
}