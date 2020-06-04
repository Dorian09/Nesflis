package com.example.nesflis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText nombre_pelicula;
    private Button boton_buscar;
    private ArrayList<Pelicula> listaPeliculas = new ArrayList<Pelicula>();
    private String url = "https://imdb-internet-movie-database-unofficial.p.rapidapi.com/search/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre_pelicula = findViewById(R.id.editText);
        boton_buscar = findViewById(R.id.btnBuscar);

        boton_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestDatos();
            }
        });
    }

    public void requestDatos(){
        RequestQueue cola = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url+nombre_pelicula.getText(), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //dato.setText(response.toString());
                        parserJson(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Error en la conexion", Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                // headers.put("Content-Type", "application/json");
                headers.put("x-rapidapi-key","83b8f8dd34msha8f0fe798d79689p1c306fjsna350f7b9b645");
                return headers;
            }
        };
        cola.add(jsonObjectRequest);
    }

    public void parserJson(JSONObject response){
        try {
            JSONArray peliculas = response.getJSONArray("titles");
            for (int i = 0 ; i < peliculas.length(); i++) {
                JSONObject pel = peliculas.getJSONObject(i);
                String nombre = pel.getString("title");
                String imagen = pel.getString("image");
                Pelicula p = new Pelicula(nombre, imagen);
                listaPeliculas.add(p);
            }
            buscar();
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void buscar(){
        Intent i = new Intent(getApplicationContext(), ListadoActivity.class);
        i.putParcelableArrayListExtra("peliculas", listaPeliculas);
        startActivity(i);
    }
}
