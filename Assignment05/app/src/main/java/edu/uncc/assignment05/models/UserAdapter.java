package edu.uncc.assignment05.models;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import edu.uncc.assignment05.R;

public class UserAdapter extends ArrayAdapter<User> {


    public UserAdapter(@NonNull Context context, int resource, @NonNull List<User> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.user_list_item,parent,false);
        }
        User user = getItem(position);
        Log.d("demo", "Current USER: "+user);
        TextView textViewName = convertView.findViewById(R.id.textViewName);
        textViewName.setText(user.getName());
        TextView textViewEmail = convertView.findViewById(R.id.textViewEmail);
        textViewEmail.setText(user.getEmail());
        TextView textViewAge = convertView.findViewById(R.id.textViewAge);
        textViewAge.setText(user.getAge()+"");
        TextView textViewGender = convertView.findViewById(R.id.textViewGender);
        textViewGender.setText(user.getGender());
        TextView textViewState = convertView.findViewById(R.id.textViewState);
        textViewState.setText(user.getState());
        TextView textViewGroup = convertView.findViewById(R.id.textViewGroup);
        textViewGroup.setText(user.getGroup());

        return convertView;
    }
}
