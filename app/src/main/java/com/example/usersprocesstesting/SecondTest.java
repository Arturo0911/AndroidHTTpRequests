package com.example.usersprocesstesting;

import androidx.appcompat.app.AppCompatActivity;

import android.app.VoiceInteractor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class SecondTest extends AppCompatActivity {

    private Button buttonClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_test);

        buttonClick = findViewById(R.id.buttonClick);

        buttonClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                * The number of the port in a String url for example, must be a integer
                * like this: String URL = "http://" + "10.0.2.2" + ":" + 5000 + "/api";
                * */
                RequestQueue requestQueue = Volley.newRequestQueue(SecondTest.this);
                String URL = "http://" + "10.0.2.2" + ":" + 5000 + "/api";

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
                JsonObjectRequest objectRequest = new JsonObjectRequest(
                        Request.Method.GET,
                        URL,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.e("Rest response", response.toString());
                                Toast.makeText(SecondTest.this, "Rest response: "+response.names().toString(), Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("Rest response", error.toString());
                                Toast.makeText(SecondTest.this, "Rest response: "+error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                );
                requestQueue.add(objectRequest);
            }
        });


    }
}