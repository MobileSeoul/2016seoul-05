
package seoul.emergency.bbibbo.pharmacy;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import seoul.emergency.bbibbo.BuildConfig;
import seoul.emergency.bbibbo.MainActivity;
import seoul.emergency.bbibbo.R;
import seoul.emergency.bbibbo.model.Pharmacy;

/**
 * Created by user on 2016-08-23.
 */
public class PharmacyAdapter extends BaseAdapter {


    Context context;
    LayoutInflater inflater;
    int layout;
    ArrayList<Pharmacy> pharmacyArrayList;

    //    커스텀 어댑터 생성자
    public PharmacyAdapter(Context context, int layout, ArrayList<Pharmacy> list) {
        this.context = context;
        this.layout = layout;
        this.pharmacyArrayList = list;
//        직접 작성한 레이아웃을 inflation 하기 위한 inflator 준비
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    //    원본 데이터의 전체 개수 반환
    @Override
    public int getCount() {
        return pharmacyArrayList.size();
    }

    //    원본 데이터의 특정 항목 반환
    @Override
    public Object getItem(int i) {
        return pharmacyArrayList.get(i);
    }

    //    원본 데이터 특정 항목의 아이디 반환
    @Override
    public long getItemId(int i) {
        return pharmacyArrayList.get(i).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();
        //user = MainActivity.mainActivity.getUser();
        //Log.d(user.getLogin_id(),"userId");
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_pharmacy, parent, false);
        }
        LinearLayout layout_customPharmaycy = (LinearLayout) convertView.findViewById(R.id.layout_customPharmaycy);
        TextView englishName = (TextView) convertView.findViewById(R.id.phamList_englishName);
        TextView englishAddr = (TextView) convertView.findViewById(R.id.phamList_englishAddr);
        TextView pham_distance = (TextView) convertView.findViewById(R.id.phamList_distance);
        Button btn_call = (Button) convertView.findViewById(R.id.phamList_btnCall);

        ViewGroup.LayoutParams params8 = (ViewGroup.LayoutParams) layout_customPharmaycy.getLayoutParams();

        params8.height = (int)(MainActivity.window_height * 73.0 / 640);

   //     ViewGroup.LayoutParams params11 = (ViewGroup.LayoutParams) distance.getLayoutParams();


//        ViewGroup.LayoutParams params9 = (ViewGroup.LayoutParams) english_name.getLayoutParams();
//        params9.width = (int)((MainActivity.window_width * 263.0 / 360))- params11.width;
 //       ViewGroup.LayoutParams params10 = (ViewGroup.LayoutParams) english_addr.getLayoutParams();
 //       params10.leftMargin = MainActivity.window_width * 16 / 360;

        ViewGroup.LayoutParams params12 = (ViewGroup.LayoutParams) btn_call.getLayoutParams();
        params12.width = (int)(MainActivity.window_width * 43.0 / 360);
        params12.height = (int)(MainActivity.window_height * 48.0 / 640);


        //필요한 데이터 수정 (현재 -> pharmacy)
        TextView id = (TextView) convertView.findViewById(R.id.phamList_id);
        TextView dutyName = (TextView) convertView.findViewById(R.id.phamList_dutyName);

        final TextView dutyTel1 = (TextView) convertView.findViewById(R.id.phamList_dutyTel1);
        TextView dutyTime1s = (TextView) convertView.findViewById(R.id.phamList_dutyTime1s);
        TextView dutyTime1c = (TextView) convertView.findViewById(R.id.phamList_dutyTime1c);
        TextView postCdn1= (TextView) convertView.findViewById(R.id.phamList_postCdn1);
        TextView postCdn2 = (TextView) convertView.findViewById(R.id.phamList_postCdn2);
        TextView latitude = (TextView) convertView.findViewById(R.id.phamList_latitude);
        TextView longitude = (TextView) convertView.findViewById(R.id.phamList_longitude);
        TextView foreignLanguage1 = (TextView) convertView.findViewById(R.id.phamList_foriegnLanguage1);

        // TextView dutyName = (TextView)convertView.findViewById(R.id.hospitalList_id);
        Typeface helR = Typeface.createFromAsset(convertView.getContext().getAssets(),"fonts/Helvetica.ttf");
        Typeface helL= Typeface.createFromAsset(convertView.getContext().getAssets(),"fonts/Helvetica Light.ttf");
        Typeface y340 = Typeface.createFromAsset(convertView.getContext().getAssets(),"fonts/yoon340.ttf");

        englishName.setTypeface(helR);
        pham_distance.setTypeface(helR);

        englishAddr.setTypeface(helL);



        //id.setText(hospitalArrayList.get(position).getId());
        pham_distance.setText( Integer.toString( (int) Double.parseDouble(pharmacyArrayList.get(position).getDistance() )) +"m");
        englishAddr.setText(pharmacyArrayList.get(position).getEnglishAddr());
        dutyName.setText(pharmacyArrayList.get(position).getDutyName());
        englishName.setText(pharmacyArrayList.get(position).getEnglishName());
        dutyTel1.setText(pharmacyArrayList.get(position).getDutyTel1());
        dutyTime1s.setText(pharmacyArrayList.get(position).getDutyTime1s());
        dutyTime1c.setText(pharmacyArrayList.get(position).getDutyTime1c());
        postCdn1.setText(pharmacyArrayList.get(position).getPostCdn1());
        postCdn2.setText(pharmacyArrayList.get(position).getPostCdn2());
        latitude.setText(pharmacyArrayList.get(position).getLatitude());
        longitude.setText(pharmacyArrayList.get(position).getLongitude());
        foreignLanguage1.setText(pharmacyArrayList.get(position).getforeignLanguage1());


        btn_call.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+ pharmacyArrayList.get(position).getDutyTel1()));
                    context.startActivity(intent);

            }
        });
/*
        //항목 클릭했을때 Bundle로 데이터 보내기(Intent 사용 X)
       /* convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(user.getLogin_id(),"1");
                Log.d(hospitalArrayList.get(pos).getWriter_id(),"2");
                if (user.getLogin_id().equals(hospitalArrayList.get(pos).getWriter_id())) {
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

