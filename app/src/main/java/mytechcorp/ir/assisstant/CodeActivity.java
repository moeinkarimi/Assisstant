package mytechcorp.ir.assisstant;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class CodeActivity extends AppCompatActivity {

    Button btnSave;
    String Game;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/IRANSansMobile_Light.ttf");
        btnSave = findViewById(R.id.btnSave);

        dbHandler = new DBHandler(this);

        btnSave.setTypeface(tf);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
        {
            Game = String.valueOf(bundle.getString("Game"));
        }
        Log.d("Game Value", Game);
    }

    public void setBtnSaveOnClickListener(View v) {

        Intent intent = new Intent(this, MainActivity.class);
        if (Game.equals("1")){
            dbHandler.UpdateState(Integer.parseInt(Game));
            startActivity(intent);
            MainActivity.fa.finish();
            this.finish();
        }
        else if (Game.equals("2")){
            dbHandler.UpdateState(Integer.parseInt(Game));
            startActivity(intent);
            MainActivity.fa.finish();
            this.finish();
        }
        else if (Game.equals("3")){
            dbHandler.UpdateState(Integer.parseInt(Game));
            startActivity(intent);
            MainActivity.fa.finish();
            this.finish();
        }
        else if (Game.equals("4")){
            dbHandler.UpdateState(Integer.parseInt(Game));
            startActivity(intent);
            MainActivity.fa.finish();
            this.finish();
        }
        else if (Game.equals("5")){
            dbHandler.UpdateState(Integer.parseInt(Game));
            startActivity(intent);
            MainActivity.fa.finish();
            this.finish();
        }
        else if (Game.equals("6")){
            dbHandler.UpdateState(Integer.parseInt(Game));
            startActivity(intent);
            MainActivity.fa.finish();
            this.finish();
        }
        else if (Game.equals("7")){
            dbHandler.UpdateState(Integer.parseInt(Game));
            startActivity(intent);
            MainActivity.fa.finish();
            this.finish();
        }
    }
}
