package com.example.aplikacija;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HomeFragment extends Fragment implements CardView.OnClickListener {

    private TextView[] pvz = new TextView[3];
    private TextView[] rpvz = new TextView[3];

    private Button btn_login;
    private ProgressBar loading;
    SessionManager sessionManager;

    private static String URL_TOP = "http://10.151.24.151/aplikacija/newest_members.php";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        //loading = findViewById(R.id.loading);
        pvz[0] = rootView.findViewById(R.id.textView1);
        pvz[1] = rootView.findViewById(R.id.textView2);
        pvz[2] = rootView.findViewById(R.id.textView3);
        pvz[2] = rootView.findViewById(R.id.textView3);
        rpvz[0] = rootView.findViewById(R.id.textView11);
        rpvz[1] = rootView.findViewById(R.id.textView12);
        rpvz[2] = rootView.findViewById(R.id.textView13);
        GetTop();
        //password = findViewById(R.id.editText);
        //btn_login = findViewById(R.id.button);

        CardView card = rootView.findViewById(R.id.cardView);
        card.setOnClickListener(this);
        CardView card2 = rootView.findViewById(R.id.cardView2);
        card2.setOnClickListener(this);
        CardView card3 = rootView.findViewById(R.id.cardView3);
        card3.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        //Intent intent;
        switch (v.getId()) {
            case R.id.cardView:
                //intent = new Intent(getActivity(), ReadActivity.class);
                GetIntent(0);
                //startActivity(intent);
                break;

            case R.id.cardView2:
                //intent = new Intent(getActivity(), ReadActivity.class);
                GetIntent(1);
                //startActivity(intent);
                break;

            case R.id.cardView3:
                //intent = new Intent(getActivity(), ReadActivity.class);
                GetIntent(2);
                //startActivity(intent);
                break;
        }
    }

    private void GetIntent(final int element){
        //loading.setVisibility(View.VISIBLE);
        //btn_login.setVisibility(View.GONE);
        //Intent intent = new Intent(getActivity(), ReadActivity.class);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_TOP,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("top");

                            if(success.equals("1")){
                                //for(int i=element; i == element; i++){
                                    JSONObject object = jsonArray.getJSONObject(element);

                                    String id = object.getString("id").trim();
                                    String name = object.getString("name").trim();
                                    String surname = object.getString("surname").trim();
                                    String email = object.getString("email").trim();
                                    String phone = object.getString("phone").trim();
                                    String role = object.getString("role").trim();
                                    //pvz[i].setText(name + " " + surname);
                                    //rpvz[i].setText(role);
                                    //Toast.makeText(getActivity(), "Sėkmingas prisijungimas, \n" +name, Toast.LENGTH_SHORT).show();

                                    //sessionManager.createSession(name, surname, email, id, phone, role);
                                    Intent intent = new Intent(getActivity(), ReadActivity.class);
                                    intent.putExtra("id", id);
                                    intent.putExtra("name", name);
                                    intent.putExtra("surname", surname);
                                    intent.putExtra("email", email);
                                    intent.putExtra("phone", phone);
                                    intent.putExtra("role", role);

                                    startActivity(intent);

                                    //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                                //}
                            }
                        } catch (JSONException e){
                            e.printStackTrace();
                            //loading.setVisibility(View.GONE);
                            //btn_login.setVisibility(View.VISIBLE);
                            //Toast.makeText(getActivity(), "Klaida! " +e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //loading.setVisibility(View.GONE);
                        //btn_login.setVisibility(View.VISIBLE);
                        //Toast.makeText(getActivity(), "Klaida! " +error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //params.put("email", email);
                //params.put("password", password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    private void GetTop(){
        //loading.setVisibility(View.VISIBLE);
        //btn_login.setVisibility(View.GONE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_TOP,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("top");

                            if(success.equals("1")){
                                for(int i=0; i < jsonArray.length(); i++){
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String id = object.getString("id").trim();
                                    String name = object.getString("name").trim();
                                    String surname = object.getString("surname").trim();
                                    String email = object.getString("email").trim();
                                    String phone = object.getString("phone").trim();
                                    String role = object.getString("role").trim();
                                    pvz[i].setText(name + " " + surname);
                                    rpvz[i].setText(role);
                                    //Toast.makeText(getActivity(), "Sėkmingas prisijungimas, \n" +name, Toast.LENGTH_SHORT).show();

                                    //sessionManager.createSession(name, surname, email, id, phone, role);
/*
                                    intent.putExtra("id"+i, id);
                                    intent.putExtra("name"+i, name);
                                    intent.putExtra("surname"+i, surname);
                                    intent.putExtra("email"+i, email);
                                    intent.putExtra("phone"+i, phone);
                                    intent.putExtra("role"+i, role);
*/
                                    //startActivity(intent);

                                    //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                                }
                            }
                        } catch (JSONException e){
                            e.printStackTrace();
                            //loading.setVisibility(View.GONE);
                            //btn_login.setVisibility(View.VISIBLE);
                            //Toast.makeText(getActivity(), "Klaida! " +e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //loading.setVisibility(View.GONE);
                        //btn_login.setVisibility(View.VISIBLE);
                        //Toast.makeText(getActivity(), "Klaida! " +error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //params.put("email", email);
                //params.put("password", password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }
}
