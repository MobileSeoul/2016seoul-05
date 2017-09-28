package seoul.emergency.bbibbo.model;

import com.google.android.gms.maps.model.Marker;

import java.io.Serializable;

/**
 * Created by USER on 2016-08-24.
 */
public class Aed implements Serializable {
    private int id;
    private String buildAddress;
    private String buildPlace;
    private String clerkTel;
    private String gugun;
    private String manager;
    private String managerTel;
    private String mfg;
    private String model;
    private String org;
    private String latitude;
    private String longitude;
    private String zipcode1;
    private String zipcode2;
    private String distance;

    public String getMakerId() {
        return makerId;
    }

    public void setMakerId(String makerId) {
        this.makerId = makerId;
    }

    private String makerId;

    public Aed(){}
    //전체변수
    public Aed(int id, String buildAddress, String buildPlace, String clerkTel, String gugun, String manager, String managerTel, String mfg, String model, String org, String latitude, String longitude, String zipcode1, String zipcode2, String distance) {
        this.id = id;
        this.buildAddress = buildAddress;
        this.buildPlace = buildPlace;
        this.clerkTel = clerkTel;
        this.gugun = gugun;
        this.manager = manager;
        this.managerTel = managerTel;
        this.mfg = mfg;
        this.model = model;
        this.org = org;
        this.latitude = latitude;
        this.longitude = longitude;
        this.zipcode1 = zipcode1;
        this.zipcode2 = zipcode2;
        this.distance = distance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBuildAddress() {
        return buildAddress;
    }

    public void setBuildAddress(String buildAddress) {
        this.buildAddress = buildAddress;
    }

    public String getBuildPlace() {
        return buildPlace;
    }

    public void setBuildPlace(String buildPlace) {
        this.buildPlace = buildPlace;
    }

    public String getClerkTel() {
        return clerkTel;
    }

    public void setClerkTel(String clerkTel) {
        this.clerkTel = clerkTel;
    }

    public String getGugun() {
        return gugun;
    }

    public void setGugun(String gugun) {
        this.gugun = gugun;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getManagerTel() {
        return managerTel;
    }

    public void setManagerTel(String managerTel) {
        this.managerTel = managerTel;
    }

    public String getMfg() {
        return mfg;
    }

    public void setMfg(String mfg) {
        this.mfg = mfg;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
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

    public String getZipcode1() {
        return zipcode1;
    }

    public void setZipcode1(String zipcode1) {
        this.zipcode1 = zipcode1;
    }

    public String getZipcode2() {
        return zipcode2;
    }

    public void setZipcode2(String zipcode2) {
        this.zipcode2 = zipcode2;
    }

    public String getDistance() {
        return this.distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
