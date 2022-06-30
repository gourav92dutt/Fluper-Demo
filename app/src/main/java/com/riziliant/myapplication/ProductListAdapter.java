package com.riziliant.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.MyProductListViewHolder> {

    private List<Product> productsList;

    private ProductClickedListener productClickedListener;

    public ProductListAdapter(List<Product> products, ProductClickedListener productClickedListener) {
        this.productsList = products;
        this.productClickedListener = productClickedListener;
    }

    @NonNull
    @Override
    public MyProductListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_product_item, parent, false);
        return new MyProductListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyProductListViewHolder holder, int position) {
        Product product = productsList.get(position);

        holder.tvName.setText("Name  :-"+product.getProductName());
        holder.tvSalePrice.setText("S Price  :-"+product.getSalePrice());
        holder.tvRegularPrice.setText("R Price  :-"+product.getRegularPrice());
        holder.tvDescription.setText("Description  :-"+product.getProductDescription());
        holder.tvLikeCont.setText("Like Count  :-"+product.getStoresDictionary().getLike());
        holder.productRating.setRating(Float.parseFloat(product.getStoresDictionary().getRating()));
        String colorsString = "";

        for (int i = 0; i < product.getColors().size(); i++) {
           colorsString=colorsString+" "+product.getColors().get(i);
        }

        holder.tvColorsNames.setText("Colors  :-"+colorsString);
        holder.thumb.setImageBitmap(product.getImageThumb());
        holder.thumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productClickedListener.onClicked();
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productClickedListener.onItemDelete(product);
            }
        });
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productClickedListener.onItemUpdate(product);
            }
        });


    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public void updateData(List<Product> all) {
        this.productsList=all;
        notifyDataSetChanged();
    }

    public class MyProductListViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvRegularPrice, tvSalePrice, tvDescription, tvLikeCont, tvColorsNames,delete,update;
        RatingBar productRating;
        ImageView thumb;

        public MyProductListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.prd_name);
            tvRegularPrice = itemView.findViewById(R.id.prd_reg_price);
            tvSalePrice = itemView.findViewById(R.id.prd_sale_price);
            tvDescription = itemView.findViewById(R.id.prd_discription);
            tvLikeCont = itemView.findViewById(R.id.prd_prd_like_count);
            tvColorsNames = itemView.findViewById(R.id.prd_colors_name);
            productRating = itemView.findViewById(R.id.prd_rating);
            thumb=itemView.findViewById(R.id.img_thumb);
            delete=itemView.findViewById(R.id.txt_delete);
            update=itemView.findViewById(R.id.txt_update);

        }
    }
}
