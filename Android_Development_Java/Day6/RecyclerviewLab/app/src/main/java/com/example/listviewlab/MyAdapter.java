package com.example.listviewlab;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {


    private final Context context;
    private List<cake> values;
    private static final String TAG = "RecyclerView";
    public MyAdapter(Context context, List<cake> tst)
    {
        values = tst;
        this.context = context;;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTitle;
        public TextView txtDescription;
        public ImageView imageView;
        public ConstraintLayout constraintLayout;
        public View layout;

        @SuppressLint("WrongViewCast")
        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtTitle = v.findViewById(R.id.txtTitle);
            txtDescription = v.findViewById(R.id.txtDescritpion);
            imageView = v.findViewById(R.id.thumbnail);
            constraintLayout = v.findViewById(R.id.row);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.recycle_layout, recyclerView, false);
        ViewHolder vh = new ViewHolder(v);
        Log.i(TAG, "=== onCreateViewHolder ===");
        //return vh;
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder,int position) {
        holder.txtTitle.setText(values.get(position).gettitle());
        holder.txtDescription.setText(values.get(position).getdescription());
        holder.imageView.setImageResource(values.get(position).getThumbnail());

    }
    @Override
    public int getItemCount() {
        return values.size();
    }



}