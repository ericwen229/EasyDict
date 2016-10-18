package main.controller;

import main.view.ResultList;

import java.util.ArrayList;

public class ResultListController {

    private final ResultList resultList;

    public ResultListController(ResultList resultList) {
        this.resultList = resultList;
    }

    void setPreciseResult(ArrayList<String> list) {
        this.resultList.setPreciseResult(list);
    }

    void setFuzzyResult(ArrayList<String> list) {
        this.resultList.setFuzzyResult(list);
    }

    void clearPreciseResult() {
        this.resultList.clearPreciseResult();
    }

    void clearFuzzyResult() {
        this.resultList.clearFuzzyResult();
    }

}
