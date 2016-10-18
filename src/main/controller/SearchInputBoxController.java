package main.controller;

import java.util.*;

import main.model.dict.Dict;

import main.view.SearchInputBox;
import main.view.ResultList;

public class SearchInputBoxController {

    private final SearchInputBox inputBox;

    public SearchInputBoxController(SearchInputBox inputBox) {
        this.inputBox = inputBox;
    }

    public boolean update() {
        if (this.inputBox.isEmpty()) {
            return true;
        }
        String word = this.inputBox.getText();
        Dict d = Dict.createDict();
        ArrayList<String> preciseResult = d.searchWithCommonPrefix(word);
        ArrayList<String> fuzzyResult = d.searchWithEditDist(word, 2, 10);
        ResultList resultList = ResultList.createResultList();
        resultList.setPreciseResult(preciseResult);
        resultList.setFuzzyResult(fuzzyResult);
        return (preciseResult.size() > 0);
    }

}
