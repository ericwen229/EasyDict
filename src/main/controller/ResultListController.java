package main.controller;

import main.view.ResultList;

import java.util.ArrayList;

public class ResultListController implements Controller {

    private static ResultListController controller;

    private final ResultList resultList;

    private ResultListController(ResultList resultList) {
        this.resultList = resultList;
    }

    public static ResultListController createResultListController(ResultList resultList) {
        if (ResultListController.controller == null) {
            ResultListController.controller = new ResultListController(resultList);
        }
        return ResultListController.controller;
    }

    @Override
    public void update() {
    }

    void setPreciseResult(ArrayList<String> list) {
        this.resultList.setPreciseResult(list);
    }

    void setFuzzyResult(ArrayList<String> list) {
        this.resultList.setFuzzyResult(list);
    }

}
