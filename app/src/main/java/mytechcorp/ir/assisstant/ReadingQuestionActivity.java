package mytechcorp.ir.assisstant;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Models.Answers;
import Models.Questions;
import Models.Scores;
import Models.ShowAnswer;

public class ReadingQuestionActivity extends Activity {

    Button btnQ1,btnQ2,btnQ3, btnEnter6;
    EditText txtAnswer2;
    TextViewPlus tvQDesc;
    private DBHandler dbHandler;
    private int qID = 0;
    Activity rq;
    private SQLiteDatabase db;
    private GridView lvShowAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_question);
        dbHandler = new DBHandler(this);
        rq = this;

        btnQ1 = findViewById(R.id.btnQ1);
        btnQ2 = findViewById(R.id.btnQ2);
        btnQ3 = findViewById(R.id.btnQ3);
        lvShowAnswer = findViewById(R.id.lvShowAnswer);
        btnEnter6 = findViewById(R.id.btnEnter6);
        txtAnswer2 = findViewById(R.id.txtAnswer2);
        tvQDesc = findViewById(R.id.tvQDesc);
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/IRANSansMobile_Light.ttf");

        btnQ1.setTypeface(tf);
        btnQ2.setTypeface(tf);
        btnQ3.setTypeface(tf);
        btnEnter6.setTypeface(tf);
        txtAnswer2.setTypeface(tf);
        loadData();
    }

    public void setBtnEnterOnClickListener(View v) {
        if (dbHandler.GetAnswerCount(2) != 3){
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
        else if (dbHandler.GetAnswerCount(2) == 3){
            dbHandler.UpdateState(2);
            Intent intent = new Intent(ReadingQuestionActivity.this, MainActivity.class);
            startActivity(intent);
            MainActivity.fa.finish();
            rq.finish();
        }
    }

    void Answer(){

        if (dbHandler.GetAnswerCount(2) != 3){
            if (!dbHandler.GetQuestionState(qID,2)) {
                dbHandler.AddAnswer(
                        new Answers(
                                txtAnswer2.getText().toString(),
                                2,
                                qID
                        )
                );
            }

            if (qID == 1 && txtAnswer2.getText().toString().replace(" ", "").equals(this.getString(R.string.sr1j))) {

                if (!dbHandler.GetQuestionState(qID,2)) {
                    dbHandler.AddScore(
                            new Scores(
                                    2,
                                    10,
                                    qID
                            )
                    );
                    Toast.makeText(this,"پاسخ صحیح است",Toast.LENGTH_SHORT).show();
                    dbHandler.UpdateQuestionState(qID, 2);
                } else {
                    Toast.makeText(this,"قبلا به این سوال پاسخ داده اید",Toast.LENGTH_SHORT).show();
                }
            } else if (qID == 2 && txtAnswer2.getText().toString().replace(" ", "").equals(this.getString(R.string.sr2j))) {

                if (!dbHandler.GetQuestionState(qID,2)) {
                    dbHandler.AddScore(
                            new Scores(
                                    2,
                                    10,
                                    qID
                            )
                    );
                    Toast.makeText(this,"پاسخ صحیح است",Toast.LENGTH_SHORT).show();
                    dbHandler.UpdateQuestionState(qID, 2);
                } else {
                    Toast.makeText(this,"قبلا به این سوال پاسخ داده اید",Toast.LENGTH_SHORT).show();
                }
            } else if (qID == 3 && txtAnswer2.getText().toString().replace(" ", "").equals(this.getString(R.string.sr3j))) {

                if (!dbHandler.GetQuestionState(qID,2)) {
                    dbHandler.AddScore(
                            new Scores(
                                    2,
                                    10,
                                    qID
                            )
                    );
                    Toast.makeText(this,"پاسخ صحیح است",Toast.LENGTH_SHORT).show();
                    dbHandler.UpdateQuestionState(qID, 2);
                } else {
                    Toast.makeText(this,"قبلا به این سوال پاسخ داده اید",Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this,"پاسخ غلط است",Toast.LENGTH_SHORT).show();

                if (!dbHandler.GetQuestionState(qID,2)) {

                    dbHandler.UpdateQuestionState(qID, 2);
                }
            }
            txtAnswer2.setText("");
            loadData();
        }
        else if (dbHandler.GetAnswerCount(2) == 3){
            dbHandler.UpdateState(2);
            Intent intent = new Intent(ReadingQuestionActivity.this, MainActivity.class);
            startActivity(intent);
            MainActivity.fa.finish();
            rq.finish();
        }
    }

    public void setBtnQ1OnClickListener(View v){
        qID = 1;
        Questions q = dbHandler.GetQuestion(qID,2);
        tvQDesc.setText(q.getQuestion());
    }

    public void setBtnEQ2OnClickListener(View v){
        qID = 2;
        Questions q = dbHandler.GetQuestion(qID,2);
        tvQDesc.setText(q.getQuestion());
    }

    public void setBtnQ3OnClickListener(View v){
        qID = 3;
        Questions q = dbHandler.GetQuestion(qID,2);
        tvQDesc.setText(q.getQuestion());
    }

    private void loadData(){
        db = dbHandler.getReadableDatabase();
        final ArrayList<HashMap<String, String>> Items = new ArrayList<HashMap<String, String>>();

        List<ShowAnswer> showAnswer = dbHandler.ShowAnswerList(2);
        for(ShowAnswer answer : showAnswer){
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("ques", answer.getQuestion());
            map.put("answer", answer.getAnswer());
            Items.add(map);
        }

        if (Items.isEmpty()){
            String[] notfound = {"متأسفانه داده ای وجود ندارد.\n لطفا به سوالات پاسخ دهید"};
            ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,R.layout.personnotfound,R.id.desctb2,notfound);
            lvShowAnswer.setAdapter(adapter1);
        }
        else {
            if (dbHandler.GetAnswerCount(2) == 3) {
                btnEnter6.setText("رفتن به صفحه اصلی");
            }
            ListAdapter adapter = new SimpleAdapter(this, Items,
                    R.layout.showtablefields,
                    new String[]{
                            "ques", "answer"
                    },
                    new int[]{
                            R.id.tvQues, R.id.tvAnswer
                    });

            lvShowAnswer.setAdapter(adapter);
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
