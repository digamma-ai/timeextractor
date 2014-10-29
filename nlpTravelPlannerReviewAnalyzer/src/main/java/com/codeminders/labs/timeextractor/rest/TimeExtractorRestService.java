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

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.joda.time.LocalDateTime;

import com.codeminders.labs.timeextractor.constants.RestParameters;
import com.codeminders.labs.timeextractor.dto.DTORule;
import com.codeminders.labs.timeextractor.entities.AnnotationInterval;
import com.codeminders.labs.timeextractor.entities.AnnotationIntervalHtml;
import com.codeminders.labs.timeextractor.entities.BaseText;
import com.codeminders.labs.timeextractor.entities.Settings;
import com.codeminders.labs.timeextractor.entities.TemporalExtraction;
import com.codeminders.labs.timeextractor.entities.UserInfo;
import com.codeminders.labs.timeextractor.exceptions.ExceptionMessages;
import com.codeminders.labs.timeextractor.service.GetRulesService;
import com.codeminders.labs.timeextractor.service.TemporalExtractionService;
import com.codeminders.labs.timeextractor.service.key.ApiKeyRegistration;
import com.codeminders.labs.timeextractor.service.key.ApiKeyService;
import com.codeminders.labs.timeextractor.service.key.IORegistrationService;

/* Rest service to extract temporal date either from array of texts or from html page*/

@Path("/")
public class TimeExtractorRestService {
    private static final Logger logger = Logger.getLogger(TimeExtractorRestService.class);
    private TemporalExtractionService service = new TemporalExtractionService();
    private GetRulesService rulesService = new GetRulesService();
    private ApiKeyService keyGenService = new ApiKeyService();
    private RestParametersService paramsService = new RestParametersService();
    private IORegistrationService registration = new IORegistrationService();
    private ApiKeyRegistration apiKeyRegistration = new ApiKeyRegistration();

    @POST
    @Path("/annotate")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAnnotationsForMultipleTexts(JSONArray jsonArray) throws JSONException {
        JSONObject object = jsonArray.getJSONObject(0);
        Settings settings = null;
        String html = object.optString(RestParameters.HTML);
        String timezoneOffset = object.optString(RestParameters.TIMEZONE_OFFSET);
        String key = object.optString(RestParameters.KEY);
        String email = object.optString(RestParameters.EMAIL);

        // checks if email exists in database
        boolean validEmail = registration.checkIfEmailExists(email);
        if (!validEmail) {
            return Response.status(400).entity(ExceptionMessages.API_KEY_VALIDATION_EMAIL).build();
        }
        // checks if provided key is valid
        boolean validKey = keyGenService.checkKey(email, key);
        if (!validKey) {
            return Response.status(400).entity(ExceptionMessages.API_KEY_VALIDATION).build();
        }
        boolean filledParams = paramsService.validateTextOrHtml(object);
        if (filledParams) {
            return Response.status(400).entity(ExceptionMessages.FILLED_FIELEDS).build();
        }
        LocalDateTime localDate = paramsService.getLocalDateTimeParameter(object);
        if (localDate == null) {
            return Response.status(400).entity(ExceptionMessages.DATE_RULES).build();
        }
        String rulesToIgnore = paramsService.rulesToIgnore(object);
        if (rulesToIgnore == null) {
            return Response.status(400).entity(ExceptionMessages.FIELD_RULES).build();
        }
        int latestDates = paramsService.latestDates(object);

        try {
            settings = new Settings(localDate, timezoneOffset, rulesToIgnore, latestDates);
        } catch (NumberFormatException ex) {
            logger.error(ex);
            return Response.status(400).entity(ExceptionMessages.TIMEZONE).build();
        } catch (Exception ex) {
            logger.error(ex);
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
                    logger.error(ex);
                }
                baseTexts.add(baseText);
            }
            Map<String, TreeSet<TemporalExtraction>> extractDates = service.extractDatesAndTimeFromMultipleText(baseTexts, settings);
            Map<String, TreeSet<AnnotationInterval>> annotatedIntervals = service.getAllAnnotations(extractDates);
            return Response.status(200).entity(annotatedIntervals).build();

        }
    }

    @GET
    @Path("/rules")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRules() throws JSONException {
        Map<String, TreeSet<DTORule>> rules = rulesService.getAllAvailableRules();
        return Response.status(200).entity(rules).build();
    }

    @POST
    @Path("/generatekey")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response generateAPIKey(JSONObject object) throws JSONException {
        String email = object.optString(RestParameters.EMAIL);
        String name = object.optString(RestParameters.NAME);
        String country = object.optString(RestParameters.COUNTRY);
        UserInfo user = new UserInfo(name, email, country);
        try {
            String key = apiKeyRegistration.registerAndGetAPIKey(user);
            return Response.status(200).entity(key).build();
        } catch (Exception e) {
            return Response.status(400).build();
        }
    }

}
