package com.example.fyp;

import java.util.ArrayList;
import java.util.List;

public class ListOfWords {
    private List<String> list = new ArrayList<>();

    public List<String> getList() {
        storeList(this.list);
        return this.list;
    }

    private void storeList(List<String> list) {
        List<String> temp = new ArrayList<>();
    }
}
