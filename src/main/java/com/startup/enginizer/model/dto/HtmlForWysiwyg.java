package com.startup.enginizer.model.dto;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * Created by Dragos on 11/18/2015.
 */
public class HtmlForWysiwyg {

    @NotNull(message = "htmlForWywiwyg.newHtml.notNull")
    private String newHtml;

    @Range(min = 1 , max=2 ,message = "htmlForWywiwyg.whatUsersSee.range")
    private int whatUsersSee;

    public String getNewHtml() {
        return newHtml;
    }

    public void setNewHtml(String newHtml) {
        this.newHtml = newHtml;
    }

    public int getWhatUsersSee() {
        return whatUsersSee;
    }

    public void setWhatUsersSee(int whatUsersSee) {
        this.whatUsersSee = whatUsersSee;
    }
}
