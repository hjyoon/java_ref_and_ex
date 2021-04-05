package com.example;

import java.io.*;
import java.math.*;
import java.util.*;
import java.lang.reflect.*;

import com.google.gson.*;
import com.google.gson.reflect.*;

class Data {
    private String userName;
    private String userMsg;

    /*
        빈 생성자 없으면
        Exception in thread "main" java.lang.RuntimeException:
        Unable to invoke no-args constructor for class com.example.Data.
        Registering an InstanceCreator with Gson for this type may fix this problem.
        오류가 뜬다.
    */
    public Data() {}

    public Data(String userName, String userMsg) {
        this.userName = userName;
        this.userMsg = userMsg;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserMsg() {
        return userMsg;
    }

    @Override
    public String toString() {
        return userName + "\n"+ userMsg + "\n";
    }
}

class ArrayData {
    private int[] int_arr;
    private double[] double_arr;
    private String[] string_arr;

    public ArrayData() {}

    public ArrayData(int[] int_arr, double[] double_arr, String[] string_arr) {
        this.int_arr = int_arr;
        this.double_arr = double_arr;
        this.string_arr = string_arr;
    }

    public int[] getInt_arr() {
        return int_arr;
    }

    public double[] getDouble_arr() {
        return double_arr;
    }

    public String[] getString_arr() {
        return string_arr;
    }

    public void print_Int_arr() {
        for(int i : int_arr) {
            System.out.print(i+" ");
        }
        System.out.println("");
    }

    public void print_Double_arr() {
        for(double i : double_arr) {
            System.out.print(i+" ");
        }
        System.out.println("");
    }

    public void print_String_arr() {
        for(String i : string_arr) {
            System.out.print(i+" ");
        }
        System.out.println("");
    }
}

class ObjectArrayData {
    private Data[] dataArray;

    public ObjectArrayData() {}

    public ObjectArrayData(Data[] dataArray) {
        this.dataArray = dataArray;
    }

    public Data[] getDataArray() {
        return dataArray;
    }

    public void print_dataArray() {
        for(Data i : dataArray) {
            System.out.println(i.getUserName());
            System.out.println(i.getUserMsg());
        }
    }
}

public class Main {
    public static void main(String args[]) throws Exception {
        System.out.println("hello");
        Gson gson = new Gson();
        //Gson gson = new GsonBuilder().create();

        //==========================================================================
        // 기본 타입을 변환
        // int var1 = gson.fromJson("1", int.class);
        // Integer var2 = gson.fromJson("1", Integer.class);
        // Long var3 = gson.fromJson("1", Long.class);
        // Boolean var4 = gson.fromJson("false", Boolean.class);
        // String var5 = gson.fromJson("\"abc\"", String.class);
        // String[] var6 = gson.fromJson("[\"abc\"]", String[].class);

        //==========================================================================
        // Data data = new Data("hjyoon", "hi");
        // String json = gson.toJson(data);    // object -> json
        // System.out.println(json);

        // String json2 = "{\"userName\":\"se1620236\",\"userMsg\":\"hihihi\"}";
        // Data data2 = gson.fromJson(json2, Data.class);  // json -> object
        // System.out.println(data2.getUserName());
        // System.out.println(data2.getUserMsg());

        //==========================================================================
        // ArrayData arraydata = new ArrayData(new int[] {0, 1, 2}, new double[] {0.1, 1.1, 2.1}, new String[] {"hj", "dy", "wb"});
        // String json3 = gson.toJson(arraydata);    // object -> json
        // System.out.println(json3);

        // String json4 = "{\"int_arr\":[4,5,6],\"double_arr\":[3.2,4.2,5.2], \"string_arr\":[\"hj2\",\"dy2\",\"wb2\"]}";
        // ArrayData arraydata2 = gson.fromJson(json4, ArrayData.class);  // json -> object
        // arraydata2.print_Int_arr();
        // arraydata2.print_Double_arr();
        // arraydata2.print_String_arr();

        //==========================================================================
        // ObjectArrayData objectarraydata = new ObjectArrayData(new Data[] {new Data("hjyoon","hi"), new Data("se1620236","hello"), new Data("hy","good")});
        // String json5 = gson.toJson(objectarraydata);    // object -> json
        // System.out.println(json5);

        // String json6 = "{\"dataArray\":[{\"userName\":\"hjyoon\",\"userMsg\":\"hi\"},{\"userName\":\"se1620236\",\"userMsg\":\"hello\"},{\"userName\":\"hy\",\"userMsg\":\"good\"}]}";
        // ObjectArrayData objectarraydata2 = gson.fromJson(json6, ObjectArrayData.class); // json -> object
        // objectarraydata2.print_dataArray();

        //==========================================================================
        // 컬렉션을 json으로 변환
        // Collection collection = new ArrayList();
        // collection.add("hello");
        // collection.add(5);
        // collection.add(new Data("hjyoon", "hi"));
        // String json = gson.toJson(collection);
        // System.out.println(json);

        // // JsonParser parser = new JsonParser();    // deprecated API
        // JsonArray array = JsonParser.parseString(json).getAsJsonArray();

        // String message = gson.fromJson(array.get(0), String.class);
        // int number = gson.fromJson(array.get(1), int.class);
        // Data data = gson.fromJson(array.get(2), Data.class);

        // System.out.printf("Using Gson.fromJson() to get: %s, %d, %s", message, number, data);

        //==========================================================================
        // Map 을 json으로 변환
        // Map<String, String> pair = new HashMap<>();
        // pair.put("hjyoon", "hi");
        // pair.put("se1620236", "hello");
        // String json = gson.toJson(pair);
        // System.out.println(json);

        // String json = "{\"hjyoon\":\"hi\",\"se1620236\":\"hello\"}";
        // Type type = new TypeToken<Map<String, String>>(){}.getType();
        // Map<String, String> pair2 = gson.fromJson(json, type);
        // System.out.println(pair2);
    }
}