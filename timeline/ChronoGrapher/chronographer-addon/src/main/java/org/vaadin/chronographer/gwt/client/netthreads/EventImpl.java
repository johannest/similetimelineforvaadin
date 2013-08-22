/**
 * Created by: Billy Wagner
 * Sep 17, 2008
 */
package org.vaadin.chronographer.gwt.client.netthreads;

/**
 * @author Billy Wagner
 *
 * Implementation of the Event javascript object functions
 * 
 * Note there are still more functions that can be used here- accessors and such
 */
public class EventImpl {

	public native static Event createEvent(Integer id, String start, String end, String latestStart,String earliestEnd,boolean isInstance,
						String text,String description, String image, String link,String icon, String color, String highlightColor, String textColor)/*-{
						
						var event=new $wnd.Timeline.DefaultEventSource.Event(id, start, end, latestStart, earliestEnd, isInstance, text,
																	description, image, link, icon, color, highlightColor, textColor);
						return event;
						}-*/;
																
				
}
