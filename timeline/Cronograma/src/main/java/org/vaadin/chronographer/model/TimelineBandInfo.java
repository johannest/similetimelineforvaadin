package org.vaadin.chronographer.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.vaadin.chronographer.theme.TimelineTheme;

public class TimelineBandInfo {

    private Number trackGap;
    private Number trackHeight;
    private Integer intervalPixels;
    private Integer timeZone;
    private Integer syncWith;
    private String width;
    private Boolean showEventText;
    private Boolean overview;
    private Boolean highligh;
    private Date date;

    private TimeUnit intervalUnit;
    private TimelineTheme theme;

    private List<TimelineZone> timelineZones = new ArrayList<TimelineZone>();
    private List<HighlighDecorator> decorators = new ArrayList<HighlighDecorator>();

    public TimelineBandInfo() {

    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public TimeUnit getIntervalUnit() {
        return intervalUnit;
    }

    public void setIntervalUnit(TimeUnit intervalUnit) {
        this.intervalUnit = intervalUnit;
    }

    public Integer getIntervalPixels() {
        return intervalPixels;
    }

    public void setIntervalPixels(Integer intervalPixels) {
        this.intervalPixels = intervalPixels;
    }

    public Integer getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(Integer timeZone) {
        this.timeZone = timeZone;
    }

    public Integer getSyncWith() {
        return syncWith;
    }

    public void setSyncWith(Integer syncWith) {
        this.syncWith = syncWith;
    }

    public Boolean getOverview() {
        return overview;
    }

    public void setOverview(Boolean overview) {
        this.overview = overview;
    }

    public Boolean getHighligh() {
        return highligh;
    }

    public void setHighligh(Boolean highligh) {
        this.highligh = highligh;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<TimelineZone> getTimelineZones() {
        return timelineZones;
    }

    public void setTimelineZones(List<TimelineZone> timelineZones) {
        this.timelineZones = timelineZones;
    }

    public void addTimelineZone(TimelineZone zone) {
        timelineZones.add(zone);
    }

    public void clearTimelineZones() {
        timelineZones.clear();
    }

    public void setTheme(TimelineTheme theme) {
        this.theme = theme;
    }

    public TimelineTheme getTheme() {
        return theme;
    }

    public void setShowEventText(Boolean showEventText) {
        this.showEventText = showEventText;
    }

    public Boolean getShowEventText() {
        return showEventText;
    }

    public void setTrackGap(Number trackGap) {
        this.trackGap = trackGap;
    }

    public Number getTrackGap() {
        return trackGap;
    }

    public void setTrackHeight(Number trackHeight) {
        this.trackHeight = trackHeight;
    }

    public Number getTrackHeight() {
        return trackHeight;
    }

    public List<HighlighDecorator> getHighlightDecorators() {
        return decorators;
    }

    public void setHighlightDecorators(List<HighlighDecorator> decorators) {
        this.decorators = decorators;
    }

    public void addHighlightDecorator(HighlighDecorator decorator) {
        decorators.add(decorator);
    }

    public void clearHighlightDecorators() {
        decorators.clear();
    }

}
