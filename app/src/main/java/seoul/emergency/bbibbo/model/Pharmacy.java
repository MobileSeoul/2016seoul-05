package seoul.emergency.bbibbo.model;

import java.io.Serializable;

/**
 * Created by USER on 2016-08-24.
 */
public class Pharmacy implements Serializable {

    private int id;
    private String dutyAddr;
    private String englishAddr;
    private String dutyName;
    private String englishName;
    private String dutyTel1;
    private String dutyTime1s;
    private String dutyTime1c;
    private String dutyTime2s;
    private String dutyTime2c;
    private String dutyTime3s;
    private String dutyTime3c;
    private String dutyTime4s;
    private String dutyTime4c;
    private String dutyTime5s;
    private String dutyTime5c;
    private String dutyTime6s;
    private String dutyTime6c;
    private String dutyTime7s;
    private String dutyTime7c;
    private String dutyTime8s;
    private String dutyTime8c;
    private String postCdn1;
    private String postCdn2;
    private String latitude;
    private String longitude;
    private String foreignLanguage1;
    private String foreignLanguage2;
    private String foreignLanguage3;
    private String foreignLanguage4;
    private String markerId;
    private String distance;

    public Pharmacy(){}
    //전체 변수
    public Pharmacy(int id, String dutyAddr, String englishAddr, String dutyName, String englishName, String dutyTel1, String dutyTime1s, String dutyTime1c, String dutyTime2s, String dutyTime2c, String dutyTime3s, String dutyTime3c, String dutyTime4s, String dutyTime4c, String dutyTime5s, String dutyTime5c, String dutyTime6s, String dutyTime6c, String dutyTime7s, String dutyTime7c, String dutyTime8s, String dutyTime8c, String postCdn1, String postCdn2, String latitude, String longitude, String foreignLanguage1, String foreignLanguage2, String foreignLanguage3, String foreignLanguage4, String distance) {
        this.id = id;
        this.dutyAddr = dutyAddr;
        this.englishAddr = englishAddr;
        this.dutyName = dutyName;
        this.englishName = englishName;
        this.dutyTel1 = dutyTel1;
        this.dutyTime1s = dutyTime1s;
        this.dutyTime1c = dutyTime1c;
        this.dutyTime2s = dutyTime2s;
        this.dutyTime2c = dutyTime2c;
        this.dutyTime3s = dutyTime3s;
        this.dutyTime3c = dutyTime3c;
        this.dutyTime4s = dutyTime4s;
        this.dutyTime4c = dutyTime4c;
        this.dutyTime5s = dutyTime5s;
        this.dutyTime5c = dutyTime5c;
        this.dutyTime6s = dutyTime6s;
        this.dutyTime6c = dutyTime6c;
        this.dutyTime7s = dutyTime7s;
        this.dutyTime7c = dutyTime7c;
        this.dutyTime8s = dutyTime8s;
        this.dutyTime8c = dutyTime8c;
        this.postCdn1 = postCdn1;
        this.postCdn2 = postCdn2;
        this.latitude = latitude;
        this.longitude = longitude;
        this.foreignLanguage1 = foreignLanguage1;
        this.foreignLanguage2 = foreignLanguage2;
        this.foreignLanguage3 = foreignLanguage3;
        this.foreignLanguage4 = foreignLanguage4;
        this.distance = distance;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDutyAddr() {
        return dutyAddr;
    }

    public void setDutyAddr(String dutyAddr) {
        this.dutyAddr = dutyAddr;
    }

    public String getEnglishAddr() {
        return englishAddr;
    }

    public void setEnglishAddr(String englishAddr) {
        this.englishAddr = englishAddr;
    }

    public String getDutyName() {
        return dutyName;
    }

    public void setDutyName(String dutyName) {
        this.dutyName = dutyName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getDutyTel1() {
        return dutyTel1;
    }

    public void setDutyTel1(String dutyTel1) {
        this.dutyTel1 = dutyTel1;
    }

    public String getDutyTime1s() {
        return dutyTime1s;
    }

    public void setDutyTime1s(String dutyTime1s) {
        this.dutyTime1s = dutyTime1s;
    }

    public String getDutyTime1c() {
        return dutyTime1c;
    }

    public void setDutyTime1c(String dutyTime1c) {
        this.dutyTime1c = dutyTime1c;
    }

    public String getDutyTime2s() {
        return dutyTime2s;
    }

    public void setDutyTime2s(String dutyTime2s) {
        this.dutyTime2s = dutyTime2s;
    }

    public String getDutyTime2c() {
        return dutyTime2c;
    }

    public void setDutyTime2c(String dutyTime2c) {
        this.dutyTime2c = dutyTime2c;
    }

    public String getDutyTime3s() {
        return dutyTime3s;
    }

    public void setDutyTime3s(String dutyTime3s) {
        this.dutyTime3s = dutyTime3s;
    }

    public String getDutyTime3c() {
        return dutyTime3c;
    }

    public void setDutyTime3c(String dutyTime3c) {
        this.dutyTime3c = dutyTime3c;
    }

    public String getDutyTime4s() {
        return dutyTime4s;
    }

    public void setDutyTime4s(String dutyTime4s) {
        this.dutyTime4s = dutyTime4s;
    }

    public String getDutyTime4c() {
        return dutyTime4c;
    }

    public void setDutyTime4c(String dutyTime4c) {
        this.dutyTime4c = dutyTime4c;
    }

    public String getDutyTime5s() {
        return dutyTime5s;
    }

    public void setDutyTime5s(String dutyTime5s) {
        this.dutyTime5s = dutyTime5s;
    }

    public String getDutyTime5c() {
        return dutyTime5c;
    }

    public void setDutyTime5c(String dutyTime5c) {
        this.dutyTime5c = dutyTime5c;
    }

    public String getDutyTime6s() {
        return dutyTime6s;
    }

    public void setDutyTime6s(String dutyTime6s) {
        this.dutyTime6s = dutyTime6s;
    }

    public String getDutyTime6c() {
        return dutyTime6c;
    }

    public void setDutyTime6c(String dutyTime6c) {
        this.dutyTime6c = dutyTime6c;
    }

    public String getDutyTime7s() {
        return dutyTime7s;
    }

    public void setDutyTime7s(String dutyTime7s) {
        this.dutyTime7s = dutyTime7s;
    }

    public String getDutyTime7c() {
        return dutyTime7c;
    }

    public void setDutyTime7c(String dutyTime7c) {
        this.dutyTime7c = dutyTime7c;
    }

    public String getDutyTime8s() {
        return dutyTime8s;
    }

    public void setDutyTime8s(String dutyTime8s) {
        this.dutyTime8s = dutyTime8s;
    }

    public String getDutyTime8c() {
        return dutyTime8c;
    }

    public void setDutyTime8c(String dutyTime8c) {
        this.dutyTime8c = dutyTime8c;
    }

    public String getPostCdn1() {
        return postCdn1;
    }

    public void setPostCdn1(String postCdn1) {
        this.postCdn1 = postCdn1;
    }

    public String getPostCdn2() {
        return postCdn2;
    }

    public void setPostCdn2(String postCdn2) {
        this.postCdn2 = postCdn2;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude.toString();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude.toString();
    }

    public String getforeignLanguage1() {
        return foreignLanguage1;
    }

    public void setforeignLanguage1(String foreignLanguage1) {
        this.foreignLanguage1 = foreignLanguage1;
    }

    public String getforeignLanguage2() {
        return foreignLanguage2;
    }

    public void setforeignLanguage2(String foreignLanguage2) {
        this.foreignLanguage2 = foreignLanguage2;
    }

    public String getforeignLanguage3() {
        return foreignLanguage3;
    }

    public void setforeignLanguage3(String foreignLanguage3) {
        this.foreignLanguage3 = foreignLanguage3;
    }

    public String getforeignLanguage4() {
        return foreignLanguage4;
    }

    public void setforeignLanguage4(String foreignLanguage4) {
        this.foreignLanguage4 = foreignLanguage4;
    }
    public String getMarkerId() {
        return markerId;
    }

    public void setMarkerId(String markerId) {
        this.markerId = markerId;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }


}
