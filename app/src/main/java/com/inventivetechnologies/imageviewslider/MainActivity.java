package com.inventivetechnologies.imageviewslider;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public int[] mCatagoriesIds = {R.drawable.food, R.drawable.cinema, R.drawable.doctor, R.drawable.travel,
            R.drawable.cinema, R.drawable.doctor};
    int position = 0;
    int[] imgresource = {R.drawable.bhuj1, R.drawable.bhuj2, R.drawable.bhuj3, R.drawable.bhuj4,
            R.drawable.bhuj5, R.drawable.bhuj6, R.drawable.bhuj7};
    Runnable runnable;
    private ViewPager viewPager;
    private CustomSwipeAdapter adapter;
    private GridView categoryGrid;
    // private ImageAdapter gridapadter =null;
    private ImageView[] dots;
    private int dotsCount;
    private LinearLayout pager_indicator;
    private Toolbar toolbar;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        // set the toolbar title
        getSupportActionBar().setTitle("Test");


        // ViewPager Adapter to set image
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        adapter = new CustomSwipeAdapter(MainActivity.this, imgresource);
        pager_indicator = (LinearLayout) findViewById(R.id.viewPagerCountDots);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < dotsCount; i++) {
                    dots[i].setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.nonselecteditem_dot, null));
                }

                dots[position].setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.selecteditem_dot, null));

                /*if (position + 1 == dotsCount) {
                    btnNext.setVisibility(View.GONE);
                    btnFinish.setVisibility(View.VISIBLE);
                } else {
                    btnNext.setVisibility(View.VISIBLE);
                    btnFinish.setVisibility(View.GONE);
                }*/

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // Setting ImageAdapter for CategoryGridview
        // Instance of ImageAdapter Class
        //  ImageAdapter gridAdapter = new ImageAdapter(MainActivity.this,mCatagoriesIds);
        categoryGrid = (GridView) findViewById(R.id.gridCategory);


        //categoryGrid.setAdapter(gridAdapter);
        categoryGrid.setAdapter(new ImageAdapter(MainActivity.this));
        categoryGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "You Clicked at " + position, Toast.LENGTH_SHORT).show();

            }
        });


        setUiPageViewController();
        //For Automatic Slider after 5secs used Handler
        handler = new Handler();
        runnable = new Runnable() {
            public void run() {
                if (position >= imgresource.length) {
                    position = 0;
                } else {
                    position = position + 1;
                }
                // Setting current Image into viewpager
                viewPager.setCurrentItem(position, true);
                handler.postDelayed(runnable, 2000);


            }
        };


    }


    private void setUiPageViewController() {

        dotsCount = adapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.nonselecteditem_dot, null));
            //ResourcesCompat.getDrawable(getResources(), R.drawable.name, null);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);

            pager_indicator.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.selecteditem_dot, null));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
       /* SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;*/
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
/*
            case R.id.action_settings:
                return true;
*/
            case R.id.action_search:
               /* handleMenuSearch();*/
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (handler != null) {
            handler.removeCallbacks(runnable);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            handler.removeCallbacks(runnable);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 2000);

    }


    private void doSearch() {
//
    }


    @Override
    public void onClick(View v) {

    }
}
