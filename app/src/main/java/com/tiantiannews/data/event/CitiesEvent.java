package com.tiantiannews.data.event;

public class CitiesEvent {

    private String inputContent;

    public CitiesEvent(String inputContent) {
        this.inputContent = inputContent;
    }

    public String getInputContent() {
        return inputContent;
    }

    public void setInputContent(String inputContent) {
        this.inputContent = inputContent;
    }
}
