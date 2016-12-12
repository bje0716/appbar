package com.grapefruit.appbar;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grapefruit.appbar.databinding.RecyclerItemBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GrapeFruit on 2016-12-12.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

    List<RecyclerItem> items = new ArrayList<>();

    public RecyclerAdapter() {
        super();
        for (int i = 0; i < 20; i++) {
            items.add(new RecyclerItem("asdf"));
        }
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        RecyclerItem item = items.get(position);
        holder.binding.recyclerTxt.setText(item.getItem());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private RecyclerItemBinding binding;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

    public class RecyclerItem {

        String item;

        public RecyclerItem(String item) {
            this.item = item;
        }

        public String getItem() {
            return item;
        }
    }
}
