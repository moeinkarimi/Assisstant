package mytechcorp.ir.assisstant;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class HelpActivity extends Dialog {

    Activity a;
    TextViewPlus tvpDesc;
    Button btnOK;
    int ID;

    public HelpActivity(Activity activity, int id) {
        super(activity);
        this.a = activity;
        this.ID = id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_help);
        btnOK = findViewById(R.id.btnOK);
        tvpDesc = findViewById(R.id.tvpDesc);
        btnOK.setTypeface(Typeface.createFromAsset(a.getAssets(),"fonts/IRANSansMobile_Light.ttf"));
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        if (ID == 1){
            tvpDesc.setText(R.string.TableHelp);
        }
        else if (ID == 2){
            tvpDesc.setText(R.string.MysteryHelp);
        }
        else if (ID == 3) {
            tvpDesc.setText(R.string.ReadingHelp);
        }
        else if (ID == 4) {
            tvpDesc.setText(R.string.AppDesc);
        }
        else if (ID == 5) {
            tvpDesc.setText(R.string.ScoreDesc);
        }
        else if (ID == 6) {
            tvpDesc.setText(R.string.SpecialDesc);
        }
        else if (ID == 7) {
            tvpDesc.setText(R.string.GIHelp);
        }
    }
}
