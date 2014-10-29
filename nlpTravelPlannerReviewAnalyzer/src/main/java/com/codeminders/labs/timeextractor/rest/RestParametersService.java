package com.codeminders.labs.timeextractor.rest;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jettison.json.JSONObject;
import org.joda.time.LocalDateTime;

import com.codeminders.labs.timeextractor.constants.RestParameters;

public class RestParametersService {

    public LocalDateTime getLocalDateTimeParameter(JSONObject object) {
        if (object == null) {
            return null;
        }
        String date = object.optString(RestParameters.DATE);

        LocalDateTime localDate = null;
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        try {
            Date dateStr = sdf.parse(date);
            localDate = new LocalDateTime(dateStr);
        } catch (Exception ex) {
            return null;
        }
        return localDate;
    }

    public String rulesToIgnore(JSONObject object) {
        if (object == null) {
            return null;
        }
        String rulesToIgnore = null;
        try {
            rulesToIgnore = object.optString(RestParameters.RULES_TO_IGNORE);
        } catch (Exception ex) {
            return null;
        }
        return rulesToIgnore;

    }

    public int latestDates(JSONObject object) {
        String latestDates = object.optString(RestParameters.ONLY_LATEST_DATES);
        int lDates = 0;
        if (latestDates != null) {
            try {
                lDates = Integer.parseInt(latestDates);
            } catch (Exception ex) {
            }
        }
        return lDates;
    }

    public boolean validateTextOrHtml(JSONObject object) {
        String html = object.optString(RestParameters.HTML);
        String text = object.optString(RestParameters.TEXT);
        if (html == null && text == null) {
            return true;
        }
        return false;
    }

}
