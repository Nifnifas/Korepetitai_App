package com.example.aplikacija;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText name, surname, email, password, phone;
    private String role;
    private Spinner spinner;
    private Button btn_reg;
    private ProgressBar loading;
    private static String URL_REGIST = "http://10.151.24.151/aplikacija/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final Spinner dropdown = findViewById(R.id.spinner1);
        dropdown.setOnItemSelectedListener(this);

        String[] items = new String[]{"Jūs esate...", "Mokinys", "Mokytojas"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        loading = findViewById(R.id.loading);
        name = findViewById(R.id.editText7);
        surname = findViewById(R.id.editText9);
        phone = findViewById(R.id.editText10);
        email = findViewById(R.id.editText3);
        password = findViewById(R.id.editText);


        btn_reg = findViewById(R.id.button);

        btn_reg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                role = dropdown.getSelectedItem().toString();
                String Email = email.getText().toString().trim();
                String Password = password.getText().toString().trim();
                String Name = name.getText().toString().trim();
                String Surname = surname.getText().toString().trim();
                String Phone = phone.getText().toString().trim();

                    if(Email.isEmpty())
                        email.setError("Įveskite el. paštą");
                    if(Password.isEmpty())
                        password.setError("Įveskite slaptažodį");
                    if(Name.isEmpty())
                        name.setError("Įveskite vardą");
                    if(Surname.isEmpty())
                        surname.setError("Įveskite pavardę");
                    if(Phone.isEmpty())
                        phone.setError("Įveskite telefono nr.");
                    if(!Email.isEmpty() && !Password.isEmpty() && !Name.isEmpty() && !Surname.isEmpty() && !Phone.isEmpty())
                        Register();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

    private void Register() {
        loading.setVisibility(View.VISIBLE);
        btn_reg.setVisibility(View.GONE);

        final String name = this.name.getText().toString().trim();
        final String surname = this.surname.getText().toString().trim();
        final String email = this.email.getText().toString().trim();
        final String password = this.password.getText().toString().trim();
        final String phone = this.phone.getText().toString().trim();
        final String role = this.role;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(RegisterActivity.this, "Registracija sėkminga!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(RegisterActivity.this, "Klaida! " + e.toString(), Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.GONE);
                            btn_reg.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this, "Klaida " + error.toString(), Toast.LENGTH_SHORT).show();
                        loading.setVisibility(View.GONE);
                        btn_reg.setVisibility(View.VISIBLE);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("surname", surname);
                params.put("email", email);
                params.put("password", password);
                params.put("phone", phone);
                params.put("role", role);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }



   /* public void registerClick(View view) {
        //Intent intent = new Intent(this, IndexActivity.class);
        //startActivity(intent);
        //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }*/

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
