package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

public class DataDownloader implements Runnable {
    private final int pageNo;
    private final CountDownLatch latch;

    public DataDownloader(int pageNo, CountDownLatch latch) {
        this.pageNo = pageNo;
        this.latch = latch;
    }

    @Override
    public void run() {
        Logger logger = LoggerFactory.getLogger(App.class);
        logger.info("Data downloader thread starting: {}, Time: {}", Thread.currentThread().getName(), new Date());

        try {
            for (int i = 1; i <= 4; i++) {
                Thread.sleep(1000);
                logger.info("Page {} downloading {}%. Thread: {}, Time: {}", pageNo, i*25, Thread.currentThread().getName(), new Date());
            }
            latch.countDown();
        } catch (InterruptedException e) {
            logger.info("Thread: {} interrupted, Time: {}", Thread.currentThread().getName(), new Date());
            Thread.currentThread().interrupt();
        }
    }
}
