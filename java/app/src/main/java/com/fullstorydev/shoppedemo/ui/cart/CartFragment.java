package com.fullstorydev.shoppedemo.ui.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.fullstorydev.shoppedemo.R;
import com.fullstorydev.shoppedemo.adapters.CartItemAdapter;
import com.fullstorydev.shoppedemo.data.Item;

public class CartFragment extends Fragment implements CartEventHandlers{
    private CartViewModel cartViewModel;
    private CartItemAdapter mCartItemAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cart, container, false);

        mCartItemAdapter = new CartItemAdapter(this);
        RecyclerView mRecyclerView = root.findViewById(R.id.rv_cart);
        mRecyclerView.setAdapter(mCartItemAdapter);

        root.findViewById(R.id.btn_checkout).setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_navigation_cart_to_checkoutFragment);
        });
        
        return root;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        cartViewModel.getItemList().observe(this.getViewLifecycleOwner(), items -> mCartItemAdapter.setItemList(items));
    }

    public void onClickRemoveFromCart(Item item) {
        cartViewModel.decreaseQuantityInCart(item);
    }
    public void onClickAddToCart(Item item){ cartViewModel.increaseQuantityInCart(item); }
}