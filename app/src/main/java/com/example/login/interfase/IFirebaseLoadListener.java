package com.example.login.interfase;

import com.example.login.model.ItemGroup;

import java.util.List;

public interface IFirebaseLoadListener {

    void onFirebaseLoadSuccess(List<ItemGroup>itemGroupList);
    void  onFirebaseLoadFailed(String message);
}
