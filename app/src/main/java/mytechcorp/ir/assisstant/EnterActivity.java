package mytechcorp.ir.assisstant;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Models.CustomTypefaceSpan;
import Models.FirstRun;
import Models.Person;

public class EnterActivity extends Activity {

    Button btnEnter, btnSave;
    EditText txtName, txtFamily;
    ListView lvPerson;
    Spinner spLocal, spGrade;

    SQLiteDatabase db;
    DBHandler dbHandler;
    Typeface tf;

    final int sdk = android.os.Build.VERSION.SDK_INT;
    private static final String TABLE_States = "States";

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        tf = Typeface.createFromAsset(getAssets(),"fonts/IRANSansMobile_Light.ttf");

        dbHandler =new DBHandler(this);
        btnEnter = findViewById(R.id.btnEnter);
        btnSave = findViewById(R.id.btnSave);
        txtName = findViewById(R.id.txtName);
        txtFamily = findViewById(R.id.txtFamily);
        lvPerson = findViewById(R.id.lvPerson);
        spLocal = findViewById(R.id.spLocal);
        spGrade = findViewById(R.id.spGrade);
        btnEnter.setTypeface(tf);
        btnSave.setTypeface(tf);
        txtName.setTypeface(tf);
        txtFamily.setTypeface(tf);
        start();

        String[] local = new String[]{"1", "2", "3", "4", "5", "6", "7", "8",
                                    "9", "10", "11", "12", "13", "14", "15"
                                    , "16", "17" , "18", "19", "20", "21", "22"};
        ArrayAdapter<String> localadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, local);
        spLocal.setAdapter(localadapter);

        String[] Grade = new String[]{"پایه هشتم", "پایه نهم", "پایه دهم"};
        ArrayAdapter<String> Gradeadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Grade);
        spGrade.setAdapter(Gradeadapter);

    }

    public void setBtnEnterOnClickListener(View v) {
        if(!dbHandler.getCodeState(1)){
            checkGCode();
        }
        else {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void setBtnSaveOnClickListener(View v) {

        SpannableStringBuilder ssbuilder = new SpannableStringBuilder(getString(R.string.err_wrong_input));
        ssbuilder.setSpan(new CustomTypefaceSpan("", this.tf), 0, ssbuilder.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        if(!txtName.getText().toString().equals("") && !txtFamily.getText().toString().equals("")){
            dbHandler.AddPerson(
                    new Person(
                            txtName.getText().toString(),
                            txtFamily.getText().toString(),
                            Integer.parseInt(spLocal.getSelectedItem().toString()),
                            spGrade.getSelectedItem().toString()
                    )
            );
            txtName.setText("");
            txtFamily.setText("");
            loadData();
        }
        else if(txtName.getText().toString().equals("") && txtFamily.getText().toString().equals("")){
            txtName.setError(ssbuilder);
            txtFamily.setError(ssbuilder);
            if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                txtName.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.eterror) );
                txtFamily.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.eterror) );
            } else {
                txtName.setBackground(ContextCompat.getDrawable(this, R.drawable.eterror));
                txtFamily.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.eterror) );
            }
        }
        else if(txtName.getText().toString().equals("")){
            txtName.setError(ssbuilder);
            if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                txtName.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.eterror) );
            } else {
                txtName.setBackground(ContextCompat.getDrawable(this, R.drawable.eterror));
            }
        }

        else if(txtFamily.getText().toString().equals("")){
            txtFamily.setError(ssbuilder);
            if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                txtFamily.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.eterror) );
            } else {
                txtFamily.setBackground(ContextCompat.getDrawable(this, R.drawable.eterror));
            }
        }
    }

    private void start(){
        String query = "SELECT * FROM " + TABLE_States;
        db = dbHandler.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            //dbHandler.UpdateStates();
            if(dbHandler.GetPersonCount()>0 && dbHandler.getCodeState(1)){
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }

        }
        else {
            for (int i=1;i<11;i++){
                dbHandler.AddState();
            }
        }

        if (dbHandler.GetQuestionCount()==0)
        {
            FirstRun firstRun = new FirstRun();
            firstRun.AddQuestions(this);
        }
        btnEnter.setEnabled(false);
        loadData();
    }

    private void checkGCode(){
        if(dbHandler.getCodeState(1)){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        else {
            GCodeActivity gCodeActivity = new GCodeActivity(this,1);
            gCodeActivity.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            gCodeActivity.show();
        }
    }

    private void loadData(){
        db = dbHandler.getReadableDatabase();
        final ArrayList<HashMap<String, String>> Items = new ArrayList<HashMap<String, String>>();

        List<Person> personList = dbHandler.getAllPerson();
        for(Person person : personList){
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("id",String.valueOf(person.getPersonID()));
            map.put("name", person.getPersonName());
            map.put("family", person.getPersonFamily());
            map.put("grade", person.getGrade());
            Items.add(map);
        }

        if (Items.isEmpty()){
            btnEnter.setEnabled(false);
            String[] notfound = {"متأسفانه داده ای وجود ندارد.\n لطفا نام افراد گروه را اضافه کنید."};
            ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,R.layout.personnotfound,R.id.desctb2,notfound);
            lvPerson.setAdapter(adapter1);
        }
        else {
            btnEnter.setEnabled(true);
            ListAdapter adapter = new SimpleAdapter(this, Items,
                    R.layout.showpersoncard,
                    new String[]{
                            "id", "name", "family","grade"
                    },
                    new int[]{
                            R.id.lblID, R.id.lblpersonName, R.id.lblpersonFamily, R.id.lblpersonGrade
                    });

            lvPerson.setAdapter(adapter);
        }
    }
}
