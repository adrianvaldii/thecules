package com.example.belajardicoding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    private List<PlayerItem> playerItemList;
    private static final String JSON_URL = "http://adrianvaldii.github.io/player.json";
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.about,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.aboutMe:

                Intent moveIntent = new Intent(MainActivity.this, TentangKita.class);
                startActivity(moveIntent);

                return true;

        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        playerItemList = new ArrayList<>();
        loadPlayer();

//        listView.setOnItemClickListener(this);

    }

    private void loadPlayer() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject obj = new JSONObject(response);
                            final JSONArray playerArray = obj.getJSONArray("result");

                            for (int i = 0; i < playerArray.length(); i++) {

                                JSONObject playerObject = playerArray.getJSONObject(i);


                                PlayerItem playerItem = new PlayerItem(
                                        playerObject.getString("no"),
                                        playerObject.getString("name"),
                                        playerObject.getString("Position"),
                                        playerObject.getString("birth_date"),
                                        playerObject.getString("Poster"));

                                playerItemList.add(playerItem);
                            }

                            ListViewAdapter adapter = new ListViewAdapter(playerItemList, getApplicationContext());

                            listView.setAdapter(adapter);

                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                                @Override
                                public void onItemClick(AdapterView<?> pa, View v, int p, long id) {
                                    // klik intent
                                    PlayerItem playerItems = playerItemList.get(p);

                                    Intent i = new Intent(MainActivity.this, DetailActivity.class);

                                    i.putExtra("imagePoster", playerItems.getPoster());
                                    i.putExtra("namePoster", playerItems.getName());
                                    i.putExtra("noPoster", playerItems.getNo());
                                    i.putExtra("positionPoster", playerItems.getPosition());
                                    i.putExtra("birthPoster", playerItems.getBirth_date());
                                    startActivity(i);
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
