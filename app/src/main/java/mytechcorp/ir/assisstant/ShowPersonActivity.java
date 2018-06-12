package mytechcorp.ir.assisstant;

import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Models.Person;

public class ShowPersonActivity extends AppCompatActivity {

    ListView lvPerson;
    SQLiteDatabase db;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_person);

        dbHandler =new DBHandler(this);
        lvPerson = findViewById(R.id.lvPerson);
        loadData();
    }

    private void loadData() {
        db = dbHandler.getReadableDatabase();
        final ArrayList<HashMap<String, String>> Items = new ArrayList<HashMap<String, String>>();

        List<Person> personList = dbHandler.getAllPerson();
        for (Person person : personList) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("id",String.valueOf(person.getPersonID()));
            map.put("name",person.getPersonName());
            map.put("family",person.getPersonFamily());
            Items.add(map);
        }

        if (Items.isEmpty()) {
            String[] notfound = {"متأسفانه داده ای وجود ندارد.\n لطفا نام افراد گروه را اضافه کنید."};
            ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,R.layout.personnotfound,R.id.desctb2,notfound);
            lvPerson.setAdapter(adapter1);
        } else {
            ListAdapter adapter = new SimpleAdapter(this,Items,
                    R.layout.showpersoncard,
                    new String[]{
                            "id","name","family"
                    },
                    new int[]{
                            R.id.lblID,R.id.lblpersonName,R.id.lblpersonFamily
                    });

            lvPerson.setAdapter(adapter);
            lvPerson.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(final AdapterView<?> parent,final View view,int position,long id) {

                    final String options[] = new String[] {"ویرایش", "حذف"};

                    AlertDialog.Builder builder = new AlertDialog.Builder(ShowPersonActivity.this);
                    builder.setTitle("انتخاب کنید :");

                    builder.setItems(options, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (options[which]){
                                case "ویرایش":
                                    Toast.makeText(ShowPersonActivity.this, "ویرایش", Toast.LENGTH_LONG).show();
                                    break;
                                case "حذف":
                                    Toast.makeText(ShowPersonActivity.this, "حذف", Toast.LENGTH_LONG).show();
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
