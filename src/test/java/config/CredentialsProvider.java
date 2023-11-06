package config;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CredentialsProvider {

    private final String host;
    private final String user;
    private final String password;

    public CredentialsProvider(String filename) throws FileNotFoundException {
        String filePath = "src/test/resources/json/" + filename;
        try {
            JSONObject jsonObj = (JSONObject) new JSONParser().parse(new FileReader(filePath));
            host = (String) jsonObj.get("host");
            user = (String) jsonObj.get("user");
            password = (String) jsonObj.get("password");
        } catch (IOException e) {
            throw new FileNotFoundException("The file " + filePath + " does not exist");
        } catch (ParseException e) {
            throw new RuntimeException("The file " + filePath + " has not been parsed correctly");
        }
    }

    public String getHost() { return host; }

    public String getUser() { return user; }

    public String getPassword() { return password; }

}
