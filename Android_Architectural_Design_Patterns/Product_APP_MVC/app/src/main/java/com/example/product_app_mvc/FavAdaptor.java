package com.example.product_app_mvc;



import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

class FavAdapter extends RecyclerView.Adapter<FavAdapter.ViewHolder> {


    private final Context context;

    private static final String TAG = "RecyclerView";
    private List<POJO_class> myDataSet;
    if_deletefavproduct myif_deletefavproduct;
    int temp;
    public FavAdapter(Context context , if_deletefavproduct l_ref)
    {

        this.context = context;;
        this.myDataSet = myDataSet;;
        this.myif_deletefavproduct = l_ref;

        //Log.i("TAG", myDataSet.toString());
        //notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setadapter(List<POJO_class> myDataSet)
    {
        this.myDataSet = myDataSet;;
        //Log.i("TAG", myDataSet.toString());
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView editTitle;
        public TextView editPrice;
        public TextView editBrand;
        public TextView editDescription;
        public RatingBar ratingBarr;
        public ImageView img;
        Button btndeleteToFav;
        public ConstraintLayout constraintLayout;
        public View layout;

        @SuppressLint("WrongViewCast")
        public ViewHolder(View v) {
            super(v);

            layout = v;
            editTitle = v.findViewById(R.id.editTitle);
            editPrice = v.findViewById(R.id.editPrice);
            editBrand = v.findViewById(R.id.editBrand);
            editDescription = v.findViewById(R.id.editDescription);
            btndeleteToFav = v.findViewById(R.id.btndeleteToFav);
            //btnPrev = v.findViewById(R.id.btnPrev);
            //btnNext = v.findViewById(R.id.btnNext);
            img = v.findViewById(R.id.imageView);
            ratingBarr = v.findViewById(R.id.ratingBarr);
            ratingBarr.setEnabled(false);
            constraintLayout = v.findViewById(R.id.row);




        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.fav_recycle_layout, recyclerView, false);
        ViewHolder vh = new ViewHolder(v);
        Log.i(TAG, "=== onCreateViewHolder ===");
        //return vh;
        return vh;
    }
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder,int position) {
        if(myDataSet != null )
        {
            this.temp = myDataSet.size();
            holder.editTitle.setText(myDataSet.get(position).getTitle());
            holder.editPrice.setText(Double.toString(myDataSet.get(position).getPrice()));
            holder.editBrand.setText(myDataSet.get(position).toString());
            holder.editDescription.setText(myDataSet.get(position).getDescription());
            //holder.ratingBarr.setRating(Float.parseFloat(myDataSet.get(position).getRating()));
            float rating = (float) ((myDataSet.get(position).getRating()) /2);
            holder.ratingBarr.setRating(rating);

            Glide.with(context)
                    .load(myDataSet.get(position).getThumbnail())
                    .apply(new RequestOptions()
                            .override(200, 200)
                            .placeholder(R.drawable.ic_launcher_foreground)
                            .error(R.drawable.ic_launcher_foreground))
                    .into(holder.img); // Use holder.imageView instead of holder.itemView

            holder.btndeleteToFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myif_deletefavproduct.onFavdeleteclick(myDataSet.get(position));
                }
            });
        }


    }
    @Override
    public int getItemCount() {
        return 30;
    }

}


