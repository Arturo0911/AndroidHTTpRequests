package com.example.usersprocesstesting;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONObject;

public class POSTRequestmethod extends AppCompatActivity {
    /**
     *  credentials, user, email, password
     *
     */
    private static final String postRquest = "http://" + "10.0.2.2"+":"+5000+"/android";
    private EditText fullname;
    private EditText email;
    private EditText password;
    private EditText password2;
    ActionBar actionBar;


    public void sendData(View view){


        fullname = (EditText) findViewById(R.id.fullname);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        password2 = (EditText) findViewById(R.id.password2);

        try {
            if(password.getText().toString().equals(password2.getText().toString())){
                RequestMethodPOST(fullname,email, password,postRquest);
                clearFields(fullname,email, password, password2);
                switchToAnotherActivity();
            }else{
                Toast.makeText(POSTRequestmethod.this, "The passwords field must be same", Toast.LENGTH_SHORT).show();
            }

        }catch (Exception e){
            Toast.makeText(POSTRequestmethod.this, "Error by: "+e.toString(), Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_o_s_t_requestmethod);
        /*actionBar  = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f3f4f5")));
        actionBar.setTitle("Pandas solution");*/
    }

    /**
     * @param fullname
     * @param email
     * @param password
     * @param url The url that we will the POST request to send the data to the server.
    * */
    public void RequestMethodPOST(EditText fullname , EditText email, EditText password, String url){

        JSONObject jsonObject = new JSONObject();
        try {

            jsonObject.put("Fullname", fullname.getText());
            jsonObject.put("Email", email.getText());
            jsonObject.put("Password", password.getText());


        }catch (Exception e ){
            Toast.makeText(POSTRequestmethod.this, "Error by: "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        RequestQueue requestQueue = Volley.newRequestQueue(POSTRequestmethod.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                jsonObject,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(POSTRequestmethod.this, "this is my response: "+response.toString(), Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(POSTRequestmethod.this, "this is my error: "+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(jsonObjectRequest);
    }

    private void clearFields(EditText fullname , EditText email, EditText password, EditText password2) {
        fullname.setText("");
        email.setText("");
        password.setText("");
        password2.setText("");
    }

    private void switchToAnotherActivity(){

        Intent intent = new Intent(POSTRequestmethod.this, MainActivity.class);
        startActivity(intent);
    }





}