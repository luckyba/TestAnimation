package com.luckyba.testanimation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity{

    private PeopleAdapter mAdapter;
    private RecyclerView mRecycleView;
    ArrayList<People> listPeople = new ArrayList<>();
    List<Integer> listIndex = new ArrayList<>();
    DefaultItemAnimator defaultItemAnimator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecycleView = findViewById(R.id.list_people);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mRecycleView.setHasFixedSize(true);

        generatePeople(listPeople);
        mAdapter = new PeopleAdapter(this, new Listener() {
            @Override
            public void onClickItem(View view, int pos) {
                if (view.getId() == R.id.cb_favarite) {
                    People people = listPeople.get(pos);
                    people.isFavorite = !people.isFavorite;
                }
            }

            @Override
            public void onLongClickItem(View view, int pos) {
                listPeople.remove(pos);
                filterAll();
                mAdapter.notifyItemRemoved(pos);
            }
        });
        mAdapter.setData(listPeople, listIndex);
        mRecycleView.setAdapter(mAdapter);
        mRecycleView.setLayoutManager(new GridLayoutManager(this, 3));
        defaultItemAnimator = new DefaultItemAnimator();
        mRecycleView.setItemAnimator(defaultItemAnimator);
    }

    void generatePeople(ArrayList<People> listPeople) {
        char[] name = {'a', 'b', 'c', 'd', 'e','f','g','h','i','k','l','m','x','y','z'};
        for(int id = 0; id < 20; id++) {
            String sName = "";
            sName = name[id%14] + String.valueOf(name[(id+1)%14]) + name[(id+2)%14];
            listIndex.add(id);
            listPeople.add(new People(id, sName, false));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.show_all:
                Toast.makeText(this, "Show all", Toast.LENGTH_SHORT).show();
                filterAll();
                mAdapter.notifyDataSetChanged();
                return true;
            case R.id.book_mark:
                Toast.makeText(this, "Book mark", Toast.LENGTH_SHORT).show();
                filterBookMark();
                mAdapter.notifyDataSetChanged();
                return true;
            default:
        }
        return false;
    }

    List<Integer> unBookMark = new ArrayList<>();

    private void filterBookMark() {
        listIndex.clear();
        for(People people: listPeople) {
            if (people.isFavorite) {
                listIndex.add(people.id);
            } else {
                unBookMark.add(listPeople.indexOf(people));
            }
        }
    }

    private void filterAll() {
        listIndex.clear();
        for(People people: listPeople) {
            listIndex.add(people.id);
        }
    }

    interface Listener {
        void onClickItem(View view, int pos);

        void onLongClickItem(View view, int pos);
    }
}