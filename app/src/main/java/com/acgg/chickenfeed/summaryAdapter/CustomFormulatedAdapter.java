package com.acgg.chickenfeed.summaryAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.acgg.chickenfeed.CustomListAdapter;
import com.acgg.chickenfeed.Feed;
import com.acgg.chickenfeed.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

public class CustomFormulatedAdapter extends ArrayAdapter<FormulatedDetails>{

    private static final String TAG = "CustomFormulatedAdapter";

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    /**
     * Default constructor for the PersonListAdapter
     * @param context
     * @param resource
     * @param objects
     */
    public CustomFormulatedAdapter(Context context, int resource, ArrayList<FormulatedDetails> objects) {
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
        String ingredient = Objects.requireNonNull(getItem(position).getIngredient());
        String IngredientClass = Objects.requireNonNull(getItem(position)).getIngredientClass();
        String commentFormulated = Objects.requireNonNull(getItem(position)).getCommentFormulated();
        Double crudeProteinFormulated = Objects.requireNonNull(getItem(position)).getCrudeProteinFormulated();
        Double calciumFormulated = Objects.requireNonNull(getItem(position)).getCalciumFormulated();
        Double phosphorusFormulated = Objects.requireNonNull(getItem(position)).getPhosphorusFormulated();
        Double proportion = Objects.requireNonNull(getItem(position)).getProportion();
        Double proportionUnit = Objects.requireNonNull(getItem(position)).getProportionUnit();
        Integer qtySpecified = Objects.requireNonNull(getItem(position)).getQtySpecified();
        Integer qtyAvailable = Objects.requireNonNull(getItem(position)).getQtyAvailable();


        try{

            //create the view result for showing the animation
            final View result;

            //ViewHolder object
            final CustomFormulatedAdapter.ViewHolder holder;

            if(convertView == null){
                LayoutInflater inflater = LayoutInflater.from(mContext);
                convertView = inflater.inflate(mResource, parent, false);
                holder= new CustomFormulatedAdapter.ViewHolder();

                holder.ingredient = convertView.findViewById(R.id.formulate_ingredientName);
                holder.IngredientClass = convertView.findViewById(R.id.valueClass_formulated);
                holder.commentFormulated = convertView.findViewById(R.id.valueComment_formulated);
                holder.crudeProteinFormulated = convertView.findViewById(R.id.valueCrudeProtein_formulated);
                holder.calciumFormulated = convertView.findViewById(R.id.valueCalcium_formulated);
                holder.phosphorusFormulated = convertView.findViewById(R.id.valuePhosphorus_formulated);
                holder.proportion = convertView.findViewById(R.id.valueproportion_100Kg_formulated);
                holder.proportionUnit = convertView.findViewById(R.id.valueProportionKg_formulated);
                holder.qtySpecified = convertView.findViewById(R.id.valueQuantity_specified);
                holder.qtyAvailable = convertView.findViewById(R.id.valueQuantity_formulated);



                result = convertView;

                convertView.setTag(holder);
            }
            else{
                holder = (CustomFormulatedAdapter.ViewHolder) convertView.getTag();
                result = convertView;
            }


            lastPosition = position;

            DecimalFormat decimalFormat = new DecimalFormat("0.00");


            holder.ingredient.setText(ingredient);
            holder.IngredientClass.setText(IngredientClass);
            holder.commentFormulated.setText(commentFormulated);
            holder.crudeProteinFormulated.setText(String.valueOf(crudeProteinFormulated));
            holder.calciumFormulated.setText(String.valueOf(calciumFormulated));
            holder.phosphorusFormulated.setText(String.valueOf(phosphorusFormulated));
            holder.proportion.setText(decimalFormat.format(proportion));
            holder.proportionUnit.setText(decimalFormat.format(proportionUnit));
            holder.qtySpecified.setText(String.valueOf(qtySpecified));
            holder.qtyAvailable.setText(String.valueOf(qtyAvailable));


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
        TextView ingredient, IngredientClass, commentFormulated, crudeProteinFormulated, calciumFormulated, phosphorusFormulated, proportion, proportionUnit, qtySpecified,
                qtyAvailable;
    }
}
