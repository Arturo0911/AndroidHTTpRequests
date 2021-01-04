package com.example.usersprocesstesting;

import androidx.appcompat.app.AppCompatActivity;

import android.app.VoiceInteractor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Iterator;

public class SecondTest extends AppCompatActivity {

    //private Button buttonClick;
    private TextView nameText;
    private TextView lnameText;
    private TextView cedulaText;

    /**
     * GET method
     * To focus the localhost site from the our serve
     * The number of the port in a String url for example, must be a integer
     * like this: String URL = "http://" + "10.0.2.2" + ":" + 5000 + "/api";
     * */
    private static final String urlRequest = "http://" + "10.0.2.2"+":"+5000+"/api";



    public void onButtonClick(View view){

        nameText = (TextView) findViewById(R.id.nameText);
        lnameText = (TextView) findViewById(R.id.lnameText);
        cedulaText = (TextView) findViewById(R.id.cedulaText);

        HttpRequestGET(nameText, cedulaText,lnameText, urlRequest);

    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_test);



    }


    /** GET method */
    public void HttpRequestGET(TextView name, TextView ced, TextView lastname,String url){

        /**
         * Creates a new request.
         *
         * @param method the HTTP method to use
         * @param url URL to fetch the JSON from
         * @param jsonRequest A {@link JSONObject} to post with the request. Null indicates no
         *     parameters will be posted along with request.
         * @param listener Listener to receive the JSON response
         * @param errorListener Error listener, or null to ignore errors.
         */
        RequestQueue requestQueue = Volley.newRequestQueue(SecondTest.this);

        JsonObjectRequest jsonObject = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        /**
                         * If it's successfull
                         */
                        try {
                            name.setText(response.get("Name").toString());
                            lastname.setText(response.get("Lname").toString());
                            ced.setText(response.get("Id").toString());
                        }catch (Exception e){
                            Toast.makeText(SecondTest.this, "Error occurs when the value is trying to connect with the server", Toast.LENGTH_SHORT).show();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                /**
                 * If error occurs
                 */

                Toast.makeText(SecondTest.this, "Error by: "+ error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObject);

    }

    /**POST method*/
}