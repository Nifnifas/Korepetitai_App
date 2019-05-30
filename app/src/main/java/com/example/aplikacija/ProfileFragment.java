package com.example.aplikacija;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class ProfileFragment extends Fragment implements Button.OnClickListener {

    public static Button btn ;
    private TextView name, email, phone, role;
    SessionManager sessionManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        btn = rootView.findViewById(R.id.superButton);

        sessionManager = new SessionManager(getActivity());
        sessionManager.checkLogin();

        name = rootView.findViewById(R.id.textView5);
        email = rootView.findViewById(R.id.textView13);
        phone = rootView.findViewById(R.id.textView12);
        role = rootView.findViewById(R.id.textView6);

        HashMap<String, String> user = sessionManager.getUserDetail();
        String mName = user.get(sessionManager.NAME);
        String mEmail = user.get(sessionManager.EMAIL);
        String mSurname = user.get(sessionManager.SURNAME);
        String mPhone = user.get(sessionManager.PHONE);
        String mRole = user.get(sessionManager.ROLE);

        /*String extraName = getActivity().getIntent().getStringExtra("name");
        String extraSurname = getActivity().getIntent().getStringExtra("surname");
        String extraEmail = getActivity().getIntent().getStringExtra("email");
        String extraPhone = getActivity().getIntent().getStringExtra("phone");
        String extraRole = getActivity().getIntent().getStringExtra("role");

        name.setText(extraName + " " + extraSurname);
        email.setText(extraEmail);
        phone.setText(extraPhone);
        role.setText(extraRole);*/

        name.setText(mName + " " + mSurname);
        email.setText(mEmail);
        phone.setText(mPhone);
        role.setText(mRole);

        btn.setOnClickListener(this);
        //btn.setVisibility(View.INVISIBLE);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.superButton:
                intent = new Intent(getActivity(), UserEditActivity.class);
                startActivity(intent);
                break;
        }
    }
}