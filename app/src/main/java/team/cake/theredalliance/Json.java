package team.cake.theredalliance;

import android.content.SharedPreferences;

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
    public void writeJsonSharedPref(String contents, String filename, SharedPreferences sprefs){
        SharedPreferences.Editor prefsEditor = sprefs.edit();
        GsonBuilder builder = new GsonBuilder();
        Gson _json = builder.create();

        prefsEditor.putString(filename, String.valueOf(_json.toJson(contents)));
        prefsEditor.commit();
    }
    public String readJsonSharedPref(String filename, SharedPreferences sprefs){

        GsonBuilder builder = new GsonBuilder();
        Gson _json = builder.create();

        String json = sprefs.getString(filename, "");
        return _json.fromJson(json, String.class);
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
    public void saveInterview(interview inter, String key, SharedPreferences SP){
        SharedPreferences.Editor prefsEditor = SP.edit();
        GsonBuilder builder = new GsonBuilder();
        Gson _json = builder.create();

        prefsEditor.putString(key, String.valueOf(_json.toJson(inter)));
        prefsEditor.commit();
    }
    public interview readInterview(String key, SharedPreferences SP){
        interview contents = null;
        GsonBuilder builder = new GsonBuilder();
        Gson _json = builder.create();

        String json = SP.getString(key, "");
        contents = _json.fromJson(json, interview.class);
        return contents;
    }
}