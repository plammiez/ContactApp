package ayp.aug.contactapp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.UUID;

public class ContactActivity extends SingleFragmentActivity{

    @Override
    protected Fragment onCreateFragment() {
        return new ContactFragment();
    }

    public static Intent newIntent(Context activity, UUID id) {
        Intent intent = new Intent(activity, ContactActivity.class);
        intent.putExtra("CONTACT_ID", id);
        return intent;
    }
}
