package seoul.emergency.bbibbo.model;

import java.io.Serializable;

/**
 * Created by USER on 2016-08-24.
 */
public class Hospital implements Serializable {

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
    private String distance;

    //전체변수
    public Hospital(int id, String dutyAddr, String englishAddr, String dutyEmcls, String dutyEmclsName, String dutyName, String englishName, String dutyTel1, String dutyTel2, String latitude, String longitude, String distance) {
        this.id = id;
        this.dutyAddr = dutyAddr;
        this.englishAddr = englishAddr;
        this.dutyEmcls = dutyEmcls;
        this.dutyEmclsName = dutyEmclsName;
        this.dutyName = dutyName;
        this.englishName = englishName;
        this.dutyTel1 = dutyTel1;
        this.dutyTel2 = dutyTel2;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
    }

    public Hospital() {
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

    public String getDutyEmcls() {
        return dutyEmcls;
    }

    public void setDutyEmcls(String dutyEmcls) {
        this.dutyEmcls = dutyEmcls;
    }

    public String getDutyEmclsName() {
        return dutyEmclsName;
    }

    public void setDutyEmclsName(String dutyEmclsName) {
        this.dutyEmclsName = dutyEmclsName;
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

    public String getDutyTel2() {
        return dutyTel2;
    }

    public void setDutyTel2(String dutyTel2) {
        this.dutyTel2 = dutyTel2;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDistance(){return distance;}

    public void setDistance(String distance){this.distance =distance;}
}
