package mytechcorp.ir.assisstant;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import Models.Answers;
import Models.Person;
import Models.Scores;

public class FinalActivity extends Dialog {

    Activity a;
    DBHandler dbHandler;
    Button btnSave2;
    EditText txtCode3;

    public FinalActivity(Activity activity) {
        super(activity);
        this.a = activity;
        dbHandler = new DBHandler(activity);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        btnSave2 = findViewById(R.id.btnSave2);
        txtCode3 = findViewById(R.id.txtCode3);
        Typeface tf = Typeface.createFromAsset(a.getAssets(),"fonts/IRANSansMobile_Light.ttf");
        btnSave2.setTypeface(tf);
        txtCode3.setTypeface(tf);
        if (dbHandler.GetScoreState(9)){
            btnSave2.setVisibility(View.GONE);
            txtCode3.setText(dbHandler.GetAnswer(9));
        }
        btnSave2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(a, R.style.AlertDialogCustom));
                builder.setTitle("ثبت نهایی");
                builder.setMessage("در صورت فشردن دکمه بله جمله ی شما ثبت می شود و دیگر امکان بازگشت وجود ندارد. همچنین در فایل خروجی هیچگونه اطلاعاتی پس از آن درج نمیگردد.\nآیا مطمئنید ؟");
                builder.setPositiveButton("بله", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(!txtCode3.getText().toString().equals("")){
                            dbHandler.UpdateState(9);
                            dbHandler.AddAnswer(
                                    new Answers(
                                            txtCode3.getText().toString(),
                                            9,
                                            91
                                    )
                            );
                            if (txtCode3.getText().toString().equals("فردا از آن ماست")
                                    ||txtCode3.getText().toString().equals("فردااز آن ماست")
                                    ||txtCode3.getText().toString().equals("فرداازآن ماست")
                                    ||txtCode3.getText().toString().equals("فردا ازآن ماست")
                                    ||txtCode3.getText().toString().equals("فردا از ان ماست")
                                    ||txtCode3.getText().toString().equals("فردااز ان ماست")
                                    ||txtCode3.getText().toString().equals("فرداازان ماست")
                                    ||txtCode3.getText().toString().equals("فردا ازان ماست")){
                                dbHandler.AddScore(
                                        new Scores(
                                                9,
                                                50,
                                                91
                                        )
                                );
                            }
                            else {
                                Toast.makeText(a, "متاسفانه پاسخ شما صحیح نمی باشد.", Toast.LENGTH_LONG).show();
                            }
                            GetOutput();
                        }
                        else {
                            Toast.makeText(a,"متاسفانه فرصت شما از دست رفت.",Toast.LENGTH_LONG).show();
                            dbHandler.UpdateState(9);
                            dbHandler.AddAnswer(
                                    new Answers(
                                            " ",
                                            9,
                                            91
                                    )
                            );
                            GetOutput();
                        }
                    }
                }).setNegativeButton("خیر",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setIcon(R.mipmap.ic_warning);
                builder.create().show();

            }
        });

    }

    private void GetOutput(){
        File file = new File(Environment.getExternalStorageDirectory() +
                File.separator + "Assisstant Output");
        if(!file.exists()){
            file.mkdir();
        }

        try{
            String report;
            String persons = "";
            persons = String.valueOf(dbHandler.GetLocal())+"\n";
            List<Person> personList = dbHandler.getAllPerson();
            for (Person person : personList) {
                persons +=  person.getPersonName()+"\n"+person.getPersonFamily()+"\n"+person.getGrade()+"\n";
            }
            report = persons +" \n\n"
                   + "TKL : "   + Integer.toString(Integer.parseInt(String.valueOf(dbHandler.GetPersonCount()), 10), 2)  +" \t\n" //کل افراد
                   + "RNVSH : " + dbHandler.GetAnswer(9) +" \t \n" //رمز نهایی
                   + "JKE : "   + Integer.toString(Integer.parseInt(String.valueOf(dbHandler.GetSumOfScores()), 10), 2)  +" \t \n" //جمع کل امتیاز
                   + "EBT : "   + Integer.toString(Integer.parseInt(String.valueOf(dbHandler.GetSumOfScore(1)), 10), 2)   +" \t \n" //امتیاز بازی جدول
                   + "EBM : "   + Integer.toString(Integer.parseInt(String.valueOf(dbHandler.GetSumOfScore(2)), 10), 2) +" \t \n" //امتیاز بازی مطالعه
                   + "EBMO : "  + Integer.toString(Integer.parseInt(String.valueOf(dbHandler.GetSumOfScore(3)), 10), 2) +" \t \n" //امتیاز بازی معما
                   + "EBP : "   + Integer.toString(Integer.parseInt(String.valueOf(dbHandler.GetSumOfScore(4)), 10), 2)  +" \t \n" //امتیاز بازی پازل
                   + "EBMA : "  + Integer.toString(Integer.parseInt(String.valueOf(dbHandler.GetSumOfScore(5)), 10), 2)  +" \t \n" //امتیاز بازی ماکت
                   + "EBR : "   + Integer.toString(Integer.parseInt(String.valueOf(dbHandler.GetSumOfScore(6)), 10), 2)  +" \t \n" //امتیاز بازی رکورد
                   + "EBG : "   + Integer.toString(Integer.parseInt(String.valueOf(dbHandler.GetSumOfScore(7)), 10), 2)  +" \t \n" //امتیاز بازی گروهی
                   + "EBMS : "  + Integer.toString(Integer.parseInt(String.valueOf(dbHandler.GetSumOfScore(8) ), 10), 2) +" \t \n" //امتیاز بازی مستند سازی
                   + "ERN : "   + Integer.toString(Integer.parseInt(String.valueOf(dbHandler.GetSumOfScore(9)), 10), 2); //امتیاز رمز نهایی
            Date currentTime = Calendar.getInstance().getTime();
            File gpxfile = new File(file, "output " + currentTime.toString().replace(":", " - ") + ".mnk");
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(report);
            writer.flush();
            writer.close();
            Toast.makeText(a, "فایل خروجی با موفقیت در مسیر اصلی در پوشه Assisstant Output ذخیره شد.", Toast.LENGTH_LONG).show();
            btnSave2.setVisibility(View.GONE);

        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(a, e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
}
