package edu.uncc.assignment06;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SelectionRecyclerViewAdapter extends RecyclerView.Adapter<SelectionRecyclerViewAdapter.SelectionViewHolder>{

    String [] dataList;

    String key;
    ISelectionListener mListener;

    public SelectionRecyclerViewAdapter(String[] dataList, ISelectionListener mListener,String key) {
        this.dataList = dataList;
        this.mListener = mListener;
        this.key= key;
    }

    @NonNull
    @Override
    public SelectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.selection_list_item, parent, false);
        SelectionViewHolder selectionViewHolder = new SelectionViewHolder(view,mListener,key);
        return selectionViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SelectionViewHolder holder, int position) {
        String priority = dataList[position];
        holder.textView.setText(priority);
        Log.d("demo", "onBindViewHolder: "+priority);
    }

    @Override
    public int getItemCount() {
        return this.dataList.length;
    }

    public static class SelectionViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        int position;

        String key;

        ISelectionListener mListener;
        public SelectionViewHolder(@NonNull View itemView, ISelectionListener mListener,String key) {
            super(itemView);
            this.mListener = mListener;
            this.key = key;
            textView = itemView.findViewById(R.id.textView);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("demo", "onClick: "+textView.getText().toString());
                    try {
                        if(key.equals("priority")) {
                            mListener.sendSelectedPriority(textView.getText().toString());
                        }
                        else if(key.equals("category"))
                        {
                            mListener.sendSelectedCategory(textView.getText().toString());
                        }

                    }catch (Exception e){
                        Log.d("demo", "onClick: error calling adapter method");
                        Log.d("demo", e.toString());
                    }
                }
            });




        }
    }

    public interface ISelectionListener{
        void sendSelectedPriority(String selectedValue);

        void sendSelectedCategory(String selectedValue);
    }



}
