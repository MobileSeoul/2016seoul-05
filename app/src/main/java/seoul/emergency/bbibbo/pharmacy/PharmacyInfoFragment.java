package seoul.emergency.bbibbo.pharmacy;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import seoul.emergency.bbibbo.MainActivity;
import seoul.emergency.bbibbo.NetworkService;
import seoul.emergency.bbibbo.R;
import seoul.emergency.bbibbo.model.Hospital;
import seoul.emergency.bbibbo.model.Pharmacy;

/**
 * Created by USER on 2016-08-25.
 */
public class PharmacyInfoFragment extends Fragment {
    //텍스트
    TextView pharmacy_info_name;
    TextView pharmacy_info_distance;
    TextView pharmacy_info_eng_address;
    TextView pharmacy_info_kor_address;
    TextView pharmacy_info_weekdayO;
    TextView pharmacy_info_weekdayC;
    TextView pharmacy_info_phone;
    TextView pharmacy_info_weekend_satO;
    TextView pharmacy_info_weekend_satC;
    TextView pharmacy_info_weekend_sunO;
    TextView pharmacy_info_weekend_sunC;
    TextView pharmacy_info_title_weekday;
    TextView pharmacy_info_title_phone;
    TextView pharmacy_info_title_sat;
    TextView pharmacy_info_title_sun;
    TextView pharmacy_info_title_language;
    TextView pharmacy_info_title_contact;

    TextView textView7;//일요일짝대기
    ImageView imageView1;//영어
    ImageView imageView2;//일본어
    ImageView imageView3;//중국어
    ImageView imageView4;//프랑스어
    ImageView imageView5;//독일어
    ImageView imageView6;//러시아어

    ImageButton pharmacy_info_iBtn_phone;
    Pharmacy pharmacy;

    //String [] Language = {"English","Japanese","Chinese","French","German","Russian"};
    String [] isLanguage;




    //어댑터
    //HospitalInfoAdapter adapter;
    //네트워크서비스
    private NetworkService networkService;

    Context context;
    int pageNumber;

    /*public static HospitalFragment create(int pageNumber) {
        HospitalFragment fragment = new HospitalFragment();
        Bundle args = new Bundle();
        args.putInt("page", pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments().getInt("page");

    }
    */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("@@","들어는");
        View v = null;
        v = inflater.inflate(R.layout.pharmacy_info_content, container, false);
        Bundle bundle = this.getArguments();
        pharmacy = (Pharmacy) bundle.getSerializable("pharmacy");

        pharmacy_info_name= (TextView)v.findViewById(R.id.pharmacy_info_name);
        pharmacy_info_distance= (TextView)v.findViewById(R.id.pharmacy_info_distance);
        pharmacy_info_eng_address= (TextView)v.findViewById(R.id.pharmacy_info_eng_address);
        pharmacy_info_kor_address= (TextView)v.findViewById(R.id.pharmacy_info_kor_address);
        pharmacy_info_weekdayO= (TextView)v.findViewById(R.id.pharmacy_info_weekdayO);
        pharmacy_info_weekdayC= (TextView)v.findViewById(R.id.pharmacy_info_weekdayC);
        pharmacy_info_phone= (TextView)v.findViewById(R.id.pharmacy_info_phone);
        pharmacy_info_weekend_satO= (TextView)v.findViewById(R.id.pharmacy_info_weekend_satO);
        pharmacy_info_weekend_satC= (TextView)v.findViewById(R.id.pharmacy_info_weekend_satC);
        pharmacy_info_weekend_sunO= (TextView)v.findViewById(R.id.pharmacy_info_weekend_sunO);
        pharmacy_info_weekend_sunC= (TextView)v.findViewById(R.id.pharmacy_info_weekend_sunC);
        pharmacy_info_title_weekday= (TextView)v.findViewById(R.id.pharmacy_info_title_weekday);
        pharmacy_info_title_phone= (TextView)v.findViewById(R.id.pharmacy_info_title_phone);
        pharmacy_info_title_sat= (TextView)v.findViewById(R.id.pharmacy_info_title_sat);
        pharmacy_info_title_sun= (TextView)v.findViewById(R.id.pharmacy_info_title_sun);
        pharmacy_info_title_language= (TextView)v.findViewById(R.id.pharmacy_info_title_language);
        textView7=(TextView)v.findViewById(R.id.textView7);
        pharmacy_info_title_contact= (TextView)v.findViewById(R.id.pharmacy_info_title_contact);

