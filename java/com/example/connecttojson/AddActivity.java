package com.example.connecttojson;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;

public class AddActivity extends AppCompatActivity {

    RequestQueue requestQueue;

    String param;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setView();


        param = getIntent().getStringExtra("param");
        Toast.makeText(this, ""+param, Toast.LENGTH_SHORT).show();
    //  cek apakah yang dipilih tambah data atau edit data
        if (param.equalsIgnoreCase("add")){
            btnHapus.setVisibility(View.GONE);
        }else if(param.equalsIgnoreCase("edit")){
            btnHapus.setVisibility(View.VISIBLE);
    //  ambil data dari intent
        String id, nama, alamat, hp;
        id = getIntent().getStringExtra()
        }


    }

    private void editPelanggan(){

    }

    private void addPelanggan(){
        StringRequest request = new StringRequest(Request.Method.POST, base_url, new Response)
    }

    private void setView(){

    }


}