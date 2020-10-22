package com.example.mobilv2.ui.map;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.mobilv2.ApiManager;
import com.example.mobilv2.MainActivity;
import com.example.mobilv2.R;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.Polyline;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static androidx.core.content.ContextCompat.getSystemService;

//MAP FRAGMENT




public class MapFragment extends Fragment {

    private final int REQUEST_PERMISSIONS_REQUEST_CODE = 1;
    private MapView map = null;
    private LocationManager lm;
    int controled = 0;
    private Button btn_go;
    private static String course_id;
    private static JSONArray course = null;

    final double[] lat = new double[1];
    final double[] lng = new double[1];


    @RequiresApi(api = Build.VERSION_CODES.M)
    public View onCreateView(@NonNull final LayoutInflater inflater,
                         ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //View root = inflater.inflate(R.layout.fragment_map, container, false);

        //handle permissions first, before map is created. not depicted here

        //load/initialize the osmdroid configuration, this can be done
        Context ctx = getContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        //setting this before the layout is inflated is a good idea
        //it 'should' ensure that the map has a writable location for the map cache, even without permissions
        //if no tiles are displayed, you can try overriding the cache path using Configuration.getInstance().setCachePath
        //see also StorageUtils
        //note, the load method also sets the HTTP User Agent to your application's package name, abusing osm's
        //tile servers will get you banned based on this string

        //inflate and create the map
        final View root = inflater.inflate(R.layout.fragment_map, container, false);

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

        map = (MapView) root.findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);

        lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        requestPermissionsIfNecessary(new String[]{
                // if you need to show the current location, uncomment the line below
                // Manifest.permission.ACCESS_FINE_LOCATION,
                // WRITE_EXTERNAL_STORAGE is required in order to show the map
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        });

