package Adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import glory.katalogpenjualan.PenjelasanPesananActivity;
import glory.katalogpenjualan.R;

/**
 * Created by Glory on 03/10/2016.
 */
public class RecycleAdapterPesanan extends RecyclerView.Adapter<RecycleViewHolderPesanan> {

    private final Context context;
    LayoutInflater inflater;
    Intent i;
    int videoKirim,gambarKirim;
    private CheckBox cekbok;
    TextView txtListJudul;

    DataHelper dbcenter;
    protected Cursor cursor;

    //dekalrasi buat List nya
    String[] id,nama,nope,alamat;//={"Outlet 1 ","Outlet 2","Outlet 3"};

    int[]icon = {R.drawable.greencircle,R.drawable.greencircle,R.drawable.greencircle,R.drawable.greencircle,R.drawable.greencircle,
            R.drawable.greencircle,R.drawable.greencircle};
    int ic_aja = R.drawable.greencircle;

    public RecycleAdapterPesanan(Context context) {

        dbcenter = new DataHelper(context);
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
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecycleViewHolderPesanan onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_list2,parent,false);
        RecycleViewHolderPesanan viewHolderPesanan = new RecycleViewHolderPesanan(v);

        return viewHolderPesanan;

    }

    @Override
    public void onBindViewHolder(RecycleViewHolderPesanan holder, int position) {

        //holder.checkBoxJudul.setText(nama[position]);
        holder.txtNamaOutlet.setText(nama[position]);
        holder.gmbrList.setImageResource(ic_aja);

        holder.gmbrList.setOnClickListener(clicklistener);
        holder.txtNamaOutlet.setOnClickListener(clicklistener);

        holder.txtNamaOutlet.setTag(holder);
        holder.gmbrList.setTag(holder);

    }

    View.OnClickListener clicklistener = new View.OnClickListener() {


        @Override
        public void onClick(View v) {

            RecycleViewHolderPesanan vHolder = (RecycleViewHolderPesanan) v.getTag();
            int position = vHolder.getPosition();
            Toast.makeText(context.getApplicationContext(),nama[position], Toast.LENGTH_SHORT).show();
            Intent i = new Intent(context.getApplicationContext(),PenjelasanPesananActivity.class);
            i.putExtra("id",id[position].toString());
            i.putExtra("nama",nama[position].toString());
            i.putExtra("nope",nope[position].toString());
            i.putExtra("alamat",alamat[position].toString());
            context.startActivity(i);
        }
    };


    public int getItemCount() {
        return nama.length;
    }
}
