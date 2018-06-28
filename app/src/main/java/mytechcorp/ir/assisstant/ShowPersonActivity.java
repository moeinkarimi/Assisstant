package mytechcorp.ir.assisstant;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Models.Person;

public class ShowPersonActivity extends Activity {

    GridView lvPerson;
    SQLiteDatabase db;
    DBHandler dbHandler;
    Button btnAddPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_person);

        dbHandler =new DBHandler(this);
        btnAddPerson = findViewById(R.id.btnAddPerson);
        lvPerson = findViewById(R.id.lvPerson);
        loadData();

        btnAddPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddPersonActivity apa = new AddPersonActivity(ShowPersonActivity.this, 0);
                apa.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                apa.show();
                apa.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        loadData();
                    }
                });


            }
        });

    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        MainActivity.fa.finish();
        finish();
    }

    private void loadData() {
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
            String[] notfound = {"متأسفانه داده ای وجود ندارد.\n لطفا نام افراد گروه را اضافه کنید."};
            ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,R.layout.personnotfound,R.id.desctb2,notfound);
            lvPerson.setAdapter(adapter1);
        }
        else {
            ListAdapter adapter = new SimpleAdapter(this, Items,
                    R.layout.showpersoncard,
                    new String[]{
                            "id", "name", "family","grade"
                    },
                    new int[]{
                            R.id.lblID, R.id.lblpersonName, R.id.lblpersonFamily, R.id.lblpersonGrade
                    });

            lvPerson.setAdapter(adapter);
            lvPerson.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(final AdapterView<?> parent,final View view,int position,long id) {

                    final String options[] = new String[] {"ویرایش", "حذف"};

                    AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(ShowPersonActivity.this, R.style.AlertDialogCustom));
                    builder.setTitle("انتخاب کنید :");

                    builder.setItems(options, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            int id = Integer.parseInt(((TextView)view.findViewById(R.id.lblID)).getText().toString());
                            switch (options[which]){
                                case "ویرایش":
                                    AddPersonActivity apa = new AddPersonActivity(ShowPersonActivity.this, id);
                                    apa.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                    apa.show();
                                    apa.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                        @Override
                                        public void onDismiss(DialogInterface dialogInterface) {
                                            loadData();
                                        }
                                    });
                                    break;
                                case "حذف":
                                    dbHandler.DeletePerson(id);
                                    loadData();
                                    break;
                            }
                        }
                    });
                    builder.show();
                }
            });

        }
    }
}
