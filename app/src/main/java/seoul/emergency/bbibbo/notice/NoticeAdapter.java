package seoul.emergency.bbibbo.notice;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import seoul.emergency.bbibbo.R;
import seoul.emergency.bbibbo.model.Notice;

/**
 * Created by USER on 2016-09-29.
 */
public class NoticeAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    int layout;
    ArrayList<Notice> noticeArray;

    //    커스텀 어댑터 생성자
    public NoticeAdapter(Context context, int layout, ArrayList<Notice> list) {
        this.context = context;
        this.layout = layout;
        this.noticeArray = list;
//        직접 작성한 레이아웃을 inflation 하기 위한 inflator 준비
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return noticeArray.size();
    }

    @Override
    public Object getItem(int i) {
        return noticeArray.get(i);
    }

    @Override
    public long getItemId(int i) {
        return noticeArray.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView notice_id;
        TextView notice_title;
        //TextView notice_content;
        TextView notice_date;
        final int pos = i;
        final Context context = viewGroup.getContext();
        Typeface helveticaBold = Typeface.createFromAsset(context.getAssets(), "fonts/Helvetica Bold.ttf");
        Typeface helvetica = Typeface.createFromAsset(context.getAssets(), "fonts/Helvetica.ttf");
        //user = MainActivity.mainActivity.getUser();
        //Log.d(user.getLogin_id(),"userId");
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.custom_notice, viewGroup, false);
        }
        notice_id = (TextView) view.findViewById(R.id.notice_id);
        notice_title = (TextView) view.findViewById(R.id.notice_title);
        notice_date = (TextView) view.findViewById(R.id.notice_date);
        notice_id.setText("[Notice]");
        notice_title.setText(noticeArray.get(i).getTitle());
        notice_date.setText(noticeArray.get(i).getWritten_date().substring(0,10));

        notice_id.setTypeface(helveticaBold);
        notice_title.setTypeface(helveticaBold);
        notice_date.setTypeface(helvetica);
        return view;
    }
}
