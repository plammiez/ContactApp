package ayp.aug.contactapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;

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
}
