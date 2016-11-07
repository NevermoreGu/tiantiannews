package com.tiantiannews.data.event;

public class CitiesSearchInputEvent {

    private String inputContent;

    public CitiesSearchInputEvent(String inputContent) {
        this.inputContent = inputContent;
    }

    public String getInputContent() {
        return inputContent;
    }

    public void setInputContent(String inputContent) {
        this.inputContent = inputContent;
    }
}
