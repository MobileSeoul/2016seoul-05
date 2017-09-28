package seoul.emergency.bbibbo.aed;

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
import seoul.emergency.bbibbo.map.PharmacyMapActivity;
import seoul.emergency.bbibbo.model.Aed;

/**
 * Created by user on 2016-08-26.
 */
public class AedFragment extends Fragment {
    //리스트뷰
    ListView listView;
    //어댑터
    AedAdapter adapter;
    //네트워크서비스
    private NetworkService networkService;
    //Aed 어레이
    ArrayList<Aed> aedArrayList;
    //Aed 어댑터
    AedAdapter aedAdapter;
    Context context;
    Aed aed = new Aed();
    AedMapActivity aedMapActivity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = null;

        int pos = 0;
        v = inflater.inflate(R.layout.aed_listview, container, false);
        networkService = ApplicationController.getInstance().getNetworkService();
        aedArrayList = new ArrayList<Aed>();
        listView = (ListView) v.findViewById(R.id.aed_name_listView);

        //내위치
        Bundle extra = getArguments();
        Log.d("@@", "" + extra.get("mylatitude_aed"));
        Log.d("@@", "" + extra.getFloat("mylatitude_aed"));
        aed.setLatitude(String.valueOf(extra.getFloat("mylatitude_aed")));
        aed.setLongitude(String.valueOf(extra.getFloat("mylongitude_aed")));

        try {
            final Call<List<Aed>> allAed = networkService.getAed(aed.getLatitude(), aed.getLongitude());
            allAed.enqueue(new Callback<List<Aed>>() {
                @Override
                public void onResponse(Call<List<Aed>> call, Response<List<Aed>> response) {
                    if (response.code() == 201) {
                        //0916_soyeon
                        if (response.body().size() == 0)
                            Toast.makeText(getActivity(), "Failed to load thumbnails", Toast.LENGTH_SHORT).show();
                        else {
                            List<Aed> allaed = response.body();
                            //Log.d("SOPT", "  allhospital_id : " + allaed.get(0).getId());
                            //Hospital(int id, String dutyAddr, String englishAddr, String dutyEmcls, String dutyEmclsName, String dutyName, String englishName, String dutyTel1, String dutyTel2, String latitude, String longitude)
                            for (int i = 0; i < allaed.size(); i++) {

                                aedArrayList.add(new Aed(allaed.get(i).getId(), allaed.get(i).getBuildAddress(), allaed.get(i).getBuildPlace(), allaed.get(i).getClerkTel(), allaed.get(i).getGugun(), allaed.get(i).getManager(),
                                        allaed.get(i).getManagerTel(), allaed.get(i).getMfg(), allaed.get(i).getModel(), allaed.get(i).getOrg(), allaed.get(i).getLatitude(), allaed.get(i).getLongitude(), allaed.get(i).getZipcode1(), allaed.get(i).getZipcode2(), allaed.get(i).getDistance()));

                            }
                            aedAdapter = new AedAdapter(getActivity(), R.layout.custom_aed, aedArrayList);
                            listView.setAdapter(aedAdapter);
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                                    Log.d("인성", "here");
                                    aedMapActivity = (AedMapActivity) getActivity();
                                    changeFragment(aedArrayList.get(pos));
                                    aedMapActivity.setMarkerMap(pos);


                                }
                            });
                            aedAdapter.notifyDataSetChanged();
                        }
                    } else {
                        int statusCode = response.code();
                        Log.d("SOPT", "응답코드: " + statusCode);
                    }
                }

                @Override
                public void onFailure(Call<List<Aed>> call, Throwable t) {
                    Toast.makeText(getActivity(), "Failed to load data", Toast.LENGTH_LONG).show();
                    //Log.i("SOPT", "에러내용: " + t.getMessage());

                }
            });
        }catch(Exception e) {
            e.getMessage();
            Toast.makeText(getActivity(), "Don't turn off during loading ", Toast.LENGTH_SHORT).show();
        }
        return v;
    }


    public void changeFragment(Aed aed) {
        AedMapActivity aedMapActivity = (AedMapActivity) this.getActivity();
        aedMapActivity.changeFragment(aed);
    }

    public void setListView(String title) {
        for (int i = 0; i < aedArrayList.size(); i++) {
            if (aedArrayList.get(i).getBuildAddress().equals(title)) {
                if (i == aedArrayList.size() - 1) {
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
