package com.example.bogdan.dou_feed.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bogdan.dou_feed.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 21.06.16
 */
public class MainActivity extends AppCompatActivity  implements FragmentManager.OnBackStackChangedListener{
    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        ButterKnife.bind(this);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportFragmentManager().addOnBackStackChangedListener(this);
        isDisplayingHomeBtn();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new FeedFragment())
                .commit();

    }


    private void isDisplayingHomeBtn() {
        boolean display = isEmptyBackStack();
        getSupportActionBar().setDisplayHomeAsUpEnabled(display);
        getSupportActionBar().setDisplayShowHomeEnabled(display);
    }

    private boolean isEmptyBackStack() {
        return getSupportFragmentManager().getBackStackEntryCount()>0;
    }


    @Override
    public void onBackStackChanged() {
        isDisplayingHomeBtn();
    }

    @Override
    public boolean onSupportNavigateUp() {
        getSupportFragmentManager().popBackStack();
        return true;
    }
}
