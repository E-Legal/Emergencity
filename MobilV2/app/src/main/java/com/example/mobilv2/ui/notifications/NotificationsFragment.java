package com.example.mobilv2.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mobilv2.ApiManager;
import com.example.mobilv2.MainActivity;
import com.example.mobilv2.R;
import com.example.mobilv2.ui.login.LoginFragment;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class NotificationsFragment extends Fragment {

    private TextView nom;
    private TextView prenom;
    private TextView email;
    private TextView poste;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_notifications, container, false);
        nom = root.findViewById(R.id.cp_firstname);
        prenom = root.findViewById(R.id.cp_lastname);
        email = root.findViewById(R.id.cp_email);
        poste = root.findViewById(R.id.cp_nbreview);


        ApiManager.getInstance().getProfile(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String my_response = response.body().string();
                    try {
                        final JSONObject jsonObject = new JSONObject(my_response);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    System.out.println("Retour de ma requete : " + jsonObject.toString());
                                    nom.setText(jsonObject.getString("first_name"));
                                    prenom.setText(jsonObject.getString("last_name"));
                                    email.setText(jsonObject.getString("user_id"));
                                    poste.setText(jsonObject.getString("job"));
                                }
                                catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Button button = (Button) root.findViewById(R.id.cp_bottom_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireActivity().getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }
}