
package seoul.emergency.bbibbo.aed;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import seoul.emergency.bbibbo.MainActivity;
import seoul.emergency.bbibbo.NetworkService;
import seoul.emergency.bbibbo.R;
import seoul.emergency.bbibbo.model.Aed;
import seoul.emergency.bbibbo.model.Hospital;


/**
 * Created by user on 2016-08-23.
 */
public class AedInfoFragment extends Fragment {
    //텍스트
    TextView aed_info_name;
    TextView aed_info_distance;
    TextView aed_info_buildPlaceEnglish;
    TextView aed_info_buildPlace;
    TextView aed_info_manager;
    TextView aed_info_phone;

    TextView aed_info_title_manager;
    TextView aed_info_title_phone;

    TextView aed_info_title_contact;

    ImageButton aed_info_iBtn_phone;
    Aed aed;


    //어댑터
    //HospitalInfoAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("@@","들어는");
        View v = null;
        v = inflater.inflate(R.layout.aed_info_content, container, false);
        Bundle bundle = this.getArguments();
        aed = (Aed)bundle.getSerializable("aed");


        aed_info_name = (TextView)v.findViewById(R.id.aed_info_name);
        aed_info_distance = (TextView)v.findViewById(R.id.aed_info_distance);
        aed_info_buildPlaceEnglish = (TextView)v.findViewById(R.id.aed_info_buildPlaceEnglish);
        aed_info_buildPlace = (TextView)v.findViewById(R.id.aed_info_buildPlace);
        aed_info_phone = (TextView)v.findViewById(R.id.aed_info_phone);
        aed_info_manager = (TextView)v.findViewById(R.id.aed_info_manager);
        aed_info_title_manager = (TextView)v.findViewById(R.id.aed_info_title_manager);
        aed_info_title_phone = (TextView)v.findViewById(R.id.aed_info_title_phone);
        aed_info_iBtn_phone = (ImageButton)v.findViewById(R.id.aed_info_iBtn_phone);
        aed_info_title_contact = (TextView)v.findViewById(R.id.aed_info_title_contact);

        Typeface helR = Typeface.createFromAsset(v.getContext().getAssets(),"fonts/Helvetica.ttf");
        Typeface helL= Typeface.createFromAsset(v.getContext().getAssets(),"fonts/Helvetica Light.ttf");
        Typeface y340 = Typeface.createFromAsset(v.getContext().getAssets(),"fonts/yoon340.ttf");

        aed_info_name.setTypeface(helR);
        aed_info_distance.setTypeface(helR);

        aed_info_buildPlaceEnglish.setTypeface(helL);
        aed_info_buildPlace.setTypeface(helL);
        aed_info_manager.setTypeface(helL);
        aed_info_phone.setTypeface(helL);

        aed_info_title_manager.setTypeface(y340);
        aed_info_title_phone.setTypeface(y340);
        aed_info_title_contact.setTypeface(y340);

        float distance = Float.parseFloat(aed.getDistance());
        int distance2 = (int)distance;
        String distance3 = String.valueOf(distance2);
        aed_info_distance.setText(distance3+"m");
        aed_info_buildPlaceEnglish.setText(aed.getBuildPlace());
        aed_info_buildPlace.setText(aed.getBuildAddress());
        aed_info_manager.setText(aed.getManager());
        aed_info_phone.setText(aed.getManagerTel());

        aed_info_iBtn_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+aed.getManagerTel()));
                startActivity(intent);
            }
        });


        ImageButton btn_call = (ImageButton)v.findViewById(R.id.aed_info_iBtn_phone);

        ViewGroup.LayoutParams params_btnCall = (ViewGroup.LayoutParams) btn_call.getLayoutParams();
        params_btnCall.width = (int)(MainActivity.window_width * 37.0 / 360);
        params_btnCall.height = (int)(MainActivity.window_height * 40.0 / 640);




        return v;
    }



}