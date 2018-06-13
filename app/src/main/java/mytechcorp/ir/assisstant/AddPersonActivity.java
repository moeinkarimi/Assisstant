package mytechcorp.ir.assisstant;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Models.CustomTypefaceSpan;
import Models.Person;

public class AddPersonActivity extends Dialog {


    Button btnSave;
    EditText txtName, txtFamily;

    Activity c;
    SQLiteDatabase db;
    DBHandler dbHandler;
    Typeface tf;

    private int ID;

    final int sdk = android.os.Build.VERSION.SDK_INT;

    public AddPersonActivity(Activity a) {
        super(a);
        this.c = a;
    }

    public AddPersonActivity(Activity a, int id) {
        super(a);
        this.c = a;
        this.ID = id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);
        tf = Typeface.createFromAsset(c.getAssets(),"fonts/IRANSansMobile_Light.ttf");

        dbHandler = new DBHandler(c);
        btnSave = findViewById(R.id.btnSave);
        txtName = findViewById(R.id.txtName);
        txtFamily = findViewById(R.id.txtFamily);

        if(ID != 0) {
            Log.d("ID" , String.valueOf(ID));
            Person person = dbHandler.GetPerson(ID);
            txtName.setText(person.getPersonName());
            txtFamily.setText(person.getPersonFamily());
        }

        btnSave.setTypeface(tf);
        txtName.setTypeface(tf);
        txtFamily.setTypeface(tf);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = String.valueOf(ID);
                if(s == null) {
                    SpannableStringBuilder ssbuilder = new SpannableStringBuilder("مقدار درخواستی را وارد کنید");
                    ssbuilder.setSpan(new CustomTypefaceSpan("", tf), 0, ssbuilder.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

                    if (!txtName.getText().toString().equals("") && !txtFamily.getText().toString().equals("")) {
                        dbHandler.AddPerson(
                                new Person(
                                        txtName.getText().toString(),
                                        txtFamily.getText().toString()
                                )
                        );
                        txtName.setText("");
                        txtFamily.setText("");
                        dismiss();
                    } else if (txtName.getText().toString().equals("") && txtFamily.getText().toString().equals("")) {
                        txtName.setError(ssbuilder);
                        txtFamily.setError(ssbuilder);
                        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                            txtName.setBackgroundDrawable(ContextCompat.getDrawable(c, R.drawable.eterror));
                            txtFamily.setBackgroundDrawable(ContextCompat.getDrawable(c, R.drawable.eterror));
                        } else {
                            txtName.setBackground(ContextCompat.getDrawable(c, R.drawable.eterror));
                            txtFamily.setBackgroundDrawable(ContextCompat.getDrawable(c, R.drawable.eterror));
                        }
                    } else if (txtName.getText().toString().equals("")) {
                        txtName.setError(ssbuilder);
                        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                            txtName.setBackgroundDrawable(ContextCompat.getDrawable(c, R.drawable.eterror));
                        } else {
                            txtName.setBackground(ContextCompat.getDrawable(c, R.drawable.eterror));
                        }
                    } else if (txtFamily.getText().toString().equals("")) {
                        txtFamily.setError(ssbuilder);
                        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                            txtFamily.setBackgroundDrawable(ContextCompat.getDrawable(c, R.drawable.eterror));
                        } else {
                            txtFamily.setBackground(ContextCompat.getDrawable(c, R.drawable.eterror));
                        }
                    }
                }
                else {
                    SpannableStringBuilder ssbuilder = new SpannableStringBuilder("مقدار درخواستی را وارد کنید");
                    ssbuilder.setSpan(new CustomTypefaceSpan("", tf), 0, ssbuilder.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

                    if (!txtName.getText().toString().equals("") && !txtFamily.getText().toString().equals("")) {
                        dbHandler.UpdatePerson(
                                new Person(
                                        txtName.getText().toString(),
                                        txtFamily.getText().toString()
                                ), ID
                        );

                        txtName.setText("");
                        txtFamily.setText("");
                        dismiss();
                    } else if (txtName.getText().toString().equals("") && txtFamily.getText().toString().equals("")) {
                        txtName.setError(ssbuilder);
                        txtFamily.setError(ssbuilder);
                        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                            txtName.setBackgroundDrawable(ContextCompat.getDrawable(c, R.drawable.eterror));
                            txtFamily.setBackgroundDrawable(ContextCompat.getDrawable(c, R.drawable.eterror));
                        } else {
                            txtName.setBackground(ContextCompat.getDrawable(c, R.drawable.eterror));
                            txtFamily.setBackgroundDrawable(ContextCompat.getDrawable(c, R.drawable.eterror));
                        }
                    } else if (txtName.getText().toString().equals("")) {
                        txtName.setError(ssbuilder);
                        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                            txtName.setBackgroundDrawable(ContextCompat.getDrawable(c, R.drawable.eterror));
                        } else {
                            txtName.setBackground(ContextCompat.getDrawable(c, R.drawable.eterror));
                        }
                    } else if (txtFamily.getText().toString().equals("")) {
                        txtFamily.setError(ssbuilder);
                        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                            txtFamily.setBackgroundDrawable(ContextCompat.getDrawable(c, R.drawable.eterror));
                        } else {
                            txtFamily.setBackground(ContextCompat.getDrawable(c, R.drawable.eterror));
                        }
                    }
                }
            }
        });
    }


}
