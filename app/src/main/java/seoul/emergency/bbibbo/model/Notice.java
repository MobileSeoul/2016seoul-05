package seoul.emergency.bbibbo.model;

import java.io.Serializable;

/**
 * Created by USER on 2016-09-29.
 */
public class Notice implements Serializable{
    private int id;
    private String title;
    private String content;
    private String written_date;

    public Notice(int id, String title, String content, String written_date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.written_date = written_date;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWritten_date() {
        return written_date;
    }

    public void setWritten_date(String written_date) {
        this.written_date = written_date;
    }

}
