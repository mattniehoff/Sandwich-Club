package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        // Define JSON tags
        final String SAND_NAME = "name";
        final String SAND_MAINNAME = "mainName";
        final String SAND_AKA = "alsoKnownAs";

        final String SAND_ORIGIN = "placeOfOrigin";
        final String SAND_DESCRIPTION = "description";
        final String SAND_IMAGE = "image";
        final String SAND_INGREDIENTS = "ingredients";

        // Initialize object that we will return
        Sandwich sandwich = new Sandwich();

        try {
            JSONObject sandwichJson = new JSONObject(json);
            JSONObject sandwichNameJson = sandwichJson.getJSONObject(SAND_NAME);
            JSONArray sandwichAliases = sandwichNameJson.getJSONArray(SAND_AKA);
            JSONArray sandwichIngredients = sandwichJson.getJSONArray(SAND_INGREDIENTS);

            sandwich.setMainName(sandwichNameJson.getString(SAND_MAINNAME));
            sandwich.setAlsoKnownAs(buildStringList(sandwichAliases));
            sandwich.setPlaceOfOrigin(sandwichJson.getString(SAND_ORIGIN));
            sandwich.setDescription(sandwichJson.getString(SAND_DESCRIPTION));
            sandwich.setImage(sandwichJson.getString(SAND_IMAGE));
            sandwich.setIngredients(buildStringList(sandwichIngredients));
        } catch (JSONException e) {
            e.printStackTrace();
            sandwich = null;
        }

        return sandwich;
    }

    private static List<String> buildStringList(JSONArray jsonArray) throws JSONException {
        List<String> returnList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            returnList.add(jsonArray.getString(i));
        }

        return returnList;
    }
}
