package com.example.connecttojson;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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






public class MainActivity extends AppCompatActivity {

    //buat variabel volley untuk mengirim request ke jsno
    RequestQueue requestQueue;

    String base_url ="http//BelajarAPI" ;
    RecyclerView rvPelanggan;
    AdapterPelanggan adapterPelanggan;
    ArrayList<ModelPelanggan> list;
    ProgressDialog progressDialog;
    AlphaAnimation btnAnimasi = new AlphaAnimation(1F, 0,5F);
    TextView btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView((R.layout.activity_main);
        View.setonapplywindowInsertsListener(findViewById(R.id.main), (v.insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setView();
        requestQueue = Volley.newRequestQueue(this)
        getData();

        btnAdd.setOnClickLitsener() {
            @Override
            public void onClick (View v){
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
    }
    private void setView () {

        progressDialog = new progressDialog (this) ;
        btnAdd = (TextView) findViewById(R.id.btnAdd) ;
        rvPelanggan = findViewById (R.id.rvPelanggan) ;
        list = new ArrayList<>();

        LinearLayoutManager llm = new LinearLayoutManager(this) ;
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvPelanggan.setLayoutManager(llm) ;
        rvPelanggan.setHasFixedSize(true);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            v.startAnimation(btnAnimasi);
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
            int position = viewHolder.getAdapterPosition();
            ModelPelanggan np = list.get(position);

            Intent a = new Intent(MainActivity.this, AddActivity.class);
            a.putExtra("param","edit");
            startActivity(a);
        }
    };

    private void showMsg() {
        progressDialog.setTitle("Informasi");
        progressDialog.setMessage("Loading Data..");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    private void getData() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, base_url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    String status = jsonObject.getString("status");
                    String message = jsonObject.getString("message");

                    if (status.equalsIgnoreCase("true")) {
                        // ambil json array dari objek data
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        for (int a = 0; a < jsonArray.length(); a++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(a);
                            ModelPelanggan mp = new ModelPelanggan();
                            mp.setId(jsonObject1.getString("id"));
                            mp.setNama(jsonObject1.getString("nama"));
                            mp.setEmail(jsonObject1.getString("alamat"));
                            mp.setHp(jsonObject1.getString("hp"));
                            list.add(mp);
                        }

                        adapterPelanggan = new AdapterPelanggan(MainActivity.this, list);
                        adapterPelanggan.notifyDataSetChanged();
                        rvPelanggan.setAdapter(adapterPelanggan);

                        // adapterPelanggan.setOnItemClickListener(onClickListener);
                        Toast.makeText(MainActivity.this, ""+jsonArray.toString(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, ""+message, Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError){

            }
        });

        requestQueue.add(jsonObjectRequest);
    }

}