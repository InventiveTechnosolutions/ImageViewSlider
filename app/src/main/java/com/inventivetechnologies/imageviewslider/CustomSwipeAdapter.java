package com.inventivetechnologies.imageviewslider;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Shiv on 12-Apr-16.
 */
public class CustomSwipeAdapter extends PagerAdapter {


    private int[] imgresource = {R.drawable.bhuj1, R.drawable.bhuj2, R.drawable.bhuj3, R.drawable.bhuj4,
            R.drawable.bhuj5, R.drawable.bhuj6, R.drawable.bhuj7};
    private Context ctx;
    private LayoutInflater layoutInflater;
    private Handler handler;
    private int[] mResources;

    public CustomSwipeAdapter(Context context, int[] mResources) {
        this.ctx = context;
        this.mResources = mResources;

    }

    @Override
    public int getCount() {
        return imgresource.length;
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflater.inflate(R.layout.swipe_layout, container, false);
        /// Set Images one by one in ViewPager
        ImageView imgImageView = (ImageView) item_view.findViewById(R.id.image_view);
        imgImageView.setImageResource(imgresource[position]);
        container.addView(item_view);
        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((LinearLayout) object);

    }

}
