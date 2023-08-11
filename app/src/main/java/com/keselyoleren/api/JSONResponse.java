package com.keselyoleren.api;

import com.keselyoleren.model.ListUsers;

public class JSONResponse {
    private ListUsers[] users;
    public ListUsers[] getUsers(){
        return users;
    }
}
