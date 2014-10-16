package com.codeminders.labs.timeextractor.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.codeminders.labs.timeextractor.constants.RestParameters;
import com.codeminders.labs.timeextractor.dto.DTORule;
import com.codeminders.labs.timeextractor.entities.AnnotationInterval;
import com.codeminders.labs.timeextractor.entities.AnnotationIntervalHtml;
import com.codeminders.labs.timeextractor.entities.BaseText;
import com.codeminders.labs.timeextractor.entities.Settings;
import com.codeminders.labs.timeextractor.entities.TemporalExtraction;
import com.codeminders.labs.timeextractor.exceptions.ExceptionMessages;
import com.codeminders.labs.timeextractor.service.GetRulesService;
import com.codeminders.labs.timeextractor.service.TemporalExtractionService;

/* Rest service to extract temporal date either from array of texts or from html page*/

@Path("/")
public class TimeExtractorRestService {

    private TemporalExtractionService service = new TemporalExtractionService();
    private GetRulesService rulesService = new GetRulesService();

    @POST
    @Path("/annotate")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAnnotationsForMultipleTexts(JSONArray jsonArray) throws JSONException {
        JSONObject object = jsonArray.getJSONObject(0);

        String html = object.optString(RestParameters.HTML);
        String text = object.optString(RestParameters.TEXT);
        String timezoneOffset = object.optString(RestParameters.TIMEZONE_OFFSET);

        String rulesToIgnore = null;
        try {
            object.optString(RestParameters.RULES_TO_IGNORE);
        } catch (Exception ex) {
        }
        try {
            object.optString(RestParameters.RULES_TO_IGNORE);
        } catch (Exception ex) {
        }
        if ((html == null) && (text == null)) {
            return Response.status(400).entity(ExceptionMessages.FILLED_FIELEDS).build();
        }
        Settings settings = null;
        try {
            settings = new Settings(null, timezoneOffset, rulesToIgnore);
        } catch (NumberFormatException ex) {
            return Response.status(400).entity(ExceptionMessages.TIMEZONE).build();
        } catch (Exception e) {
            return Response.status(400).entity(ExceptionMessages.FIELD_RULES).build();
        }

        // html case
        if (html != null & !html.isEmpty()) {
            long currentTime = System.currentTimeMillis();
            Map<String, TreeSet<AnnotationIntervalHtml>> result = service.extractDatesAndTimeFromHtml(html, settings);
            long endTime = System.currentTimeMillis();
            long totalTime = endTime - currentTime;
            System.out.println(totalTime);

            return Response.status(200).entity(result).build();

        }
        // text case
        else {
            List<BaseText> baseTexts = new ArrayList<BaseText>();
            for (int i = 0; i < jsonArray.length(); i++) {
                BaseText baseText = new BaseText();
                object = jsonArray.getJSONObject(i);
                baseText.setId(object.optString(RestParameters.ID));
                baseText.setText(object.optString(RestParameters.TEXT));
                try {
                    baseText.setDate(object.optString(RestParameters.DATE));
                } catch (Exception ex) {

                }
                baseTexts.add(baseText);
            }
            Map<String, TreeSet<TemporalExtraction>> extractDates = service.extractDatesAndTimeFromMultipleText(baseTexts, settings);
            Map<String, TreeSet<AnnotationInterval>> annotatedIntervals = service.getAllAnnotations(extractDates);
            return Response.status(200).entity(annotatedIntervals).build();

        }
    }

    @GET
    @Path("/getRules")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRules() throws JSONException {
        Map<String, TreeSet<DTORule>> rules = rulesService.getAllAvailableRules();
        return Response.status(200).entity(rules).build();
    }

}
