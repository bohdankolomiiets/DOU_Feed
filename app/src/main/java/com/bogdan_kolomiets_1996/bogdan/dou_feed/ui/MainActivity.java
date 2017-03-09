package com.bogdan_kolomiets_1996.bogdan.dou_feed.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.bogdan_kolomiets_1996.bogdan.dou_feed.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 21.06.16
 */
public class MainActivity extends AppCompatActivity {
    @BindView(R.id.searchView)
    SearchView searchView;

    private Toolbar mToolbar;

    private FragmentLauncher mLauncher; // TODO: 01.08.16 add it to DI

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        ButterKnife.bind(this);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        System.out.println("Application " + getApplication());
        System.out.println("Activity " + this);
        mLauncher = FragmentLauncher.with(this).into(R.id.container);
        mLauncher.open();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        mLauncher.onNewIntent(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        getSupportFragmentManager().popBackStack();
        return true;
    }

    public void setUpSearchViewVisibility(int visibility) {
        searchView.setVisibility(visibility);
    }

    public void setSearchViewListener(OnSearchListener listener) {
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onOpenClick(mToolbar);
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                listener.onCloseClick(mToolbar);
                return false;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                listener.onSearchClick(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    public interface OnSearchListener {
        void onOpenClick(Toolbar toolbar);

        void onCloseClick(Toolbar toolbar);

        void onSearchClick(String query);
    }
}
