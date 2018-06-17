package mytechcorp.ir.assisstant;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;


public class AboutUsActivity extends Activity {

    LinearLayout btnDevBale, btnDevSor, btnDevWeb, btnDevInsta, btnDevEmail, btnEtWeb, btnEtSor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        btnDevBale = findViewById(R.id.btnDevBale);
        btnDevSor = findViewById(R.id.btnDevSor);
        btnDevWeb = findViewById(R.id.btnDevWeb);
        btnDevInsta = findViewById(R.id.btnDevInsta);
        btnDevEmail = findViewById(R.id.btnDevEmail);
        btnEtWeb = findViewById(R.id.btnEtWeb);
        btnEtSor = findViewById(R.id.btnEtISor);

        btnDevInsta.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Uri uri = Uri.parse("http://instagram.com/moeinkarimi76");
                        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                        likeIng.setPackage("com.instagram.android");

                        try {
                            startActivity(likeIng);
                        } catch (ActivityNotFoundException e) {
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("http://instagram.com/moeinkarimi76")));
                        }
                    }
                }
        );

        btnEtSor.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Uri uri = Uri.parse("https://sapp.ir/Anjomanyaran");
                        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                        likeIng.setPackage("mobi.mmdt.ott");

                        try {
                            startActivity(likeIng);
                        } catch (ActivityNotFoundException e) {
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("https://sapp.ir/Anjomanyaran")));
                        }
                    }
                }
        );

        btnDevBale.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Uri uri = Uri.parse("moeinkarimi76");
                        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                        likeIng.setPackage("ir.nasim");

                        try {
                            startActivity(likeIng);
                        } catch (ActivityNotFoundException e) {
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("http://instagram.com/moeinkarimi76")));
                        }
                    }
                }
        );

        btnDevSor.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Uri uri = Uri.parse("hhttps://sapp.ir/moeinkarimi76");
                        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                        likeIng.setPackage("mobi.mmdt.ott");

                        try {
                            startActivity(likeIng);
                        } catch (ActivityNotFoundException e) {
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("https://sapp.ir/moeinkarimi76")));
                        }
                    }
                }
        );

        btnDevEmail.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("message/rfc822");
                        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"moein.karimi76@yahoo.com"});
                        i.putExtra(Intent.EXTRA_SUBJECT, "اپلیکیشن امین ");
                        try {
                            startActivity(Intent.createChooser(i, "ارسال پیام ..."));
                        } catch (android.content.ActivityNotFoundException ex) {
                        }
                    }
                }
        );


        btnDevWeb.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse("https://mytechcorp.ir/"));
                        try {
                            startActivity(Intent.createChooser(i, "انتخاب مرورگر ..."));
                        } catch (android.content.ActivityNotFoundException ex) {
                        }
                    }
                }
        );

        btnEtWeb.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse("http://anjomanyaran.ir"));
                        try {
                            startActivity(Intent.createChooser(i, "انتخاب مرورگر ..."));
                        } catch (android.content.ActivityNotFoundException ex) {
                        }
                    }
                }
        );
    }


}
