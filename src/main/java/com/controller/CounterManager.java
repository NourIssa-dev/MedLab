package com.controller;
import javax.inject.Singleton;

@Singleton
public class CounterManager {
    private static CounterManager instance;
    private int counter;

    private CounterManager() {
        // Initialize counter
        counter = 0;
    }

    public static synchronized CounterManager getInstance() {
        if (instance == null) {
            instance = new CounterManager();
        }
        return instance;
    }

    public synchronized int getCounter() {
        return counter;
    }

    public synchronized void incrementCounter() {
        counter++;
    }

  
}