package com.example.android;

import android.content.Context;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.*;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private final Context context;
    private final List<Cart> cartList;
    private final Runnable onCartChanged;

    public CartAdapter(Context context, List<Cart> cartList, Runnable onCartChanged) {
        this.context = context;
        this.cartList = cartList;
        this.onCartChanged = onCartChanged;
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name, price, txtQuantity;
        Button btnDelete, btnPlus, btnMinus;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgCart);
            name = itemView.findViewById(R.id.txtCartName);
            price = itemView.findViewById(R.id.txtCartPrice);
            txtQuantity = itemView.findViewById(R.id.txtQuantity);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnPlus = itemView.findViewById(R.id.btnPlus);
            btnMinus = itemView.findViewById(R.id.btnMinus);
        }
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Cart cart = cartList.get(position);

        Glide.with(context)
                .load(cart.getImageUrl())
                .into(holder.img);

        holder.name.setText(cart.getName());
        holder.price.setText(cart.getPrice());
        holder.txtQuantity.setText("x" + cart.getQuantity());

        holder.btnPlus.setOnClickListener(v -> {
            CartManager.increaseQty(position);
            notifyItemChanged(position);
            onCartChanged.run();
        });

        holder.btnMinus.setOnClickListener(v -> {
            CartManager.decreaseQty(position);
            notifyItemChanged(position);
            onCartChanged.run();
        });

        holder.btnDelete.setOnClickListener(v -> {
            CartManager.removeItem(position);
            notifyDataSetChanged();
            onCartChanged.run();
        });
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }
}
