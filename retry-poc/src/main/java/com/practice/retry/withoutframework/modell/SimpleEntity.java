package com.practice.retry.withoutframework.modell;


public class SimpleEntity {

    private int startData;
    private int maxInputData;
    private State state;

    private SimpleEntity(int startData, int maxInputData, State state) {
        this.startData = startData;
        this.maxInputData = maxInputData;
        this.state = state;
        this.toString();
    }

    public static SimpleEntity create(int startData, int maxInputData) {
        return new SimpleEntity(startData, maxInputData, State.NEXT_INPUT);
    }

    public SimpleEntity successfullResponseFromService() {
        return new SimpleEntity(startData, maxInputData, State.HAS_DATA);
    }

    public SimpleEntity notSuccessfullResponseFromService()
    {

        if (State.NO_DATA.equals(state)) {
            return this;
        }

        if (startData == maxInputData) {
            return new SimpleEntity(startData, maxInputData, State.NO_DATA);
        }

        return new SimpleEntity(startData + 1, maxInputData, State.NEXT_INPUT);
    }

    public int getStartData() {
        return startData;
    }

    public int getMaxInputData() {
        return maxInputData;
    }

    public State getState() {
        return state;
    }

    @Override
    public String toString() {
        return "SimpleEntity{" +
                "startData=" + startData +
                ", maxInputData=" + maxInputData +
                ", state=" + state +
                '}';
    }
}
