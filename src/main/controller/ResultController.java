package main.controller;

import main.view.Result;

public class ResultController implements Controller {

    private final Result result;

    public ResultController(Result result) {
        this.result = result;
    }

    @Override
    public void update() {
        String key = this.result.getCurrentKey();
        // TODO: search with key
    }

}
