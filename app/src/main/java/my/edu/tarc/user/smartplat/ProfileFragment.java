package my.edu.tarc.user.smartplat;

import android.content.Intent;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ProfileFragment extends Fragment {
    private View view;
    static private TextView textViewUser, textViewName, textViewContact, textViewAddress, Email;
    private Button btnSave;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.profile_fragment, container, false);
        textViewUser = (TextView) view.findViewById(R.id.tv_Username);
        textViewName = (TextView) view.findViewById(R.id.tv_Name);
        textViewContact = (TextView) view.findViewById(R.id.tv_Phone);
        textViewAddress = (TextView) view.findViewById(R.id.tv_Address);

        btnSave = (Button)view.findViewById(R.id.btnSave);
        textViewUser.setText(SharedPrefManager.getInstance(getContext()).getUsername());
        textViewName.setText(SharedPrefManager.getInstance(getContext()).getName());
        textViewContact.setText(SharedPrefManager.getInstance(getContext()).getUserContact());
        textViewAddress.setText(SharedPrefManager.getInstance(getContext()).getUserAddress());

        displayProfile();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
            }
        });

        return view;
        //return inflater.inflate(R.layout.profile_fragment,container,false);
    }

    private void displayProfile() {
        final String username = SharedPrefManager.getInstance(getContext()).getUsername();
        final String email = SharedPrefManager.getInstance(getContext()).getUserEmail();
        try {
            StringRequest stringRequest = new StringRequest(
                    Request.Method.POST,
                    Constants.URL_PROFILE,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                if (!jsonObject.getBoolean("error")) {
                                    textViewUser.setText(SharedPrefManager.getInstance(getContext()).getUsername());
                                    textViewName.setText(jsonObject.getString("name"));
                                    textViewContact.setText(jsonObject.getString("contact"));
                                    textViewAddress.setText(jsonObject.getString("address"));
                                    SharedPrefManager.getInstance(getContext())
                                            .userProfile(jsonObject.getInt("id"),
                                                    jsonObject.getString("name"),
                                                    jsonObject.getString("email"),
                                                    jsonObject.getString("contact"),
                                                    jsonObject.getString("address"));
                                    Toast.makeText(getContext(),
                                            jsonObject.getString("message"),
                                            Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getContext(),
                                            jsonObject.getString("message"),
                                            Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getContext(),
                                    error.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("username", SharedPrefManager.getInstance(getContext()).getUsername());
                    params.put("email", SharedPrefManager.getInstance(getContext()).getUserEmail());
                    return params;
                }
            };
            RequestHandler.getInstance(getContext()).addToRequestQueue(stringRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateProfile() {
        final String name = textViewName.getText().toString().trim();
        final String contact = textViewContact.getText().toString().trim();
        final String address = textViewAddress.getText().toString().trim();
        final int version = SharedPrefManager.getInstance(getContext()).getVersion() + 1;
        try {
            StringRequest stringRequest = new StringRequest(
                    Request.Method.POST,
                    Constants.URL_UPDATEPROFILE,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                if (!jsonObject.getBoolean("error")) {
                                    SharedPrefManager.getInstance(getContext())
                                            .setUserAddress(jsonObject.getString("address"));
                                    SharedPrefManager.getInstance(getContext())
                                            .setUserContact(jsonObject.getString("contact"));
                                    SharedPrefManager.getInstance(getContext())
                                            .setUserName(jsonObject.getString("name"));
                                    Toast.makeText(getContext(),
                                            jsonObject.getString("message"),
                                            Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getContext(),
                                            jsonObject.getString("message"),
                                            Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getContext(),
                                    error.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("username", SharedPrefManager.getInstance(getContext()).getUsername());
                    params.put("name",name);
                    params.put("contact",contact);
                    params.put("address", address);
                    params.put("version", version + "");
                    return params;
                }
            };
            RequestHandler.getInstance(getContext()).addToRequestQueue(stringRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
