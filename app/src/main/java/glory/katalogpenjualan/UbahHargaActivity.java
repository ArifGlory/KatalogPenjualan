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
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import Adapter.DataHelper;

public class UbahHargaActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private Intent i;

    String[] id,nama_barang,harga;
    DataHelper dbcenter;
    AutoCompleteTextView textAutoCompleter;
    EditText harga_lama,harga_baru;
    protected Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_harga);

        toolbar = (Toolbar) findViewById(R.id.layout_toolbar);
        setSupportActionBar(toolbar);

        dbcenter = new DataHelper(this);
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM data_barang",null);
        id = new String[cursor.getCount()];
        nama_barang = new String[cursor.getCount()];
        harga = new String[cursor.getCount()];

        cursor.moveToFirst();
        for (int cc=0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            id[cc] = cursor.getString(0).toString();
            nama_barang[cc] = cursor.getString(1).toString();
            harga[cc] = cursor.getString(2).toString();

        }

        textAutoCompleter =(AutoCompleteTextView)findViewById(R.id.etaubarang);
        harga_lama = (EditText) findViewById(R.id.etHargalama);
        harga_baru = (EditText) findViewById(R.id.etHargabaru);

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,nama_barang);
        textAutoCompleter.setAdapter(adapter);
        textAutoCompleter.setThreshold(1);

        textAutoCompleter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int ini, long l) {
                SQLiteDatabase db = dbcenter.getReadableDatabase();
                cursor = db.rawQuery("SELECT * FROM data_barang WHERE nama = '" +
                        textAutoCompleter.getText() + "'",null);
                cursor.moveToFirst();
                if (cursor.getCount()>0) {
                    cursor.moveToPosition(0);
                    harga_lama.setText(cursor.getString(2));

                }

            }
        });
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

    public void batal(View view) {
        finish();
    }

    public void ubahharga(View view) {
        SQLiteDatabase db = dbcenter.getWritableDatabase();
        db.execSQL("update data_barang set harga='"+
                harga_baru.getText().toString() + "' where nama='" +
                textAutoCompleter.getText().toString()+"'");
        Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
        finish();
    }
}
