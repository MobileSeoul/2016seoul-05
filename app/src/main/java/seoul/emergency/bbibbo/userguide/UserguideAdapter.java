package seoul.emergency.bbibbo.userguide;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;

import seoul.emergency.bbibbo.R;

/**
 * Created by user on 2016-10-30.
 */

public class UserguideAdapter extends PagerAdapter {
    Context context;
    View.OnClickListener clickEvent;
    ArrayList<Integer> userImages;
    public UserguideAdapter(Context context ,ArrayList<Integer> userImages,View.OnClickListener clickEvent) {

        this.context = context;
        this.userImages = userImages;
        this.clickEvent = clickEvent;

    }

    @Override

    public int getCount() {

        return 5;

    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        view.setOnClickListener(clickEvent);
        return view == ((ImageView) object);
    }

    @Override
    public Object instantiateItem(final ViewGroup container, int position) {

        ImageView imageView = new ImageView(context);

        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageResource(userImages.get(position));
        ((ViewPager) container).addView(imageView, 0);


        return imageView;
    }





    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }

}
