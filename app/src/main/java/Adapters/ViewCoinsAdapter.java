package Adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.progtobi.andelabitcoinapp.R;

import java.util.List;

import Model.ExchangeModel;

public class ViewCoinsAdapter extends RecyclerView.Adapter<ViewCoinsAdapter.MyViewHolder> {

    private List<Double> eventslist;
    //private List<EventModel> mFilteredDeveloperList;
    Activity context;
    Filter filter;
    private List<String> mCountries;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView countryname, value;
        ImageView eventimg;

        public MyViewHolder(View view) {
            super(view);

            countryname = (TextView) view.findViewById(R.id.countryname);
            value = (TextView) view.findViewById(R.id.value);
        }
    }


    public ViewCoinsAdapter(List<Double> developerList, List<String> countries, Activity context) {
        this.eventslist = developerList;
        this.mCountries = countries;
        //this.mFilteredDeveloperList = developerList;
        this.context = context;
        //setFilter();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.coincard, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.countryname.setText(mCountries.get(position));
        holder.value.setText(eventslist.get(position).toString());

        /*holder.eventtitle.setText(event.getTitle());
        holder.eventday.setText(event.getDaynum());
        holder.eventmonth.setText(event.getMonth());
        holder.eventloca.setText(event.getLocation());
        //handle image loading using picasso
        Picasso.with(context).load(event.getImadeUrl()).placeholder(R.drawable.eventimg).into(holder.eventimg);*/
    }

    @Override
    public int getItemCount() {
        try {
            return eventslist.size();
        } catch (Exception e) {
            return 0;
        }

    }

}
