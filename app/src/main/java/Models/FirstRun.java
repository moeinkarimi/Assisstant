package Models;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;

import mytechcorp.ir.assisstant.DBHandler;
import mytechcorp.ir.assisstant.R;

public class FirstRun {

    SQLiteDatabase db;
    DBHandler dbHandler;

    public void AddQuestions(Activity a){
        dbHandler =new DBHandler(a);
        //Add Table Questions
        dbHandler.AddQuestion(new Questions(a.getString(R.string.s1),1,1,false));
        dbHandler.AddQuestion(new Questions(a.getString(R.string.s2),1,2,false));
        dbHandler.AddQuestion(new Questions(a.getString(R.string.s3),1,3,false));
        dbHandler.AddQuestion(new Questions(a.getString(R.string.s4),1,4,false));
        dbHandler.AddQuestion(new Questions(a.getString(R.string.s5),1,5,false));
        dbHandler.AddQuestion(new Questions(a.getString(R.string.s6),1,6,false));
        dbHandler.AddQuestion(new Questions(a.getString(R.string.s7),1,7,false));
        dbHandler.AddQuestion(new Questions(a.getString(R.string.s8),1,8,false));
        dbHandler.AddQuestion(new Questions(a.getString(R.string.s9),1,9,false));
        dbHandler.AddQuestion(new Questions(a.getString(R.string.s10),1,10,false));
        dbHandler.AddQuestion(new Questions(a.getString(R.string.s11),1,11,false));
        dbHandler.AddQuestion(new Questions(a.getString(R.string.s12),1,12,false));
        dbHandler.AddQuestion(new Questions(a.getString(R.string.s13),1,13,false));
        dbHandler.AddQuestion(new Questions(a.getString(R.string.s14),1,14,false));
        dbHandler.AddQuestion(new Questions(a.getString(R.string.s15),1,15,false));
//        dbHandler.AddQuestion(new Questions(a.getString(R.string.s16),1,16,false));
//        dbHandler.AddQuestion(new Questions(a.getString(R.string.s17),1,17,false));
//        dbHandler.AddQuestion(new Questions(a.getString(R.string.s18),1,18,false));
//        dbHandler.AddQuestion(new Questions(a.getString(R.string.s19),1,19,false));
//        dbHandler.AddQuestion(new Questions(a.getString(R.string.s20),1,20,false));
//        dbHandler.AddQuestion(new Questions(a.getString(R.string.s21),1,21,false));
//        dbHandler.AddQuestion(new Questions(a.getString(R.string.s22),1,22,false));

        //Add Reading Questions
        dbHandler.AddQuestion(new Questions(a.getString(R.string.sr1),2,1,false));
        dbHandler.AddQuestion(new Questions(a.getString(R.string.sr2),2,2,false));
        dbHandler.AddQuestion(new Questions(a.getString(R.string.sr3),2,3,false));

        //Add GI Questions
        dbHandler.AddQuestion(new Questions(a.getString(R.string.gis1),9,1,false));
        dbHandler.AddQuestion(new Questions(a.getString(R.string.gis2),9,2,false));
        dbHandler.AddQuestion(new Questions(a.getString(R.string.gis3),9,3,false));
        dbHandler.AddQuestion(new Questions(a.getString(R.string.gis4),9,4,false));
        dbHandler.AddQuestion(new Questions(a.getString(R.string.gis5),9,5,false));
        dbHandler.AddQuestion(new Questions(a.getString(R.string.gis6),9,6,false));
        dbHandler.AddQuestion(new Questions(a.getString(R.string.gis7),9,7,false));
        dbHandler.AddQuestion(new Questions(a.getString(R.string.gis8),9,8,false));
        dbHandler.AddQuestion(new Questions(a.getString(R.string.gis9),9,9,false));
        dbHandler.AddQuestion(new Questions(a.getString(R.string.gis10),9,10,false));
    }

}
