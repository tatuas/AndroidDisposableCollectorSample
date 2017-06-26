package com.tatuas.android.disposingcollectorsample;

import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.ncapdevi.fragnav.FragNavController;
import com.ncapdevi.fragnav.FragNavTransactionOptions;
import com.tatuas.android.disposingcollectorsample.common.DisposingActivity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class MainActivity extends DisposingActivity {

    BottomNavigationView bottomNavigationView;

    FragNavController fragNavController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                changeTab(0);
                                return true;
                            case R.id.navigation_dashboard:
                                changeTab(1);
                                return true;
                            case R.id.navigation_notifications:
                                changeTab(2);
                                return true;
                        }
                        return false;
                    }
                });

        setupTab(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveTab(outState);
    }

    private void setupTab(@Nullable Bundle savedInstanceState) {
        final FragNavController.Builder builder = FragNavController.newBuilder(
                savedInstanceState, getSupportFragmentManager(), R.id.container);
        builder.rootFragmentListener(new FragNavController.RootFragmentListener() {
            @Override
            public Fragment getRootFragment(int index) {
                switch (index) {
                    case Tab.HOME:
                        return new HomeFragment();
                    case Tab.DASHBOARD:
                        return new DashboardFragment();
                    case Tab.NOTIFICATION:
                        return new NotificationFragment();
                }
                throw new IllegalStateException("Need to send an index that we know");
            }
        }, 3);
        builder.defaultTransactionOptions(FragNavTransactionOptions.newBuilder()
                .transition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).build());
        fragNavController = builder.build();
    }

    private void changeTab(int pos) {
        fragNavController.switchTab(pos);
    }

    private void saveTab(@NonNull Bundle outState) {
        if (fragNavController != null) {
            fragNavController.onSaveInstanceState(outState);
        }
    }

    @IntDef({Tab.HOME, Tab.DASHBOARD, Tab.NOTIFICATION})
    @Retention(RetentionPolicy.SOURCE)
    @interface Tab {
        int HOME = 0;
        int DASHBOARD = 1;
        int NOTIFICATION = 2;
    }
}
