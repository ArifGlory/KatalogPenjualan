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
public class RecycleViewHolderPesanan extends RecyclerView.ViewHolder {

    public TextView txtNamaOutlet;
    public ImageView gmbrList;


    public RecycleViewHolderPesanan(View itemView) {
        super(itemView);

        txtNamaOutlet = (TextView) itemView.findViewById(R.id.txtJudul);
        gmbrList = (ImageView) itemView.findViewById(R.id.listIcon);

    }
}
