package com.codeminders.labs.timeextractor.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.jsoup.Jsoup;

import com.codeminders.labs.timeextractor.entities.BaseText;
import com.codeminders.labs.timeextractor.utilities.RestParameters;

@Path("/cleaner/")
public class Html2TextRestService {

    @POST
    @Path("/clean/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAnnotationsForMultipleTexts(JSONArray jsonArray) throws JSONException {
        JSONObject object = jsonArray.getJSONObject(0);
        String text = object.optString(RestParameters.TEXT);
        String result = Jsoup.parse(text).text();
        BaseText basetest = new BaseText();
        basetest.setText(result);
        return Response.status(200).entity(basetest).build();
    }

}
