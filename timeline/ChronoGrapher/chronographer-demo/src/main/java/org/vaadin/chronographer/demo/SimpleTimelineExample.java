package org.vaadin.chronographer.demo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.vaadin.chronographer.ChronoGrapher;
import org.vaadin.chronographer.gwt.client.model.TimeUnit;
import org.vaadin.chronographer.gwt.client.model.TimelineBandInfo;
import org.vaadin.chronographer.gwt.client.model.TimelineEvent;
import org.vaadin.chronographer.gwt.client.model.TimelineZone;

import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class SimpleTimelineExample extends VerticalLayout {

	private static final long serialVersionUID = 9211917184063343587L;
	private final DateFormat df = new SimpleDateFormat("yyyy");
	private ChronoGrapher timeline;
	private ChronoGrapher timelinewithStartAndEnd;

	public SimpleTimelineExample() {
		setId("timeline-ex1-verticallayout");
	}

	@SuppressWarnings("serial")
	@Override
	public void attach() {
		super.attach();

		if (timeline == null) {
			try {
				com.vaadin.ui.Label infoLabel = new com.vaadin.ui.Label("This simple example demonstrates basic usage of the ChronoGrapher widget");
				infoLabel.setId("timeline-ex1-infolabel");
				addComponent(infoLabel);
				addComponent(getTimelineComponent());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		if (timelinewithStartAndEnd == null) {
			try {
				com.vaadin.ui.Label infoLabel = new com.vaadin.ui.Label("Timeline with start year 1900 and end year 2100.");
				infoLabel.setId("timeline-ex2-infolabel");
				addComponent(infoLabel);
				addComponent(getTimelineComponentWithStartAndEnd());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		try {
			createEvents();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		HorizontalLayout buttons = new HorizontalLayout();
		addComponent(buttons);

		buttons.addComponent(new Button("Change range to 1950-2050", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				Calendar timelineStart = Calendar.getInstance();
				timelineStart.set(Calendar.YEAR, 1950);
				Calendar timelineStop = Calendar.getInstance();
				timelineStop.set(Calendar.YEAR, 2050);
				timelinewithStartAndEnd.setStartTime(timelineStart.getTime());
				timelinewithStartAndEnd.setEndTime(timelineStop.getTime());
			}
		}));
		buttons.addComponent(new Button("Select event with id 10", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				timelinewithStartAndEnd.setSelectedEvent("10");
			}
		}));
	}

	private Component getTimelineComponent() throws ParseException {
		timeline = new ChronoGrapher("timeline-ex1-timelinewidget", true);
		timeline.setEventClickHandler(new ChronoGrapher.EventClickHandler() {
			@Override
			public void handleClick(String eventId, String eventTitle) {
				Notification.show(String.format("Event with id #%s and title '%s' on upper timeline is clicked !", eventId, eventTitle));
			}
		});
		timeline.setImmediate(true);
		timeline.setWidth("100%");
		timeline.setHeight("100px");

		TimelineZone zone = new TimelineZone();
		zone.setStart(df.parse("1917"));
		zone.setEnd(df.parse("1920"));
		zone.setUnit(TimeUnit.MONTH);
		zone.setMultiple(1);
		zone.setMagnify(10);

		TimelineBandInfo bandInfo = new TimelineBandInfo();
		bandInfo.setDate(df.parse("2013"));
		bandInfo.setTimeZone(+2);
		bandInfo.setWidth("100%");
		bandInfo.setIntervalUnit(TimeUnit.YEAR);
		bandInfo.setIntervalPixels(35);
		bandInfo.addTimelineZone(zone);

		timeline.addBandInfo(bandInfo);
		return timeline;
	}

	private Component getTimelineComponentWithStartAndEnd() throws ParseException {
		Calendar timelineStart = Calendar.getInstance();
		timelineStart.set(Calendar.YEAR, 1900);
		Calendar timelineStop = Calendar.getInstance();
		timelineStop.set(Calendar.YEAR, 2100);
		timelinewithStartAndEnd = new ChronoGrapher("timeline-ex2-timelinewidget", true, timelineStart.getTime(), timelineStop.getTime());
		timelinewithStartAndEnd.setEventClickHandler(new ChronoGrapher.EventClickHandler() {
			@Override
			public void handleClick(String eventId, String eventTitle) {
				Notification.show(String.format("Event with id #%s and title '%s' on lower timeline is clicked !", eventId, eventTitle));
			}
		});
		timelinewithStartAndEnd.setImmediate(true);
		timelinewithStartAndEnd.setWidth("100%");
		timelinewithStartAndEnd.setHeight("100px");

		TimelineZone zone = new TimelineZone();
		zone.setStart(df.parse("1917"));
		zone.setEnd(df.parse("1920"));
		zone.setUnit(TimeUnit.MONTH);
		zone.setMultiple(1);
		zone.setMagnify(10);

		TimelineBandInfo bandInfo = new TimelineBandInfo();
		bandInfo.setDate(df.parse("2013"));
		bandInfo.setTimeZone(+2);
		bandInfo.setWidth("100%");
		bandInfo.setIntervalUnit(TimeUnit.YEAR);
		bandInfo.setIntervalPixels(35);
		bandInfo.addTimelineZone(zone);

		timelinewithStartAndEnd.addBandInfo(bandInfo);
		return timelinewithStartAndEnd;
	}

	private void createEvents() throws ParseException {
		String[] names = new String[] { "K. J. Sthålber", "L. K. Relander", "P. E. Svinhuvud", "Kyösti Kallio", "Risto Ryti", "C. G. Mannerheim",
				"J. K. Paasikivi", "Urho Kekkonen", "Mauno Koivisto", "Martti Ahtisaari", "Tarja Halonen", "Sauli Niinistö" };
		String[] colors = new String[] { "#3399ff", "#33cc33", "#3399ff", "#33cc33", "#3399ff", "#ffffff", "#3399ff", "#3399ff", "#33cc33", "#ff6666",
				"#ff6666", "#3399ff" };
		String[][] dates = new String[][] { { "1919", "1925" }, { "1925", "1931" }, { "1931", "1937" }, { "1937", "1940" }, { "1940", "1944" },
				{ "1944", "1946" }, { "1946", "1956" }, { "1956", "1982" }, { "1982", "1994" }, { "1994", "2000" }, { "2000", "2012" }, { "2012", "2016" } };
		String[] detailStyleNames = new String[] { "detailsStyle1", "detailsStyle2", null, "detailsStyle1", "detailsStyle2", null, "detailsStyle1", 
				"detailsStyle2", null, "detailsStyle1", "detailsStyle2", null };

		List<TimelineEvent> events = new ArrayList<TimelineEvent>();
		for (int i = 0; i < names.length; i++) {
			TimelineEvent event = new TimelineEvent();
			event.setIsDuration(true);
			event.setStart(df.parse(dates[i][0]));
			event.setEnd(df.parse(dates[i][1]));
			event.setTitle(names[i]);
			event.setId(i);
			if (i % 2 == 0) {
				event.setIcon("js/api/images/dark-red-circle.png");
			}
			event.setColor(colors[i]);
			event.setDetailsStyleName(detailStyleNames[i]);
			events.add(event);
		}
		if (timeline != null) {
			timeline.addEvents(events);
		}
		if (timelinewithStartAndEnd != null) {
			timelinewithStartAndEnd.addEvents(events);
		}
	}
}
