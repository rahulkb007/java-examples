package com.bhartiya.thread.example.threadlinkchecker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Rahul on 21-01-2017.
 */
public class HttpClientTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<String> urlList = new CopyOnWriteArrayList<>();
        try {
            urlList = Files.readAllLines(Paths.get("src/main/resources/url-list.txt"));
        } catch (IOException e) {
            Logger.getLogger(HttpClientTest.class.getName()).log(Level.SEVERE, null, e);
        }
        Results.getInstance().setLatch(new CountDownLatch(urlList.size()));
        urlList.forEach(url -> executorService.execute(new HTTPClient(url)));
        try {
            Results.getInstance().getLatch().await();
        } catch (InterruptedException e) {
            System.out.println("Interrupted: " + e.getMessage());
        } finally {
            if (!executorService.isShutdown()) {
                executorService.shutdown();
            }
        }
        System.out.println("\n------------------------------------------------------------\n");
        System.out.println("***** Invalid URLS: *****");
        System.out.println(Results.getInstance().getIncorrect());
        System.out.println("\n------------------------------------------------------------\n\n");
        System.out.println("** Valid URLS that have successfully connected : **");
        System.out.println(Results.getInstance().getSucceded());
        System.out.println("\n------------------------------------------------------------\n\n");
        System.out.println("** Broken URLS that did not successfully connect : **");
        System.out.println(Results.getInstance().getFailed());
    }
}
