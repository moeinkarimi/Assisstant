package mytechcorp.ir.assisstant;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class AboutUsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        Element versionElement = new Element();
        versionElement.setTitle("نسخه 1.0");
        View Aboutus = new AboutPage(this)
                .isRTL(true)
                .setDescription(this.getString(R.string.about))
                .setCustomFont(this.getString(R.string.Font))
                .setImage(R.drawable.my)
                .addGroup("تماس با ما")
                .addEmail("moein.karimi76@yahoo.com","پست الکترونیک")
                .addWebsite("http://mytechcorp.ir/","وب سایت تیم توسعه دهنده")
                .addWebsite("http://anjomanyaran.ir/","وب سایت اتحادیه انجمن های اسلامی شهر تهران")
                .addInstagram("moeinkarimi76","دنبال کردن در اینستاگرام")
                .addItem(versionElement)
                .create();
        setContentView(Aboutus);
        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_YES);
    }
}
