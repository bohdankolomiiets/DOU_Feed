package com.example.bogdan.dou_feed.ui;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.example.bogdan.dou_feed.R;
import com.example.bogdan.dou_feed.model.entity.page.Image;
import com.example.bogdan.dou_feed.ui.article.view.ImageViewerFragment;
import com.example.bogdan.dou_feed.ui.feed.view.FeedFragment;

import butterknife.ButterKnife;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 21.06.16
 */
public class MainActivity extends AppCompatActivity  implements
        FragmentManager.OnBackStackChangedListener, Image.OnImageClickListener{
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

    @Override
    public void onImageClick(ImageView imageView) {
        Bitmap image = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
        ImageViewerFragment fragment = ImageViewerFragment.newInstance(image);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment, null)
                .addToBackStack(null)
                .commit();
    }


}
