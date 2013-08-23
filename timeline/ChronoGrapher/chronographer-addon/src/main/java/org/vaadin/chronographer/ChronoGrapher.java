/* 
 * Copyright 2009 IT Mill Ltd.
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

package org.vaadin.chronographer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.vaadin.chronographer.gwt.client.connect.ChronoGrapherServerRpc;
import org.vaadin.chronographer.gwt.client.model.Events;
import org.vaadin.chronographer.gwt.client.model.TimelineBandInfo;
import org.vaadin.chronographer.gwt.client.model.TimelineEvent;
import org.vaadin.chronographer.gwt.client.model.theme.TimelineTheme;
import org.vaadin.chronographer.gwt.client.shared.ChronoGrapherState;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.JavaScriptFunction;
import com.vaadin.ui.Notification;

@SuppressWarnings("serial")
@JavaScript(value = { "gwt/public/js/api/timeline-api.js" })
public class ChronoGrapher extends AbstractComponent {
	private transient DateFormat df = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss Z", Locale.US);

	private final List<TimelineBandInfo> bandInfos;
	private final Events timelineEvents;
	private final List<TimelineTheme> timelineThemes;

	private String width = "100%";
	private String height = "100%";

	private boolean stateDirty;

	private EventClickHandler eventClickHandler;

	public ChronoGrapher() {
		this(null, false);
	}
	
	public ChronoGrapher(Date startTime, Date endTime) {
		this(null, false, startTime, endTime);
	}

	public ChronoGrapher(String uniqueComponentId, boolean serverCallOnEventClickEnabled) {
		this(uniqueComponentId, serverCallOnEventClickEnabled, null, null);
	}

	public ChronoGrapher(String uniqueComponentId, boolean serverCallOnEventClickEnabled, Date startTime, Date endTime) {
		super();
		bandInfos = new ArrayList<TimelineBandInfo>();
		timelineThemes = new ArrayList<TimelineTheme>();
		timelineEvents = new Events();

		registerRpc(new ChronoGrapherServerRpc() {
			@Override
			public void onClick(int id, int x, int y) {
				Notification.show("Event " + id + " clicked @ (" + x + "," + y + ")");
			}
		});

		if (serverCallOnEventClickEnabled) {
			if (uniqueComponentId == null || uniqueComponentId.isEmpty()) {
				throw new RuntimeException("uniqueComponentId is mandatory parameter when serverCallOnEventClickEnabled is enabled");
			}

			getState().serverCallOnEventClickEnabled = serverCallOnEventClickEnabled;
			String uniqueJSFuncName = uniqueComponentId + ".onEventClick";
			com.vaadin.ui.JavaScript.getCurrent().addFunction(uniqueJSFuncName, new JavaScriptFunction() {
				@Override
				public void call(JSONArray arguments) throws JSONException {
					getState().selectedEventId = null;
					if (eventClickHandler != null) {
						eventClickHandler.handleClick(arguments.getString(0), arguments.getString(1));
					}
				}
			});
		}
		if (uniqueComponentId != null && !uniqueComponentId.isEmpty()) {
			setId(uniqueComponentId);
		}
		if (startTime != null) {
			getState().timelineStart = startTime;
		}
		if (endTime != null) {
			getState().timelineEnd = endTime;
		}
	}

	public void setStartTime(Date startTime) {
		if (startTime != null) {
			getState().timelineStart = startTime;
		} else {
			getState().timelineStart = null;
		}
		drawChronoGrapher();
	}
	
	public void setEndTime(Date endTime) {
		if (endTime != null) {
			getState().timelineEnd = endTime;
		} else {
			getState().timelineEnd = null;
		}
		drawChronoGrapher();
	}
	
	public void setSelectedEvent(String eventId) {
		getState().selectedEventId = eventId;
		drawChronoGrapher();
	}

	public void addBandInfo(TimelineBandInfo bandInfo) {
		bandInfos.add(bandInfo);

		getState().width = width;
		getState().height = height;
		getState().bandInfos = bandInfos;
		System.out.println("..structure");
	}

	public void addTheme(TimelineTheme theme) {
		timelineThemes.add(theme);
		getState().timelineThemes = timelineThemes;
		System.out.println("...theme");
		drawChronoGrapher();
	}

	public void addEvent(TimelineEvent event, boolean redraw) {
		timelineEvents.add(event);
		getState().eventsJson = paintEventsOnJSON();
		System.out.println("...events");
		if (redraw) {
			drawChronoGrapher();
		}
	}

	public void addEvents(TimelineEvent... events) {
		for (TimelineEvent e : events) {
			timelineEvents.add(e);
		}
		getState().eventsJson = paintEventsOnJSON();
		System.out.println("...events");
		drawChronoGrapher();
	}

	public void addEvents(List<TimelineEvent> events) {
		for (TimelineEvent e : events) {
			timelineEvents.add(e);
		}
		getState().eventsJson = paintEventsOnJSON();
		System.out.println("...events");
		drawChronoGrapher();
	}

	public void clearBandInfos() {
		bandInfos.clear();
		getState().bandInfos = null;
		drawChronoGrapher();
	}

	public void clearEvents() {
		timelineEvents.clear();
		getState().eventsJson = null;
		drawChronoGrapher();
	}

	public void drawChronoGrapher() {
		stateDirty = true;
		beforeClientResponse(false);
	}

	@Override
	public ChronoGrapherState getState() {
		return (ChronoGrapherState) super.getState();
	}

	private String paintBandInfosAndZones() {
		GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
		Gson gson = builder.create();
		return gson.toJson(bandInfos);
	}

	private String paintEventsOnJSON() {
		GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
		Gson gson = builder.create();
		return gson.toJson(timelineEvents);
	}

	private String paintThemesOnJSON() {
		GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
		Gson gson = builder.create();
		return gson.toJson(timelineThemes);
	}

	public void setDateFormatter(DateFormat df) {
		this.df = df;
	}

	public DateFormat getDateFormatter() {
		return df;
	}

	@Override
	public void setWidth(String width) {
		this.width = width;
		super.setWidth(width);
	}

	@Override
	public void setHeight(String height) {
		this.height = height;
		super.setHeight(height);
	}

	/**
	 * Sets event click handler which executes on event click
	 * 
	 * @param eventClickHandler
	 */
	public void setEventClickHandler(EventClickHandler eventClickHandler) {
		this.eventClickHandler = eventClickHandler;
	}

	/**
	 * This interface describes which parameters will be received from the event click request
	 * 
	 * @author ivan.obradovic@codecentric.de
	 */
	public interface EventClickHandler {
		public void handleClick(String eventId, String eventTitle);
	}
}
