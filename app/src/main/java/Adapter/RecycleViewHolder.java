package Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import glory.katalogpenjualan.R;

/**
 * Created by Glory on 03/10/2016.
 */
public class RecycleViewHolder extends RecyclerView.ViewHolder {

    public TextView txtJumlah,txtHarga;
    public ImageView gmbrList;
    public CheckBox checkBoxJudul;
    public Button btnTambah,btnKurang;

    public RecycleViewHolder(View itemView) {
        super(itemView);

        txtJumlah = (TextView) itemView.findViewById(R.id.txtJumlah);
        txtHarga = (TextView) itemView.findViewById(R.id.txtjudulHarga);
        gmbrList = (ImageView) itemView.findViewById(R.id.listIcon);
        btnTambah = (Button) itemView.findViewById(R.id.btnTambahJumlah);
        btnKurang = (Button) itemView.findViewById(R.id.btnKurangJumlah);
        checkBoxJudul = (CheckBox) itemView.findViewById(R.id.checkJudul);
    }
}
