package glory.katalogpenjualan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

//import com.example.fujimiya.katalogpenjualan.R;

import Adapter.DataHelper;
import Adapter.RecycleAdapter;

public class PemilihanBarangActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    String idTerima;

    protected Cursor cursor;
    DataHelper dbHelper;
    //RecycleAdapter adapterBarang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemilihan_barang);
        idTerima = getIntent().getExtras().get("idKirim").toString();
        Toast.makeText(getApplicationContext(), "" + idTerima, Toast.LENGTH_SHORT).show();
        dbHelper = new DataHelper(this);
      //  adapterBarang  = new RecycleAdapter(getApplicationContext());



        recyclerView = (RecyclerView) findViewById(R.id.recycleView1);


        RecycleAdapter adapter = new RecycleAdapter(this);
        //membuat adapter baru untuk recycle viewnya
        recyclerView.setAdapter(adapter);
        ////menset nilaia dapter
        //recyclerView.setHasFixedSize(true);

        //menset ukuran
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void klikPesan(View view) {
        new AlertDialog.Builder(this).setMessage("Apa anda yakin  ?")
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        for (int a = 0; a < RecycleAdapter.arrNama.length; a++) {

                            String tos = String.valueOf(RecycleAdapter.arrId[0]);

                            if (RecycleAdapter.arrNama[a] != null)  {
                                Toast.makeText(getApplicationContext(), "" + RecycleAdapter.arrNama[a], Toast.LENGTH_SHORT).show();
                                Toast.makeText(getApplicationContext(), "" + RecycleAdapter.arrTotal[a], Toast.LENGTH_SHORT).show();

                                SQLiteDatabase db = dbHelper.getWritableDatabase();
                                db.execSQL("INSERT INTO data_penjualan(id_barang, id_outlet,nama_barang, jumlah,total) VALUES('" +
                                        RecycleAdapter.arrId[a] + "','" +
                                        idTerima + "','" +
                                        RecycleAdapter.arrNama[a] + "','" +
                                        RecycleAdapter.arrJumlah[a] + "','" +
                                        RecycleAdapter.arrTotal[a] + "');");
                                Toast.makeText(getApplicationContext(), "Berhasil disimpan ", Toast.LENGTH_LONG).show();

                                finish();
                                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(i);
                            }

                        }


                    }
                })
                .setNegativeButton("Tidak", null).show();

    }
}
