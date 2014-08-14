package com.codeminders.labs.timeextractor.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.codeminders.labs.timeextractor.entities.AnnotationInterval;
import com.codeminders.labs.timeextractor.entities.BaseText;
import com.codeminders.labs.timeextractor.service.SUTimeService;
import com.codeminders.labs.timeextractor.utilities.RestParameters;

import edu.stanford.nlp.util.CoreMap;

@Path("/")
public class TimeExtractorRestService {

    private static SUTimeService sutimeService = new SUTimeService();

    /* Get all annotation for multiple texts */

    @POST
    @Path("/annotate/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAnnotationsForMultipleTexts(JSONArray jsonArray) throws JSONException {
        List<BaseText> baseTexts = new ArrayList<BaseText>();

        for (int i = 0; i < jsonArray.length(); i++) {
            BaseText baseText = new BaseText();
            JSONObject object = jsonArray.getJSONObject(i);
            baseText.setId(object.optString(RestParameters.ID));
            baseText.setText(object.optString(RestParameters.TEXT));

            try {
                baseText.setDate(object.optString(RestParameters.DATE));
            } catch (Exception ex) {
            }
            baseTexts.add(baseText);
        }

        Map<String, List<CoreMap>> extractDates = sutimeService.extractDatesAndTimeFromText(baseTexts);
        Map<String, List<AnnotationInterval>> annotatedIntervals = sutimeService.getAllAnnotations(extractDates);
        return Response.status(200).entity(annotatedIntervals).build();
    }

    public SUTimeService getSutimeService() {
        return sutimeService;
    }

    public void setSutimeService(SUTimeService sutimeService) {
        this.sutimeService = sutimeService;
    }
}
