package com.toulzx.stitp_module_user.adpter;

import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.toulzx.stitp_module_user.MyViewModel;
import com.toulzx.stitp_module_user.R;
import com.toulzx.stitp_module_user.entity.Data;

import java.util.ArrayList;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {
    private MyViewModel myViewModel;

    private List<Data> dataFromUser = new ArrayList<>();

    public DataAdapter(MyViewModel myViewModel) {
        this.myViewModel = myViewModel;
    }

    public static class DataViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTime;
        private TextView tvCupboard;
        private TextView tvCableIndex;
        private TextView tvCableContent;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            // bind
            this.tvTime = itemView.findViewById(R.id.tv_data_item_time);
            this.tvCupboard = itemView.findViewById(R.id.tv_data_item_cupboard);
            this.tvCableIndex = itemView.findViewById(R.id.tv_data_item_index);
            this.tvCableContent = itemView.findViewById(R.id.tv_data_item_content);
        }
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // initialize holder
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_data, parent, false);
        return new DataViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        // set content of textView
        Data data = dataFromUser.get(position);
        holder.tvTime.setText(data.getTime());
        holder.tvCupboard.setText(data.getCupboardName());
        holder.tvCableIndex.setText(data.getCableIndex());
        holder.tvCableContent.setText(data.getCableContent());
    }

    @Override
    public int getItemCount() {
        return dataFromUser.size();
    }

    public void setDataFromUser(List<Data> dataFromUser) {
        this.dataFromUser = dataFromUser;
    }
}
