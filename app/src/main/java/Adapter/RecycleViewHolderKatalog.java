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
public class RecycleViewHolderKatalog extends RecyclerView.ViewHolder {

    public TextView txtharga1,txtharga2;
    public ImageView gmbrList1,gmbrList2;

    public RecycleViewHolderKatalog(View itemView) {
        super(itemView);

        txtharga1 = (TextView) itemView.findViewById(R.id.txtHarga1);
        txtharga2 = (TextView) itemView.findViewById(R.id.txtHarga2);
        gmbrList1 = (ImageView) itemView.findViewById(R.id.listIcon);
        gmbrList2 = (ImageView) itemView.findViewById(R.id.listIcon2);

    }
}
