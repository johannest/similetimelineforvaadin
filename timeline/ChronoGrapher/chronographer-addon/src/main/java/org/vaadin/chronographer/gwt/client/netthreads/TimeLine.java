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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.UIObject;

import java.util.Date;
import java.util.List;

import org.vaadin.chronographer.gwt.client.TimeLineClickHandler;


/**
 * TimeLine
 *
 * @author ajr
 */
public class TimeLine extends JavaScriptObject
{
    protected TimeLine()
    {
        super();
    }

    /**
     * Create TimeLine object
     */
    public static TimeLine create(List bands, EventSource source, Element divElement, Element clientElement, boolean horizontalOrientation, boolean serverCallOnEventClickEnabled, Date startTime, Date endTime)
    {
    	JavaScriptObject[] bandArr = JavaScriptObjectHelper.listToArray(bands);

        JavaScriptObject jarr = JavaScriptObjectHelper.arrayConvert(bandArr);

        boolean currVisible = UIObject.isVisible(clientElement);
        UIObject.setVisible(clientElement, true);
		double startTimeL = startTime == null ? 0 : startTime.getTime();
		double endTimeL = endTime == null ? 0 : endTime.getTime();
		TimeLine timeLine = TimeLineImpl.create(jarr, divElement, horizontalOrientation ? 0 : 1, serverCallOnEventClickEnabled, startTimeL, endTimeL);

        UIObject.setVisible(clientElement, currVisible);
        
        return timeLine;
    	
    }
    


    /**
     * Redraw timeline 
     *
     */
    public final void layout()
    {
        TimeLineImpl.layout(this);
    }

    /**
     * loadXML through handler function.
     * 
     * @param dataUrl
     * @param handler
     */
    public final void loadXML(String dataUrl, TimelineXMLHandler handler)
    {
        TimeLineImpl.loadXML(dataUrl, handler);
    }
 
    /**
     * Close info bubble for indicated band
     * @param index
     */
    public final void closeBubble(int index)
    {
    	TimeLineImpl.closeBubble(index, this);
    }
    
    /**
     * Add click handler for handling clicks on the events
     * @param _listener
     */
    public final void addClickHandler(TimeLineClickHandler _listener) {
    	TimeLineImpl.setClickListener(_listener);
    }
    
    /**
     *  Accessor function for date setting..
     *  Will flesh out later
     *  
     *  currently use BandOptions.setDate for functionality...
     *  Hooks for that are in ITimeLineRender interface
     *  
     */
    public final void centerOnEvent(){
    	
    }
    
    /**
     * Sets start of timeline range
     * 
     * @param startTime
     */
    public final void setStartTime(Date startTime){
    	TimeLineImpl.setStartTime(this, startTime.getTime());
    }
    
    /**
     * Sets end of timeline range
     * 
     * @param endTime
     */
    public final void setEndTime(Date endTime){
    	TimeLineImpl.setEndTime(this, endTime.getTime());
    }
}
