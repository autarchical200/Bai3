package org.coolstyles.sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class UserListAdapter extends ArrayAdapter<User> {

    private int layoutResourceId;

    public UserListAdapter(Context context, int resource, List<User> objects) {
        super(context, resource, objects);
        layoutResourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_user, parent, false);
        }

        User user = getItem(position);

        TextView textViewEmail = convertView.findViewById(R.id.textViewEmail);
        TextView textViewPassword = convertView.findViewById(R.id.textViewPassword);
        TextView textViewRole = convertView.findViewById(R.id.textViewRole);

        Button buttonUpdate = convertView.findViewById(R.id.buttonUpdate);
        Button buttonDelete = convertView.findViewById(R.id.buttonDelete);

        if (user != null) {
            textViewEmail.setText("Email: " + user.getEmail());
            textViewPassword.setText("Password: " + user.getPassword());
            textViewRole.setText("Role: " + user.getRole());

            // Set onClickListeners for update and delete buttons
            buttonUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (updateClickListener != null) {
                        updateClickListener.onUpdateClick(user);
                    }
                }
            });

            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (deleteClickListener != null) {
                        deleteClickListener.onDeleteClick(user);
                    }
                }
            });
        }

        return convertView;
    }


    // Interface for click events
    public interface UpdateClickListener {
        void onUpdateClick(User user);
    }

    public interface DeleteClickListener {
        void onDeleteClick(User user);
    }

    private UpdateClickListener updateClickListener;
    private DeleteClickListener deleteClickListener;

    // Setters for listeners
    public void setUpdateClickListener(UpdateClickListener listener) {
        this.updateClickListener = listener;
    }

    public void setDeleteClickListener(DeleteClickListener listener) {
        this.deleteClickListener = listener;
    }
}
