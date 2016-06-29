package com.example.bogdan.dou_feed.ui.article.view;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.bogdan.dou_feed.R;
import com.example.bogdan.dou_feed.ui.article.presenter.ArticlePresenter;
import com.example.bogdan.dou_feed.ui.common.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 29.06.16
 */
public class ImageViewerFragment extends BaseFragment implements ImageViewerView {
    public static final int LAYOUT = R.layout.image_viewer_layout;
    private static final String IMAGE_KEY = "IMAGE_KEY";

    @BindView(R.id.viewerImage)
    ImageView mImage;

    public static ImageViewerFragment newInstance(Bitmap image) {
        ImageViewerFragment fragment = new ImageViewerFragment();

        Bundle args = new Bundle();
        args.putParcelable(IMAGE_KEY, image);
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(LAYOUT, container, false);
        ButterKnife.bind(this, view);

        mImage.setImageBitmap((Bitmap) getArguments().get(IMAGE_KEY));

        return view;
    }
}
