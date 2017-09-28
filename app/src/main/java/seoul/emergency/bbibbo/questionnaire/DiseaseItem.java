package seoul.emergency.bbibbo.questionnaire;

/**
 * Created by Min-Soo on 2016-08-24.
 */
public class DiseaseItem {
    private String name;
    private String status;

    public DiseaseItem(String name, String status){
        this.name = name;
        this.status = status;

    }

    public String getName(){
        return this.name;
    }

    public String getStatus(){
        return  this.status;
    }
}
