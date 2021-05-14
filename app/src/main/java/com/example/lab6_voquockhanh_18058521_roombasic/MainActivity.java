package com.example.lab6_voquockhanh_18058521_roombasic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Person> arrayList;
    private ListView listView;
    private ArrayAdapter<Person> adapter;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "person_db").allowMainThreadQueries().build();
        PersonDAO dao = appDatabase.PersonDAO();

        listView = findViewById(R.id.listview);
        arrayList = (ArrayList<Person>) dao.getAll();

        textView = findViewById(R.id.plaintxt);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                textView.setText(arrayList.get(i).toString());
            }
        });

        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = textView.getText().toString();
                Person person = new Person(name);
                dao.add(person);
                arrayList.clear();
                arrayList.addAll(dao.getAll());
                adapter.notifyDataSetChanged();
            }
        });

        Button btnRemove = findViewById(R.id.btnRemove);
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = textView.getText().toString();
                List<Person> list = dao.getPersonByName(name);
                for (Person person : list
                ) {
                    dao.delete(person);
                }

                arrayList.clear();
                arrayList.addAll(dao.getAll());
                adapter.notifyDataSetChanged();
                textView.setText("");
            }
        });
    }
}