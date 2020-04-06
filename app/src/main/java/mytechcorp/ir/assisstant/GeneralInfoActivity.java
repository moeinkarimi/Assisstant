package mytechcorp.ir.assisstant;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class GeneralInfoActivity extends AppCompatActivity {

    Spinner spQues1;
    TextViewPlus tvQDesc;
    RadioButton rbJ1, rbJ2, rbJ3, rbJ4;

    String Game;
    int qID =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_info);

        spQues1 = findViewById(R.id.spQues1);
        tvQDesc = findViewById(R.id.tvQDesc);
        rbJ1 = findViewById(R.id.rbJ1);
        rbJ2 = findViewById(R.id.rbJ2);
        rbJ3 = findViewById(R.id.rbJ3);
        rbJ4 = findViewById(R.id.rbJ4);

        String[] QuesName = new String[]{"سوال 1","سوال 2","سوال 3","سوال 4","سوال 5","سوال 6","سوال 7","سوال 8","سوال 9","سوال 10" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, QuesName);
        spQues1.setAdapter(adapter);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
        {
            Game = bundle.getString("Game");
        }

        spQues1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    GetQuestionsAndOptions(position);
                    qID = position+1;
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void setBtnGIHelpOnClickListener(View v){
        HelpActivity cdd = new HelpActivity(this, 7);
        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cdd.show();
    }


    private void GetQuestionsAndOptions(int id) throws NoSuchFieldException {
        try {
            Field resourceField = R.string.class.getDeclaredField("gis"+String.valueOf(id+1));
            Field rbjtext1 = R.string.class.getDeclaredField("gisj1"+String.valueOf(id+1));
            Field rbjtext2 = R.string.class.getDeclaredField("gisj2"+String.valueOf(id+1));
            Field rbjtext3 = R.string.class.getDeclaredField("gisj3"+String.valueOf(id+1));
            Field rbjtext4 = R.string.class.getDeclaredField("gisj4"+String.valueOf(id+1));
            int resourceId = resourceField.getInt(resourceField);

            tvQDesc.setText(this.getString(resourceId));
            rbJ1.setText(this.getString(rbjtext1.getInt(resourceField)));
            rbJ2.setText(this.getString(rbjtext2.getInt(resourceField)));
            rbJ3.setText(this.getString(rbjtext3.getInt(resourceField)));
            rbJ4.setText(this.getString(rbjtext4.getInt(resourceField)));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
