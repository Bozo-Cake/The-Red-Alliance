package team.cake.theredalliance;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Json {

    Json(){

    }
    public void writeJson(String contents, String filename){
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson _json = builder.create();
            FileWriter writer = new FileWriter(filename);
            writer.write(String.valueOf(_json.toJson(contents)));
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String readJson(String filename){
        String contents = "Default String";
        GsonBuilder builder = new GsonBuilder();
        Gson _json = builder.create();
        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new FileReader(filename));

             contents = _json.fromJson(bufferedReader, String.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return contents;
    }

}
