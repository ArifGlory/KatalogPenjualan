package Adapter;

/**
 * Created by fujimiya on 10/21/16.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "db_katalog.db";
    private static final int DATABASE_VERSION = 1;
    ArrayList barang = new ArrayList <String>();
    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        String sql = "create table data_outlet(id_outlet char primary key, nama varchar null, nope text null, alamat text null);";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);

        sql = "INSERT INTO data_outlet(id_outlet, nama,nope, alamat) VALUES ('1001', 'Tokopedia', '19940203','Jakarta');";
        db.execSQL(sql);
        sql = "INSERT INTO data_outlet(id_outlet, nama,nope, alamat) VALUES ('1002', 'Tokokita', '199076','Papua');";
        db.execSQL(sql);

        String sql2 = "create table data_barang(id_barang char primary key, nama varchar null, harga integer null);";
        Log.d("Data", "onCreate: " + sql2);
        db.execSQL(sql2);
        isidata();

        for (int a=0; a < barang.size();a++){
            db.execSQL(barang.get(a).toString());
        }
        //db.execSQL(sql2);

        String sql3 = "create table data_penjualan (id_barang char,id_outlet char, nama_barang varchar null, jumlah integer null, total integer null,tanggal varchar null);";
        Log.d("Data", "onCreate: " + sql3);
        db.execSQL(sql3);
        String sql31;
        sql3 = "INSERT INTO data_penjualan(id_barang,id_outlet, nama_barang,jumlah,total,tanggal) VALUES ('1002','1002', 'Ades', '5','65000','30 - 11 - 2016');";
        db.execSQL(sql3);
        //db.execSQL(sql31);

        String sql4 = "create table data_sales (nama_sales varchar,sandi varchar null);";
        Log.d("Data", "onCreate: " + sql4);
        db.execSQL(sql4);
        sql4 = "INSERT INTO data_sales (nama_sales,sandi) VALUES ('sales', 'sales');";
        db.execSQL(sql4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub

    }

    public void isidata(){

        barang.add("INSERT INTO data_barang(id_barang, nama,harga) VALUES ('1002', 'Ades Pet1L', '53000');");
        barang.add("INSERT INTO data_barang(id_barang, nama,harga) VALUES ('1003', 'Ades Pet350ml', '49000');");
        barang.add("INSERT INTO data_barang(id_barang, nama,harga) VALUES ('1004', 'Ades Pet600ml', '46500');");
        barang.add("INSERT INTO data_barang(id_barang, nama,harga) VALUES ('1005', 'Aquarius PET500ml', '52500');");
        barang.add("INSERT INTO data_barang(id_barang, nama,harga) VALUES ('1006', 'AW CAn330ml', '116000');");
        barang.add("INSERT INTO data_barang(id_barang, nama,harga) VALUES ('1007', 'CocaCola PET1L', '100000');");
        barang.add("INSERT INTO data_barang(id_barang, nama,harga) VALUES ('1008', 'CocaCola CAN250ml', '109000');");
        barang.add("INSERT INTO data_barang(id_barang, nama,harga) VALUES ('1009', 'CocaCola CAN330ml', '116000');");
        barang.add("INSERT INTO data_barang(id_barang, nama,harga) VALUES ('1010', 'CocaCola PET350ml', '3000');");
        barang.add("INSERT INTO data_barang(id_barang, nama,harga) VALUES ('1011', 'CocaCola PET250ml', '32000');");
        barang.add("INSERT INTO data_barang(id_barang, nama,harga) VALUES ('1012', 'CocaCola RGB200ml', '49000');");
        barang.add("INSERT INTO data_barang(id_barang, nama,harga) VALUES ('1013', 'CocaCola RGB295ml', '70000');");
        barang.add("INSERT INTO data_barang(id_barang, nama,harga) VALUES ('1014', 'Fanta 1L', '100000');");
        barang.add("INSERT INTO data_barang(id_barang, nama,harga) VALUES ('1015', 'Fanta CAN250ml', '109000');");
        barang.add("INSERT INTO data_barang(id_barang, nama,harga) VALUES ('1016', 'Fanta CAN330ml', '116000');");
        barang.add("INSERT INTO data_barang(id_barang, nama,harga) VALUES ('1017', 'Fanta PET250ml', '32000');");
        barang.add("INSERT INTO data_barang(id_barang, nama,harga) VALUES ('1018', 'Fanta PET350ml', '3000');");
        barang.add("INSERT INTO data_barang(id_barang, nama,harga) VALUES ('1019', 'FantaPutih RGB295ml', '65000');");
        barang.add("INSERT INTO data_barang(id_barang, nama,harga) VALUES ('1020', 'Fanta RGB200ml', '49000');");
        barang.add("INSERT INTO data_barang(id_barang, nama,harga) VALUES ('1021', 'Fanta RGB295ml', '70000');");
        barang.add("INSERT INTO data_barang(id_barang, nama,harga) VALUES ('1022', 'FresGreen PET500ml', '3000');");
        barang.add("INSERT INTO data_barang(id_barang, nama,harga) VALUES ('1023', 'FresGreen PET900ml', '93000');");
        barang.add("INSERT INTO data_barang(id_barang, nama,harga) VALUES ('1024', 'FresHoney PET350ml', '40500');");
        barang.add("INSERT INTO data_barang(id_barang, nama,harga) VALUES ('1025', 'FresJasmine PET350ml', '40500');");
        barang.add("INSERT INTO data_barang(id_barang, nama,harga) VALUES ('1026', 'FresLow PET500ml', '58200');");
        barang.add("INSERT INTO data_barang(id_barang, nama,harga) VALUES ('1027', 'FresMadu PET500ml', '58200');");
        barang.add("INSERT INTO data_barang(id_barang, nama,harga) VALUES ('1028', 'FresMarkisa PET500ml', '58200');");
        barang.add("INSERT INTO data_barang(id_barang, nama,harga) VALUES ('1029', 'FresMelati PET500ml', '58200');");
        barang.add("INSERT INTO data_barang(id_barang, nama,harga) VALUES ('1030', 'FresTea RGBB220ml', '43000');");
        barang.add("INSERT INTO data_barang(id_barang, nama,harga) VALUES ('1031', 'M.MaidAloe PET350ml', '67500');");
        barang.add("INSERT INTO data_barang(id_barang, nama,harga) VALUES ('1032', 'M.MaidApel PET350ml', '67500');");
        barang.add("INSERT INTO data_barang(id_barang, nama,harga) VALUES ('1033', 'M.MaidMango CAN240ml', '73000');");
        barang.add("INSERT INTO data_barang(id_barang, nama,harga) VALUES ('1034', 'M.MaidMango PET350ml', '67500');");
        barang.add("INSERT INTO data_barang(id_barang, nama,harga) VALUES ('1035', 'M.MaidOrange CAN240ml', '73000');");
        barang.add("INSERT INTO data_barang(id_barang, nama,harga) VALUES ('1036', 'M.MaidOrange PET350ml', '67500');");
        barang.add("INSERT INTO data_barang(id_barang, nama,harga) VALUES ('1037', 'M.MaidOrange PET1L', '132000');");
        barang.add("INSERT INTO data_barang(id_barang, nama,harga) VALUES ('1038', 'M.MaidPineapple CAN240ml', '73000');");
        barang.add("INSERT INTO data_barang(id_barang, nama,harga) VALUES ('1039', 'M.MaidTropical PET350ml', '67500');");
        barang.add("INSERT INTO data_barang(id_barang, nama,harga) VALUES ('1040', 'NutriboostOrange PET300ml', '62700');");
        barang.add("INSERT INTO data_barang(id_barang, nama,harga) VALUES ('1041', 'NutriboostStraw PET300ml', '62700');");
        barang.add("INSERT INTO data_barang(id_barang, nama,harga) VALUES ('1042', 'Sprite 1L', '100000');");
        barang.add("INSERT INTO data_barang(id_barang, nama,harga) VALUES ('1043', 'Sprite CAN250ml', '109000');");
        barang.add("INSERT INTO data_barang(id_barang, nama,harga) VALUES ('1044', 'Sprite CAN330ml', '116000');");
        barang.add("INSERT INTO data_barang(id_barang, nama,harga) VALUES ('1045', 'Sprite PET250ml', '32000');");
        barang.add("INSERT INTO data_barang(id_barang, nama,harga) VALUES ('1046', 'Sprite PET350ml', '3000');");
        barang.add("INSERT INTO data_barang(id_barang, nama,harga) VALUES ('1047', 'Sprite RGB200ml', '49000');");
        barang.add("INSERT INTO data_barang(id_barang, nama,harga) VALUES ('1048', 'Sprite RGB295ml', '70000');");
        barang.add("INSERT INTO data_barang(id_barang, nama,harga) VALUES ('1049', 'Zero CAN330ml', '116000');");


        ///////////////////////////ini uji coba ////////////////////////









    }

}