package mytechcorp.ir.assisstant;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class DescriptionActivity extends AppCompatActivity {
    Button btnEnter;

    String Game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/IRANSansMobile_Light.ttf");
        btnEnter = findViewById(R.id.btnEnter);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
        {
            Game = bundle.getString("Game");
            Log.d("Game Value ", Game);
        }
        btnEnter.setTypeface(tf);
    }
    public void setBtnEnterOnClickListener(View v){
        Intent intent = new Intent(this, CodeActivity.class);
        intent.putExtra("Game",Game);
        startActivity(intent);
        this.finish();
    }
}
