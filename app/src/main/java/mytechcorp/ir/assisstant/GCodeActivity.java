package mytechcorp.ir.assisstant;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Models.Coding;
import Models.CustomTypefaceSpan;
import Models.GCode;
import Models.Person;

public class GCodeActivity extends Dialog {

    Button btnSave;
    EditText txtGCode;

    Activity c;
    SQLiteDatabase db;
    DBHandler dbHandler;
    Typeface tf;
    private int ID;

    final int sdk = android.os.Build.VERSION.SDK_INT;


    public GCodeActivity(Activity a, int id) {
        super(a);
        this.c = a;
        this.ID = id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gcode);
        tf = Typeface.createFromAsset(c.getAssets() ,"fonts/IRANSansMobile_Light.ttf");
        txtGCode = findViewById(R.id.txtGCode);
        btnSave = findViewById(R.id.btnSave);
        dbHandler = new DBHandler(c);

        txtGCode.setTypeface(tf);
        btnSave.setTypeface(tf);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                SpannableStringBuilder ssbuilder = new SpannableStringBuilder("لطفا کد را وارد کنید");
                ssbuilder.setSpan(new CustomTypefaceSpan("",tf),0,ssbuilder.length(),Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

                if (!txtGCode.getText().toString().equals("")) {
                    if (ID ==1){
                        if(dbHandler.getCodeState(ID)){
                            Intent intent = new Intent(c, MainActivity.class);
                            c.startActivity(intent);
                            c.finish();
                        }
                        else {
                            dbHandler.AddGCode(
                                    new GCode(
                                            txtGCode.getText().toString()
                                    )
                            );
                            Intent intent = new Intent(c, MainActivity.class);
                            c.startActivity(intent);
                            c.finish();
                        }
                    }
                    else if (ID == 2){
                        Coding code = new Coding(c);

                        if(dbHandler.getCodeState(ID)){
                            if(code.CheckCode(dbHandler.GetGCode(ID),57)){
                                AlertDialog.Builder dialog = new AlertDialog.Builder(new ContextThemeWrapper(c, R.style.AlertDialogCustom)).setMessage(code.GetMatchTime()).setTitle("زمان مسابقه");
                                dialog.setNeutralButton("باشه",new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface,int i) {
                                        Intent intent = new Intent(c, DescriptionActivity.class);
                                        intent.putExtra("Game","7");
                                        c.startActivity(intent);
                                        c.finish();
                                    }
                                });
                                dialog.show();
                            }

                        }
                        else {
                            if(code.CheckCode(txtGCode.getText().toString(),57)) {
                                dbHandler.AddGCode(
                                        new GCode(
                                                txtGCode.getText().toString()
                                        )
                                );
                                AlertDialog.Builder dialog = new AlertDialog.Builder(new ContextThemeWrapper(c, R.style.AlertDialogCustom)).setMessage(code.GetMatchTime()).setTitle("زمان مسابقه");
                                dialog.setNeutralButton("باشه",new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface,int i) {
                                        Intent intent = new Intent(c, DescriptionActivity.class);
                                        intent.putExtra("Game","7");
                                        c.startActivity(intent);
                                        c.finish();
                                    }
                                });
                                dialog.show();
                            }
                            else {
                                AlertDialog.Builder dialog = new AlertDialog.Builder(new ContextThemeWrapper(c, R.style.AlertDialogCustom)).setMessage("کد وارد شده غلط می باشد").setTitle("خطا");
                                dialog.setNeutralButton("باشه",null);
                                dialog.show();
                            }
                        }
                    }

                    dismiss();
                    txtGCode.setText("");
                }
                else if (txtGCode.getText().toString().equals("")) {
                    txtGCode.setError(ssbuilder);
                    if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                        txtGCode.setBackgroundDrawable(ContextCompat.getDrawable(c,R.drawable.eterror));
                    } else {
                        txtGCode.setBackground(ContextCompat.getDrawable(c,R.drawable.eterror));
                    }
                }
            }
        });
    }
}
