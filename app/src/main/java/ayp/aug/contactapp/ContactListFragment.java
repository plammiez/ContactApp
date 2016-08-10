package ayp.aug.contactapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * Created by Wilailux on 8/9/2016.
 */
public class ContactListFragment extends Fragment {

    protected static final String TAG = "CONTACT_LIST";
    private static final int REQUEST_UPDATED_CONTACT = 1;

    private RecyclerView contact_recycle_view;
    public TextView visibleText;
    private Integer[] contactPos;

    private ContactAdapter _adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_contact_list,container,false);

        contact_recycle_view = (RecyclerView) v.findViewById(R.id.fragment_contact_recycleview);
        contact_recycle_view.setLayoutManager(new GridLayoutManager(getActivity(),3));

        visibleText = (TextView) v.findViewById(R.id.crime_visible_text);

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.contact_list_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_item_new_contact:

                Contact contact = new Contact();
//                ContactLab.getInstance(getActivity()).addContact(contact);

                Intent intent = ContactActivity.newIntent(getActivity(), contact.getId());
                startActivity(intent);

                updateUI();
//                callbacks.onCrimeSelected(crime);//TODO : callBacks and onCrimeSelected
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }


    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_UPDATED_CONTACT){
            if(resultCode == Activity.RESULT_OK) {
                contactPos = (Integer[]) data.getExtras().get("position");
                Log.d(TAG, "get crimePos = " + contactPos);
            }
            //blah blah
            Log.d(TAG, "Return from CrimeFragment");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putBoolean(SUBTITLE_VISIBLE_STATE, _subtitleVisible);
    }
    public void updateUI() {

        ContactLab contactLab = ContactLab.getInstance(getActivity());
        List<Contact> contacts = contactLab.getContacts();

        int crimeCount = contactLab.getContacts().size();

        if (_adapter == null) {
            _adapter = new ContactAdapter(this, contacts);
            contact_recycle_view.setAdapter(_adapter);
        }else {
            _adapter.setContact(contactLab.getContacts());
            _adapter.notifyDataSetChanged();
        }

        // set visible text
        if (crimeCount != 0) {
            visibleText.setVisibility(View.INVISIBLE);
        } else {
            visibleText.setVisibility(View.VISIBLE);
        }
    }

    private class ContactHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView _nameTextView;
        public ImageView _photoListView;

        Contact _contact;
        int _position;

        public ContactHolder(View itemView) {
            super(itemView);

            _nameTextView = (TextView) itemView.findViewById(R.id.contact_name);
            _photoListView = (ImageView) itemView.findViewById(R.id.contact_photo);

            itemView.setOnClickListener(this);
        }


        public void bind(final Contact contact,int position) {

            _contact = contact;
            _position = position;
            _nameTextView.setText(_contact.getName());

//            ContactLab.getInstance(getActivity()).updateContact(_contact);

            // show image on listFragment page
            File photoFile = ContactLab.getInstance(getActivity()).getPhotoFile(_contact);
            Bitmap bitmap = PictureUtils.getScaledBitmap(photoFile.getPath(), getActivity());
            _photoListView.setImageBitmap(bitmap);
        }


        @Override
        public void onClick(View v) {
            Intent intent = ContactActivity.newIntent(getActivity(), _contact.getId());
            startActivityForResult(intent, REQUEST_UPDATED_CONTACT);
        }
    }

    private class ContactAdapter extends RecyclerView.Adapter<ContactHolder> {

        private List<Contact> _contacts;
        private Fragment _f;
        private int _viewCreatingCount;

        public ContactAdapter(Fragment f, List<Contact> contacts) {
            _f = f;
            _contacts = contacts;
        }

        protected void setContact(List<Contact> contacts) {
            _contacts = contacts;
        }

        @Override
        public ContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            _viewCreatingCount++;
            Log.d(TAG, "Create view holder for CrimeList: creating view time = "+ _viewCreatingCount);

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View v = layoutInflater.inflate(R.layout.list_item_contact, parent,false);

            return new ContactHolder(v);
        }

        @Override
        public void onBindViewHolder(ContactHolder holder, int position) {

            Log.d(TAG, "Bind view holder for CrimeList : position = " + position);

            Contact contact = _contacts.get(position);
            holder.bind(contact, position);
        }

        @Override
        public int getItemCount() {
            return _contacts.size();
        }
    }


}
