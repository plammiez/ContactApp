package ayp.aug.contactapp;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ContactActivity extends SingleFragmentActivity{

    @Override
    protected Fragment onCreateFragment() {
        return new ContactFragment();
    }
}
