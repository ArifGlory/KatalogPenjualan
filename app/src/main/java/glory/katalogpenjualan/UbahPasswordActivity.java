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
import android.widget.EditText;
import android.widget.Toast;

import Adapter.DataHelper;

public class UbahPasswordActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    Intent i;
    protected Cursor cursor;
    DataHelper dbHelper;
    EditText nama, pass,passbaru;
    private String paslama;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_password);

        dbHelper = new DataHelper(this);
        nama = (EditText) findViewById(R.id.etUser);
        pass = (EditText) findViewById(R.id.etPasslama);
        passbaru = (EditText) findViewById(R.id.etPassbaru);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM data_sales",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0);
            nama.setText(cursor.getString(0).toString());

            paslama = cursor.getString(1).toString();

        }
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
        i = new Intent(this,KatalogActivity.class);
        startActivity(i);
    }

    public void drawerKlik(View view) {
        drawerLayout.openDrawer(Gravity.LEFT);

    }

    public void shopKlik(View view) {
        i = new Intent(getApplicationContext(),PesanBarangActivity.class);
        startActivity(i);
    }

    public void ubah(View view) {

        if (paslama.compareTo(pass.getText().toString().trim())==0 ) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.execSQL("update data_sales set nama_sales='" +
                    nama.getText().toString() + "', sandi='" +
                    passbaru.getText().toString() + "'");
            Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
            finish();
        }
        else {
            Toast.makeText(getApplicationContext(), "Password anda tidak valid", Toast.LENGTH_LONG).show();
        }
    }

    public void batal(View view) {
        finish();
    }
}
