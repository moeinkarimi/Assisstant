package mytechcorp.ir.assisstant;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Models.Coding;
import Models.Scores;

public class CodeActivity extends Activity {

    Button btnSave;
    String Game;
    EditText txtCode, txtCode2;
    TextViewPlus tvpCode;
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
        tvpCode = findViewById(R.id.tvpCodec);

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
                    txtCode2.setText(this.getString(R.string.TableCode));
                    btnSave.setText("ورود به صفحه اصلی");
                }
            }
            else if (Game.equals("4")) {
                txtCode.setVisibility(View.GONE);
                txtCode2.setVisibility(View.VISIBLE);
                if(dbHandler.GetScoreState(4)){
                    txtCode2.setText(this.getString(R.string.PuzzleCode));
                    btnSave.setText("ورود به صفحه اصلی");
                }
            }
        }

        if (Game.equals("1")){
            tvpCode.setText("لطفا رمز جدول را وارد کنید :");
        }
        else if (Game.equals("4")){
            tvpCode.setText("لطفا رمز پازل را وارد کنید :");
        }
        else if (Game.equals("5")){
            tvpCode.setText("لطفا رمز ماکت را وارد کنید :");
        }
        else if (Game.equals("6")){
            tvpCode.setText("لطفا رمز رکورد را وارد کنید :");
        }
        else if (Game.equals("7")){
            tvpCode.setText("لطفا رمز بازی گروهی را وارد کنید :");
        }
        else if (Game.equals("8")) {
            tvpCode.setText("لطفا رمز مستند را وارد کنید :");
        }
    }

    public void setBtnSaveOnClickListener(View v) {

        Intent intent = new Intent(this, MainActivity.class);
        if (btnSave.getText().toString().equals("ورود به صفحه اصلی")){
            startActivity(intent);
            MainActivity.fa.finish();
            ca.finish();
        }
        else {
            if (Game.equals("1")) {
                tvpCode.setText("لطفا رمز جدول را وارد کنید :");
                txtCode.setVisibility(View.GONE);
                txtCode2.setVisibility(View.VISIBLE);
                if (txtCode2.getText().toString().replace(" ", "").equals(this.getString(R.string.TableCode).replace(" ", ""))) {
                    if (!dbHandler.GetScoreState(1)) {
                        dbHandler.AddScore(
                                new Scores(
                                        1,
                                        10,
                                        0
                                )
                        );

                    }
                    dbHandler.UpdateState(Integer.parseInt(Game));
                /*AlertDialog.Builder dialog = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom)).setMessage("1-\tس ا ت ق").setTitle("حروف رمز");
                dialog.setNeutralButton("باشه",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface,int i) {*/
                        Intent intent1 = new Intent(CodeActivity.this, MainActivity.class);
                        startActivity(intent1);
                        MainActivity.fa.finish();
                        ca.finish();
                 /*   }
                });
                dialog.show();*/

                } else {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom)).setMessage("لطفا پاسخ صحیح را وارد نمایید").setTitle("خطا").setIcon(R.mipmap.ic_close_web);
                    dialog.setNeutralButton("باشه", null);
                    dialog.show();
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
            else if (Game.equals("4")) {
                tvpCode.setText("لطفا رمز پازل را وارد کنید :");
                txtCode.setVisibility(View.GONE);
                txtCode2.setVisibility(View.VISIBLE);
                if (txtCode2.getText().toString().replace(" ", "").equals(this.getString(R.string.PuzzleCode).replace(" ", ""))) {
                    if (!dbHandler.GetScoreState(4)) {
                        dbHandler.AddScore(
                                new Scores(
                                        4,
                                        30,
                                        40
                                )
                        );
                    }
                    dbHandler.UpdateState(Integer.parseInt(Game));
                    Intent intent1 = new Intent(CodeActivity.this, MainActivity.class);
                    startActivity(intent1);
                    MainActivity.fa.finish();
                    ca.finish();
                } else {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom)).setMessage("لطفا پاسخ صحیح را وارد نمایید").setTitle("خطا").setIcon(R.mipmap.ic_close_web);
                    dialog.setNeutralButton("باشه", null);
                    dialog.show();
                }
            }

        /*else if (Game.equals("5")){
            Coding coding = new Coding(this);
            tvpCode.setText("لطفا رمز ماکت را وارد کنید :");
            if(!txtCode.getText().toString().equals("")) {
                if (coding.CheckCode(txtCode.getText().toString(),76)) {
                    if (!dbHandler.GetScoreState(5)) {
                        dbHandler.AddScore(
                                new Scores(
                                        5,
                                        Integer.parseInt(coding.ConvertCodeToScore()),
                                        50
                                )
                        );
                    }
                    dbHandler.UpdateState(Integer.parseInt(Game));
                    AlertDialog.Builder dialog = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom)).setMessage("5-\tه ج م").setTitle("حروف رمز");
                    dialog.setNeutralButton("باشه",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface,int i) {
                            Intent intent = new Intent(CodeActivity.this,MainActivity.class);
                            startActivity(intent);
                            MainActivity.fa.finish();
                            ca.finish();
                        }
                    });
                    dialog.show();
                } else {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom)).setMessage("کد وارد شده غلط می باشد").setTitle("خطا").setIcon(R.mipmap.ic_close_web);
                    dialog.setNeutralButton("باشه",null);
                    dialog.show();
                }
            }
            else {
                AlertDialog.Builder dialog = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom)).setMessage("لطفا کد را وارد کنید").setTitle("خطا").setIcon(R.mipmap.ic_close_web);
                dialog.setNeutralButton("باشه", null);
                dialog.show();
            }

        }
        else if (Game.equals("6")){
            Coding coding = new Coding(this);
            tvpCode.setText("لطفا رمز رکورد را وارد کنید :");
            if(!txtCode.getText().toString().equals("")) {
                if (coding.CheckCode(txtCode.getText().toString(),42)) {
                    if (!dbHandler.GetScoreState(6)) {
                        dbHandler.AddScore(
                                new Scores(
                                        6,
                                        Integer.parseInt(coding.ConvertCodeToScore()),
                                        60
                                )
                        );
                    }
                    dbHandler.UpdateState(Integer.parseInt(Game));
                    AlertDialog.Builder dialog = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom)).setMessage("6-\tر و ی").setTitle("حروف رمز");
                    dialog.setNeutralButton("باشه",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface,int i) {
                            Intent intent = new Intent(CodeActivity.this,MainActivity.class);
                            startActivity(intent);
                            MainActivity.fa.finish();
                            ca.finish();
                        }
                    });
                    dialog.show();
                } else {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom)).setMessage("کد وارد شده غلط می باشد").setTitle("خطا").setIcon(R.mipmap.ic_close_web);
                    dialog.setNeutralButton("باشه", null);
                    dialog.show();
                }
            }else {
                AlertDialog.Builder dialog = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom)).setMessage("لطفا کد را وارد کنید").setTitle("خطا").setIcon(R.mipmap.ic_close_web);
                dialog.setNeutralButton("باشه", null);
                dialog.show();
            }

        }
        else if (Game.equals("7")){
            Coding coding = new Coding(this);
            tvpCode.setText("لطفا رمز بازی گروهی را وارد کنید :");
            if(!txtCode.getText().toString().equals("")) {
                if (coding.CheckCode(txtCode.getText().toString(),57)) {
                    if (!dbHandler.GetScoreState(7)) {
                        dbHandler.AddScore(
                                new Scores(
                                        7,
                                        Integer.parseInt(coding.ConvertCodeToScore()),
                                        70
                                )
                        );
                    }
                    dbHandler.UpdateState(Integer.parseInt(Game));
                    AlertDialog.Builder dialog = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom)).setMessage("1-\tل س ا ").setTitle("حروف رمز");
                    dialog.setNeutralButton("باشه",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface,int i) {
                            Intent intent = new Intent(CodeActivity.this,MainActivity.class);
                            startActivity(intent);
                            MainActivity.fa.finish();
                            ca.finish();
                        }
                    });
                    dialog.show();
                } else {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom)).setMessage("کد وارد شده غلط می باشد").setTitle("خطا").setIcon(R.mipmap.ic_close_web);
                    dialog.setNeutralButton("باشه",null);
                    dialog.show();
                }
            }else {
                AlertDialog.Builder dialog = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom)).setMessage("لطفا کد را وارد کنید").setTitle("خطا").setIcon(R.mipmap.ic_close_web);
                dialog.setNeutralButton("باشه", null);
                dialog.show();
            }
        }
        else if (Game.equals("8")){
            Coding coding = new Coding(this);
            tvpCode.setText("لطفا رمز مستند را وارد کنید :");
            if(!txtCode.getText().toString().equals("")) {
                if (coding.CheckCode(txtCode.getText().toString(),18)) {
                    if (!dbHandler.GetScoreState(8)) {
                        dbHandler.AddScore(
                                new Scores(
                                        8,
                                        Integer.parseInt(coding.ConvertCodeToScore()),
                                        80
                                )
                        );
                    }
                    dbHandler.UpdateState(Integer.parseInt(Game));
                    AlertDialog.Builder dialog = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom)).setMessage("8-\tم ا ی").setTitle("حروف رمز");
                    dialog.setNeutralButton("باشه",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface,int i) {
                            Intent intent = new Intent(CodeActivity.this,MainActivity.class);
                            startActivity(intent);
                            MainActivity.fa.finish();
                            ca.finish();
                        }
                    });
                    dialog.show();
                } else {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom)).setMessage("کد وارد شده غلط می باشد").setTitle("خطا").setIcon(R.mipmap.ic_close_web);
                    dialog.setNeutralButton("باشه",null);
                    dialog.show();
                }
            }else {
                AlertDialog.Builder dialog = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom)).setMessage("لطفا کد را وارد کنید").setTitle("خطا").setIcon(R.mipmap.ic_close_web);
                dialog.setNeutralButton("باشه", null);
                dialog.show();
            }
        }*/
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
