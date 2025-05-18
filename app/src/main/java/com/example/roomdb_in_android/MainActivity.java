package com.example.roomdb_in_android;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    Button btnAdd;
    RecyclerView recyclerView;

    UserDatabase db;
    List<User> userList;
    UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        btnAdd = findViewById(R.id.btnAdd);
        recyclerView = findViewById(R.id.recyclerView);

        db = UserDatabase.getInstance(this);
        userList = db.userDao().getAllUsers();

        adapter = new UserAdapter(this, userList, db);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        btnAdd.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            if (!name.isEmpty()) {
                User user = new User(name);
                db.userDao().insert(user);
                userList.clear();
                userList.addAll(db.userDao().getAllUsers());
                adapter.notifyDataSetChanged();
                etName.setText("");
            }
        });
    }
}
