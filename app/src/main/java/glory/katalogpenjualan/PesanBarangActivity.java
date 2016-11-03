package glory.katalogpenjualan;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import Adapter.DataHelper;

public class PesanBarangActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private Intent i;

    String[] id,nama,nope,alamat;
    DataHelper dbcenter;
    AutoCompleteTextView textAutoComplete;
    EditText nama_txt,nope_txt,alamat_txt,tanggal_txt;
    protected Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan_barang);

        dbcenter = new DataHelper(this);
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM data_outlet",null);
        id = new String[cursor.getCount()];
        nama = new String[cursor.getCount()];
        nope = new String[cursor.getCount()];
        alamat = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc=0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            id[cc] = cursor.getString(0).toString();
            nama[cc] = cursor.getString(1).toString();
            nope[cc] = cursor.getString(2).toString();
            alamat[cc] = cursor.getString(3).toString();
        }

        textAutoComplete =(AutoCompleteTextView)findViewById(R.id.etIdOutlet);
        nama_txt = (EditText) findViewById(R.id.etnamaOutlet);
        nope_txt = (EditText) findViewById(R.id.ettelponOutlet);
        alamat_txt = (EditText) findViewById(R.id.etalamatOutlet);
        tanggal_txt = (EditText) findViewById(R.id.ettanggalOutlet);

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,id);
        textAutoComplete.setAdapter(adapter);
        textAutoComplete.setThreshold(1);
        textAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SQLiteDatabase db = dbcenter.getReadableDatabase();
                cursor = db.rawQuery("SELECT * FROM data_outlet WHERE id_outlet = '" +
                        textAutoComplete.getText() + "'",null);
                cursor.moveToFirst();
                if (cursor.getCount()>0) {
                    cursor.moveToPosition(0);
                    nama_txt.setText(cursor.getString(1));
                    nope_txt.setText(cursor.getString(2));
                    alamat_txt.setText(cursor.getString(3));

                }
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d/m/yyyy");
                tanggal_txt.setText(simpleDateFormat.format(calendar.getTime()));
            }
        });


        toolbar = (Toolbar) findViewById(R.id.layout_toolbar);
        setSupportActionBar(toolbar);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                //Memeriksa apakah item tersebut dalam keadaan dicek  atau tidak,
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);

                drawerLayout.closeDrawers();


                switch (item.getItemId()) {
                    // pilihan menu item navigasi akan menampilkan pesan toast klik kalian bisa menggantinya
                    //dengan intent activity
                    case R.id.navigation1:
                        Toast.makeText(getApplicationContext(), "Ubah password di klik", Toast.LENGTH_SHORT).show();
                        i = new Intent(getApplicationContext(), UbahPasswordActivity.class);
                        startActivity(i);
                        return true;


                    case R.id.navigation2:
                        Toast.makeText(getApplicationContext(), "data Pesanan di klik", Toast.LENGTH_SHORT).show();
                        i = new Intent(getApplicationContext(), DataPesananActivity.class);
                        startActivity(i);
                        return true;


                    case R.id.navigation3:
                        Toast.makeText(getApplicationContext(), "Ubah harga di klik", Toast.LENGTH_SHORT).show();
                        i = new Intent(getApplicationContext(), UbahHargaActivity.class);
                        startActivity(i);
                        return true;

                    case R.id.navigation4:
                        Toast.makeText(getApplicationContext(), "Logout di klik", Toast.LENGTH_SHORT).show();
                        i = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(i);
                        return true;

                    case R.id.navigation5:
                        Toast.makeText(getApplicationContext(), "Tambah outlet di klik", Toast.LENGTH_SHORT).show();
                        i = new Intent(getApplicationContext(), TambahOutletActivity.class);
                        startActivity(i);
                        return true;


                    default:
                        Toast.makeText(getApplicationContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                        return true;

                }


            }
        });
        //inisialiasi drawer
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.openDrawer,R.string.closeDrawer)

        {
            @Override
            public void onDrawerClosed(View drawerView){
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView){

                super.onDrawerOpened(drawerView);
            }

        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


    }

    public void Katalog(View view) {
        i = new Intent(getApplicationContext(),KatalogActivity.class);
        startActivity(i);
    }

    public void drawerKlik(View view) {
        drawerLayout.openDrawer(Gravity.LEFT);

    }

    public void shopKlik(View view) {
        i = new Intent(getApplicationContext(),PesanBarangActivity.class);
        startActivity(i);
    }

    public void klikProsesPemilihan(View view) {
        i = new Intent(getApplicationContext(),PemilihanBarangActivity.class);
        i.putExtra("idKirim",textAutoComplete.getText().toString());
        startActivity(i);

    }
}
