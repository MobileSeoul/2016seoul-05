package seoul.emergency.bbibbo.aed;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import seoul.emergency.bbibbo.MainActivity;
import seoul.emergency.bbibbo.R;
import seoul.emergency.bbibbo.hospital.HospitalInfoFragment;
import seoul.emergency.bbibbo.model.Aed;

/**
 * Created by user on 2016-08-26.
 */
public class AedAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    int layout;
    ArrayList<Aed> aedArrayList;

    //    커스텀 어댑터 생성자
    public AedAdapter(Context context, int layout, ArrayList<Aed> list) {
        this.context = context;
        this.layout = layout;
        this.aedArrayList = list;
//        직접 작성한 레이아웃을 inflation 하기 위한 inflator 준비
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    //    원본 데이터의 전체 개수 반환
    @Override
    public int getCount() {
        return aedArrayList.size();
    }

    //    원본 데이터의 특정 항목 반환
    @Override
    public Object getItem(int i) {
        return aedArrayList.get(i);
    }

    //    원본 데이터 특정 항목의 아이디 반환
    @Override
    public long getItemId(int i) {
        return aedArrayList.get(i).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();
        //user = MainActivity.mainActivity.getUser();
        //Log.d(user.getLogin_id(),"userId");
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_aed, parent, false);
        }
        TextView buildPlaceEnglish = (TextView) convertView.findViewById(R.id.aedList_buildPlaceEnglish);
        TextView aedDistance = (TextView)convertView.findViewById(R.id.aedList_distance);
        TextView managerTel = (TextView) convertView.findViewById(R.id.aedList_managerTel);


        LinearLayout layout_customPharmaycy = (LinearLayout) convertView.findViewById(R.id.layout_customAed);
        Button btn_call = (Button) convertView.findViewById(R.id.aedList_btnCall);

        ViewGroup.LayoutParams params8 = (ViewGroup.LayoutParams) layout_customPharmaycy.getLayoutParams();

        params8.height = (int)(MainActivity.window_height * 73.0 / 640);

        ViewGroup.LayoutParams params12 = (ViewGroup.LayoutParams) btn_call.getLayoutParams();
        params12.width = (int)(MainActivity.window_width * 43.0 / 360);
        params12.height = (int)(MainActivity.window_height * 48.0 / 640);

        // TextView dutyName = (TextView)convertView.findViewById(R.id.hospitalList_id);

        //id.setText(hospitalArrayList.get(position).getId());

        float distance = Float.parseFloat(aedArrayList.get(position).getDistance());
        int distance2 = (int)distance;
        String distance3 = String.valueOf(distance2);

        Typeface helR = Typeface.createFromAsset(convertView.getContext().getAssets(),"fonts/Helvetica.ttf");
        Typeface helL= Typeface.createFromAsset(convertView.getContext().getAssets(),"fonts/Helvetica Light.ttf");


        buildPlaceEnglish.setTypeface(helR);
        aedDistance.setTypeface(helR);
        managerTel.setTypeface(helL);

        buildPlaceEnglish.setText(aedArrayList.get(position).getBuildPlace());
        aedDistance.setText(distance3+"m");
        managerTel.setText(aedArrayList.get(position).getManagerTel());



        btn_call.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+ aedArrayList.get(position).getManagerTel()));
                context.startActivity(intent);

            }
        });

        //항목 클릭했을때 Bundle로 데이터 보내기(Intent 사용 X)
        /*convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d(user.getLogin_id(),"1");
                //Log.d(hospitalArrayList.get(pos).getWriter_id(),"2");
                Intent intent = new Intent(context, HospitalInfoFragment.class);
                context.startActivity(intent);
                /*if (user.getLogin_id().equals(hospitalArrayList.get(pos).getWriter_id())) {
                    //클릭했을 때 내 글일 경우
                    Intent intent = new Intent(context, MypostingActivity.class);
                    intent.putExtra("data", hospitalArrayList.get(pos));
                    context.startActivity(intent);
                }
                else {
                    //클릭했을 때 남의 글일 경우
                    Intent intent = new Intent(context, OtherpostingActivity.class);
                    intent.putExtra("data", hospitalArrayList.get(pos));
                    context.startActivity(intent);
                }

            }
        });*/
        return convertView;
    }
}