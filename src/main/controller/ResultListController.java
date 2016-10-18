package main.controller;

import main.view.*;

import java.util.ArrayList;

public class ResultListController {

    private final ResultList resultList;

    public ResultListController(ResultList resultList) {
        this.resultList = resultList;
    }

    void setPreciseResult(ArrayList<String> list) {
        ResultController controller = new ResultController(resultList.getPreciseResult());
        controller.setList(list);
    }

    void setFuzzyResult(ArrayList<String> list) {
        ResultController controller = new ResultController(resultList.getFuzzyResult());
        controller.setList(list);
    }

    void clearPreciseResult() {
        ResultController controller = new ResultController(resultList.getPreciseResult());
        controller.clear();
    }

    void clearFuzzyResult() {
        ResultController controller = new ResultController(resultList.getFuzzyResult());
        controller.clear();
    }

}
