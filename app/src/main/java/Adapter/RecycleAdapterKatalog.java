package Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import glory.katalogpenjualan.R;

/**
 * Created by Glory on 03/10/2016.
 */
public class RecycleAdapterKatalog extends RecyclerView.Adapter<RecycleViewHolderKatalog> {

    private final Context context;
    LayoutInflater inflater;
    Intent i;
    int videoKirim,gambarKirim;
    private CheckBox cekbok;
    TextView txtListJudul;

    //dekalrasi buat List nya
    String[] nama={"Outlet 1 ","Outlet 2","Outlet 3"};
    String[] harga1={"x12 32000","x12 40500","x12 43000","x12 52500","x12 53000","x12 58200","x12 62700",
                    "x12 67500","x12 73000","x12 96000"};
    String[] harga2={"x12 100000","x12 132000","x24 43000","x24 46500","x24 49000","x24 65000",
                    "x24 70000","x24 109000","x24 116000","x24 49000"};

    int[]icon1 = {R.drawable.x12_32000,R.drawable.x12_40500,R.drawable.x12_43000,
            R.drawable.x12_52500,R.drawable.x12_53000,R.drawable.x12_58200,R.drawable.x12_62700,
            R.drawable.x12_67500,R.drawable.x12_73000,R.drawable.x12_96000,};

    int[]icon2 = {R.drawable.x12_100000,R.drawable.x12_132000,R.drawable.x24_43000,R.drawable.x24_46500,
            R.drawable.x24_49000,R.drawable.x24_65000,R.drawable.x24_70000,R.drawable.x24_109000,
            R.drawable.x24_116000,R.drawable.x24ades_49000,};

    public RecycleAdapterKatalog(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecycleViewHolderKatalog onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_listkatalog,parent,false);
        RecycleViewHolderKatalog viewHolderKatalog = new RecycleViewHolderKatalog(v);

        return viewHolderKatalog;

    }

    @Override
    public void onBindViewHolder(RecycleViewHolderKatalog holder, int position) {

        //holder.checkBoxJudul.setText(nama[position]);


        holder.txtharga1.setText(harga1[position]);
        holder.txtharga2.setText(harga2[position]);
        holder.gmbrList1.setImageResource(icon1[position]);
        holder.gmbrList2.setImageResource(icon2[position]);

        //holder.gmbrList.setOnClickListener(clicklistener);
        //holder.txtNamaOutlet.setOnClickListener(clicklistener);

        holder.txtharga1.setTag(holder);
        holder.txtharga2.setTag(holder);
        holder.gmbrList1.setTag(holder);
        holder.gmbrList2.setTag(holder);

    }

    /*View.OnClickListener clicklistener = new View.OnClickListener() {


        @Override
        public void onClick(View v) {

            RecycleViewHolder vHolder = (RecycleViewHolder) v.getTag();
            int position = vHolder.getPosition();


            switch (position)
            {

                case 0:
                    //videoKirim = R.raw.doa_bismillah;
                    //gambarKirim = R.drawable.arti_bismilah;
                    //i = new Intent(context, PenjelasanDoa.class);
                    //i.putExtra("videoKirim_Bismilah",videoKirim);
                   // i.putExtra("gambarKirim_bismilah1",gambarKirim);
                    //context.startActivity(i);

                    break;



            }

        }
    };*/


    public int getItemCount() {
        return harga1.length;
    }
}
