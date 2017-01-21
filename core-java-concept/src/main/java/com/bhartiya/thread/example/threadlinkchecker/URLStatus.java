package com.bhartiya.thread.example.threadlinkchecker;

/**
 * Created by Rahul on 21-01-2017.
 */
public enum URLStatus {
    HTTP_OK(200, "OK", "SUCCESS"), NO_CONTENT(204, "No Content", "SUCCESS"),
    MOVED_PERMANENTLY(301, "Moved Permanently", "SUCCESS"), NOT_MODIFIED(304, "Not Modified", "SUCCESS"),
    USE_PROXY(305, "Use Proxy", "SUCCESS"), INTERNAL_SERVER_ERROR(500, "Internal Server Error", "ERROR"),
    NOT_FOUND(404, "Not Found", "ERROR");

    private int statusCode;
    private String httpMessage;
    private String result;

    public int getStatusCode() {
        return statusCode;
    }

    private URLStatus(int code, String message, String status) {
        statusCode = code;
        httpMessage = message;
        result = status;
    }

    public static String getStatusMessageForStatusCode(int httpCode) {
        String returnStatusMessage = "Status Not Defined";
        for (URLStatus object : URLStatus.values()) {
            if (object.statusCode == httpCode) {
                returnStatusMessage = object.httpMessage;
            }
        }
        return returnStatusMessage;
    }

    public static String getResultForStatusCode(int httpCode) {
        String returnResultMessage = "Result Not Defined";
        for (URLStatus object : URLStatus.values()) {
            if (object.statusCode == httpCode) {
                returnResultMessage = object.result;
            }
        }
        return returnResultMessage;
    }
}