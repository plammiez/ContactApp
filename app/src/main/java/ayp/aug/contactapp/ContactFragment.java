package ayp.aug.contactapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

/**
 * Created by Waraporn on 8/9/2016.
 */
public class ContactFragment extends Fragment {

    public static ContactFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ContactFragment fragment = new ContactFragment();
        fragment.setArguments(args);
        return fragment;
    }

    Contact contact;
    ContactLab contactLab;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contact = new Contact();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_contact_list,container,false);

        RecyclerView recycleView = (RecyclerView) v.findViewById(R.id.fragment_contact_recycleview);
        recycleView.setLayoutManager(new GridLayoutManager(getActivity(),3));// 3 is columns
//        recycleView.setAdapter(new ContactAdapter(contactLab.getContacts()));

        return v;
    }


    private class ContactHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ContactHolder(LayoutInflater layoutInflater, ViewGroup viewGroup){
            super(layoutInflater.inflate(R.layout.list_item_contact, viewGroup, false));
        }

        @Override
        public void onClick(View v) {

        }
    }

    private class ContactAdapter extends RecyclerView.Adapter<ContactHolder> {

        private List<ContactLab> contactList;

        ContactAdapter(List<ContactLab> contacts) {
            contactList = contacts;
        }

        @Override
        public ContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(ContactHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 1;
        }
    }

}
