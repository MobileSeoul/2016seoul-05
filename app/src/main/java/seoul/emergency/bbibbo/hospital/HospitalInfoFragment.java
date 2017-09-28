
package seoul.emergency.bbibbo.hospital;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import seoul.emergency.bbibbo.NetworkService;
import seoul.emergency.bbibbo.R;
import seoul.emergency.bbibbo.model.Hospital;


/**
 * Created by user on 2016-08-23.
 */
public class HospitalInfoFragment extends Fragment {
    //텍스트
    TextView hospital_info_name;
    TextView hospital_info_distance;
    TextView hospital_info_eng_address;
    TextView hospital_info_kor_address;
    TextView hospital_info_phone;
    TextView hospital_info_phone2;
    TextView hospital_info_title_contact;

    TextView hospital_info_title_phone;
    ImageButton hospital_info_iBtn_phone;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("@@","들어는");
        View v = null;
        v = inflater.inflate(R.layout.hopital_info_content2, container, false);
        Bundle bundle = this.getArguments();
        final Hospital hospital = (Hospital)bundle.getSerializable("hospital");

        hospital_info_name = (TextView)v.findViewById(R.id.hospital_info_name);
        hospital_info_distance = (TextView)v.findViewById(R.id.hospital_info_distance);
        hospital_info_eng_address = (TextView)v.findViewById(R.id.hospital_info_eng_address);
        hospital_info_kor_address = (TextView)v.findViewById(R.id.hospital_info_kor_address);
        hospital_info_phone = (TextView)v.findViewById(R.id.hospital_info_phone);
        hospital_info_phone2 = (TextView)v.findViewById(R.id.hospital_info_phone2);
        hospital_info_iBtn_phone = (ImageButton)v.findViewById(R.id.hospital_info_iBtn_phone);
        hospital_info_title_contact = (TextView)v.findViewById(R.id.hospital_info_title_contact);
        hospital_info_title_phone = (TextView)v.findViewById(R.id.hospital_info_title_phone);

        Typeface helR = Typeface.createFromAsset(v.getContext().getAssets(),"fonts/Helvetica.ttf");
        Typeface helL= Typeface.createFromAsset(v.getContext().getAssets(),"fonts/Helvetica Light.ttf");
        Typeface y340 = Typeface.createFromAsset(v.getContext().getAssets(),"fonts/yoon340.ttf");

        hospital_info_name.setTypeface(helR);
        hospital_info_distance.setTypeface(helR);

        hospital_info_eng_address.setTypeface(helL);
        hospital_info_kor_address.setTypeface(helL);
        hospital_info_phone.setTypeface(helL);
        hospital_info_phone2.setTypeface(helL);


        hospital_info_title_phone.setTypeface(y340);
        hospital_info_title_contact.setTypeface(y340);

        float distance = Float.parseFloat(hospital.getDistance());
        int distance2 = (int)distance;
        String distance3 = String.valueOf(distance2);

        hospital_info_name.setText(hospital.getEnglishName());
        hospital_info_distance.setText(distance3+"m");
        hospital_info_eng_address.setText(hospital.getEnglishAddr());
        hospital_info_kor_address.setText(hospital.getDutyAddr());
        hospital_info_phone.setText(hospital.getDutyTel1());
        hospital_info_phone2.setText(hospital.getDutyTel2());

        hospital_info_iBtn_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+hospital.getDutyTel1()));
                startActivity(intent);
            }
        });

        return v;
    }



}