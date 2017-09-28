
package seoul.emergency.bbibbo.hospital;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import seoul.emergency.bbibbo.MainActivity;
import seoul.emergency.bbibbo.R;
import seoul.emergency.bbibbo.model.Hospital;

/**
 * Created by user on 2016-08-23.
 */
public class HospitalAdapter extends BaseAdapter {


    Context context;
    LayoutInflater inflater;
    int layout;
    ArrayList<Hospital> hospitalArrayList;

    //    커스텀 어댑터 생성자
    public HospitalAdapter(Context context, int layout, ArrayList<Hospital> list) {
        this.context = context;
        this.layout = layout;
        this.hospitalArrayList = list;
//        직접 작성한 레이아웃을 inflation 하기 위한 inflator 준비
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    //    원본 데이터의 전체 개수 반환
    @Override
    public int getCount() {
        return hospitalArrayList.size();
    }

    //    원본 데이터의 특정 항목 반환
    @Override
    public Object getItem(int i) {
        return hospitalArrayList.get(i);
    }

    //    원본 데이터 특정 항목의 아이디 반환
    @Override
    public long getItemId(int i) {
        return hospitalArrayList.get(i).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();
        //user = MainActivity.mainActivity.getUser();
        //Log.d(user.getLogin_id(),"userId");
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_hospital, parent, false);
        }
        /*
        private int id;
    private String dutyAddr;
    private String englishAddr;
    private String dutyEmcls;
    private String dutyEmclsName;
    private String dutyName;
    private String englishName;
    private String dutyTel1;
    private String dutyTel2;
    private String latitude;
    private String longitude;

         */
        LinearLayout layout_customHospital = (LinearLayout) convertView.findViewById(R.id.layout_customHospital);
        TextView english_name = (TextView) convertView.findViewById(R.id.hospitalList_englishName);
        TextView english_addr = (TextView) convertView.findViewById(R.id.hospitalList_englishAddr);
        TextView distance = (TextView) convertView.findViewById(R.id.hospitalList_dutyAddr);
        TextView btn_call = (TextView) convertView.findViewById(R.id.hospitalList_btnCall);

        ViewGroup.LayoutParams params8 = (ViewGroup.LayoutParams) layout_customHospital.getLayoutParams();

        params8.height = (int)(MainActivity.window_height * 73.0 / 640);

        //     ViewGroup.LayoutParams params11 = (ViewGroup.LayoutParams) distance.getLayoutParams();


//        ViewGroup.LayoutParams params9 = (ViewGroup.LayoutParams) english_name.getLayoutParams();
//        params9.width = (int)((MainActivity.window_width * 263.0 / 360))- params11.width;
        //       ViewGroup.LayoutParams params10 = (ViewGroup.LayoutParams) english_addr.getLayoutParams();
        //       params10.leftMargin = MainActivity.window_width * 16 / 360;

        ViewGroup.LayoutParams params12 = (ViewGroup.LayoutParams) btn_call.getLayoutParams();
        params12.width = (int)(MainActivity.window_width * 43.0 / 360);
        params12.height = (int)(MainActivity.window_height * 48.0 / 640);



        TextView id = (TextView) convertView.findViewById(R.id.hospitalList_id);
        TextView dutyAddr = (TextView) convertView.findViewById(R.id.hospitalList_dutyAddr);
        TextView englishAddr = (TextView) convertView.findViewById(R.id.hospitalList_englishAddr);
        TextView dutyEmcls = (TextView) convertView.findViewById(R.id.hospitalList_dutyEmcls);
        TextView dutyEmclsName = (TextView) convertView.findViewById(R.id.hospitalList_dutyEmclsName);
        TextView englishName = (TextView) convertView.findViewById(R.id.hospitalList_englishName);
        TextView dutyTel1 = (TextView) convertView.findViewById(R.id.hospitalList_dutyTel1);
        TextView dutyTel2 = (TextView) convertView.findViewById(R.id.hospitalList_dutyTel2);
        TextView latitude = (TextView) convertView.findViewById(R.id.hospitalList_latiitude);
        TextView longitude = (TextView) convertView.findViewById(R.id.hospitalList_longitude);

        // TextView dutyName = (TextView)convertView.findViewById(R.id.hospitalList_id);

        //id.setText(hospitalArrayList.get(position).getId());
        Typeface helR = Typeface.createFromAsset(convertView.getContext().getAssets(),"fonts/Helvetica.ttf");
        Typeface helL= Typeface.createFromAsset(convertView.getContext().getAssets(),"fonts/Helvetica Light.ttf");
        Typeface y340 = Typeface.createFromAsset(convertView.getContext().getAssets(),"fonts/yoon340.ttf");

        englishName.setTypeface(helR);
        englishAddr.setTypeface(helL);
        dutyEmclsName.setTypeface(helR);


        dutyAddr.setText(hospitalArrayList.get(position).getDutyAddr());
        englishAddr.setText(hospitalArrayList.get(position).getEnglishAddr());
        dutyEmcls.setText(hospitalArrayList.get(position).getDutyEmcls());
        dutyEmclsName.setText(Integer.toString( (int) Double.parseDouble(hospitalArrayList.get(position).getDistance() )) +"m");
        englishName.setText(hospitalArrayList.get(position).getEnglishName());
        dutyTel1.setText(hospitalArrayList.get(position).getDutyTel1());
        dutyTel2.setText(hospitalArrayList.get(position).getDutyTel2());
        latitude.setText(hospitalArrayList.get(position).getLatitude());
        longitude.setText(hospitalArrayList.get(position).getLongitude());


        btn_call.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+ hospitalArrayList.get(position).getDutyTel1()));
                context.startActivity(intent);
            }
        });
        //항목 클릭했을때 Bundle로 데이터 보내기(Intent 사용 X)

        return convertView;
    }


}