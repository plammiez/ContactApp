package ayp.aug.contactapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contact = new Contact();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_contact,container,false);

        RecyclerView recycleView = (RecyclerView) v.findViewById(R.id.fragment_contact_recycleview);

        recycleView.setLayoutManager(new GridLayoutManager(getActivity(),3));// 3 is columns

        return v;
    }
}
