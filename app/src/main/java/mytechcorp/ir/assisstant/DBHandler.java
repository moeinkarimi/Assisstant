package mytechcorp.ir.assisstant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import Models.Person;


public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "Assisstant";

    private static final String TABLE_States = "States";
    private static final String TABLE_Person = "Person";

    private static final String ID = "ID";
    private static final String Name = "Name";
    private static final String Family = "Family";
    private static final String State = "State";


    public DBHandler(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String dbexec = "CREATE TABLE IF NOT EXISTS "+ TABLE_States +"("
                + ID + " Integer Primary Key Autoincrement," //0
                + State + " Text);"; //1
        db.execSQL(dbexec);

        String dbexec1 = "CREATE TABLE IF NOT EXISTS "+ TABLE_Person +"("
                + ID + " Integer Primary Key Autoincrement," //0
                + Name + " Text,"
                + Family + " Text);"; //1
        db.execSQL(dbexec1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,int i,int i1) {

    }

    //Person
    public void AddPerson(Person person){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Name,person.getPersonName());
        values.put(Family,person.getPersonFamily());
        db.insert(TABLE_Person, null, values);
        db.close();
    }

    public List<Person> getAllPerson(){
        List<Person> personList = new ArrayList<Person>();
        String query = "SELECT * FROM " + TABLE_Person;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()) {
            do {
                Person person = new Person();
                person.setPersonID(Integer.parseInt(cursor.getString(0)));
                person.setPersonName(cursor.getString(1));
                person.setPersonFamily(cursor.getString(2));
                personList.add(person);
            }while (cursor.moveToNext());
        }
        return personList;
    }

    public int GetPersonCount(){
        String query = "SELECT * FROM " + TABLE_Person;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        return cursor.getCount();
    }

    //State
    public void AddState(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(State,"0");
        db.insert(TABLE_States, null, values);
        db.close();
    }

    public void UpdateStates(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(State, "0");
        for (int i=1;i<8;i++){
            db.update(TABLE_States,values,"ID =?",new String[] {String.valueOf(i)});
        }
        db.close();
    }

    public void UpdateState(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        Log.d("ID", String.valueOf(id));
        values.put(State, "1");
        db.update(TABLE_States,values,"ID =?",new String[] {String.valueOf(id)});
        db.close();
    }

    public String GetStateData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String game ="";
        String select_user_query = "SELECT * FROM " + TABLE_States + " WHERE id = " + id;
        Cursor cursor = db.rawQuery(select_user_query, null);
        if(cursor.moveToFirst()){
            game = cursor.getString(1);
        }else{
            game = "0";
        }
        return game;
    }

    public int getProfilesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_States;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }
}
