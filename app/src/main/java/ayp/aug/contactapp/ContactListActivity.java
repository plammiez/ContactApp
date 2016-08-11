package ayp.aug.contactapp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.List;

/**
 * Created by Wilailux on 8/9/2016.
 */
public class ContactListActivity extends SingleFragmentActivity implements ContactListFragment.Callbacks, ContactFragment.Callbacks{
    @Override
    protected Fragment onCreateFragment() {
        return new ContactListFragment();
    }

    @Override
    public void onOpenSelectFirst() {

        if (findViewById(R.id.detail_fragment_container) != null) {
            List<Contact> contactList = ContactLab.getInstance(this).getContacts();

            if (contactList != null && contactList.size() > 0) {
                //get first item
                Contact contact = contactList.get(0);

                //two pane
                Fragment newDetailFragment = ContactFragment.newInstance(contact.getId());

                //replace old fragment with new one
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.detail_fragment_container,newDetailFragment)
                        .commit();
            }
        }
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

            onOpenSelectFirst();
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
    public void onContactDelete(){
        ContactListFragment listFragment = (ContactListFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        ContactFragment detailFragment = (ContactFragment)
                getSupportFragmentManager().findFragmentById(R.id.detail_fragment_container);

        listFragment.updateUI();

        //clear
        getSupportFragmentManager()
                .beginTransaction()
                .detach(detailFragment)
                .commit();
    }
}
