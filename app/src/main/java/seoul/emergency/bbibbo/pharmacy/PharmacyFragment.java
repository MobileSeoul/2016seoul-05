

package seoul.emergency.bbibbo.pharmacy;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
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
import seoul.emergency.bbibbo.map.PharmacyMapActivity;
import seoul.emergency.bbibbo.model.Pharmacy;

/**
 * Created by user on 2016-08-23.
 */
public class PharmacyFragment extends Fragment {
    //리스트뷰
    ListView listView;
    //어댑터
    PharmacyAdapter adapter;
    //네트워크서비스
    private NetworkService networkService;
    //약국 어레이
    ArrayList<Pharmacy> pharmacyArrayList;
    //약국 어댑터
    PharmacyAdapter pharmacyAdapter;
    Context context;
    int pos;
    Pharmacy pharmacy = new Pharmacy();
    PharmacyMapActivity pharmacyMapActivity;
    String language
            ,targetListViewTitle;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = null;

        v = inflater.inflate(R.layout.pharmacy_listview, container, false);
        networkService = ApplicationController.getInstance().getNetworkService();
        pharmacyArrayList = new ArrayList<Pharmacy>();
        listView = (ListView) v.findViewById(R.id.pharmacy_name_listView);
//내위치
        Bundle extra = getArguments();
        Log.d("@@@", "" + extra.getFloat("mylatitude"));
        Log.d("@@@", "" + extra.getFloat("mylongitude"));
        pharmacy.setLatitude(extra.getFloat("mylatitude"));
        pharmacy.setLongitude(extra.getFloat("mylongitude"));
        if(language ==null) {
            language = extra.getString("language");
            setNearBy();
        }
        else {

            if (language.equals("Any near by")) {
                setNearBy();
            } else setDataByLanguage(pharmacy.getLatitude(), pharmacy.getLongitude(), language);
        }
        setListView(targetListViewTitle);
        return v;
    }

    @Override
    public void onResume(){
        //Bundle bundle = getArguments();
        //String marker_name = bundle.getString("setList");
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        setListView(targetListViewTitle);
        Log.d("vv",""+targetListViewTitle);
        super.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    public void setLanguage(String lang){
        language = lang;
    }

    public void setNearBy() {

        if (pharmacyArrayList != null) pharmacyArrayList.clear();
        try {
            final Call<List<Pharmacy>> allPharmacy = networkService.getPharmacy(pharmacy.getLatitude(), pharmacy.getLongitude());
            allPharmacy.enqueue(new Callback<List<Pharmacy>>() {
                @Override
                public void onResponse(Call<List<Pharmacy>> call, Response<List<Pharmacy>> response) {
                    Log.d("my", "" + pharmacy.getLongitude());
                    if (response.code() == 201) {
                        List<Pharmacy> allpharmacy = response.body();
                        //Log.d("SOPT", "  allpharmacy_id : " + allpharmacy.get(0).getId());
                        //int id, String dutyAddr, String englishAddr, String dutyName, String englishName, String dutyTel1, String dutyTime1s, String dutyTime1c, String dutyTime2s, String dutyTime2c, String dutyTime3s, String dutyTime3c, String dutyTime4s, String dutyTime4c, String dutyTime5s, String dutyTime5c, String dutyTime6s, String dutyTime6c, String dutyTime7s, String dutyTime7c, String dutyTime8s, String dutyTime8c
                        // String postCdn1, String postCdn2, String latitude, String longitude, String foreignLanguage1, String foreignLanguage2, String foreignLanguage3, String foreignLanguage4
                        for (int i = 0; i < allpharmacy.size(); i++) {
                            pos = i;
                            pharmacyArrayList.add(new Pharmacy(allpharmacy.get(i).getId(), allpharmacy.get(i).getDutyAddr(), allpharmacy.get(i).getEnglishAddr(), allpharmacy.get(i).getDutyName(), allpharmacy.get(i).getEnglishName(), allpharmacy.get(i).getDutyTel1(), allpharmacy.get(i).getDutyAddr(),
                                    allpharmacy.get(i).getDutyTime1c(), allpharmacy.get(i).getDutyTime2s(), allpharmacy.get(i).getDutyTime2c(), allpharmacy.get(i).getDutyTime3s(), allpharmacy.get(i).getDutyTime3c(), allpharmacy.get(i).getDutyTime4s(), allpharmacy.get(i).getDutyTime4c(), allpharmacy.get(i).getDutyTime5s(), allpharmacy.get(i).getDutyTime5c(), allpharmacy.get(i).getDutyTime6s(), allpharmacy.get(i).getDutyTime6c(), allpharmacy.get(i).getDutyTime7s(), allpharmacy.get(i).getDutyTime7c(), allpharmacy.get(i).getDutyTime8s(), allpharmacy.get(i).getDutyTime8c(),
                                    allpharmacy.get(i).getPostCdn1(), allpharmacy.get(i).getPostCdn2(), allpharmacy.get(i).getLatitude(), allpharmacy.get(i).getLongitude(), allpharmacy.get(i).getforeignLanguage1(), allpharmacy.get(i).getforeignLanguage2(), allpharmacy.get(i).getforeignLanguage3(), allpharmacy.get(i).getforeignLanguage4(), allpharmacy.get(i).getDistance()));

                        }
                        pharmacyAdapter = new PharmacyAdapter(getActivity(), R.layout.custom_pharmacy, pharmacyArrayList);
                        listView.setAdapter(pharmacyAdapter);

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                                Log.d("인성", "here");
                                pharmacyMapActivity = (PharmacyMapActivity) getActivity();
                                changeFragment(pharmacyArrayList.get(pos));
                                pharmacyMapActivity.setMarkerMap(pos);
                            }
                        });
                        pharmacyAdapter.notifyDataSetChanged();
                    } else {
                        int statusCode = response.code();
                        Log.d("SOPT", "응답코드: " + statusCode);
                    }
                }

                @Override
                public void onFailure(Call<List<Pharmacy>> call, Throwable t) {
                    //Toast.makeText(context, "Failed to load thumbnails", Toast.LENGTH_LONG).show();
                    Log.i("SOPT", "에러내용: " + t.getMessage());

                }
            });
        } catch(Exception e) {
            e.getMessage();
            Toast.makeText(getActivity(), "Don't turn off during loading ", Toast.LENGTH_SHORT).show();
        }
    }

    public void setDataByLanguage(String lat, String lon, String lang) {

        if (pharmacyArrayList != null) pharmacyArrayList.clear();

        try {
            final Call<List<Pharmacy>> langPharmacy = networkService.getLanguage(lang, lat, lon);
            langPharmacy.enqueue(new Callback<List<Pharmacy>>() {
                @Override
                public void onResponse(Call<List<Pharmacy>> call, Response<List<Pharmacy>> response) {
                    Log.d("my", "" + pharmacy.getLongitude());
                    if (response.code() == 201) {
                        List<Pharmacy> allpharmacy = response.body();
                        Log.d("SOPT", "  allpharmacy_id : " + allpharmacy.get(0).getId());
                        //int id, String dutyAddr, String englishAddr, String dutyName, String englishName, String dutyTel1, String dutyTime1s, String dutyTime1c, String dutyTime2s, String dutyTime2c, String dutyTime3s, String dutyTime3c, String dutyTime4s, String dutyTime4c, String dutyTime5s, String dutyTime5c, String dutyTime6s, String dutyTime6c, String dutyTime7s, String dutyTime7c, String dutyTime8s, String dutyTime8c
                        // String postCdn1, String postCdn2, String latitude, String longitude, String foreignLanguage1, String foreignLanguage2, String foreignLanguage3, String foreignLanguage4
                        for (int i = 0; i < allpharmacy.size(); i++) {
                            pos = i;
                            pharmacyArrayList.add(new Pharmacy(allpharmacy.get(i).getId(), allpharmacy.get(i).getDutyAddr(), allpharmacy.get(i).getEnglishAddr(), allpharmacy.get(i).getDutyName(), allpharmacy.get(i).getEnglishName(), allpharmacy.get(i).getDutyTel1(), allpharmacy.get(i).getDutyAddr(),
                                    allpharmacy.get(i).getDutyTime1c(), allpharmacy.get(i).getDutyTime2s(), allpharmacy.get(i).getDutyTime2c(), allpharmacy.get(i).getDutyTime3s(), allpharmacy.get(i).getDutyTime3c(), allpharmacy.get(i).getDutyTime4s(), allpharmacy.get(i).getDutyTime4c(), allpharmacy.get(i).getDutyTime5s(), allpharmacy.get(i).getDutyTime5c(), allpharmacy.get(i).getDutyTime6s(), allpharmacy.get(i).getDutyTime6c(), allpharmacy.get(i).getDutyTime7s(), allpharmacy.get(i).getDutyTime7c(), allpharmacy.get(i).getDutyTime8s(), allpharmacy.get(i).getDutyTime8c(),
                                    allpharmacy.get(i).getPostCdn1(), allpharmacy.get(i).getPostCdn2(), allpharmacy.get(i).getLatitude(), allpharmacy.get(i).getLongitude(), allpharmacy.get(i).getforeignLanguage1(), allpharmacy.get(i).getforeignLanguage2(), allpharmacy.get(i).getforeignLanguage3(), allpharmacy.get(i).getforeignLanguage4(), allpharmacy.get(i).getDistance()));

                        }

                        pharmacyAdapter = new PharmacyAdapter(getActivity(), R.layout.custom_pharmacy, pharmacyArrayList);
                        listView.setAdapter(pharmacyAdapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                                Log.d("bbibbo", "클릭됨");
                                pharmacyMapActivity = (PharmacyMapActivity) getActivity();
                                changeFragment(pharmacyArrayList.get(pos));
                                pharmacyMapActivity.setMarkerMap(pos);

                            }
                        });
                        pharmacyAdapter.notifyDataSetChanged();
                    } else {
                        int statusCode = response.code();
                        Log.d("SOPT", "응답코드: " + statusCode);
                    }
                }

                @Override
                public void onFailure(Call<List<Pharmacy>> call, Throwable t) {
                    //Toast.makeText(context, "Failed to load thumbnails", Toast.LENGTH_LONG).show();
                    Log.i("SOPT", "에러내용: " + t.getMessage());

                }
            });

        } catch (Exception e) {
            e.getMessage();
            Toast.makeText(getActivity(), "Don't turn off during loading ", Toast.LENGTH_SHORT).show();
        }
    }


    public void changeFragment(Pharmacy pharmacy) {
        PharmacyMapActivity mapsActivity = (PharmacyMapActivity) this.getActivity();
        mapsActivity.changeFragment(pharmacy);
    }
   /* public void setPharmacyArrayList(String language){
        for(int i =0; i< pharmacyArrayList.size();i++){
            if(language.equals(pharmacyArrayList.get(i).getforeignLanguage1()))
        }
    }*/

    public void setListView(String title) {
        Log.d("title",""+title);
        for (int i = 0; i < pharmacyArrayList.size(); i++) {

            if (pharmacyArrayList.get(i).getEnglishName().equals(title)) {
                Log.d("11111",""+title+pharmacyArrayList.get(i).getEnglishName());
                if (i == pharmacyArrayList.size() - 1) {
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

    public void setListViewTitle(String title){
        targetListViewTitle = title;
    }

}
