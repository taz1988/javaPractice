package com.practice.retry.service;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

@Component
public class NotStableService {

    private static final Random RANDOM = new Random();

    public List<Boolean> batchCall(List<Integer> inputs) {
        List<Boolean> results = new ArrayList<>();
        for (Integer input: inputs) {
            results.add(callService(input));
        }
        return results;
    }

    public boolean callService(int someInput) {
        return RANDOM.nextBoolean();
    }

}
