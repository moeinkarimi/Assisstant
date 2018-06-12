package mytechcorp.ir.assisstant;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

    final int sdk = android.os.Build.VERSION.SDK_INT;


    public GCodeActivity(Activity a) {
        super(a);
        this.c = a;
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


                SpannableStringBuilder ssbuilder = new SpannableStringBuilder("مقدار درخواستی را وارد کنید");
                ssbuilder.setSpan(new CustomTypefaceSpan("",tf),0,ssbuilder.length(),Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

                if (!txtGCode.getText().toString().equals("")) {
                    dbHandler.AddGCode(
                            new GCode(
                                    txtGCode.getText().toString()
                            )
                    );

                    txtGCode.setText("");
                    if(dbHandler.getCodeState()){
                        Intent intent = new Intent(c, MainActivity.class);
                        c.startActivity(intent);
                        c.finish();
                    }
                    dismiss();

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
