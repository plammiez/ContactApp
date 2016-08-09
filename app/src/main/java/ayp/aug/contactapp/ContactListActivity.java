package ayp.aug.contactapp;

import android.support.v4.app.Fragment;

/**
 * Created by Wilailux on 8/9/2016.
 */
public class ContactListActivity extends SingleFragmentActivity{
    @Override
    protected Fragment onCreateFragment() {
        return new ContactFragment();
    }
}
