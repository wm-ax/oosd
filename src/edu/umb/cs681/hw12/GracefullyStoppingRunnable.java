package edu.umb.cs681.hw12;

import java.util.concurrent.locks.ReentrantLock;


public abstract class GracefullyStoppingRunnable implements Runnable {

    private boolean done = false;
    private ReentrantLock lock;


    GracefullyStoppingRunnable() {
        lock = new ReentrantLock();
    }

    public void run() {
        while (true) {
            setLock();
            try {
                if (done) {
                    // System.out.printf("\tRequestHandler %d: stopping\n",
                                      // Thread.currentThread().getId());
                    break;
                }
            } finally {
                unsetLock();
            };
            getMethodToRun().run();
            try {
                Thread.sleep(100);            
            } catch (InterruptedException e) {
                continue;
            }
        }
    }

    protected abstract Runnable getMethodToRun();
    
    
    protected void setLock() {
        lock.lock();
    }

    protected void unsetLock() {
        lock.unlock();
    }    

    public void gracefulStop() {
        setLock();
        try {
            done = true;
            Thread.currentThread().interrupt();
        } finally {
            unsetLock();
        }
    }
    
}
