package com.example.finalprojectavcjava;

import java.util.HashMap;

public class IDandPasswords {

    // Hashmap för att lagra användarnamn och lösenord
    private HashMap<String, String> logininfo = new HashMap<>();

    // Konstruktorn för att lägga till användarnamn och lösenord i Hashmappen
    IDandPasswords() {
        logininfo.put("Username", "Password");
<<<<<<< Updated upstream
        logininfo.put("UserTwo", "Password");
        logininfo.put("Username", "Password");
=======
        logininfo.put("Lovisa", "123");
        logininfo.put("Alex", "123");
>>>>>>> Stashed changes
    }

    // Metoden för att hämta hela Hashmappen med användarnamn och lösenord
    public HashMap<String, String> getLoginInfo() {
        return logininfo;
    }

    public void clearLoginInfo() {
    }
}
