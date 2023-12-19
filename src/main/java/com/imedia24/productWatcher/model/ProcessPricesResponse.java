package com.imedia24.productWatcher.model;
import java.util.List;

public class ProcessPricesResponse {

    private List<Action> actions;

    public ProcessPricesResponse(List<Action> actions) {
        this.actions = actions;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }
}
