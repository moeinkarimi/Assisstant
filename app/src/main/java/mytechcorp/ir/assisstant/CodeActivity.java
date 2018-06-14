package mytechcorp.ir.assisstant;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import org.w3c.dom.Text;

import Models.Scores;

public class CodeActivity extends AppCompatActivity {

    Button btnSave;
    String Game;
    EditText txtCode, txtCode2;
    DBHandler dbHandler;
    public static Activity ca;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/IRANSansMobile_Light.ttf");
        btnSave = findViewById(R.id.btnSave);
        txtCode = findViewById(R.id.txtCode);
        txtCode2 = findViewById(R.id.txtCode2);
        ca = this;
        dbHandler = new DBHandler(this);

        btnSave.setTypeface(tf);
        txtCode.setTypeface(tf);
        txtCode2.setTypeface(tf);
        txtCode.setVisibility(View.VISIBLE);
        txtCode2.setVisibility(View.GONE);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
        {
            Game = String.valueOf(bundle.getString("Game"));
            if (Game.equals("1")){
                txtCode.setVisibility(View.GONE);
                txtCode2.setVisibility(View.VISIBLE);
                if(dbHandler.GetScoreState(1)){
                    txtCode2.setText("راه نجات جوامع");
                }
            }
            else if (Game.equals("4")) {
                txtCode.setVisibility(View.GONE);
                txtCode2.setVisibility(View.VISIBLE);
                if(dbHandler.GetScoreState(4)){
                    txtCode2.setText("تولید ملی");
                }
            }
        }

    }

    public void setBtnSaveOnClickListener(View v) {

        Intent intent = new Intent(this, MainActivity.class);
        if (Game.equals("1")){
            txtCode.setVisibility(View.GONE);
            txtCode2.setVisibility(View.VISIBLE);
            if(txtCode2.getText().toString().equals("راه نجات جوامع")){
                if (!dbHandler.GetScoreState(1)){
                    dbHandler.AddScore(
                            new Scores(
                                    1,
                                    10,
                                    0
                            )
                    );

                }
                dbHandler.UpdateState(Integer.parseInt(Game));
                AlertDialog.Builder dialog = new AlertDialog.Builder(this).setMessage("1- ف").setTitle("حروف رمز");
                dialog.setNeutralButton("باشه",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface,int i) {
                        Intent intent = new Intent(CodeActivity.this, MainActivity.class);
                        startActivity(intent);
                        MainActivity.fa.finish();
                        ca.finish();
                    }
                });
                dialog.show();

            }
            else {

                dbHandler.UpdateState(Integer.parseInt(Game));
                startActivity(intent);
                MainActivity.fa.finish();
                this.finish();
            }
        }
//        else if (Game.equals("2")){
//            dbHandler.UpdateState(Integer.parseInt(Game));
//            startActivity(intent);
//            MainActivity.fa.finish();
//            this.finish();
//        }
//        else if (Game.equals("3")){
//            dbHandler.UpdateState(Integer.parseInt(Game));
//            startActivity(intent);
//            MainActivity.fa.finish();
//            this.finish();
//        }
        else if (Game.equals("4")){
            txtCode.setVisibility(View.GONE);
            txtCode2.setVisibility(View.VISIBLE);
            if(txtCode2.getText().toString().equals("تولیدملی")
                    ||txtCode2.getText().toString().equals("تولید ملی")){
                if (!dbHandler.GetScoreState(4)){
                    dbHandler.AddScore(
                            new Scores(
                                    4,
                                    20,
                                    40
                            )
                    );
                }
                AlertDialog.Builder dialog = new AlertDialog.Builder(this).setMessage("4-\tز ا").setTitle("حروف رمز");
                dialog.setNeutralButton("باشه",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface,int i) {
                        Intent intent = new Intent(CodeActivity.this, MainActivity.class);
                        startActivity(intent);
                        MainActivity.fa.finish();
                        ca.finish();
                    }
                });
                dialog.show();
            }
            else {

                dbHandler.UpdateState(Integer.parseInt(Game));
                startActivity(intent);
                MainActivity.fa.finish();
                this.finish();
            }
        }
        else if (Game.equals("5")){
            dbHandler.UpdateState(Integer.parseInt(Game));
            AlertDialog.Builder dialog = new AlertDialog.Builder(this).setMessage("5-\tا").setTitle("حروف رمز");
            dialog.setNeutralButton("باشه",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface,int i) {
                    Intent intent = new Intent(CodeActivity.this, MainActivity.class);
                    startActivity(intent);
                    MainActivity.fa.finish();
                    ca.finish();
                }
            });
            dialog.show();
        }
        else if (Game.equals("6")){
            dbHandler.UpdateState(Integer.parseInt(Game));
            AlertDialog.Builder dialog = new AlertDialog.Builder(this).setMessage("6-\tن").setTitle("حروف رمز");
            dialog.setNeutralButton("باشه",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface,int i) {
                    Intent intent = new Intent(CodeActivity.this, MainActivity.class);
                    startActivity(intent);
                    MainActivity.fa.finish();
                    ca.finish();
                }
            });
            dialog.show();
        }
        else if (Game.equals("7")){
            dbHandler.UpdateState(Integer.parseInt(Game));
            AlertDialog.Builder dialog = new AlertDialog.Builder(this).setMessage("7-\tا م").setTitle("حروف رمز");
            dialog.setNeutralButton("باشه",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface,int i) {
                    Intent intent = new Intent(CodeActivity.this, MainActivity.class);
                    startActivity(intent);
                    MainActivity.fa.finish();
                    ca.finish();
                }
            });
            dialog.show();
        }
        else if (Game.equals("8")){
            dbHandler.UpdateState(Integer.parseInt(Game));
            AlertDialog.Builder dialog = new AlertDialog.Builder(this).setMessage("8-\tت س").setTitle("حروف رمز");
            dialog.setNeutralButton("باشه",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface,int i) {
                    Intent intent = new Intent(CodeActivity.this, MainActivity.class);
                    startActivity(intent);
                    MainActivity.fa.finish();
                    ca.finish();
                }
            });
            dialog.show();
        }
    }
}
