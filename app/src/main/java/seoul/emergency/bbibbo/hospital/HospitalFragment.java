

package seoul.emergency.bbibbo.hospital;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import seoul.emergency.bbibbo.ApplicationController;
import seoul.emergency.bbibbo.NetworkService;
import seoul.emergency.bbibbo.R;
import seoul.emergency.bbibbo.map.AedMapActivity;
import seoul.emergency.bbibbo.map.MapsActivity;
import seoul.emergency.bbibbo.model.Hospital;

/**
 * Created by user on 2016-08-23.
 */
public class HospitalFragment extends Fragment {
    //리스트뷰
    ListView listView;
    //어댑터
    HospitalAdapter adapter;
    //네트워크서비스
    private NetworkService networkService;
    //병원 어레이
    ArrayList<Hospital> hospitalArrayList;
    //병원 어댑터
    HospitalAdapter hospitalAdapter;
    Hospital hospital;
    Context context;
    MapsActivity mapsActivity;
    int pageNumber;

    /*
    public static HospitalFragment create(int pageNumber) {
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

    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = null;
        int pos = 0;
        v = inflater.inflate(R.layout.hospital_listview, container, false);
        networkService = ApplicationController.getInstance().getNetworkService();
        hospitalArrayList = new ArrayList<Hospital>();
        listView = (ListView) v.findViewById(R.id.hospital_name_listView);
        hospital = new Hospital();
        //내위치
        Bundle extra = getArguments();

        hospital.setLatitude(String.valueOf(extra.getFloat("mylatitude1")));
        hospital.setLongitude(String.valueOf(extra.getFloat("mylongitude1")));

        try {
            final Call<List<Hospital>> allHospital = networkService.getAllHospital(hospital.getLatitude(), hospital.getLongitude());
            allHospital.enqueue(new Callback<List<Hospital>>() {
                @Override
                public void onResponse(Call<List<Hospital>> call, Response<List<Hospital>> response) {
                    if (response.code() == 201) {
                        //0916_soyeon
                        if (response.body().size() == 0)
                            Toast.makeText(getActivity(), "Failed to load thumbnails", Toast.LENGTH_SHORT).show();
                        else {
                            List<Hospital> allhospital = response.body();
                            //Log.d("SOPT", "  allhospital_id : " + allhospital.get(0).getId());
                            //Hospital(int id, String dutyAddr, String englishAddr, String dutyEmcls, String dutyEmclsName, String dutyName, String englishName, String dutyTel1, String dutyTel2, String latitude, String longitude)
                            for (int i = 0; i < allhospital.size(); i++) {

                                hospitalArrayList.add(new Hospital(allhospital.get(i).getId(), allhospital.get(i).getDutyAddr(), allhospital.get(i).getEnglishAddr(), allhospital.get(i).getDutyEmcls(), allhospital.get(i).getDutyEmclsName(), allhospital.get(i).getDutyName(),
                                        allhospital.get(i).getEnglishName(), allhospital.get(i).getDutyTel1(), allhospital.get(i).getDutyTel2(), allhospital.get(i).getLatitude(), allhospital.get(i).getLongitude(), allhospital.get(i).getDistance()));

                            }
                            hospitalAdapter = new HospitalAdapter(getActivity(), R.layout.custom_hospital, hospitalArrayList);
                            listView.setAdapter(hospitalAdapter);

                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                                    // Log.d("bbibbo", "클릭됨");
                                    mapsActivity = (MapsActivity) getActivity();
                                    changeFragment(hospitalArrayList.get(pos));
                                    mapsActivity.setMarkerMap(pos);


                                }
                            });

                            hospitalAdapter.notifyDataSetChanged();
                        }
                    } else {
                        int statusCode = response.code();
                        Log.d("SOPT", "응답코드: " + statusCode);
                    }
                }

                @Override
                public void onFailure(Call<List<Hospital>> call, Throwable t) {
                    Toast.makeText(getActivity(), "Failed to load thumbnails", Toast.LENGTH_LONG).show();
                    // Log.i("SOPT", "에러내용: " + t.getMessage());

                }

            });
        }catch(Exception e) {
            e.getMessage();
            Toast.makeText(getActivity(), "Don't turn off during loading ", Toast.LENGTH_SHORT).show();
        }
        return v;
    }

    public void changeFragment(Hospital hospital){
        MapsActivity mapsActivity = (MapsActivity)this.getActivity();
        mapsActivity.changeFragment(hospital);
    }

    public void setListView(String title) {
        for (int i = 0; i < hospitalArrayList.size(); i++) {
            if (hospitalArrayList.get(i).getEnglishName().equals(title)) {
                if (i == hospitalArrayList.size() - 1) {
                    listView.smoothScrollToPosition(i);
                } else if (i == 0) {
                    listView.smoothScrollToPosition(i);
                } else {
                    listView.smoothScrollToPosition(i + 1);
                }

                listView.setSelection(i);
                break;
            }

        }
    }
}