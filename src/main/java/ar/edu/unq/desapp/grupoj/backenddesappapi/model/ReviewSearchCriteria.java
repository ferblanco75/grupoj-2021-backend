package ar.edu.unq.desapp.grupoj.backenddesappapi.model;

public class ReviewSearchCriteria {

    private Integer source;
    private Integer language;
    private Integer id;
    private boolean spoilerAlert;


    public Integer getSource() {
        return source;
    }

    public boolean isSpoilerAlert() {
        return spoilerAlert;
    }

    public Integer getId(){
        return id;
    }


    public void setSource(Integer source) {
        this.source = source;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSpoilerAlert(boolean spoilerAlert) {
        this.spoilerAlert = spoilerAlert;
    }


    public Integer getLanguage() {
        return language;
    }

    public void setLanguage(Integer language) {
        this.language = language;
    }
}
