package mytechcorp.ir.assisstant;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.github.chrisbanes.photoview.PhotoView;
import com.github.chrisbanes.photoview.PhotoViewAttacher;

import Models.Coding;

public class DescriptionActivity extends Activity {

    Button btnEnter;
    TextViewPlus tvDesc, tvHeader, tvLink;
    String Game;
    ScrollView scroll1;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/IRANSansMobile_Light.ttf");
        btnEnter = findViewById(R.id.btnEnter1);
        tvDesc = findViewById(R.id.tvDesc);
        tvHeader = findViewById(R.id.tvHeader);
        dbHandler = new DBHandler(this);
        scroll1 = findViewById(R.id.scroll1);
        tvLink = findViewById(R.id.tvLink);


        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
        {
            Game = bundle.getString("Game");
        }
        btnEnter.setTypeface(tf);
        Log.d("Game", Game);
        switch (Game){
            case "4":
                tvHeader.setText(R.string.puzzle);
                tvDesc.setText(R.string.puzzleDesc);
                tvLink.setText(R.string.PuzzleLink);
                break;
            case "5":

                tvHeader.setText(R.string.maket);
                tvDesc.setText(R.string.maketDesc);
                tvLink.setText(R.string.MakeLink);
                break;
            case "6":
                tvHeader.setText(R.string.record);
                tvDesc.setText(R.string.recordDesc);
                tvLink.setText(R.string.CookingLink);
                break;
            case "7":
                tvHeader.setText(R.string.bazigroohi);
                tvDesc.setText(getString(R.string.bazigroohiDesc));
                tvLink.setText(R.string.FamilyGameLink);
                break;
            case "8":
                tvHeader.setText(R.string.mostand);
                tvDesc.setText(R.string.mostanadDesc);
                tvLink.setText(R.string.ChallengeLink);
                break;
        }
        tvLink.setMovementMethod(LinkMovementMethod.getInstance());

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        MainActivity.fa.finish();
        finish();
    }

    public void setBtnEnterOnClickListener(View v) {
        if (Game.equals("4")) {
            Intent intent = new Intent(this, CodeActivity.class);
            intent.putExtra("Game", Game);
            startActivity(intent);
            this.finish();
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            MainActivity.fa.finish();
            finish();
        }
    }
}
