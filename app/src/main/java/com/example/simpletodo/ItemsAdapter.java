package com.example.simpletodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// Responsible for displaying data from the model into a row in the recycler view
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.Viewholder> {

    public interface  OnLongClickListener{
        void onItemLongClicked(int position);
    }

    List<String> items;
    OnLongClickListener longClickListener;


    public ItemsAdapter(List<String> items, OnLongClickListener longClickListener){
        this.items = items;
        this.longClickListener = longClickListener;
    }



    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
       // Use layout inflater to inflate a view
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1,parent,false);
        //Wrap it inside view holder and return it
        return new Viewholder(todoView);
    }
    // Responsible for binding data to a particular view holder
    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        // Grab the item at the position
        String item = items.get(position);
        // Bind the item into the specified view holder
        holder.bind(item);
    }

    // Tells the RV how many items are in the list
    @Override
    public int getItemCount() {
        return items.size();
    }

    // Container for easy access to views that represent each row of the list
    class Viewholder extends RecyclerView.ViewHolder{

        TextView tvItem;
        public Viewholder(@NonNull View itemView){
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);

        }
        // Update the view inside of the view holder with this data
        public void bind(String item) {
            tvItem.setText(item);
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    // Notify the listener which position was long pressed
                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });
        }
    }


}
