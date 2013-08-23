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

import java.util.Date;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.i18n.client.DateTimeFormat;

/**
 * this,currently only creates the classic theme.
 * 
 * @author ajr
 */
public class Theme extends JavaScriptObject {

	protected Theme() {
		super();
	}

	public static Theme create() {
		return ThemeImpl.create();
	}

	public final void setEventLabelWidth(int width) {
		ThemeImpl.setEventLabelWidth(this, width);
	}

	public final void setEventTrackOffset(float offset) {
		ThemeImpl.setEventTrackOffset(this, offset);
	}

	public final void setEventTrackHeight(float height) {
		ThemeImpl.setEventTrackHeight(this, height);
	}

	public final void setEventTrackGap(float gap) {
		ThemeImpl.setEventTrackGap(this, gap);
	}

	public final void setEventInstantIcon(String icon) {
		ThemeImpl.setEventInstantIcon(this, icon);
	}

	public final void setEventInstantLineColor(String lineColor) {
		ThemeImpl.setEventInstantLineColor(this, lineColor);
	}

	public final void setEventInstantImpreciseColor(String impreciseColor) {
		ThemeImpl.setEventInstantImpreciseColor(this, impreciseColor);
	}

	public final void setEventInstantImpreciseOpacity(int impreciseOpacity) {
		ThemeImpl.setEventInstantImpreciseOpacity(this, impreciseOpacity);
	}

	public final void setEventInstantShowLineForNoText(boolean showLineForNoText) {
		ThemeImpl.setEventInstantShowLineForNoText(this, showLineForNoText);
	}

	public final void setEventDurationColor(String color) {
		ThemeImpl.setEventDurationColor(this, color);
	}

	public final void setEventDurationOpacity(int opacity) {
		ThemeImpl.setEventDurationOpacity(this, opacity);
	}

	public final void setEventDurationImpreciseColor(String impreciseColor) {
		ThemeImpl.setEventDurationImpreciseColor(this, impreciseColor);
	}

	public final void setEventDurationImpreciseOpacity(int impreciseOpacity) {
		ThemeImpl.setEventDurationImpreciseOpacity(this, impreciseOpacity);
	}

	public final void setEventLabelInsideColor(String insideColor) {
		ThemeImpl.setEventLabelInsideColor(this, insideColor);
	}

	public final void setEventLabelOutsideColor(String outsideColor) {
		ThemeImpl.setEventLabelOutsideColor(this, outsideColor);
	}

	public final void setEventHighlightColors(String[] highlightColors) {
		ThemeImpl.setEventHighlightColors(this, highlightColors);
	}

	public final void setFirstDayOfWeek(int firstDayOfWeek) {
		ThemeImpl.setFirstDayOfWeek(this, firstDayOfWeek);
	}

}
