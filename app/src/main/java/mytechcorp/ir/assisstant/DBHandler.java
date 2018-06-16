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

import Models.Answers;
import Models.GCode;
import Models.Person;
import Models.Questions;
import Models.Scores;
import Models.ShowAnswer;


public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "Assisstant";

    private static final String TABLE_States = "States";
    private static final String TABLE_Person = "Person";
    private static final String TABLE_Score = "Score";
    private static final String TABLE_GCode = "GCode";
    private static final String TABLE_Questions = "Questions";
    private static final String TABLE_Answers = "Answers";
    private static final String TABLE_Groups = "Groups";

    private static final String ID = "ID";
    private static final String Name = "Name";
    private static final String Family = "Family";
    private static final String State = "State";
    private static final String GameID = "GameID";
    private static final String Code = "Code";
    private static final String Score = "Score";
    private static final String Question = "Question";
    private static final String QuestionID = "QuestionID";
    private static final String Answer = "Answer";
    private static final String Flag = "Flag";


    public DBHandler(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String dbexec = "CREATE TABLE IF NOT EXISTS "+ TABLE_GCode +"("
                + ID + " Integer Primary Key Autoincrement," //0
                + Code + " Text);"; //1
        db.execSQL(dbexec);

        String dbexec0 = "CREATE TABLE IF NOT EXISTS "+ TABLE_States +"("
                + ID + " Integer Primary Key Autoincrement," //0
                + State + " Text);"; //1
        db.execSQL(dbexec0);

        String dbexec1 = "CREATE TABLE IF NOT EXISTS "+ TABLE_Person +"("
                + ID + " Integer Primary Key Autoincrement," //0
                + Name + " Text,"
                + Family + " Text);"; //2
        db.execSQL(dbexec1);


        String dbexec2 = "CREATE TABLE IF NOT EXISTS "+ TABLE_Score +"("
                + ID + " Integer Primary Key Autoincrement," //0
                + GameID + " Integer,"//1
                + QuestionID + " Integer, "//2
                + Score + " Integer);"; //3
        db.execSQL(dbexec2);

        String dbexec3 = "CREATE TABLE IF NOT EXISTS "+ TABLE_Questions +"("
                + ID + " Integer Primary Key Autoincrement," //0
                + GameID + " Integer,"//1
                + Question + " Text,"//2
                + Flag + " Boolean,"//3
                + QuestionID + " Integer);"; //4
        db.execSQL(dbexec3);

        String dbexec4 = "CREATE TABLE IF NOT EXISTS "+ TABLE_Answers +"("
                + ID + " Integer Primary Key Autoincrement," //0
                + GameID + " Integer,"//1
                + Answer + " Text,"//2
                + QuestionID + " Integer);"; //3
        db.execSQL(dbexec4);
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

    public void UpdatePerson(Person person,int i){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Name, person.getPersonName());
        values.put(Family, person.getPersonFamily());
        db.update(TABLE_Person,values,"ID =?",new String[] {String.valueOf(i)});
        db.close();
    }

    public Person GetPerson(int id){
        String query = "SELECT * FROM " + TABLE_Person + " WHERE ID = " + id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        return new Person(cursor.getString(1), cursor.getString(2));
    }

    public int GetPersonCount(){
        String query = "SELECT * FROM " + TABLE_Person;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        return cursor.getCount();
    }

    public void DeletePerson(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(TABLE_Person, ID + "=" + id, null);
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
        for (int i=1;i<9;i++){
            db.update(TABLE_States,values,"ID =?",new String[] {String.valueOf(i)});
        }
        db.close();
    }

    public void UpdateState(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
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

    //GCode
    public boolean getCodeState(){
        String countQuery = "SELECT  * FROM " + TABLE_GCode;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor.getCount()>0){
            return true;
        }
        else {
            return false;
        }

    }

    public void AddGCode(GCode gCode){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Code, gCode.getGCode());
        db.insert(TABLE_GCode, null, contentValues);
        db.close();
    }

    public String GetGCode(int id){
        String query = "SELECT  * FROM " + TABLE_GCode + " WHERE ID = "+ id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            return cursor.getString(1);
        }
        return "";
    }


    //Questions
    public void AddQuestion(Questions questions){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(GameID, questions.getQGameID());
        contentValues.put(Question, questions.getQuestion());
        contentValues.put(QuestionID, questions.getQuestionID());
        db.insert(TABLE_Questions, null,contentValues);
        db.close();
    }

    public Questions GetQuestion(int QuestionID, int GameId){
        String query = "SELECT * FROM " + TABLE_Questions + " WHERE QuestionID = " + QuestionID+" AND GameID = " + GameId;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        return new Questions(cursor.getString(2));
    }

    public int GetQuestionCount(){
        String query = "SELECT * FROM " + TABLE_Questions;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        return cursor.getCount();
    }

    public int GetAnswerCount(int id){
        String query = "SELECT * FROM " + TABLE_Answers + " WHERE GameID = " + id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if (cursor != null){
            cursor.moveToFirst();
            return cursor.getCount();
        }
        else return 0;
    }

    public boolean GetQuestionState(int questionID, int GameId){
        String query = "SELECT * FROM " + TABLE_Questions + " WHERE QuestionID = " + questionID+" AND GameID = " + GameId;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null){
            cursor.moveToFirst();
            if (cursor.getString(3)!= null && cursor.getString(3).equals("1")){
                return true;
            }
            else
                return false;
        }
        return false;
    }

    public void UpdateQuestionState(int questionID, int GameId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Flag, true);
        db.update(TABLE_Questions,values,"QuestionID =? And GameId =? ",new String[] {String.valueOf(questionID),String.valueOf(GameId)});
        db.close();

    }

    //Answer
    public void AddAnswer(Answers answers){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(GameID, answers.getAGameID());
        contentValues.put(Answer, answers.getAnswer());
        contentValues.put(QuestionID, answers.getQuestionID());
        db.insert(TABLE_Answers, null,contentValues);
        db.close();
    }

    public List<ShowAnswer> ShowAnswerList(int Gameid){
        List<ShowAnswer> showAnswers = new ArrayList<ShowAnswer>();
        String query = "SELECT q.Question, a.Answer FROM " + TABLE_Questions +" q INNER JOIN " + TABLE_Answers
                + " a ON q.QuestionID = a.QuestionID Where q.GameID = "+Gameid+" AND a.Answer IS NOT NULL AND a.GameID = "+ Gameid;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()) {
            do {
                ShowAnswer showAnswer = new ShowAnswer();
                showAnswer.setQuestion(cursor.getString(0));
                showAnswer.setAnswer(cursor.getString(1));
                showAnswers.add(showAnswer);
            }while (cursor.moveToNext());
        }
        return showAnswers;
    }

    //Score
    public void AddScore(Scores scores){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(GameID, scores.getSGameID());
        contentValues.put(Score, scores.getScore());
        contentValues.put(QuestionID, scores.getSQuestionID());
        db.insert(TABLE_Score, null,contentValues);
        db.close();
    }

    public int GetSumOfScores(){
        String query = "SELECT SUM(Score) FROM " + TABLE_Score;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    public int GetSumOfScore(int Gameid){
        String query = "SELECT SUM(Score) FROM " + TABLE_Score + " WHERE GameID = " + Gameid ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    public boolean GetScoreState(int GameId){
        String query = "SELECT * FROM " + TABLE_States + " WHERE ID = " + GameId;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null){
            cursor.moveToFirst();
            if (cursor.getString(1)!= null && cursor.getString(1).equals("1")){
                return true;
            }
            else
                return false;
        }
        return false;
    }
}
