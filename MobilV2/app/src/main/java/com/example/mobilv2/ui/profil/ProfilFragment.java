package com.example.mobilv2.ui.profil;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.mobilv2.ApiManager;
import com.example.mobilv2.MainActivity;
import com.example.mobilv2.R;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ProfilFragment extends Fragment {

    private TextView nom;
    private TextView prenom;
    private TextView email;
    private TextView poste;
    private View root;

    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_profil, container, false);
        if (ApiManager.getInstance().getToken().isEmpty()) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    TextView title = new TextView(getActivity());
                    title.setText("Vous n'etes pas connecté !");
                    title.setTextSize(20);
                    title.setTypeface(Typeface.DEFAULT_BOLD);
                    title.setPadding(10, 10, 10, 40);
                    title.setGravity(Gravity.CENTER);
                    builder.setCustomTitle(title);
                    builder.setView(inflater.inflate(R.layout.fragment_alert_login, null)).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(requireActivity().getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }
                    }).show();
                    builder.create();
                }
            });
        }
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
                ApiManager.getInstance().removeToken();
                Intent intent = new Intent(requireActivity().getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }
}