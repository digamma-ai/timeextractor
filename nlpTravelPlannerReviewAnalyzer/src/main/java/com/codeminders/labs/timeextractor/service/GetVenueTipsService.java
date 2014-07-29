package com.codeminders.labs.timeextractor.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.codeminders.labs.timeextractor.entities.Gender;
import com.codeminders.labs.timeextractor.entities.Sort;
import com.codeminders.labs.timeextractor.entities.Tip;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/*Class connects to foresquare API and gets all tips for certain venue */

public class GetVenueTipsService {

    public static final String URL = "https://api.foursquare.com/v2/venues/";
    public static final String TIPS = "/tips";
    public static final String SORT_PARAM = "?sort=";
    public static final String OAUTH_TOKEN = "oauth_token=";
    public static final String V = "v=";
    public static final String LIMIT = "limit=";

    public static final String METHOD = "GET";
    public static final String ACCEPT = "Accept";
    public static final String APPLICATION_TYPE = "application/json";

    public static final String JSON_PARAM_RESULTS = "response";
    public static final String JSON_PARAM_TIPS = "tips";
    public static final String JSON_PARAM_ITEMS = "items";
    public static final String JSON_PARAM_ITEM_ID = "id";
    public static final String JSON_PARAM_CREATED_AT = "createdAt";
    public static final String JSON_PARAM_TEXT = "text";
    public static final String JSON_PARAM_USER = "user";
    public static final String JSON_PARAM_USER_ID = "id";
    public static final String JSON_PARAM_USER_FIRST_NAME = "firstName";
    public static final String JSON_PARAM_USER_LAST_NAME = "lastName";
    public static final String JSON_PARAM_USER_GENDER = "gender";

    /* Get from URL json with results for concrete Venue */

    public String getJsonAsString(String venueId, Sort sort, String oathToken, String v, int limit)
            throws IOException {
        URL url = new URL(URL + venueId + TIPS + SORT_PARAM + sort.getValue() + "&" + OAUTH_TOKEN
                + oathToken + "&" + V + v + "&" + LIMIT + limit);
        HttpURLConnection conn = null;

        BufferedReader br = null;
        StringBuffer jsonAsString;
        try {

            conn = (HttpURLConnection) url.openConnection();
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String input;
            jsonAsString = new StringBuffer();
            while ((input = br.readLine()) != null) {
                jsonAsString.append(input);
            }
        } finally {
            br.close();
            conn.disconnect();
        }
        return jsonAsString.toString();

    }

    /* Get List of Tips for Venue */

    public List<Tip> tipsForVenue(String json, String venueId) {
        List<Tip> allTips = new ArrayList<Tip>();

        JsonParser parser = new JsonParser();
        JsonObject venueTips = (JsonObject) parser.parse(json);
        JsonObject results = venueTips.getAsJsonObject(JSON_PARAM_RESULTS);
        JsonObject tips = results.getAsJsonObject(JSON_PARAM_TIPS);
        JsonArray items = tips.getAsJsonArray(JSON_PARAM_ITEMS);

        for (int i = 0; i < items.size(); i++) {
            Tip tip = new Tip();
            tip.setVenueId(venueId);
            JsonObject item = (JsonObject) items.get(i);
            String id = item.getAsJsonPrimitive(JSON_PARAM_ITEM_ID).getAsString();
            long createdDate = item.getAsJsonPrimitive(JSON_PARAM_CREATED_AT).getAsLong();
            String text = item.getAsJsonPrimitive(JSON_PARAM_TEXT).getAsString();
            JsonObject user = item.getAsJsonObject(JSON_PARAM_USER);
            String userId = null;

            try {
                userId = user.getAsJsonPrimitive(JSON_PARAM_USER_ID).getAsString();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

            String firstName = null;
            try {
                firstName = user.getAsJsonPrimitive(JSON_PARAM_USER_FIRST_NAME).getAsString();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

            String lastName = null;
            try {
                lastName = user.getAsJsonPrimitive(JSON_PARAM_USER_LAST_NAME).getAsString();

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            String gender = null;

            try {
                gender = user.getAsJsonPrimitive(JSON_PARAM_USER_GENDER).getAsString();

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

            tip.setId(id);
            tip.setCreatedAt(createdDate);
            tip.setTipText(text);
            tip.setUserId(userId);
            tip.setUserFirstName(firstName);
            tip.setUserLastname(lastName);
            tip.setGender(Gender.getGender(gender));

            allTips.add(tip);
        }

        return allTips;
    }

}
