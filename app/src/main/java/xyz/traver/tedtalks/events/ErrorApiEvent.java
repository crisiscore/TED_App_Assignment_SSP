package xyz.traver.tedtalks.events;

public class ErrorApiEvent {

    private String errorMsg;

    public ErrorApiEvent(String message) {
        errorMsg = message;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
