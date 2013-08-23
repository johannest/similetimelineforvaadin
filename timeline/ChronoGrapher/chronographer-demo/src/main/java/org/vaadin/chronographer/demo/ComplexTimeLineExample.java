package org.vaadin.chronographer.demo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;

import org.vaadin.chronographer.ChronoGrapher;
import org.vaadin.chronographer.gwt.client.model.HighlighDecorator;
import org.vaadin.chronographer.gwt.client.model.TimeUnit;
import org.vaadin.chronographer.gwt.client.model.TimelineBandInfo;
import org.vaadin.chronographer.gwt.client.model.TimelineEvent;
import org.vaadin.chronographer.gwt.client.model.TimelineZone;
import org.vaadin.chronographer.gwt.client.model.theme.TimelineTheme;
import org.vaadin.chronographer.gwt.client.model.theme.event.Duration;
import org.vaadin.chronographer.gwt.client.model.theme.event.EventsTheme;
import org.vaadin.chronographer.gwt.client.model.theme.event.Instant;
import org.vaadin.chronographer.gwt.client.model.theme.event.Label;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

public class ComplexTimeLineExample extends VerticalLayout {
	private static final long serialVersionUID = 1L;

	private ChronoGrapher timeline;

    private final DateFormat df2 = new SimpleDateFormat(
            "EEE MMM dd yyyy HH:mm:ss", Locale.US);
    private final DateFormat df3 = new SimpleDateFormat("MMM dd yyyy HH:mm:ss",
            Locale.US);

    private TimelineTheme upperBandsTheme;
    private TimelineTheme lowerBandsTheme;

    public ComplexTimeLineExample() {
        setId("timeline-ex-2-verticallayout");
    }

