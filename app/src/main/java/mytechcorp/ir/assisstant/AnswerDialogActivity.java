package mytechcorp.ir.assisstant;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;

public class AnswerDialogActivity extends Dialog {

    public Activity c;
    public Dialog d;
    ListView lvShowAnswer;

    public AnswerDialogActivity(Activity a) {
        super(a);
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_answer_dialog);
        lvShowAnswer = findViewById(R.id.lvShowAnswer);
    }
}
