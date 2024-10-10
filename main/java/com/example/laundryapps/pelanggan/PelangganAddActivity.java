package com.example.laundryapps.pelanggan;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.laundryapps.R;
import com.example.laundryapps.database.SQLiteHelper;
import com.example.laundryapps.model.ModelPelanggan;

import java.util.UUID;

public class PelangganAddActivity extends AppCompatActivity {
    EditText nama, email, hp;
    Button btnSimpan, btnBatal;
    SQLiteHelper db;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pelanggan_add);


        nama = (EditText) findViewById(R.id.edPelNama);
        email = (EditText) findViewById(R.id.edPelEmail);
        hp = (EditText) findViewById(R.id.edPelAddHp);
        btnSimpan = (Button) findViewById(R.id.btnPelAddSimpan);
        btnBatal = (Button) findViewById(R.id.btnPelAddBatal);

        db = new SQLiteHelper(PelangganAddActivity.this);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModelPelanggan mp = new ModelPelanggan();
                String uniqueID = UUID.randomUUID().toString();
                mp.setNama(nama.getText().toString());
                mp.setEmail(email.getText().toString());
                mp.setHp(hp.getText().toString());

                Toast.makeText(PelangganAddActivity.this, "Nama: " + mp.getNama() + " Email: " + mp.getEmail() + " Hp: " + mp.getHp(), Toast.LENGTH_SHORT).show();

                boolean cek = db.insertPelanggan(mp);
                if (cek == true) {
                    Toast.makeText(PelangganAddActivity.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(PelangganAddActivity.this, PelangganActivity.class));
                    finish();
                } else {
                    Toast.makeText(PelangganAddActivity.this, "Data gagal disimpan", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}