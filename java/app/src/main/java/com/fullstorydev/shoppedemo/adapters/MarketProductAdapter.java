package com.fullstorydev.shoppedemo.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.fullstorydev.shoppedemo.R;
import com.fullstorydev.shoppedemo.data.Product;
import com.fullstorydev.shoppedemo.databinding.ListItemMarketBinding;

import java.util.ArrayList;
import java.util.List;

public class MarketProductAdapter extends RecyclerView.Adapter<MarketProductAdapter.MarketProductViewHolder> {
    private List<Product> mProductList;
    private LayoutInflater layoutInflater;

    public MarketProductAdapter() {
        mProductList = new ArrayList<>();
    }

    @NonNull
    @Override
    public MarketProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        // binding class automatically generated by Data Binding Library from list_item_market
        ListItemMarketBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.list_item_market, parent, false);
        return new MarketProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MarketProductViewHolder holder, int position) {
        holder.bind(mProductList.get(position));
    }

    @Override
    public int getItemCount() {
        if(mProductList==null) return 0;
        return mProductList.size();
    }

    public void setProductList(List<Product> products){
        //TODO: check diff and use callback & DiffUtil.DiffResult to avoid recreating every view
        mProductList = products;
        notifyDataSetChanged();
    }

    class MarketProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ListItemMarketBinding binding;
        protected MarketProductViewHolder(@NonNull ListItemMarketBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Product product) {
            binding.setProduct(product);
            binding.executePendingBindings();
        }

        @Override
        public void onClick(View v) {
            // getting clicked product from adapter position
            Product product = mProductList.get(getAdapterPosition());
            try {
                //TODO: When recyclerview is clicked, get the product and perform action here, i.e. show product details fragment
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}