package mytechcorp.ir.assisstant;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.github.chrisbanes.photoview.PhotoView;
import com.github.chrisbanes.photoview.PhotoViewAttacher;

import Models.Coding;

public class DescriptionActivity extends Activity {

    PhotoView ivTable2;
    Button btnEnter;
    TextViewPlus tvDesc, tvHeader;
    String Game;
    ImageView ivPuzzle;
    ScrollView scroll1;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/IRANSansMobile_Light.ttf");
        btnEnter = findViewById(R.id.btnEnter);
        tvDesc = findViewById(R.id.tvDesc);
        tvHeader = findViewById(R.id.tvHeader);
        dbHandler = new DBHandler(this);
        ivTable2 = findViewById(R.id.ivTable2);
        ivPuzzle = findViewById(R.id.ivPuzzle);
        scroll1 = findViewById(R.id.scroll1);


        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
        {
            Game = bundle.getString("Game");
        }
        btnEnter.setTypeface(tf);

        switch (Game){
            case "4":
                if(dbHandler.GetScoreState(4)){
                    tvHeader.setText(R.string.puzzle);
                    scroll1.setVisibility(View.GONE);
                    ivPuzzle.setVisibility(View.VISIBLE);
                }else {
                    scroll1.setVisibility(View.VISIBLE);
                    ivPuzzle.setVisibility(View.GONE);
                    tvHeader.setText(R.string.puzzle);
                    tvDesc.setText(R.string.puzzleDesc);
                }
                break;
            case "5":

                tvHeader.setText(R.string.maket);
                tvDesc.setText(R.string.maketDesc);
                break;
            case "6":
                tvHeader.setText(R.string.record);
                tvDesc.setText(R.string.recordDesc);
                break;
            case "7":
                tvHeader.setText(R.string.bazigroohi);
                tvDesc.setText(getString(R.string.bazigroohiDesc));
                PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(ivTable2);
                photoViewAttacher.update();
                ivTable2.setVisibility(View.VISIBLE);
                break;
            case "8":
                tvHeader.setText(R.string.mostand);
                tvDesc.setText(R.string.mostanadDesc);
                break;
        }


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        MainActivity.fa.finish();
        finish();
    }

    public void setBtnEnterOnClickListener(View v){
        /*Intent intent = new Intent(this, CodeActivity.class);
        intent.putExtra("Game",Game);
        startActivity(intent);
        this.finish();*/
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        MainActivity.fa.finish();
        finish();
    }
}
