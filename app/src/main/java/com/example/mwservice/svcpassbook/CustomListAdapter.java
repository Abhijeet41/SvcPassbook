package com.example.mwservice.svcpassbook;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mwservice on 04-04-2018.
 */

public class CustomListAdapter extends ArrayAdapter<DataModel> implements View.OnClickListener{

    private ArrayList<DataModel> dataset;
    Context mContext;


    public CustomListAdapter(ArrayList<DataModel> dataModels, Context applicationContext) {
        super(applicationContext,R.layout.single_list, dataModels);
        this.dataset = dataModels;
        this.mContext = applicationContext;
    }

    public static class ViewHolder
    {
        TextView date;TextView particular;TextView transactionId;TextView withdraw;TextView deposit;TextView balance;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        DataModel dataModel = getItem(position);
        ViewHolder holder;

        final View result;

        if (convertView == null)
        {
            holder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.single_list,parent,false);
            holder.date = (TextView) convertView.findViewById(R.id.textView21);
            holder.particular = (TextView) convertView.findViewById(R.id.textView20);
            holder.transactionId = (TextView) convertView.findViewById(R.id.textView19);
            holder.withdraw = (TextView) convertView.findViewById(R.id.textView18);
            holder.deposit = (TextView) convertView.findViewById(R.id.textView17);
            holder.balance = (TextView) convertView.findViewById(R.id.textView16);
            result = convertView;
            convertView.setTag(holder);
        }else
        {
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }
        holder.date.setText(dataModel.getDate());
        holder.particular.setText(dataModel.getParticular());
        holder.transactionId.setText(dataModel.getTransactionId());
        holder.withdraw.setText(dataModel.getWithdraw());
        holder.deposit.setText(dataModel.getDeposit());
        holder.balance.setText(dataModel.getBalance());

        return convertView;
    }

    @Override
    public void onClick(View v) {

    }
}
