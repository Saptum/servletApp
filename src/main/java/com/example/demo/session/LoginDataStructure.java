package com.example.demo.session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class LoginDataStructure {
    public static HashMap<String, String> mapUsers = new HashMap<>();
    public static List<String[]> listUsers = new ArrayList<>();

    public static void initMapUsers() {
        mapUsers.put("Sam", "241424");
        mapUsers.put("Din", "super");
        mapUsers.put("Harry", "423rvq32");
        mapUsers.put("Tom", "353q514");
        mapUsers.put("Ezio", "creed");
    }

    public static void initListUsers(){
        listUsers.add(new String[]{"Sam", "241424"});
        listUsers.add(new String[]{"Din", "super"});
        listUsers.add(new String[]{"Harry", "423rvq32"});
        listUsers.add(new String[]{"Tom", "353q514"});
        listUsers.add(new String[]{"Ezio", "creed"});
    }

    public static boolean checkInitMap(String login, String password) {
        if (mapUsers.containsKey(login) && mapUsers.get(login).equals(password)) {
            return true;
        }
        return false;
    }

    public static boolean checkInitList(String login, String password){
        for (String[] x:listUsers
        ) {
            if (x[0].equals(login) && x[1].equals(password)){
                return true;
            }
        }
        return false;
    }
}
