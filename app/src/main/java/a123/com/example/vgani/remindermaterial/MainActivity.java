package a123.com.example.vgani.remindermaterial;

import android.app.FragmentManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;


import android.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import a123.com.example.vgani.remindermaterial.adapter.TabAdapter;
import a123.com.example.vgani.remindermaterial.dialog.AddingTaskDialogFragment;
import a123.com.example.vgani.remindermaterial.fragment.SplashFragment;

public class MainActivity extends AppCompatActivity
        implements AddingTaskDialogFragment.AddingTaskListener{

    FragmentManager mFragmentManager;
    PreferenceHelper mPreferenceHelper;
    Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PreferenceHelper.getmInstance().init(getApplicationContext());
        mPreferenceHelper = PreferenceHelper.getmInstance();
        mFragmentManager = getFragmentManager();

        runSplash();
        setUi();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem splash = menu.findItem(R.id.menu_action_splash);
        splash.setChecked(mPreferenceHelper.getBoolean(mPreferenceHelper.SPLASH_IS_INVISIBLE));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_action_splash) {
            item.setChecked(!item.isChecked());
            mPreferenceHelper.putBoolean(mPreferenceHelper.SPLASH_IS_INVISIBLE, item.isChecked());
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    public void runSplash() {
        if (!mPreferenceHelper.getBoolean(mPreferenceHelper.SPLASH_IS_INVISIBLE)) {
            SplashFragment splashFragment = new SplashFragment();

            mFragmentManager.beginTransaction()
                    .replace(R.id.content_frame, splashFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }

    private void setUi() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
            setSupportActionBar(mToolbar);

            TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
            tabLayout.addTab(tabLayout.newTab().setText(R.string.current_task));
            tabLayout.addTab(tabLayout.newTab().setText(R.string.done_task));

            final ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
            TabAdapter tabAdapter = new TabAdapter(mFragmentManager, 2);

            viewPager.setAdapter(tabAdapter);
            viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

            tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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

            FloatingActionButton fab= (FloatingActionButton) findViewById(R.id.action_button);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogFragment addingTaskDialogFragment=new AddingTaskDialogFragment();
                   addingTaskDialogFragment.show(mFragmentManager,"AddingTaskDialogFragment");
                }
            });
        }
    }

    @Override
    public void onTaskAdded() {
        Toast.makeText(this,"Task added",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onTaskAddingCancel() {
        Toast.makeText(this,"Task added cancel",Toast.LENGTH_LONG).show();
    }
}
