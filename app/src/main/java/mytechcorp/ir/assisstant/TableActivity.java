package mytechcorp.ir.assisstant;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.chrisbanes.photoview.PhotoView;
import com.github.chrisbanes.photoview.PhotoViewAttacher;

public class TableActivity extends AppCompatActivity {

    PhotoView ivTable;
    Button btnEnter, btnSelect, btnSave, btnShowAnswers;
    EditText txtAnswer;

    String Game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        ivTable = findViewById(R.id.ivTable);
        PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(ivTable);
        photoViewAttacher.update();

        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/IRANSansMobile_Light.ttf");
        btnEnter = findViewById(R.id.btnEnter);
        btnSelect = findViewById(R.id.btnSelect);
        btnSave = findViewById(R.id.btnSave);
        btnShowAnswers = findViewById(R.id.btnShowAnswers);
        txtAnswer = findViewById(R.id.txtAnswer);

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

    public void setBtnEnterOnClickListener(View v){
        Intent intent = new Intent(this, CodeActivity.class);
        intent.putExtra("Game",Game);
        startActivity(intent);
        this.finish();
    }

    public void setBtnSelectOnClickListener(View v){
        Toast.makeText(this, "در حال حاضر سوالی وجود ندارد", Toast.LENGTH_LONG).show();
    }

    public void setBtnShowAnswersOnClickListener(View v){
        AnswerDialogActivity cdd = new AnswerDialogActivity(TableActivity.this);
        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cdd.show();
    }
}
