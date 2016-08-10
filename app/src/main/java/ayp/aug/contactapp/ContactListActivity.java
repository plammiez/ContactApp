package ayp.aug.contactapp;

import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.List;

/**
 * Created by Wilailux on 8/9/2016.
 */
public class ContactListActivity extends SingleFragmentActivity implements ContactListFragment.Callbacks{
    @Override
    protected Fragment onCreateFragment() {
        return new ContactListFragment();
    }


    public void onContactUpdated(Contact contact) {
        // Update List
        ContactListFragment listFragment = (ContactListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_container);

        listFragment.updateUI();
    }

    @Override
    public void onContactSelected(Contact contact) {
        Intent intent = ContactActivity.newIntent(this, contact.getId());
        startActivity(intent);
    }

    @Override
    public void onOpenSelectFirst() {

    }
}
