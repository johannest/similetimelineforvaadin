package org.vaadin.chronographer.gwt.client.model;

import java.util.Date;

public class TimelineEvent {

    /** Basic attributes */
    private Integer id;
    private Date start;
    private Date end;
    private String title;
    private String description;
    private Boolean isDuration = false;

    /** Additional attributes */
    private String icon;
    private String image;
    private String link;
    private String tapeImage;
    private String tapeRepeat;
    private String caption;
    private String classname;
    private String color;
    private String highlightColor;
    private String textColor;
    private String detailsStyleName;

    public TimelineEvent() {

    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return description;
    }

    public void setBody(String body) {
        description = body;
    }

    public Boolean isDuration() {
        return isDuration;
    }

    public void setIsDuration(Boolean isDuration) {
        this.isDuration = isDuration;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getTapeImage() {
        return tapeImage;
    }

    public void setTapeImage(String tapeImage) {
        this.tapeImage = tapeImage;
    }

    public String getTapeRepeat() {
        return tapeRepeat;
    }

    public void setTapeRepeat(String tapeRepeat) {
        this.tapeRepeat = tapeRepeat;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
    public String getHighlightColor() {
		return highlightColor;
	}

	public void setHighlightColor(String highlightColor) {
		this.highlightColor = highlightColor;
	}

	public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }
    
    public String getDetailsStyleName() {
		return detailsStyleName;
	}

	public void setDetailsStyleName(String detailsStyleName) {
		this.detailsStyleName = detailsStyleName;
	}

	public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }
}
