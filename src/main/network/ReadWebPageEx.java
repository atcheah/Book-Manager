package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class ReadWebPageEx {
    String output;

    public ReadWebPageEx() {
        String jsonData = readWebPage();
        LibraryParser libraryParser = new LibraryParser();
        //libraryParser.parseLibraries("[" + jsonData + "]");
        output = libraryParser.parseLibraries(jsonData);
    }

    // EFFECTS: Reads data from url, and gets raw string format
    public String readWebPage() {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = null;
            URL url = urlHelper();
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }
            br.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return sb.toString();
        }
    }

    // EFFECTS: Establishes link to URL
    private URL urlHelper() {
        URL url = null;
        try {
            String theURL = "https://opendata.vancouver.ca/api/records/1.0/search/?dataset=libraries&facet=address"
                    + "&facet=name&facet=geo_local_area"; //this can point to any URL
            url = new URL(theURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } finally {
            return url;
        }
    }

    public String returnOutput() {
        return output;
    }
}