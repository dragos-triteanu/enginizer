package com.startup.enginizer.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by dragos.triteanu on 10/31/15.
 */

@Entity
@Table(name = "home")
public class WysiwygHtml {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "html")
    private String html;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }
}
