package com.example.roomdb_in_android;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.jspecify.annotations.NonNull;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    List<User> userList;
    Context context;
    UserDatabase db;

    public UserAdapter(Context context, List<User> userList, UserDatabase db) {
        this.context = context;
        this.userList = userList;
        this.db = db;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(context).inflate(R.layout.item_user, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.tvName.setText(user.name);

        holder.btnDelete.setOnClickListener(v -> {
            db.userDao().delete(user);
            userList.remove(position);
            notifyItemRemoved(position);
        });

        holder.btnEdit.setOnClickListener(v -> {
            EditText input = new EditText(context);
            input.setText(user.name);

            new AlertDialog.Builder(context)
                    .setTitle("Edit Name")
                    .setView(input)
                    .setPositiveButton("Update", (dialog, which) -> {
                        user.name = input.getText().toString();
                        db.userDao().update(user);
                        notifyItemChanged(position);
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        Button btnEdit, btnDelete;

        public UserViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
