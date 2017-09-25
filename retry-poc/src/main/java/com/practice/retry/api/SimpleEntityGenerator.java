package com.practice.retry.api;

import com.practice.retry.withoutframework.modell.SimpleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class SimpleEntityGenerator {

    private static final Random RANDOM = new Random();

    public List<SimpleEntity> generate(int numberOfEntities)
    {
        List<SimpleEntity> entities = new ArrayList<>();

        for (int i = 0; i < numberOfEntities; i++) {
            entities.add(SimpleEntity.create(1, RANDOM.nextInt(5)));
        }

        return entities;
    }

}
