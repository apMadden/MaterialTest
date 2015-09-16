package com.andy.keyme;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.andy.keyme.CustomAdapters.StoreViewPagerAdapter;
import com.andy.keyme.StoreFragments.StoreAccessoriesFragment;
import com.andy.keyme.StoreFragments.StoreKeysFragment;


public class StoreActivity extends AppCompatActivity implements StoreKeysFragment.OnFragmentInteractionListener, StoreAccessoriesFragment.OnFragmentInteractionListener {
    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        setupWindowAnimations();

        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager_store);
        setupViewPager(viewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout_store);
        tabLayout.setupWithViewPager(viewPager);

    }
    private void setupWindowAnimations() {
        Explode explode = new Explode();
        explode.setDuration(10000);
        getWindow().setEnterTransition(explode);
    }
    private void setupViewPager(ViewPager viewPager) {
        StoreViewPagerAdapter adapter = new StoreViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(StoreKeysFragment.newInstance(getResources().getColor(R.color.colorPrimaryLight)), "KEYS");
        adapter.addFrag(StoreAccessoriesFragment.newInstance(getResources().getColor(R.color.colorPrimaryLight)), "ACCESSORIES");
        viewPager.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_store, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(id){
            case R.id.action_settings:
                break;
            case R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAfterTransition();
    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