    @SuppressWarnings("serial")
    @Override
    public void attach() {
        super.attach();
        if (timeline == null) {
            timeline = new ChronoGrapher();
            // we need to set true in ChronoGrapher constructor to enable this feature
			// timeline.setEventClickHandler(new
			// ChronoGrapher.EventClickHandler() {
			// @Override
			// public void handleClick(String eventId, String eventTitle) {
			// Notification.show(String.format("Event with id #%s and title '%s' is clicked !",
			// eventId, eventTitle));
			// }
			// });

            df2.setTimeZone(TimeZone.getTimeZone("GMT-0600"));
            df3.setTimeZone(TimeZone.getTimeZone("GMT-0600"));

            try {
                addBandInfosAndZonesIntoTimeline();
                upperBandsTheme = createUpperBandsTheme();
                lowerBandsTheme = createLowerBandsTheme();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            com.vaadin.ui.Label infoLabel = new com.vaadin.ui.Label(
                    "This example demonstrates most of implemented functionality. Clicking the \"Load Events\" button loads events from the xml file. Event icon clicks are passed on the server side.");
            infoLabel.setId("timeline-ex-2-infolabel");
            addComponent(infoLabel);

            HorizontalLayout buttons = new HorizontalLayout();
            addComponent(buttons);
            
            Button loadButton = new Button("Load Events",
                    new Button.ClickListener() {
                        @Override
                        public void buttonClick(ClickEvent event) {
                            try {
                                addEventsIntoTimeline();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
            loadButton.setId("timeline-ex-2-loadButton");
            buttons.addComponent(loadButton);
            
            Button switchThemeButton = new Button("Switch theme",
                    new Button.ClickListener() {
                        @Override
                        public void buttonClick(ClickEvent event) {
                        	if(timeline.getStyleName().contains("dark-theme")){
                        		timeline.setStyleName("");
                        	}else{
                        		timeline.setStyleName("dark-theme");
                        	}
                        }
                    });
            buttons.addComponent(switchThemeButton);

            addComponent(timeline);
            timeline.setId("timeline-widget");
            timeline.setImmediate(true);
            timeline.setWidth("100%");
            timeline.setHeight("500px");
            timeline.addTheme(upperBandsTheme);
            timeline.addTheme(lowerBandsTheme);
        }
    }

    private void addBandInfosAndZonesIntoTimeline() throws ParseException {
        TimelineBandInfo bandInfo1 = new TimelineBandInfo();
        TimelineBandInfo bandInfo2 = new TimelineBandInfo();

        HighlighDecorator deco1 = HighlighDecorator.createSpanDecorator(
                df2.parse("Fri Nov 22 1963 12:30:00"),
                df2.parse("Fri Nov 22 1963 13:00:00"), "shot", "t.o.d.",
                "#FFC080", 50, null);
        HighlighDecorator deco2 = HighlighDecorator.createPointDecorator(
                df2.parse("Fri Nov 22 1963 14:38:00"), "#FFC080", 50, null);
        HighlighDecorator deco3 = HighlighDecorator.createPointDecorator(
                df2.parse("Sun Nov 24 1963 13:00:00 "), "#FFC080", 50, null);

        TimelineZone zone11 = new TimelineZone();
        TimelineZone zone12 = new TimelineZone();
        TimelineZone zone13 = new TimelineZone();
        TimelineZone zone14 = new TimelineZone();
        TimelineZone zone2 = new TimelineZone();

        zone11.setStart(df2.parse("Fri Nov 22 1963 00:00:00"));
        zone11.setEnd(df2.parse("Mon Nov 25 1963 00:00:00"));
        zone11.setMagnify(10);
        zone11.setUnit(TimeUnit.DAY);

        zone12.setStart(df2.parse("Fri Nov 22 1963 09:00:00"));
        zone12.setEnd(df2.parse("Sun Nov 24 1963 00:00:00"));
        zone12.setMagnify(5);
        zone12.setUnit(TimeUnit.HOUR);

        zone13.setStart(df2.parse("Fri Nov 22 1963 11:00:00"));
        zone13.setEnd(df2.parse("Sat Nov 23 1963 00:00:00"));
        zone13.setMagnify(5);
        zone13.setUnit(TimeUnit.MINUTE);
        zone13.setMultiple(10);

        zone14.setStart(df2.parse("Fri Nov 22 1963 12:00:00"));
        zone14.setEnd(df2.parse("Fri Nov 22 1963 14:00:00"));
        zone14.setMagnify(3);
        zone14.setUnit(TimeUnit.MINUTE);
        zone14.setMultiple(5);

        zone2.setStart(df2.parse("Fri Nov 22 1963 12:00:00"));
        zone2.setEnd(df2.parse("Fri Nov 22 1963 14:00:00"));
        zone2.setMagnify(3);
        zone2.setUnit(TimeUnit.MINUTE);
        zone2.setMultiple(15);

        bandInfo1.addTimelineZone(zone11);
        bandInfo1.addTimelineZone(zone12);
        bandInfo1.addTimelineZone(zone13);
        bandInfo1.addTimelineZone(zone14);
        bandInfo1.addHighlightDecorator(deco1);
        bandInfo1.addHighlightDecorator(deco2);
        bandInfo1.addHighlightDecorator(deco3);

        bandInfo2.addTimelineZone(zone2);
        bandInfo2.addHighlightDecorator(deco1);
        bandInfo2.addHighlightDecorator(deco2);
        bandInfo2.addHighlightDecorator(deco3);

        bandInfo1.setDate(df2.parse("Fri Nov 22 1963 13:00:00"));
        bandInfo1.setTimeZone(-6);
        bandInfo1.setWidth("80%");
        bandInfo1.setIntervalUnit(TimeUnit.WEEK);
        bandInfo1.setIntervalPixels(220);
        bandInfo1.setTheme(upperBandsTheme);

        bandInfo2.setDate(df2.parse("Fri Nov 22 1963 13:00:00"));
        bandInfo2.setTimeZone(-6);
        bandInfo2.setWidth("20%");
        bandInfo2.setIntervalUnit(TimeUnit.MONTH);
        bandInfo2.setIntervalPixels(200);
        bandInfo2.setOverview(true);
        bandInfo2.setTheme(lowerBandsTheme);
        bandInfo2.setSyncWith(0);
        bandInfo2.setHighligh(true);

        timeline.addBandInfo(bandInfo1);
        timeline.addBandInfo(bandInfo2);
    }

    private TimelineTheme createUpperBandsTheme() {
        EventsTheme event = new EventsTheme();
        Label label = new Label();
        label.setInsideColor("green");
        label.setOutsideColor("#554400");

        Instant instant = new Instant();
        instant.setIcon("js/api/images/dull-green-circle.png");
        instant.setLineColor("black");
        instant.setImpreciseColor("gray");
        instant.setImpreciseOpacity(20);
        instant.setShowLineForNoText(false);

        Duration duration = new Duration();
        duration.setColor("red");
        duration.setOpacity(30);
        duration.setImpreciseColor("gray");
        duration.setImpreciseOpacity(20);

        event.setLabel(label);
        event.setInstant(instant);
        event.setDuration(duration);

        TimelineTheme theme = new TimelineTheme();
        theme.setEvent(event);
        theme.setFirstDayOfWeek(1);
        theme.setThemesIndex(0);

        return theme;
    }

    private TimelineTheme createLowerBandsTheme() {
        EventsTheme event = new EventsTheme();

        Label label = new Label();
        label.setInsideColor("green");
        label.setOutsideColor("#554400");
        label.setWidth(5);

        Instant instant = new Instant();
        instant.setIcon("js/api/images/blue-mark.png");
        instant.setLineColor("black");
        instant.setImpreciseColor("red");
        instant.setImpreciseOpacity(20);
        instant.setShowLineForNoText(false);

        Duration duration = new Duration();
        duration.setImpreciseColor("red");
        duration.setImpreciseOpacity(40);

        event.setLabel(label);
        event.setInstant(instant);
        event.setDuration(duration);

        TimelineTheme theme = new TimelineTheme();
        theme.setEvent(event);
        theme.setFirstDayOfWeek(1);
        theme.setThemesIndex(1);

        return theme;
    }

    private void addEventsIntoTimeline() throws Exception {
        List<TimelineEvent> events = new ArrayList<TimelineEvent>();
        try {
        	  Builder parser = new Builder();
        	  Document document = parser.build("jfk.xml");
        	  Element root = document.getRootElement();
              int index = 0;

              // iterate through child elements of root
              for (int i=0; i<root.getChildElements().size(); i++) {
            	  Element element = root.getChildElements().get(i);
            	  
                  String start = element.getAttributeValue("start");
                  String end = element.getAttributeValue("end");
                  String title = element.getAttributeValue("title");
                  String content = element.getValue();
                  start = start.substring(0, start.length() - 9);

                  String icon = element.getAttributeValue("icon");
                  String image = element.getAttributeValue("image");
                  String link = element.getAttributeValue("link");
                  String tapeImage = element.getAttributeValue("tapeImage");
                  String tapeRepeat = element.getAttributeValue("tapeRepeat");
                  String caption = element.getAttributeValue("caption");
                  String classname = element.getAttributeValue("classname");
                  String color = element.getAttributeValue("color");
                  String textColor = element.getAttributeValue("textColor");

                  TimelineEvent event = new TimelineEvent();
                  try {
                      event.setStart(df2.parse(start));
                      if (end != null) {
                          end = end.substring(0, end.length() - 9);
                          event.setEnd(df2.parse(end));
                          event.setIsDuration(true);
                      }
                  } catch (ParseException e) {
                      try {
                          event.setStart(df3.parse(start));
                          if (end != null) {
                              end = end.substring(0, end.length() - 9);
                              event.setEnd(df3.parse(end));
                              event.setIsDuration(true);
                          }
                      } catch (ParseException e1) {
                          System.out.println("unparseable date - skipped");
                          continue;
                      }
                  }
                  event.setTitle(title);
                  event.setBody(content);
                  event.setId(index);
                  event.setIcon(icon);
                  event.setImage(image);
                  event.setLink(link);
                  event.setTapeImage(tapeImage);
                  event.setTapeRepeat(tapeRepeat);
                  event.setCaption(caption);
                  event.setClassname(classname);
                  event.setColor(color);
                  event.setTextColor(textColor);

                  events.add(event);

                  ++index;
              }
              timeline.addEvents(events);
        	}
        	catch (Exception ex) {
        	  System.out.println("Error parsing xml file jfk.xml");
        	}
        
       
    }
}
