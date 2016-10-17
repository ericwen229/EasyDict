package main.view;

import com.sun.org.apache.regexp.internal.RE;

import java.awt.*;
import javax.swing.*;

class ResultList extends JSplitPane {

    private Result preciseResult;
    private Result fuzzyResult;

    private static ResultList resultList;

    private ResultList() {
        super(JSplitPane.VERTICAL_SPLIT, true);

        this.preciseResult = new Result("Results");
        this.fuzzyResult = new Result("Are you trying to search");
        this.setTopComponent(this.preciseResult);
        this.setBottomComponent(this.fuzzyResult);

        this.setOneTouchExpandable(true);
    }

    static ResultList createResultList() {
        if (ResultList.resultList == null) {
            ResultList.resultList = new ResultList();
        }
        return ResultList.resultList;
    }

    void adjust() {
        this.setDividerLocation(0.5);
    }

}
