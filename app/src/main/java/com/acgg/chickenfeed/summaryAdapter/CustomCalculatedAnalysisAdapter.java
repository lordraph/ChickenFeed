package com.acgg.chickenfeed.summaryAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.acgg.chickenfeed.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

public class CustomCalculatedAnalysisAdapter extends ArrayAdapter<CalculatedAnalysis>{

    private static final String TAG = "CustomCalculatedAnalysisAdapter";

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    /**
     * Default constructor for the PersonListAdapter
     * @param context
     * @param resource
     * @param objects
     */
    public CustomCalculatedAnalysisAdapter(Context context, int resource, ArrayList<CalculatedAnalysis> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    /*
    *  private String ingredient;

    * */

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //get the persons information
        Double crude_protein = Objects.requireNonNull(getItem(position)).getCrudeProtein();
        Double calcium = Objects.requireNonNull(getItem(position)).getCalcium();
        Double phosphorus = Objects.requireNonNull(getItem(position)).getPhosphorus();
        Double energy = Objects.requireNonNull(getItem(position)).getEnergy();


        try{

            //create the view result for showing the animation
            final View result;

            //ViewHolder object
            final CustomCalculatedAnalysisAdapter.ViewHolder holder;

            if(convertView == null){
                LayoutInflater inflater = LayoutInflater.from(mContext);
                convertView = inflater.inflate(mResource, parent, false);
                holder= new CustomCalculatedAnalysisAdapter.ViewHolder();

                holder.crude_protein = convertView.findViewById(R.id.value_analyzed_crudeProtein);
                holder.calcium = convertView.findViewById(R.id.value_analyzed_calcium);
                holder.phosphorus = convertView.findViewById(R.id.value_analyzed_phosphorus);
                holder.energy = convertView.findViewById(R.id.value_analyzed_energy);



                result = convertView;

                convertView.setTag(holder);
            }
            else{
                holder = (CustomCalculatedAnalysisAdapter.ViewHolder) convertView.getTag();
                result = convertView;
            }


            lastPosition = position;

            DecimalFormat decimalFormat = new DecimalFormat("0.00");


            holder.crude_protein.setText(decimalFormat.format(crude_protein));
            holder.calcium.setText(decimalFormat.format(calcium));
            holder.phosphorus.setText(decimalFormat.format(phosphorus));
            holder.energy.setText(decimalFormat.format(energy));


            return convertView;
        }catch (IllegalArgumentException e){
//            Log.e(TAG, "getView: IllegalArgException: " + e.getMessage() );
            return convertView;
        }

    }
    /**
     * Holds variables in a View
     */
    private static class ViewHolder {
        TextView crude_protein, calcium, phosphorus, energy;
    }
}
