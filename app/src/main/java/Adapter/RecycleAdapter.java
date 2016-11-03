package Adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;

import glory.katalogpenjualan.R;

/**
 * Created by Glory on 03/10/2016.
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecycleViewHolder> {

    private final Context context;
    LayoutInflater inflater;
    Intent i;
    int videoKirim,gambarKirim;
    private CheckBox cekbok;

    DataHelper dbcenter;
    protected Cursor cursor;
    String idOutlet;
    String[] idBarang,namaBarang,jumlahBarang;
    int[] hargaBarang;
    public static String[] arrId,arrNama;
    public static int[] arrJumlah,arrHarga,arrTotal;
   //public   ArrayList arrID = new ArrayList<String>();
    //public ArrayList arrNama = new ArrayList<String>();
    //public ArrayList arrHarga = new ArrayList<String>();



    //dekalrasi buat List nya
    String[] nama={"Ades PET1L","Ades PET350ml","Ades PET600ml","Aquarius PET500ml","AW CAn330ml","CocaCola PET1L","CocaCola CAN250ml",
            "CocaCola CAN330ml","CocaCola PET350ml","CocaCola PET250ml","CocaCola RGB200ml","CocaCola RGB295ml","Fanta 1L",
            "Fanta CAN250ml","Fanta CAN330ml","Fanta PET250ml","Fanta PET350ml","FantaPutih RGB295ml","Fanta RGB200ml",
            "Fanta RGB295ml","FresGreen PET500ml","FresGreen PET900ml","FresHoney PET350ml","FresJasmine PET350ml","FresLow PET500ml",
            "FresMadu PET500ml","FresMarkisa PET500ml","FresMelati PET500ml","FresTea RGBB220ml","M.MaidAloe PET350ml",
            "M.MaidApel PET350ml","M.MaidMango CAN240ml","M.MaidMango PET350ml","M.MaidOrange CAN240ml","M.MaidOrange PET350ml",
            "M.MaidOrange PET1L","M.MaidPineapple CAN240ml","M.MaidTropical PET350ml","NutriboostOrange PET300ml","NutriboostStraw PET300ml",
            "Sprite 1L","Sprite CAN250ml","Sprite CAN330ml","Sprite PET250ml","Sprite PET350ml","Sprite RGB200ml","Sprite RGB295ml",
            "Zero CAN330ml",};

    int[]icon = {R.drawable.adespet15l,R.drawable.adespet350ml,R.drawable.adespet600ml,R.drawable.aquariuspet500ml,
            R.drawable.awcan330ml,R.drawable.cocacola1l,R.drawable.cocacolacan250ml,R.drawable.cocacolacan330ml,
            R.drawable.cocacolapet350ml,R.drawable.cocacolapet250ml,R.drawable.cocacolargb200ml,
            R.drawable.cocacolargb295ml,R.drawable.fanta1l,R.drawable.fantacan250ml,R.drawable.fantacan330ml,
            R.drawable.fantapet250ml,R.drawable.fantapet350ml,R.drawable.fantaputihrgb295ml,R.drawable.fantargb200ml,
            R.drawable.fantargb295ml,R.drawable.fresgreen500ml,R.drawable.fresgreenpet900ml,R.drawable.freshoneypet350ml,
            R.drawable.fresjasminepet350ml,R.drawable.freskaloripet500ml,R.drawable.fresmadupet500ml,R.drawable.fresmarkisapet500ml,
            R.drawable.fresmelatipet500ml,R.drawable.fresteargb220ml,R.drawable.mmaidaloepet350ml,R.drawable.mmaidapelpet350ml,
            R.drawable.mmaidmangocan240ml,R.drawable.mmaidmangopet350ml,R.drawable.mmaidorangecan240ml,R.drawable.mmaidorangepet350ml,
            R.drawable.mmaidorangepet1l,R.drawable.mmaidpineapplecan240ml,R.drawable.mmaidtropicalpet350ml,R.drawable.nutriboostorangepet300ml,
            R.drawable.nutribooststrawpet300ml,R.drawable.sprite1l,R.drawable.spritecan250ml,R.drawable.spritecan330ml,
            R.drawable.spritepet250ml,R.drawable.spritepet350ml,R.drawable.spritergb200ml,R.drawable.spritergb295ml,
            R.drawable.zerocan330ml,};

    public RecycleAdapter(Context context) {

        dbcenter = new DataHelper(context);
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM data_barang",null);

        arrId = new String[49];
        arrNama = new String[49];
        arrHarga = new int[49];
        arrJumlah = new int[49];
        arrTotal = new int[49];

        idBarang = new String[cursor.getCount()];
        namaBarang = new String[cursor.getCount()];
        hargaBarang = new int[cursor.getCount()];
        cursor.moveToFirst();

        for (int cc=0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            idBarang[cc] = cursor.getString(0).toString();
            namaBarang[cc] = cursor.getString(1).toString();
            hargaBarang[cc] = cursor.getInt(2);
        }


        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_list,parent,false);
        RecycleViewHolder viewHolder = new RecycleViewHolder(v);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(RecycleViewHolder holder, int position) {

        holder.checkBoxJudul.setText(nama[position]);
        //holder.txtListJudul.setText(nama[position]);
        holder.gmbrList.setImageResource(icon[position]);
        holder.txtHarga.setText(String.valueOf(hargaBarang[position] + " x "));


        holder.gmbrList.setOnClickListener(clicklistener);
        holder.checkBoxJudul.setOnClickListener(clicklistener);
        holder.btnTambah.setOnClickListener(listenerTambah);
        holder.btnKurang.setOnClickListener(listenerKurang);

        holder.checkBoxJudul.setTag(holder);
        holder.gmbrList.setTag(holder);
        holder.btnTambah.setTag(holder);
        holder.btnKurang.setTag(holder);

    }

    View.OnClickListener clicklistener = new View.OnClickListener() {


        @Override
        public void onClick(View v) {

            RecycleViewHolder vHolder = (RecycleViewHolder) v.getTag();
            int position = vHolder.getPosition();

            if (vHolder.checkBoxJudul.isChecked() ) {//== true ya gw apus
                //vHolder.checkBoxJudul.setChecked(true);
                vHolder.btnTambah.setEnabled(true);
                vHolder.btnKurang.setEnabled(true);

               // arrID.add(idBarang[position]);
                //arrNama.add(namaBarang[position]);
                //arrHarga.add(hargaBarang[position]);
                arrId[position] = idBarang[position];
                arrNama[position] = namaBarang[position];
                //arrHarga[position] = hargaBarang[position];
                arrJumlah[position]=0;
                vHolder.txtJumlah.setText(""+arrJumlah[position]);
                //Toast.makeText(context.getApplicationContext(), "" + arrId[position], Toast.LENGTH_SHORT).show();
                //Log.i("id barang nya = ", arrId[position]);
                //Toast.makeText(context.getApplicationContext(), "" + arrNama[position], Toast.LENGTH_SHORT).show();
                //Log.i("nama barang nya = ", arrNama[position]);

            } else {
                vHolder.btnTambah.setEnabled(false);
                vHolder.btnKurang.setEnabled(false);
                //vHolder.checkBoxJudul.setChecked(false);
                arrId[position] = "";
                arrNama[position] = null;
                arrJumlah[position]=0;
                vHolder.txtJumlah.setText(""+arrJumlah[position]);
                arrTotal[position]=0;

              //  Log.i("id barang nya = ",arrId[position]);
              //  Log.i("nama barang nya = ",arrNama[position]);

                //arrID.remove(idBarang[position]);
                //arrNama.remove(namaBarang[position]);
                //arrHarga.remove(hargaBarang[position]);
            }

        }
    };


    View.OnClickListener listenerTambah = new View.OnClickListener() {


        @Override
        public void onClick(View v) {

            RecycleViewHolder vHolder1 = (RecycleViewHolder) v.getTag();
            int position = vHolder1.getPosition();


            arrJumlah[position] = arrJumlah[position] + 1;
            vHolder1.txtJumlah.setText(""+ arrJumlah[position]);
            arrTotal[position] = arrJumlah[position] * hargaBarang[position];
            //Toast.makeText(context.getApplicationContext(), "" + arrTotal[position], Toast.LENGTH_SHORT).show();


        }
    };

    View.OnClickListener listenerKurang = new View.OnClickListener() {


        @Override
        public void onClick(View v) {

            RecycleViewHolder vHolder3 = (RecycleViewHolder) v.getTag();
            int position = vHolder3.getPosition();

            if (arrJumlah[position] > 0) {
                arrJumlah[position] = arrJumlah[position] - 1;
                vHolder3.txtJumlah.setText("" + arrJumlah[position]);
                arrTotal[position] = arrJumlah[position] * hargaBarang[position];
               // Toast.makeText(context.getApplicationContext(), "" + arrTotal[position], Toast.LENGTH_SHORT).show();
            }



        }
    };

    public int getItemCount() {
        return nama.length;
    }
}
