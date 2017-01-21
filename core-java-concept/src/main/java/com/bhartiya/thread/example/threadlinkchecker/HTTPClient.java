package com.bhartiya.thread.example.threadlinkchecker;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Rahul on 21-01-2017.
 * http://www.oracle.com/webfolder/technetwork/tutorials/obe/java/linkChecker/ThreadLinkChecker.html
 */
public class HTTPClient implements Runnable {
    private final String urlToBeValidated;

    HTTPClient(String url) {
        this.urlToBeValidated = url;
    }

    public void validateUrl(String url) throws IOException {
        if (verifyUrl(url)) {
            try {
                URL myUrl = new URL(url);
                HttpURLConnection myConnection = (HttpURLConnection) myUrl.openConnection();
                if (myConnection.getResponseCode() == URLStatus.HTTP_OK.getStatusCode()) {
                    Results.getInstance().appendSucceded("\n" + url + "***** Status message is : " +
                            URLStatus.getStatusMessageForStatusCode(myConnection.getResponseCode()));
                } else {
                    Results.getInstance().appendFailed("\n" + url + "***** Status message is : " +
                            URLStatus.getStatusMessageForStatusCode(myConnection.getResponseCode()));
                }
            } catch (Exception e) {
                Results.getInstance().appendFailed("\n" + url + "***** Status message is : " + e.getMessage());
            }
        } else {
            Results.getInstance().appendIncorrect("\n" + url);
        }
    }

    @Override
    public void run() {
        System.out.println("Started thread " + Thread.currentThread().getName() +
                " Verifying URL " + this.urlToBeValidated);
        try {
            this.validateUrl(this.urlToBeValidated);
        } catch (IOException e) {
            Logger.getLogger(HTTPClient.class.getName()).log(Level.SEVERE, null, e);
        }
        Results.getInstance().latchCountDown();
    }

    private boolean verifyUrl(String url) {
        String urlRegex = "^(http|https)://[-a-zA-Z0-9+&@#/%?=~_|,!:.;]*[-a-zA-Z0-9+@#/%=&_|]";
        Pattern pattern = Pattern.compile(urlRegex);
        Matcher matcher = pattern.matcher(url);
        return matcher.matches();
    }

}
