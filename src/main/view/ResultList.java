package main.view;

import java.util.ArrayList;
import javax.swing.*;

public class ResultList extends JSplitPane {

    private Result preciseResult;
    private Result fuzzyResult;

    ResultList() {
        super(JSplitPane.VERTICAL_SPLIT, true);

        this.preciseResult = new Result();
        this.fuzzyResult = new Result();
        JScrollPane precisePane = new JScrollPane(this.preciseResult);
        JScrollPane fuzzyPane = new JScrollPane(this.fuzzyResult);
        precisePane.setBorder(BorderFactory.createTitledBorder("Results"));
        fuzzyPane.setBorder(BorderFactory.createTitledBorder("Are you trying to search"));
        this.setTopComponent(precisePane);
        this.setBottomComponent(fuzzyPane);

        this.setOneTouchExpandable(true);
    }

    void adjust() {
        this.preciseResult.adjust();
        this.fuzzyResult.adjust();

        this.setDividerLocation(0.5);
    }

    public void setPreciseResult(ArrayList<String> list) {
        this.preciseResult.setList(list);
    }

    public void setFuzzyResult(ArrayList<String> list) {
        this.fuzzyResult.setList(list);
    }

    public void clearPreciseResult() {
        this.preciseResult.clear();
    }

    public void clearFuzzyResult() {
        this.fuzzyResult.clear();
    }

}
