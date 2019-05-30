package com.example.aplikacija;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText email, password;
    private Button btn_login;
    private ProgressBar loading;
    SessionManager sessionManager;
    private static String URL_LOGIN = "http://192.168.0.107/aplikacija/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loading = findViewById(R.id.loading);
        email = findViewById(R.id.editText3);
        password = findViewById(R.id.editText);
        btn_login = findViewById(R.id.button);

        sessionManager = new SessionManager(this);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = email.getText().toString().trim();
                String Password = password.getText().toString().trim();

                if(Email.isEmpty()){
                    email.setError("Įveskite el. paštą");
                }
                if(Password.isEmpty()){
                    password.setError("Įveskite slaptažodį");
                }
                if(!Email.isEmpty() && !Password.isEmpty()){
                    Login(Email, Password);
                }
            }
        });
    }

    private void Login(final String email, final String password){
        loading.setVisibility(View.VISIBLE);
        btn_login.setVisibility(View.GONE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");

                            if(success.equals("1")){
                                for(int i=0; i < jsonArray.length(); i++){
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String id = object.getString("id").trim();
                                    String name = object.getString("name").trim();
                                    String surname = object.getString("surname").trim();
                                    String email = object.getString("email").trim();
                                    String phone = object.getString("phone").trim();
                                    String role = object.getString("role").trim();

                                    Toast.makeText(MainActivity.this, "Sėkmingas prisijungimas, \n" +name, Toast.LENGTH_SHORT).show();

                                    sessionManager.createSession(name, surname, email, id, phone, role);

                                    Intent intent = new Intent(MainActivity.this, IndexActivity.class);
                                    intent.putExtra("id", id);
                                    intent.putExtra("name", name);
                                    intent.putExtra("surname", surname);
                                    intent.putExtra("email", email);
                                    intent.putExtra("phone", phone);
                                    intent.putExtra("role", role);


                                    startActivity(intent);

                                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                                }
                            }
                        } catch (JSONException e){
                            e.printStackTrace();
                            loading.setVisibility(View.GONE);
                            btn_login.setVisibility(View.VISIBLE);
                            Toast.makeText(MainActivity.this, "Klaida! Neteisingi duomenys.", Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.setVisibility(View.GONE);
                        btn_login.setVisibility(View.VISIBLE);
                        Toast.makeText(MainActivity.this, "Klaida! Neteisingi duomenys.", Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    /*public void loginClick(View view){
        Intent intent = new Intent(this, IndexActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }*/

    public void registerClick(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
