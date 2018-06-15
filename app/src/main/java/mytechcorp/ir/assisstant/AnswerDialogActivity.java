package mytechcorp.ir.assisstant;

import android.app.Activity;
import android.app.Dialog;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Models.Person;
import Models.ShowAnswer;

public class AnswerDialogActivity extends Dialog {

    public Activity c;
    public Dialog d;
    GridView lvShowAnswer;
    private DBHandler dbHandler;
    SQLiteDatabase db;
    Button btnOK;

    public AnswerDialogActivity(Activity a) {
        super(a);
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_answer_dialog);
        lvShowAnswer = (GridView)findViewById(R.id.lvShowAnswer);
        btnOK = findViewById(R.id.btnOK);
        dbHandler =new DBHandler(c);
        loadData();
        btnOK.setTypeface(Typeface.createFromAsset(c.getAssets(),"fonts/IRANSansMobile_Light.ttf"));
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    private void loadData(){
        db = dbHandler.getReadableDatabase();
        final ArrayList<HashMap<String, String>> Items = new ArrayList<HashMap<String, String>>();

        List<ShowAnswer> showAnswer = dbHandler.ShowAnswerList(1);
        for(ShowAnswer answer : showAnswer){
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("ques", answer.getQuestion());
            map.put("answer", answer.getAnswer());
            Items.add(map);
        }

        if (Items.isEmpty()){
            String[] notfound = {"متأسفانه داده ای وجود ندارد.\n لطفا به سوالات پاسخ دهید"};
            ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(c,R.layout.personnotfound,R.id.desctb2,notfound);
            lvShowAnswer.setAdapter(adapter1);
        }
        else {
            ListAdapter adapter = new SimpleAdapter(c, Items,
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
}
