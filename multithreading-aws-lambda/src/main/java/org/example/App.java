package org.example;

import com.amazonaws.services.lambda.runtime.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

public class App
{
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main( String[] args ) throws InterruptedException {
        handleRequest("", null);
    }

    /**
     * AWS Lambda entry point
     * @param input
     * @param context
     * @return
     */
    public static String handleRequest(String input, Context context) throws InterruptedException {
        LOGGER.info("Data downloader started");
        int numberOfPages = 3;
        CountDownLatch latch = new CountDownLatch(numberOfPages);

        for (int i = 0; i < numberOfPages; i++) {
            new Thread(new DataDownloader(i, latch)).start();
        }

        latch.await();
        LOGGER.info("Data downloader finished");
        return "Data download success";
    }
}
