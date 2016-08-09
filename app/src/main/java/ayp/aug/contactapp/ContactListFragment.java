package ayp.aug.contactapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        contact_recycle_view.setLayoutManager(new LinearLayoutManager(getActivity()));

        visibleText = (TextView) v.findViewById(R.id.crime_visible_text);

        return v;
    }
}
