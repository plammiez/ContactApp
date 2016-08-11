package ayp.aug.contactapp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import java.util.List;
import java.util.UUID;

/**
 * Created by Wilailux on 8/9/2016.
 */
public class ContactListActivity extends SingleFragmentActivity
        implements ContactListFragment.Callbacks, ContactFragment.Callbacks, DeleteFragment.Callbacks{
    
    @Override
    protected Fragment onCreateFragment() {
        return new ContactListFragment();
    }

    @Override
    public void onContactSelected(Contact contact) {
        if (findViewById(R.id.detail_fragment_container) == null) {
            //single pane
            Intent intent = ContactActivity.newIntent(this, contact.getId());
            startActivity(intent);
        } else {
            //two pane
            Fragment newDetailFragment = ContactFragment.newInstance(contact.getId());

            //replace fragment
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.detail_fragment_container, newDetailFragment)
                    .commit();
        }
    }
    @Override
    public void onContactUpdated(Contact contact) {
        //Update list
        ContactListFragment listFragment = (ContactListFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        listFragment.updateUI();
    }

    @Override
    public void onContactDeleted() {
        FragmentManager fm = getSupportFragmentManager();
        ContactListFragment listFragment = (ContactListFragment)
                fm.findFragmentById(R.id.fragment_container);
        listFragment.updateUI();

        ContactFragment cf = (ContactFragment)
                fm.findFragmentById(R.id.detail_fragment_container);

        fm.beginTransaction()
                .detach(cf)
                .commit();
    }
}
