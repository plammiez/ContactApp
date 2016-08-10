package ayp.aug.contactapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.List;
import java.util.UUID;

/**
 * Created by Waraporn on 8/9/2016.
 */
public class ContactFragment extends Fragment {

    private Contact contact;

    private EditText name;
    private EditText tel;
    private EditText email;
    private ImageView photoView;
    private ImageButton photoButton;
    private Button button_delete;

    public static ContactFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ContactFragment fragment = new ContactFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ContactLab contactLab = ContactLab.getInstance(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_contact, container, false);

        name = (EditText) v.findViewById(R.id.contact_name);
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                contact.setName(name.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        tel = (EditText) v.findViewById(R.id.contact_tel);
        tel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                contact.setTel(tel.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        email = (EditText) v.findViewById(R.id.contact_email);
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                contact.setEmail(email.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        photoView = (ImageView) v.findViewById(R.id.contact_frame_photo);

        photoButton = (ImageButton) v.findViewById(R.id.contact_camera);

        button_delete = (Button) v.findViewById(R.id.button_delete);

        return v;
    }

}
