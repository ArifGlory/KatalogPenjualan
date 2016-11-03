package glory.katalogpenjualan;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import Adapter.RecycleAdapterPesanan;

public class DataPesananActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pesanan);

        recyclerView = (RecyclerView) findViewById(R.id.recycleView2);


        RecycleAdapterPesanan adapterPesanan = new RecycleAdapterPesanan(this);
        //membuat adapter baru untuk recycle viewnya
        recyclerView.setAdapter(adapterPesanan);
        ////menset nilaia dapter
        //recyclerView.setHasFixedSize(true);

        //menset ukuran
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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
                        Toast.makeText(getApplicationContext(), "Logout di klik", Toast.LENGTH_SHORT).show();
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
}
