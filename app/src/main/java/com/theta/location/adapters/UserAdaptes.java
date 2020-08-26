package com.theta.location.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.theta.location.BR;
import com.theta.location.R;
import com.theta.location.databinding.CustomRowBinding;
import com.theta.location.viewmodel.UserViewModel;

import java.util.List;

public class UserAdaptes extends RecyclerView.Adapter<UserAdaptes.CustomViewHolder> {

    private List<UserViewModel> dataList;
    private Context context;

    public UserAdaptes(Context context, List<UserViewModel> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        CustomRowBinding customRowBinding;

        CustomViewHolder(CustomRowBinding customRowBinding) {
            super(customRowBinding.getRoot());
            this.customRowBinding = customRowBinding;
        }

        public void bind(Object obj) {
            customRowBinding.setVariable(BR.model, obj);
            customRowBinding.executePendingBindings();
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

//        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//        View view = layoutInflater.inflate(R.layout.custom_row, parent, false);

        CustomRowBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.custom_row, parent, false);

        return new CustomViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        UserViewModel dataModel = dataList.get(position);
        holder.customRowBinding.setModel(dataModel);
        holder.bind(dataModel);
//
//        holder.txtName.setText(dataList.get(position).getFirstName() + " " + dataList.get(position).getLastName());
//        holder.txtEmail.setText(dataList.get(position).getEmail());
//
//        //Picasso
//        Picasso.with(context)
//                .load(dataList.get(position).getAvatar())
//                .into(holder.imgIcon, new Callback() {
//                    @Override
//                    public void onSuccess() {
//                    }
//
//                    @Override
//                    public void onError() {
//                    }
//                });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @BindingAdapter({"src"})
    public void setImageSrc(ImageView view, String imageUrl) {

        //Picasso
        Picasso.with(context)
                .load(imageUrl)
                .into(view, new Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError() {
                    }
                });
    }
}