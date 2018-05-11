package com.acgg.chickenfeed;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Prof Ogunjuyigbe on 5/9/2018.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private Context mContext ;
    private List<Feed> FeedLibrary ;


    public RecyclerViewAdapter(Context mContext, List lst) {


        this.mContext = mContext;
        this.FeedLibrary = lst;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.card,parent,false);
        // click listener here
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.name.setText(FeedLibrary.get(position).getName());
        holder.crude_protein.setText(FeedLibrary.get(position).getName());
        holder.ether_extract.setText(FeedLibrary.get(position).getName());
        holder.crude_fibre.setText(FeedLibrary.get(position).getName());
        holder.nitrogen.setText(FeedLibrary.get(position).getName());
        holder.total_ash.setText(FeedLibrary.get(position).getName());
        holder.me.setText(FeedLibrary.get(position).getName());
        holder.calcium.setText(FeedLibrary.get(position).getName());
        holder.phosphorus.setText(FeedLibrary.get(position).getName());
        holder.lysine.setText(FeedLibrary.get(position).getName());
        holder.methionine.setText(FeedLibrary.get(position).getName());
        holder.dry_matter.setText(FeedLibrary.get(position).getName());


    }

    @Override
    public int getItemCount() {
        return FeedLibrary.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, dry_matter, crude_protein, ether_extract, crude_fibre, nitrogen, total_ash, me, calcium, phosphorus,
                lysine, methionine;


        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.ingre_name);
            dry_matter = itemView.findViewById(R.id.value_drymatter);
            crude_protein = itemView.findViewById(R.id.value_crudeprotein);
            ether_extract = itemView.findViewById(R.id.ether_extract);
            crude_fibre = itemView.findViewById(R.id.crude_fibre);
            nitrogen = itemView.findViewById(R.id.value_nitrogenfreeextract);
            total_ash = itemView.findViewById(R.id.value_totalash);
            me = itemView.findViewById(R.id.value_kcal);
            calcium = itemView.findViewById(R.id.value_calcium);
            phosphorus = itemView.findViewById(R.id.value_phosphorus);
            lysine = itemView.findViewById(R.id.value_lysine);
            methionine = itemView.findViewById(R.id.value_methionine);

        }
    }
    }
