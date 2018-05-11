package com.acgg.chickenfeed;

import android.view.View;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.ArrayList;

public class CustomListAdapter  extends ArrayAdapter<Feed> {

    private static final String TAG = "CustomListAdapter";

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    /**
     * Default constructor for the PersonListAdapter
     * @param context
     * @param resource
     * @param objects
     */
    public CustomListAdapter(Context context, int resource, ArrayList<Feed> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //get the persons information
        String name = getItem(position).getName();
        double dry_matter = getItem(position).getdry_matter();
        double crude_protein= getItem(position).getcrude_protein();
        double ether_extract = getItem(position).getether_extract();
        double crude_fibre = getItem(position).getcrude_fibre();
        double nitrogen = getItem(position).getnitrogen();
        double total_ash = getItem(position).gettotal_ash();
        double me = getItem(position).getme();
        double calcium = getItem(position).getcalcium();
        double phosphorus = getItem(position).getphosphorus();
        double lysine = getItem(position).getlysine();
        double methionine = getItem(position).getmethionine();
        String group = getItem(position).getgroup();

        try{

            //create the view result for showing the animation
            final View result;

            //ViewHolder object
            final ViewHolder holder;

            if(convertView == null){
                LayoutInflater inflater = LayoutInflater.from(mContext);
                convertView = inflater.inflate(mResource, parent, false);
                holder= new ViewHolder();

                holder.name = convertView.findViewById(R.id.ingre_name);
                holder.dry_matter= convertView.findViewById(R.id.value_drymatter);
                holder.ether_extract = convertView.findViewById(R.id.value_etherextract);
                holder.crude_fibre = convertView.findViewById(R.id.value_crudefibre);
                holder.lysine = convertView.findViewById(R.id.value_lysine);
                holder.methionine = convertView.findViewById(R.id.value_methionine);
                holder.calcium = convertView.findViewById(R.id.value_calcium);
                holder.phosphorus = convertView.findViewById(R.id.value_phosphorus);
                holder.crude_protein = convertView.findViewById(R.id.value_crudeprotein);
                holder.nitrogen = convertView.findViewById(R.id.value_nitrogenfreeextract);
                holder.total_ash = convertView.findViewById(R.id.value_totalash);
                holder.me = convertView.findViewById(R.id.value_kcal);
                holder.methionine = convertView.findViewById(R.id.value_methionine);
                holder.group = convertView.findViewById(R.id.group);


                result = convertView;

                convertView.setTag(holder);
            }
            else{
                holder = (ViewHolder) convertView.getTag();
                result = convertView;
            }


            //   Animation animation = AnimationUtils.loadAnimation(mContext,
            //           (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
            //  result.startAnimation(animation);
            lastPosition = position;

            holder.name.setText(name);
            holder.dry_matter.setText(Double.toString(dry_matter));
            holder.crude_protein.setText(Double.toString(crude_protein));
            holder.ether_extract.setText(Double.toString(ether_extract));
            holder.crude_fibre.setText(Double.toString(crude_fibre));
            holder.nitrogen.setText(Double.toString(nitrogen));
            holder.total_ash.setText(Double.toString(total_ash));
            holder.me.setText(Double.toString(me));
            holder.calcium.setText(Double.toString(calcium));
            holder.phosphorus.setText(Double.toString(phosphorus));
            holder.lysine.setText(Double.toString(lysine));
            holder.methionine.setText(Double.toString(methionine));
            holder.group.setText(group);

            return convertView;
        }catch (IllegalArgumentException e){
            Log.e(TAG, "getView: IllegalArgumentException: " + e.getMessage() );
            return convertView;
        }

    }
    /**
     * Holds variables in a View
     */
    private static class ViewHolder {
        TextView name, dry_matter, crude_protein, ether_extract, crude_fibre, nitrogen, total_ash, me,
        lysine, methionine, calcium, phosphorus, group;
    }
}
