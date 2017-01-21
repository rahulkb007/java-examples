package com.bhartiya.thread.example.threadlinkchecker;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Rahul on 21-01-2017.
 */
public class Results {
    private static Results resultInstance = null;

    private Results() {
    }

    public static Results getInstance() {
        if (resultInstance == null) {
            resultInstance = new Results();
        }
        return resultInstance;
    }

    private AtomicReference<String> failedURLS = new AtomicReference<>("");
    private AtomicReference<String> succededURLS = new AtomicReference<>("");
    private AtomicReference<String> incorrectURLS = new AtomicReference<>("");
    private CountDownLatch latch;

    public CountDownLatch getLatch() {
        return latch;
    }

    public void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }

    public void latchCountDown() {
        latch.countDown();
    }

    public void appendFailed(String message) {
        failedURLS.accumulateAndGet(message, (s1, s2) -> s1 + s2);
    }

    public String getFailed() {
        return failedURLS.get();
    }

    public void appendSucceded(String message) {
        succededURLS.accumulateAndGet(message, (s1, s2) -> s1 + s2);
    }

    public String getSucceded() {
        return succededURLS.get();
    }

    public void appendIncorrect(String message) {
        incorrectURLS.accumulateAndGet(message, (s1, s2) -> s1 + s2);
    }

    public String getIncorrect() {
        return incorrectURLS.get();
    }
}
