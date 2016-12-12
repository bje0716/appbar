package com.grapefruit.appbar;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.grapefruit.appbar.databinding.ActivityMainBinding;
import com.grapefruit.appbar.databinding.ItemBinding;

import java.util.ArrayList;
import java.util.List;

import static com.grapefruit.appbar.R.layout.item;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ActivityMainBinding binding;
    private ActionBarDrawerToggle toggle;
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setSupportActionBar(binding.mainToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toggle = new ActionBarDrawerToggle(this, binding.mainDrawer, binding.mainToolbar,
                R.string.app_name, R.string.app_name);
        toggle.syncState();
        binding.mainDrawer.addDrawerListener(toggle);
        binding.mainNavigation.setNavigationItemSelectedListener(this);

        adapter = new MainAdapter(getSupportFragmentManager());
        for (int i = 1; i < 5; i++) {
            adapter.addFragment(new MainFragment(), i + "");
        }

        binding.mainViewpager.setAdapter(adapter);
        binding.mainTablayout.setupWithViewPager(binding.mainViewpager);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            default:
                binding.mainDrawer.closeDrawer(GravityCompat.START);
                break;
        }
        return true;
    }

    private class MainAdapter extends FragmentPagerAdapter {

        private final List<Fragment> fragments = new ArrayList<>();
        private final List<String> titles = new ArrayList<>();

        public MainAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            fragments.add(fragment);
            titles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }

    public static class MainFragment extends Fragment {

        private ItemBinding binding;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
            binding = DataBindingUtil.inflate(inflater, item, container, false);
            binding.itemRecycler.setHasFixedSize(true);
            binding.itemRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
            binding.itemRecycler.setItemAnimator(new DefaultItemAnimator());
            binding.itemRecycler.setAdapter(new RecyclerAdapter());
            return binding.getRoot();
        }
    }
}