        pharmacy_info_iBtn_phone = (ImageButton)v.findViewById(R.id.pharmacy_info_iBtn_phone);

        Typeface helR = Typeface.createFromAsset(v.getContext().getAssets(),"fonts/Helvetica.ttf");
        Typeface helL= Typeface.createFromAsset(v.getContext().getAssets(),"fonts/Helvetica Light.ttf");
        Typeface y340 = Typeface.createFromAsset(v.getContext().getAssets(),"fonts/yoon340.ttf");

        pharmacy_info_name.setTypeface(helR);
        pharmacy_info_distance.setTypeface(helR);


        pharmacy_info_title_weekday.setTypeface(y340);
        pharmacy_info_title_phone.setTypeface(y340);
        pharmacy_info_title_sat.setTypeface(y340);
        pharmacy_info_title_sun.setTypeface(y340);
        pharmacy_info_title_language.setTypeface(y340);
        pharmacy_info_title_contact.setTypeface(y340);

        pharmacy_info_eng_address.setTypeface(helL);
        pharmacy_info_kor_address.setTypeface(helL);
        pharmacy_info_weekdayO.setTypeface(helL);
        pharmacy_info_weekdayC.setTypeface(helL);
        pharmacy_info_phone.setTypeface(helL);
        pharmacy_info_weekend_satO.setTypeface(helL);
        pharmacy_info_weekend_satC.setTypeface(helL);
        pharmacy_info_weekend_sunO.setTypeface(helL);
        pharmacy_info_weekend_sunC.setTypeface(helL);






        imageView1 = (ImageView)v.findViewById(R.id.imageView1);
        imageView2 = (ImageView)v.findViewById(R.id.imageView2);
        imageView3 = (ImageView)v.findViewById(R.id.imageView3);
        imageView4 = (ImageView)v.findViewById(R.id.imageView4);
        imageView5 = (ImageView)v.findViewById(R.id.imageView5);
        imageView6 = (ImageView)v.findViewById(R.id.imageView6);


        ImageButton btn_call = (ImageButton)v.findViewById(R.id.pharmacy_info_iBtn_phone);

        ViewGroup.LayoutParams params_view1 = (ViewGroup.LayoutParams) imageView1.getLayoutParams();
        params_view1.width = (int)(MainActivity.window_width * 28.0 / 360);
        params_view1.height = (int)(MainActivity.window_height * 30.0 / 640);
        ViewGroup.LayoutParams params_view2 = (ViewGroup.LayoutParams) imageView2.getLayoutParams();
        params_view2.width = (int)(MainActivity.window_width * 28.0 / 360);
        params_view2.height = (int)(MainActivity.window_height * 30.0 / 640);
        ViewGroup.LayoutParams params_view3 = (ViewGroup.LayoutParams) imageView3.getLayoutParams();
        params_view3.width = (int)(MainActivity.window_width * 28.0 / 360);
        params_view3.height = (int)(MainActivity.window_height * 30.0 / 640);
        ViewGroup.LayoutParams params_view4 = (ViewGroup.LayoutParams) imageView4.getLayoutParams();
        params_view4.width = (int)(MainActivity.window_width * 28.0 / 360);
        params_view4.height = (int)(MainActivity.window_height * 30.0 / 640);
        ViewGroup.LayoutParams params_view5 = (ViewGroup.LayoutParams) imageView5.getLayoutParams();
        params_view5.width = (int)(MainActivity.window_width * 28.0 / 360);
        params_view5.height = (int)(MainActivity.window_height * 30.0 / 640);
        ViewGroup.LayoutParams params_view6 = (ViewGroup.LayoutParams) imageView6.getLayoutParams();
        params_view6.width = (int)(MainActivity.window_width * 28.0 / 360);
        params_view6.height = (int)(MainActivity.window_height * 30.0 / 640);

