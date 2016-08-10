package ayp.aug.contactapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Wilailux on 8/9/2016.
 */
public class ContactListFragment extends Fragment {

    private RecyclerView contact_recycle_view;
    public TextView visibleText;


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
                ContactLab.getInstance(getActivity()).addContact(contact);

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

    public void updateUI() {
        // TODO : updateUI
    }



}
