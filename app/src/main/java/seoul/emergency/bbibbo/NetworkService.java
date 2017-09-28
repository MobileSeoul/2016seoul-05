package seoul.emergency.bbibbo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import seoul.emergency.bbibbo.model.Aed;
import seoul.emergency.bbibbo.model.Hospital;
import seoul.emergency.bbibbo.model.Notice;
import seoul.emergency.bbibbo.model.Pharmacy;

/**
 * Created by USER on 2016-08-25.
 */
public interface NetworkService {

    @GET("/phar_list")
    Call<List<Pharmacy>> getAllPharmacy();

    @GET("/phar_listByLoca/{lat}/{long}")
    Call<List<Pharmacy>> getPharmacy(@Path("lat") String lat,@Path("long") String longi);

    @GET("/emer_list/{lat}/{long}")
    Call<List<Hospital>> getAllHospital(@Path("lat") String lat,@Path("long") String longi);

    @GET("/emer_listByLoca/{lat}/{long}")
    Call<List<Hospital>> getHospital(@Path("lat") String lat,@Path("long") String longi);

    @GET("/aed_list")
    Call<List<Aed>> getAllAed();

    @GET("/aed_listByLoca/{lat}/{long}")
    Call<List<Aed>> getAed(@Path("lat") String lat,@Path("long") String longi);

    @GET("/phar_listByLang/{lang}/{lat}/{long}")
    Call<List<Pharmacy>> getLanguage(@Path("lang") String lang, @Path("lat") String lat, @Path("long") String longi);

    @GET("/notice_list")
    Call<List<Notice>> getNotice();
}