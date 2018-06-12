package mytechcorp.ir.assisstant;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Models.Person;

public class EnterActivity extends AppCompatActivity {

    Button btnEnter, btnSave;
    EditText txtName, txtFamily;
    ListView lvPerson;

    SQLiteDatabase db;
    DBHandler dbHandler;

    private static final String TABLE_States = "States";

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/IRANSansMobile_Light.ttf");

        dbHandler =new DBHandler(this);
        btnEnter = findViewById(R.id.btnEnter);
        btnSave = findViewById(R.id.btnSave);
        txtName = findViewById(R.id.txtName);
        txtFamily = findViewById(R.id.txtFamily);
        lvPerson = findViewById(R.id.lvPerson);

        btnEnter.setTypeface(tf);
        btnSave.setTypeface(tf);
        txtName.setTypeface(tf);
        txtFamily.setTypeface(tf);
        start();

    }

    public void setBtnEnterOnClickListener(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void setBtnSaveOnClickListener(View v) {
        if(!txtName.getText().toString().equals("") && !txtFamily.getText().toString().equals("")){
            dbHandler.AddPerson(
                    new Person(
                            txtName.getText().toString(),
                            txtFamily.getText().toString()
                    )
            );
            txtName.setText("");
            txtFamily.setText("");
            loadData();
        }
        else{
            Toast.makeText(this, "لطفا نام و نام خانوادگی را وارد کنید", Toast.LENGTH_LONG).show();
        }
    }

    private void start(){
        String query = "SELECT * FROM " + TABLE_States;
        db = dbHandler.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            //dbHandler.UpdateStates();
        } else {
            for (int i=1;i<8;i++){
                dbHandler.AddState();
            }
        }
        btnEnter.setEnabled(false);
        loadData();
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
                            "id", "name", "family"
                    },
                    new int[]{
                            R.id.lblID, R.id.lblpersonName, R.id.lblpersonFamily
                    });

            lvPerson.setAdapter(adapter);
        }
    }
}
