package com.example.android;

import android.content.Context;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RelatedProductAdapter extends RecyclerView.Adapter<RelatedProductAdapter.RelatedViewHolder> {

    private final Context context;
    private final List<Product> productList;

    public RelatedProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    static class RelatedViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txtName, txtPrice;

        public RelatedViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgRelated);
            txtName = itemView.findViewById(R.id.txtRelatedName);
            txtPrice = itemView.findViewById(R.id.txtRelatedPrice);
        }
    }

    @NonNull
    @Override
    public RelatedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_related_product, parent, false);
        return new RelatedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RelatedViewHolder holder, int position) {
        Product p = productList.get(position);
        holder.img.setImageResource(p.getImageResId());
        holder.txtName.setText(p.getName());
        holder.txtPrice.setText(p.getPrice());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
