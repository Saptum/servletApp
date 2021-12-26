package com.example.demo.filters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class AuthentificationCheck {
    public static HashMap<String, Boolean> checkMap = new HashMap<>();
    public static List<String> authList = new ArrayList<>();

    public static void checkMapInit(){
        checkMap.put("demo/saveServlet", false);
        checkMap.put("demo/viewServlet", true);
        checkMap.put("demo/deleteServlet", false);
        checkMap.put("demo/putServlet", false);
        checkMap.put("demo/viewByCountryServlet", true);
        checkMap.put("demo/viewByIDServlet", true);
        checkMap.put("demo/LogoutServlet", true);
        checkMap.put("demo/viewProductsServlet", true);
        checkMap.put("demo/createOrderServlet", true);
        checkMap.put("demo/addToOrderServlet", true);
        checkMap.put("demo/viewOrderServlet", true);
        checkMap.put("demo/deleteOrderServlet", true);
    }

    public static void authListInit(){
        authList.add("demo/viewByIDServlet");
        authList.add("demo/LogoutServlet");
        authList.add("demo/viewByCountryServlet");
        authList.add("demo/viewServlet");
    }

    public static boolean checkListUri(String uri){
        for (String x:authList
        ) {
            if (uri.endsWith(x)){
                return true;
            }
        }
        return false;
    }


    public static boolean checkMapUri(String uri){
        Set<String> set = checkMap.keySet();
        for (String key: set
        ) {
            if (uri.endsWith(key) && checkMap.get(key)){
                return true;
            }
        }
        return false;
    }

    public static boolean checkLogin(String uri){
        return uri.endsWith("demo/loginServlet");
    }
}
