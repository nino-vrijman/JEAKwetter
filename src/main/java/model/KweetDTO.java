package model;

import java.util.Date;

/**
 * Created by Nino Vrijman on 3-5-2017.
 */
public class KweetDTO {
    private long id;
    private String content;
    private Date date;
    private String createdBy;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public KweetDTO(Kweet kweet) {
        this.id = kweet.getId();
        this.content = kweet.getContent();
        this.date = kweet.getDate();
        this.createdBy = kweet.getCreatedBy().getUsername();
    }
}
