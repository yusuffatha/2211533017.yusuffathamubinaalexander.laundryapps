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
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.laundryapps.R;
import com.example.laundryapps.database.SQLiteHelper;
import com.example.laundryapps.model.ModelPelanggan;

import java.util.UUID;

public class PelangganEditActivity extends AppCompatActivity {
    EditText etnama, etemail, ethp;
    Button btnSimpan, btnBatal, btnEdit;
    SQLiteHelper db;
    String id, nama, hp, email;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pelanggan_edit);


        etnama = (EditText) findViewById(R.id.edPelNama);
        etemail = (EditText) findViewById(R.id.edPelEmail);
        ethp = (EditText) findViewById(R.id.edPelAddHp);
        btnBatal = (Button) findViewById(R.id.btnPelAddBatal);
        btnEdit = (Button) findViewById(R.id.btnPelAddEdit);

        db = new SQLiteHelper(PelangganEditActivity.this);

        // menerima data mp dari Pelanggan_RecyclerViewAdapter
        id = getIntent().getStringExtra("id");
        nama = getIntent().getStringExtra("nama");
        email = getIntent().getStringExtra("email");
        hp = getIntent().getStringExtra("hp");

        /* supaya editText nya tidak kosong
         *  atau apakah sebaiknya dibiarkan kosong?
         * */
        etnama.setText(nama);
        etemail.setText(email);
        ethp.setText(hp);


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModelPelanggan mp = new ModelPelanggan();
                String uniqueID = UUID.randomUUID().toString();
                mp.setNama(etnama.getText().toString());
                mp.setEmail(etemail.getText().toString());
                mp.setHp(ethp.getText().toString());

                Toast.makeText(PelangganEditActivity.this, "Nama: " + mp.getNama() + " Email: " + mp.getEmail() + " Hp: " + mp.getHp(), Toast.LENGTH_SHORT).show();

                boolean cek = db.editPelanggan(mp, id);
                if (cek) {
                    Toast.makeText(PelangganEditActivity.this, "Data berhasil diubah", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(PelangganEditActivity.this, PelangganActivity.class));
                    finish();
                } else {
                    Toast.makeText(PelangganEditActivity.this, "Data gagal diubah", Toast.LENGTH_SHORT).show();
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