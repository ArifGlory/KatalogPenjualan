package glory.katalogpenjualan;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import Adapter.DataHelper;

public class PenjelasanPesananActivity extends AppCompatActivity {

    TextView txtid,txtnama,txtnope,txtalamat;

    String[] isi;
    String[] listArray={"Asus","Acer","Apple","Samsung","Thoshiba","Sony","Xiomi","Motorola"};
    DataHelper dbcenter;
    protected Cursor cursor;
    int jumlahSemua;
    TextView txtTotalsemua;

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    Intent i;
    private DatabaseReference root;
    private String temp_namaOutlet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penjelasan_pesanan);
       // jumlahSemua = new int[49];
        txtTotalsemua = (TextView) findViewById(R.id.txtJumlahTotal);
        root = FirebaseDatabase.getInstance().getReference().child("outlet");

        txtid = (TextView) findViewById(R.id.txtpenjelID);
        txtnama = (TextView) findViewById(R.id.txtpenjelNama);
        txtnope = (TextView) findViewById(R.id.txtpenjelTelpon);
        txtalamat = (TextView) findViewById(R.id.txtpenjelAlamat);
        txtid.setText(getIntent().getExtras().get("id").toString());
        txtnama.setText(getIntent().getExtras().get("nama").toString());
        txtnope.setText(getIntent().getExtras().get("nope").toString());
        txtalamat.setText(getIntent().getExtras().get("alamat").toString());

        dbcenter = new DataHelper(this);
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM data_penjualan WHERE id_outlet = '" +getIntent().getExtras().get("id").toString()+ "'",null);

        isi = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc=0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            isi[cc] =cursor.getString(2).toString()+"       "+cursor.getString(3).toString()+"      "+cursor.getString(4).toString();
            jumlahSemua = jumlahSemua + Integer.parseInt(cursor.getString(4).toString());
        }

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,isi);
        ListView listview =(ListView) findViewById(R.id.listView1);
        listview.setAdapter(adapter);
        txtTotalsemua.setText("Total semua : "+jumlahSemua);

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


        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Toast.makeText(getBaseContext(), "Data di Input", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {


            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    public void drawerKlik(View view) {
        drawerLayout.openDrawer(Gravity.LEFT);

    }

    public void Katalog(View view) {
        i = new Intent(this,KatalogActivity.class);
        startActivity(i);
    }

    public void shopKlik(View view) {
        i = new Intent(this,PesanBarangActivity.class);
        startActivity(i);
    }

    public void klikKirimFirebase(View view) {

        dialogPemastian();
        

    }

    private void dialogPemastian(){

        new AlertDialog.Builder(this).setMessage("Apakah anda Yakin ?")
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Map<String, Object> map = new HashMap<String, Object>();
                        temp_namaOutlet = txtnama.getText().toString();
                        root.push().child(temp_namaOutlet);
                        root.updateChildren(map);
                        DatabaseReference namaOutlet_root = root.child(temp_namaOutlet);
                        Map<String, Object> map2 = new HashMap<String, Object>();
                        map2.put("id", txtid.getText().toString());
                        map2.put("nama", txtnama.getText().toString());
                        map2.put("nope", txtnope.getText().toString());
                        map2.put("alamat", txtalamat.getText().toString());
                        map2.put("totalBayar", txtTotalsemua.getText().toString());
                        for (int a=0;a<isi.length;a++){
                            map2.put("list"+a,isi[a].toString());
                        }
                        namaOutlet_root.updateChildren(map2);


                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(getBaseContext(),MainActivity.class);
                        startActivity(i);
                    }
                }).show();
    }
}
