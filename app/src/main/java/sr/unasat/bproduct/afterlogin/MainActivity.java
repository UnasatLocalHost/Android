package sr.unasat.bproduct.afterlogin;



import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import androidx.viewpager.widget.ViewPager;


import com.google.android.material.tabs.TabLayout;


import sr.unasat.bproduct.Account_Activity;
import sr.unasat.bproduct.LoginActivity;
import sr.unasat.bproduct.R;
import sr.unasat.bproduct.fragments.PagerAdapter;
import sr.unasat.bproduct.menu.MenuActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Camera"));
        tabLayout.addTab(tabLayout.newTab().setText("Share"));
        tabLayout.addTab(tabLayout.newTab().setText("Images"));
        tabLayout.setTabGravity(tabLayout.GRAVITY_FILL);








        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter =  new PagerAdapter
                (getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id ==R.id.action_log_out) {
            startActivity(new Intent(this, LoginActivity.class));
        }
        if (id==R.id.action_account_settings) {
            startActivity(new Intent(this, Account_Activity.class));

            return true;
        }
        return super.onOptionsItemSelected(item);
    }



}