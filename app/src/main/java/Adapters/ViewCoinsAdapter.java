package Adapters;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.progtobi.andelabitcoinapp.R;

import java.util.List;

import Model.ViewModel;

public class ViewCoinsAdapter extends RecyclerView.Adapter<ViewCoinsAdapter.MyViewHolder> {

    private List<ViewModel> eventslist;
    Activity context;
    String fragtype;
    EditText amttocon;
    TextView countryequi;
    ImageView coinView;
    AlertDialog.Builder builder;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView countryname, value;

        public MyViewHolder(View view) {
            super(view);

            countryname = view.findViewById(R.id.countryname);
            value = view.findViewById(R.id.value);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        final ViewModel clickedDataItem = eventslist.get(pos);
                        Float rate = clickedDataItem.getcValue();
                        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle(clickedDataItem.getcName());
                        LayoutInflater inflater = context.getLayoutInflater();
                        View view1 = inflater.inflate(R.layout.coinconversion, null);
                        countryequi = view1.findViewById(R.id.countryequi);
                        amttocon = view1.findViewById(R.id.amttocon);
                        coinView = view1.findViewById(R.id.coinimg);
                        if (fragtype.equals("btc")) {
                            coinView.setImageResource(R.drawable.btcimage2);
                        } else {
                            coinView.setImageResource(R.drawable.ethimg);
                        }
                        countryequi.setText(clickedDataItem.getcName() + " " + clickedDataItem.getcValue());
                        builder.setView(view1);
                        builder.setPositiveButton("CONVERT", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                double converted = Double.parseDouble(amttocon.getText().toString()) / clickedDataItem.getcValue();
                                if (fragtype.equals("btc")) {
                                    builder.setIcon(R.drawable.btcimage);
                                    displayAlert(String.valueOf(converted), "btc");
                                } else if (fragtype.equals("eth")) {
                                    builder.setIcon(R.drawable.ethimg);
                                    displayAlert(String.valueOf(converted), "eth");
                                }

                            }
                        });

                        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        builder.show();

                    }
                }
            });
        }
    }


    public ViewCoinsAdapter(List<ViewModel> viewModelList, Activity context, String fragtype) {
        this.eventslist = viewModelList;
        this.context = context;
        this.fragtype = fragtype;
        builder = new AlertDialog.Builder(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.coincard, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        ViewModel item = eventslist.get(position);
        holder.countryname.setText(item.getcName());
        holder.value.setText(item.getcValue().toString());

    }

    @Override
    public int getItemCount() {
        try {
            return eventslist.size();
        } catch (Exception e) {
            return 0;
        }

    }

    public void displayAlert(String message, String fragtype) {
        if (fragtype.equals("btc")) {
            builder.setIcon(R.drawable.btcimage);
            builder.setMessage(message + " BTC");
        } else {
            builder.setIcon(R.drawable.ethimg);
            builder.setMessage(message + " ETH");
        }
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

}
