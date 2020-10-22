package com.example.mobilv2.ui.support;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.mobilv2.R;

public class SupportFragment extends Fragment {

    EditText editText;
    Button button;
    String comment;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_support, container, false);
        editText = root.findViewById(R.id.cp_probleme);
        button = root.findViewById(R.id.cp_support_btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comment = editText.getText().toString();
                if (comment.length() > 0) {
                    Intent email = new Intent(Intent.ACTION_SEND);
                    email.putExtra(Intent.EXTRA_EMAIL, new String[]{"emergencity_2021@labeip.epitech.eu"});
                    email.putExtra(Intent.EXTRA_SUBJECT, "Problème sur le mobile");
                    email.putExtra(Intent.EXTRA_TEXT, comment);
                    email.setType("message/rfc822");
                    startActivity(Intent.createChooser(email, "Choose an Email client :"));
                }
                else {
                    h.sendEmptyMessage(0);
                }
            }
        });

        return root;
    }

    @SuppressLint("HandlerLeak")
    Handler h = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                Toast.makeText(getActivity().getApplicationContext(), "Merci de remplir le formulaire avant !", Toast.LENGTH_SHORT).show();
            }
        }
    };
}