        btn_go = root.findViewById(R.id.btn_go);
        btn_go.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View view) {
                String add_to = ((TextView) (root.findViewById(R.id.txt_add))).getText().toString();
                String origine = "705 chemin de brandine";

                if (add_to.equals("")) {
                    ((TextView) (root.findViewById(R.id.txt_add))).setError("Enter addresse");
                    return;
                }

                //add_to = "4 chemin les chaboeufs";
                Log.i("oula", "oula");

                ApiManager.getInstance().addCourse(add_to, lat[0], lng[0] , new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Snackbar.make(view, e.getMessage(), Snackbar.LENGTH_LONG)
                                .setAction("Erreur", null).show();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.code() == 200) { //OK
                            try {
                                JSONObject responseBody = new JSONObject(response.body().string());
                                course_id = responseBody.getString("id");
                                Log.i("course id", course_id);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            //Intent intent = new Intent(MainActivity.this, GpsActivity.class);
                            //startActivity(intent);
                        } else {
                            Snackbar.make(view, "Adresse incorect", Snackbar.LENGTH_LONG)
                                    .setAction("Erreur", null).show();

                        }

                        // Do Something
                    }
                });

                final int[] oups = {0};
                final int max_test = 10000;

                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        try {
                            while (course == null && oups[0] < max_test) {
                                ApiManager.getInstance().getCourse(course_id, new Callback() {
                                    @Override
                                    public void onFailure(Call call, IOException e) {
                                        Snackbar.make(view, e.getMessage(), Snackbar.LENGTH_LONG)
                                                .setAction("Erreur", null).show();
                                    }

                                    @Override
                                    public void onResponse(Call call, Response response) throws IOException {
                                        if (response.code() == 200) { //OK
                                            try {
                                                String res = response.body().string();
                                                JSONObject responseBody = new JSONObject(res);
                                                //Log.i("retour api route", responseBody.toString());
                                                if (responseBody.getString("course") != "null") {
                                                    course = new JSONArray(responseBody.getString("course"));
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                });
                                oups[0]++;
                                sleep(100);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };

                thread.start();

                while (course == null && oups[0] < max_test);

                if (oups[0] >= max_test) { //probleme
                    Snackbar.make(view, "Une erreur est survenue", Snackbar.LENGTH_LONG)
                            .setAction("Erreur", null).show();
                } else { // tout va bien
                    List<GeoPoint> geoPoints = new ArrayList<>();
                    //add your points here

                    for (int n = 0; n < course.length(); n++) {
                        JSONArray object = null;
                        try {
                            object = course.getJSONArray(n);
                            geoPoints.add(new GeoPoint(object.getDouble(1), object.getDouble(0)));
                            Log.i("coord", "" + object.getDouble(1) + " - " + object.getDouble(0));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    Polyline line = new Polyline();   //see note below!
                    line.setPoints(geoPoints);
                    /*line.setOnClickListener(new Polyline.OnClickListener() {
                        @Override
                        public boolean onClick(Polyline polyline, MapView mapView, GeoPoint eventPos) {
                            Toast.makeText(mapView.getContext(), "polyline with " + polyline.getPoints().size() + "pts was tapped", Toast.LENGTH_LONG).show();
                            return false;
                        }
                    });*/
                    map.getOverlayManager().add(line);
                    /*Snackbar.make(view, "Ok bebe", Snackbar.LENGTH_LONG)
                            .setAction("Erreur", null).show();*/
                }
                Log.i("oups?", "" + oups[0]);
                Log.i("course", course.toString());

            }
        });

        addLocationListener();

        return root;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void addLocationListener() {

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // request the required permission
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 0, new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {
                // the location of the device has changed so update the
                // textviews to reflect this

                /* TODO todo */
                if (controled == 0) {
                    IMapController mapController = map.getController();
                    mapController.setZoom(18);
                    GeoPoint startPoint = new GeoPoint(location.getLatitude(), location.getLongitude());
                    lat[0] = location.getLatitude();
                    lng[0] = location.getLongitude();
                    mapController.setCenter(startPoint);
                    controled = 1;


                    //your items
                    ArrayList<OverlayItem> items = new ArrayList<OverlayItem>();
                    items.add(new OverlayItem("Title", "Description", new GeoPoint(location.getLatitude(), location.getLongitude()))); // Lat/Lon decimal degrees

                    //the overlay
                    ItemizedOverlayWithFocus<OverlayItem> mOverlay = new ItemizedOverlayWithFocus<OverlayItem>(items,
                            new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
                                @Override
                                public boolean onItemSingleTapUp(final int index, final OverlayItem item) {
                                    //do something
                                    return true;
                                }

                                @Override
                                public boolean onItemLongPress(final int index, final OverlayItem item) {
                                    return false;
                                }
                            }, getActivity());
                    mOverlay.setFocusItemsOnTap(true);

                    map.getOverlays().add(mOverlay);
                }

            }

            @Override
            public void onProviderDisabled(String provider) {
                // if GPS has been disabled then update the textviews to reflect
                // this
                if (provider == LocationManager.GPS_PROVIDER) {
                }
            }

            @Override
            public void onProviderEnabled(String provider) {
                if (provider == LocationManager.GPS_PROVIDER) {
                    if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // request the required permission
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                        return;
                    }
                    // if there is a last known location then set it on the textviews
                    Location l = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if (l != null) {
                    }
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }
        });
    }

    /*
    @Override
    public void onResume() {
        super.onResume();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
        map.onResume(); //needed for compass, my location overlays, v6.0.0 and up
    }

    @Override
    public void onPause() {
        super.onPause();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().save(this, prefs);
        map.onPause();  //needed for compass, my location overlays, v6.0.0 and up
    }*/

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        ArrayList<String> permissionsToRequest = new ArrayList<>();
        for (int i = 0; i < grantResults.length; i++) {
            permissionsToRequest.add(permissions[i]);
        }
        if (permissionsToRequest.size() > 0) {
            ActivityCompat.requestPermissions(
                    getActivity(),
                    permissionsToRequest.toArray(new String[0]),
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }

    private void requestPermissionsIfNecessary(String[] permissions) {
        ArrayList<String> permissionsToRequest = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(getContext(), permission)
                    != PackageManager.PERMISSION_GRANTED) {
                // Permission is not granted
                permissionsToRequest.add(permission);
            }
        }
        if (permissionsToRequest.size() > 0) {
            ActivityCompat.requestPermissions(
                    getActivity(),
                    permissionsToRequest.toArray(new String[0]),
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }

}