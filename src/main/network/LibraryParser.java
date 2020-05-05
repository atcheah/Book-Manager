package network;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LibraryParser {

    String message = "";

    // REQUIRES: jsonData != null
    // EFFECTS: parses the different library data from jsonData grabbed from web
    public String parseLibraries(String jsonData) throws JSONException {
        JSONObject obj = new JSONObject(jsonData);
        JSONArray libraries = obj.getJSONArray("records");

        for (int i = 0; i < libraries.length(); i++) {
            JSONObject library = libraries.getJSONObject(i);
            message += parseLibrary(library);
        }
        return message;
    }

    // REQUIRES: library != null
    // EFFECTS: parses the data for a singular library
    private String parseLibrary(JSONObject library) throws JSONException {
        JSONObject libinstance = library.getJSONObject("fields");
        String name = libinstance.getString("name");
        String address = libinstance.getString("address");
        return name + " Library at " + address + "\n";
    }
}
