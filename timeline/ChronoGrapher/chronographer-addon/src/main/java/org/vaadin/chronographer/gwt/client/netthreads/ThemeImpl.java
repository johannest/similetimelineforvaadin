/*
 * Copyright 2006 Alistair Rutherford (http://code.google.com/p/gwtsimiletimeline/)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.vaadin.chronographer.gwt.client.netthreads;

/**
 * ThemeImpl
 * 
 * @author ajr
 * 
 */
class ThemeImpl {
    // -------------------------------------------------------------------
    // Theme
    // -------------------------------------------------------------------

    public native static Theme create()/*-{
                                       var theme = $wnd.Timeline.ClassicTheme.create();
                                       theme.event.label.width = 400; // default px 
                                       return theme;
                                       }-*/;

    public native static void setEventTrackOffset(Theme theme, float offset) /*-{
                                                                             theme.event.track.offset = offset; // em 
                                                                             }-*/;

    public native static void setEventTrackHeight(Theme theme, float height) /*-{
                                                                             theme.event.track.height = height; // em 
                                                                             }-*/;

    public native static void setEventTrackGap(Theme theme, float gap) /*-{
                                                                       theme.event.track.gap = gap; // em
                                                                       }-*/;

    public native static void setEventInstantIcon(Theme theme, String icon) /*-{
                                                                            theme.event.instant.icon = icon
                                                                            }-*/;

    public native static void setEventInstantLineColor(Theme theme,
            String lineColor) /*-{
                              theme.event.instant.lineColor = lineColor;
                              }-*/;

    public native static void setEventInstantImpreciseColor(Theme theme,
            String impreciseColor) /*-{
                                   theme.event.instant.impreciseColor = impreciseColor; 
                                   }-*/;

    public native static void setEventInstantImpreciseOpacity(Theme theme,
            int impreciseOpacity) /*-{
                                  theme.event.instant.impreciseOpacity = impreciseOpacity;
                                  }-*/;

    public native static void setEventInstantShowLineForNoText(Theme theme,
            boolean showLineForNoText) /*-{
                                       theme.event.instant.showLineForNoText = showLineForNoText; 
                                       }-*/;

    public native static void setEventDurationColor(Theme theme, String color) /*-{
                                                                               theme.event.duration.color = color;
                                                                               }-*/;

    public native static void setEventDurationOpacity(Theme theme, int opacity) /*-{
                                                                                theme.event.duration.opacity = opacity;
                                                                                }-*/;

    public native static void setEventDurationImpreciseColor(Theme theme,
            String impreciseColor) /*-{
                                   theme.event.duration.impreciseColor = impreciseColor;
                                   }-*/;

    public native static void setEventDurationImpreciseOpacity(Theme theme,
            int impreciseOpacity) /*-{
                                  theme.event.duration.impreciseOpacity = impreciseOpacity;
                                  }-*/;

    public native static void setEventLabelInsideColor(Theme theme,
            String insideColor) /*-{
                                theme.event.label.insideColor = insideColor;
                                }-*/;

    public native static void setEventLabelOutsideColor(Theme theme,
            String outsideColor) /*-{
                                 theme.event.label.outsideColor = outsideColor;
                                 }-*/;

    public native static void setEventLabelWidth(Theme theme, int width) /*-{
                                                                         theme.event.label.width = width;
                                                                         }-*/;

    public native static void setEventHighlightColors(Theme theme,
            String[] highlightColors) /*-{
                                      theme.event.label.highlightColors = highlightColors;
                                      }-*/;

    public native static void setFirstDayOfWeek(Theme theme, int firstDayOfWeek) /*-{
                                                                                 theme.firstDayOfWeek = firstDayOfWeek;
                                                                                 }-*/;

    public native static void setTimelineStart(Theme theme, int year, int month, int day, int hour, int minute, int second, int millisecond) 
    /*-{
            debugger;
    		theme.timeline_start = new Date(Date.UTC(year, month, day, hour, minute, second, millisecond));
       }-*/;
    
    public native static void setTimelineStop(Theme theme, int year, int month, int day, int hour, int minute, int second, int millisecond) 
    /*-{
    		debugger;
    		theme.timeline_stop = new Date(Date.UTC(year, month, day, hour, minute, second, millisecond));
       }-*/;
}

