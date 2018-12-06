package com.example.megan.memo_ry;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    PagerTabStrip tabs;
    ViewPager viewPager;



    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager)findViewById(R.id.pager);
        tabs = (PagerTabStrip)findViewById(R.id.tabStrip);

        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        tabs.setViewPager(viewPager);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        if(toolbar!=null)
        {
            setSupportActionBar(toolbar);
        }

        button = (Button) findViewById(R.id.buttonCreateNew);
        button. setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewMemory();
            }
        });

        button = (Button) findViewById(R.id.buttonAccessDiary);
        button. setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDiary();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) //open settings activity
        {
            case R.id.settings_id:
        }
        return super.onOptionsItemSelected(item);
    }

    public class MyAdapter extends FragmentPagerAdapter
    {
        String [] titles = {"Record","Saved Recordings"};
        public MyAdapter(FragmentManager fm)
        {
            super(fm);
        }
        @Override
        public Fragment getItem (int position)
        {
            switch(position)
            {
                case 0: return RecordFragment.newInstance(position);
                case 1: return FileViwerFragment.newInstance(position);
            }
            return null;
        }

        @Override
        public int getCount()
        {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position)
        {
            return titles[position];
        }
    }

    public MainActivity ()
    {

    }

    public void openNewMemory () {
        Intent intent = new Intent (this, CreateNewMemory.class);
        startActivity (intent);
    }

    public void openDiary () {
        Intent intent = new Intent (this, MemoriesList.class);
        startActivity(intent);
    }

}
