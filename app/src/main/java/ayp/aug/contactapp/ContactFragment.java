package ayp.aug.contactapp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;
import java.util.UUID;

/**
 * Created by Waraporn on 8/9/2016.
 */
public class ContactFragment extends Fragment {

    private static final String CONTACT_ID = "ContactFragment.CONTACT_ID";
    private static final String DIALOG_DELETE = "ContactFragment.DIALOG_DELETE";
    private static final int REQUEST_CAPTURE_PHOTO = 29800;
    private static final String TAG = "ContactFragment";

    private Contact contact;
    protected File photoFile;

    private EditText name;
    private EditText tel;
    private EditText email;
    private ImageView photoView;
    private ImageButton photoButton;
    private Button button_delete;

    public static ContactFragment newInstance(UUID contactId) {
        
        Bundle args = new Bundle();
        args.putSerializable(CONTACT_ID,contactId);
        ContactFragment fragment = new ContactFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private Callbacks callbacks;

    //Callback
    public interface Callbacks {
        void onContactUpdated(Contact contact);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callbacks = null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callbacks = (Callbacks) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ContactLab contactLab = ContactLab.getInstance(getActivity());

        if(getArguments().get(CONTACT_ID) != null) {
            UUID contactId = (UUID) getArguments().getSerializable(CONTACT_ID);
            contact = ContactLab.getInstance(getActivity()).getContactById(contactId);
            Log.d(ContactListFragment.TAG, " contact.getId()=" + contact.getId());
        }else {
            //== null
            Contact contact = new Contact();
            contactLab.addContact(contact);
            this.contact = contact;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_contact, container, false);

        name = (EditText) v.findViewById(R.id.contact_name);
        name.setText(contact.getName());
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                contact.setName(name.getText().toString());
                updateContact();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        tel = (EditText) v.findViewById(R.id.contact_tel);
        tel.setText(contact.getTel());
        tel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                contact.setTel(tel.getText().toString());
                updateContact();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        email = (EditText) v.findViewById(R.id.contact_email);
        email.setText(contact.getEmail());
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                contact.setEmail(email.getText().toString());
                updateContact();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        PackageManager packageManager = getActivity().getPackageManager();

        photoView = (ImageView) v.findViewById(R.id.contact_frame_photo);

        photoButton = (ImageButton) v.findViewById(R.id.contact_camera);

        //call camera intent
        final Intent captureImageIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        photoFile = ContactLab.getInstance(getActivity()).getPhotoFile(contact);

        boolean canTakePhoto = photoFile != null
                && captureImageIntent.resolveActivity(packageManager) != null;


        if (canTakePhoto) {
            Uri uri = Uri.fromFile(photoFile);
            Log.d(TAG, "File output at " + photoFile.getAbsolutePath());
            captureImageIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }

        //on click --> start activity for camera
        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(captureImageIntent, REQUEST_CAPTURE_PHOTO);
            }
        });
        updatePhotoView();
        updateContact();

        button_delete = (Button) v.findViewById(R.id.button_delete);
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                DeleteFragment deleteFragment = DeleteFragment.newInstance(contact.getId());
                deleteFragment.setTargetFragment(ContactFragment.this, REQUEST_CAPTURE_PHOTO);
                deleteFragment.show(fm, DIALOG_DELETE);
            }
        });
        updateContact();
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CAPTURE_PHOTO) {
            updatePhotoView();
        }
    }

    public void updateContact(){
        ContactLab.getInstance(getActivity()).updateContact(contact);
        callbacks.onContactUpdated(contact);
    }

    private void updatePhotoView(){
        if (photoFile == null || !photoFile.exists()) {
            photoView.setImageDrawable(null);
        } else {
            Bitmap bitmap = PictureUtils.getScaleBitmap(photoFile.getPath(), getActivity());
            photoView.setImageBitmap(bitmap);
        }
    }
}