        ViewGroup.LayoutParams params_btnCall = (ViewGroup.LayoutParams) btn_call.getLayoutParams();
        params_btnCall.width = (int)(MainActivity.window_width * 37.0 / 360);
        params_btnCall.height = (int)(MainActivity.window_height * 40.0 / 640);



        pharmacy_info_name.setText(pharmacy.getEnglishName());
        float distance = Float.parseFloat(pharmacy.getDistance());
        int distance2 = (int)distance;
        String distance3 = String.valueOf(distance2);
        String weekOpen = "";
        String weekClose = "";
        String weekEndOpen ="";
        String weekEndClose = "";
        String sunOpen ="";
        String sunClose = "";

        for(int i =0;i<4;i++){
            if(!pharmacy.getDutyTime2s().isEmpty())
                weekOpen += pharmacy.getDutyTime2s().charAt(i);
            if(!pharmacy.getDutyTime2c().isEmpty())
                weekClose += pharmacy.getDutyTime2c().charAt(i);
            if(!pharmacy.getDutyTime6s().isEmpty())
                weekEndOpen += pharmacy.getDutyTime6s().charAt(i);
            if(!pharmacy.getDutyTime6c().isEmpty())
                weekEndClose += pharmacy.getDutyTime6c().charAt(i);
            if(!pharmacy.getDutyTime7s().isEmpty())
                sunOpen += pharmacy.getDutyTime7s().charAt(i);
            if(!pharmacy.getDutyTime7c().isEmpty())
                sunClose += pharmacy.getDutyTime7c().charAt(i);
            if(i==1){
                weekOpen += ":";
                weekClose += ":";
                weekEndOpen += ":";
                weekEndClose +=":";
                sunOpen +=":";
                sunClose +=":";
            }

        }


        pharmacy_info_distance.setText(distance3+"m");
        pharmacy_info_eng_address.setText(pharmacy.getEnglishAddr());
        pharmacy_info_kor_address.setText(pharmacy.getDutyAddr());
        pharmacy_info_weekdayO.setText(weekOpen);
        pharmacy_info_weekdayC.setText(weekClose);
        pharmacy_info_phone.setText(pharmacy.getDutyTel1());
        pharmacy_info_weekend_satO.setText(weekEndOpen);
        pharmacy_info_weekend_satC.setText(weekEndClose);
        pharmacy_info_weekend_sunO.setText(sunOpen);
        pharmacy_info_weekend_sunC.setText(sunClose);
       /* if(pharmacy.getDutyTime7s().length() == 0) {
            pharmacy_info_weekend_sunO.setText("day off");
            textView7.setVisibility(View.GONE);
            pharmacy_info_weekend_sunC.setVisibility(View.GONE);
        }else{
            pharmacy_info_weekend_sunO.setText(pharmacy.getDutyTime7s());
            pharmacy_info_weekend_sunC.setText(pharmacy.getDutyTime7c());
        }*/
        isLanguage = new String[]{pharmacy.getforeignLanguage1(),pharmacy.getforeignLanguage2(),pharmacy.getforeignLanguage3(),pharmacy.getforeignLanguage4()};
        int i;
        for(i=0;i<4;i++){
            if(isLanguage[i]!=null&&isLanguage[i].equals("English")){
                imageView1.setVisibility(v.VISIBLE);
            }else if(isLanguage[i]!=null&&isLanguage[i].equals("Japanese")){
                imageView2.setVisibility(v.VISIBLE);
            }else if(isLanguage[i]!=null&&isLanguage[i].equals("Chinese")){
                imageView3.setVisibility(v.VISIBLE);
            }else if(isLanguage[i]!=null&&isLanguage[i].equals("French")){
                imageView4.setVisibility(v.VISIBLE);
            }else if(isLanguage[i]!=null&&isLanguage[i].equals("German")){
                imageView5.setVisibility(v.VISIBLE);
            }else if(isLanguage[i]!=null&&isLanguage[i].equals("Russian")){
                imageView6.setVisibility(v.VISIBLE);
            }
        }
        pharmacy_info_iBtn_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+pharmacy.getDutyTel1()));
                startActivity(intent);
            }
        });


        return v;
    }
}
