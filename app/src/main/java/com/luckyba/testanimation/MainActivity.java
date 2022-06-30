package com.luckyba.testanimation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class MainActivity extends AppCompatActivity{

    private PeopleAdapter mAdapter;
    private RecyclerView mRecycleView;
    ArrayList<People> listPeople = new ArrayList<>();
    List<Integer> listPageId = new ArrayList<>();
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
                int id = listPageId.get(pos);
                listPageId.remove(pos);
                listPeople.removeIf(people -> people.id == id);
                mAdapter.notifyItemRemoved(pos);
            }
        });
        mAdapter.setData(listPeople, listPageId);
        mRecycleView.setAdapter(mAdapter);
        mRecycleView.setLayoutManager(new GridLayoutManager(this, 3));
        defaultItemAnimator = new DefaultItemAnimator();
//        mRecycleView.setItemAnimator(defaultItemAnimator);
    }

    void generatePeople(ArrayList<People> listPeople) {
        char[] name = {'a', 'b', 'c', 'd', 'e','f','g','h','i','k','l','m','x','y','z'};
        for(int id = 0; id < 20; id++) {
            String sName = "";
            sName = name[id%14] + String.valueOf(name[(id+1)%14]) + name[(id+2)%14];
            listPageId.add(id);
            listPeople.add(new People(id, sName, false));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @SuppressLint({"NonConstantResourceId", "UseCompatLoadingForDrawables"})
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.book_mark:
                Toast.makeText(this, "Book mark", Toast.LENGTH_SHORT).show();
                if (!item.isChecked()) {
                    item.setChecked(true);
                    item.setIcon(getResources().getDrawable( R.drawable.ic_baseline_star_24 , null));
                    filterBookMark();
                } else {
                    item.setChecked(false);
                    item.setIcon(getResources().getDrawable( R.drawable.ic_baseline_star_border_24 , null));
                    filterAll();
                }
                return true;
            default:
        }
        return false;
    }

    private void filterBookMark() {
        List<Integer> unBookmarkIndex = new ArrayList<>();
        mapListPageUnBookMark(unBookmarkIndex);

        for (int i = unBookmarkIndex.size() -1; i >= 0; i--) {
            int index = unBookmarkIndex.get(i);
            listPageId.remove(index);
            mAdapter.notifyItemRemoved(index);
        }
    }

    private void filterAll() {
        List<Integer> unBookmarkIndex = new ArrayList<>();
        mapListPageUnBookMark(unBookmarkIndex);

        for(int i = 0; i < unBookmarkIndex.size(); i++) {
            int index = unBookmarkIndex.get(i);
            listPageId.add(index, listPeople.get(index).id);
            mAdapter.notifyItemInserted(index);
        }
    }

    void mapListPageUnBookMark(List<Integer> listIndexUnBookMark) {
        listIndexUnBookMark.clear();
        for (People people: listPeople) {
            if (!people.isFavorite) {
                listIndexUnBookMark.add(listPeople.indexOf(people));
            }
        }
    }

    interface Listener {
        void onClickItem(View view, int pos);

        void onLongClickItem(View view, int pos);
    }
}