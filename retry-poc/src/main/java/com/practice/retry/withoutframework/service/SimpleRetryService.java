package com.practice.retry.withoutframework.service;

import com.practice.retry.service.NotStableService;
import com.practice.retry.withoutframework.modell.SimpleEntity;
import com.practice.retry.withoutframework.modell.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SimpleRetryService {

    private NotStableService notStableService;

    @Autowired
    public SimpleRetryService(NotStableService notStableService) {
        this.notStableService = notStableService;
    }

    public List<SimpleEntity> getResponseWithRetry(List<SimpleEntity> entities) {

            List<SimpleEntity> processedEntities = new ArrayList<>();
            List<SimpleEntity> entitiesToProcess = new ArrayList<>(entities);

            while (hasAvailableInput(entitiesToProcess))
            {
                List<Integer> nextInputs = entitiesToProcess.stream().map(SimpleEntity::getStartData).collect(Collectors.toList());

                List<Boolean> isHaveResponse = notStableService.batchCall(nextInputs);

                List<SimpleEntity> entitiesSendToRetry = new ArrayList<>();

                for (int i = 0; i < isHaveResponse.size(); i++)
                {
                    if (isHaveResponse.get(i)) {
                        processedEntities.add(entitiesToProcess.get(i).successfullResponseFromService());
                    } else {
                        entitiesSendToRetry.add(entitiesToProcess.get(i).notSuccessfullResponseFromService());
                    }
                }

                entitiesToProcess = entitiesSendToRetry;
            }

            return processedEntities;
        }

        private boolean hasAvailableInput(List<SimpleEntity> entities)
        {
            return entities.stream().map(SimpleEntity::getState).filter(State.NEXT_INPUT::equals).count() > 0;
        }

}
