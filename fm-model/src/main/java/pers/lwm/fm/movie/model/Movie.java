package pers.lwm.fm.movie.model;

/**
 * Created by lwm on 2016/3/27.
 */
public class Movie {

    private int id;
    private String ChnName;
    private String EngName;
    private float score;
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChnName() {
        return ChnName;
    }

    public void setChnName(String chnName) {
        ChnName = chnName;
    }

    public String getEngName() {
        return EngName;
    }

    public void setEngName(String engName) {
        EngName = engName;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
