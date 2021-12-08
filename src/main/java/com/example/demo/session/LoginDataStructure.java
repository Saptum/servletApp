package com.example.demo.session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class LoginDataStructure {

    public static HashMap<String, String> map = new HashMap<>();
    public static List<String[]> list = new ArrayList<>();

    public static void initList() {
        list.add(new String[]{"Sam", "241424"});
        list.add(new String[]{"Din", "super"});
        list.add(new String[]{"Harry", "423rvq32"});
        list.add(new String[]{"Tom", "353q514"});
        list.add(new String[]{"Ezio", "creed"});
    }

    public static boolean checkInitList(String login, String password) {
        for (String[] strings : list
        ) {
            if (strings[0].equals(login) && strings[1].equals(password)) {
                return true;
            }
        }
        return false;
    }

    public static void initMap() {
        map.put("Sam", "241424");
        map.put("Din", "super");
        map.put("Harry", "423rvq32");
        map.put("Tom", "353q514");
        map.put("Ezio", "creed");
    }

    public static boolean checkInitMap(String login, String password) {
        if (map.containsKey(login) && map.get(login).equals(password)) {
            return true;
        }
        return false;
    }

}
