package ayp.aug.contactapp;

import android.support.v4.app.Fragment;

/**
 * Created by Wilailux on 8/9/2016.
 */
public class ContactListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment onCreateFragment() {
        return new ContactListFragment();
    }


    public void onCrimeUpdated(Contact contact) {
        // Update List
        ContactListFragment listFragment = (ContactListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        listFragment.updateUI();
    }

}
