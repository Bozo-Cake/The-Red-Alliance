package team.cake.theredalliance;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class Json {
    String className = "JSON";

    Json(){

    }
    public void writeJson(String contents, String filename){
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson _json = builder.create();
            FileWriter writer = new FileWriter(filename);
            writer.write(String.valueOf(_json.toJson(contents)));
            writer.close();
            Log.i(className, "Successfully saved data");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToSharedPref(Map<String, String> contents, String key, SharedPreferences sprefs){
        SharedPreferences.Editor prefsEditor = sprefs.edit();
        GsonBuilder builder = new GsonBuilder();
        Gson _json = builder.create();

        prefsEditor.putString(key, String.valueOf(_json.toJson(contents)));
        prefsEditor.commit();
        Log.i(className, "Successfully saved Interview data");
    }
    public HashMap readFromSharedPref(String key, SharedPreferences sprefs){

        GsonBuilder builder = new GsonBuilder();
        Gson _json = builder.create();

        String json = sprefs.getString(key, "");
        Log.i(className, "Successfully Loaded Interview data");
        return _json.fromJson(json, HashMap.class);
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
        Log.i(className, "Successfully read data");
        return contents;
    }
}
