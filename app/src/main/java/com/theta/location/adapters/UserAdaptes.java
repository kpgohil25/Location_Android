package com.theta.location.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.theta.location.R;
import com.theta.location.models.DataList;

import java.util.List;

public class UserAdaptes extends RecyclerView.Adapter<UserAdaptes.CustomViewHolder> {

    private List<DataList> dataList;
    private Context context;

    public UserAdaptes(Context context, List<DataList> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView txtName, txtEmail;
        ImageView imgIcon;


        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            txtName = mView.findViewById(R.id.txtName);
            imgIcon = mView.findViewById(R.id.imgIcon);
            txtEmail = mView.findViewById(R.id.txtEmail);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_row, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        holder.txtName.setText(dataList.get(position).getFirstName() + " " + dataList.get(position).getLastName());
        holder.txtEmail.setText(dataList.get(position).getEmail());

        //Picasso
        Picasso.with(context)
                .load(dataList.get(position).getAvatar())
                .into(holder.imgIcon, new Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError() {
                    }
                });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}