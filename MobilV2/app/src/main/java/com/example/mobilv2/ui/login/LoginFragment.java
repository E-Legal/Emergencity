package com.example.mobilv2.ui.login;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobilv2.ApiManager;
import com.example.mobilv2.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LoginFragment extends Fragment {

    private EditText login;
    private EditText password;
    private Button login_btn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_login, container, false);
        login = root.findViewById(R.id.loginText);
        password = root.findViewById(R.id.passwordText);
        login_btn = root.findViewById(R.id.login_btn);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String my_username = login.getText().toString();
                String my_password = password.getText().toString();
                if (my_username.length() == 0 || my_password.length() == 0) {
                    h.sendEmptyMessage(0);
                } else {
                    JSONObject content = new JSONObject();
                    try {
                        content.put("username", my_username);
                        content.put("password", my_password);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    ApiManager.getInstance().login(content.toString(), new Callback() {
                        @Override
                        public void onFailure(@NotNull Call call, @NotNull IOException e) {
                            h.sendEmptyMessage(1);
                        }

                        @Override
                        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                            if (response.isSuccessful()) {
                                h.sendEmptyMessage(1);
                            } else {
                                h.sendEmptyMessage(2);
                            }
                        }
                    });
                }
            }
        });

        return root;
    }

    Handler h = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                Toast.makeText(getActivity().getApplicationContext(), "Merci de rentrer vos identifiant !", Toast.LENGTH_SHORT).show();
            } else if (msg.what == 1) {
                Toast.makeText(getActivity().getApplicationContext(), "Connexion avec succés !", Toast.LENGTH_SHORT).show();
            } else if (msg.what == 2) {
                Toast.makeText(getActivity().getApplicationContext(), "Votre Authentification a échoué !", Toast.LENGTH_SHORT).show();
            }
        }
    };

}