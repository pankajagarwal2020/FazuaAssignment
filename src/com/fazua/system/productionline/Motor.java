package com.fazua.system.productionline;

import com.google.common.base.Preconditions;

import java.util.Random;

public class Motor implements Validation16BitsShortSerialNumber {


    private final short motorSerialNumber;

    private State state;

    private int factor ;

    private volatile int motorPower;


    // Constructor

    public Motor(short motorSerialNumber) {

        validate16bitSpecification(motorSerialNumber);
        this.motorSerialNumber = motorSerialNumber;
        this.state = State.OFF;

    }

    // Set factor for Support Level at which motor can run
    public void setFactor(SupportLevel level){

        switch (level){

            case WHITE:
                this.factor = 1 ;
                break;
            case GREEN:
                this.factor = 2;
                break;
            case BLUE:
                this.factor = 3;
                break;
            case PINK:
                this.factor = 4;
                break;

        }

    }

    // Run motor
    public int runMotor(SupportLevel level){

        this.state = State.ON;

        // set factor for various support level in case motor speed needs to be increased

        setFactor(level);

        motorPower = motorPower(Constants.MINIMUM_POWER,Constants.MAXIMUM_POWER);

        return motorPower;
    }

    // shut down motor
    public void shutDownMotor(){

        if(!this.state.equals(State.OFF)){

            this.state = State.OFF;
            motorPower = 0;

            System.out.println("Motor shut down complete");
            System.out.println();
        }else{

            System.out.println("Motor already in a shut state");
            System.out.println();
        }
    }

    // calculate random generated motor power
    public int motorPower(int min,int max){
        if(min>=max){
            throw new IllegalArgumentException("Maximum Power needs to be greater than minimum power");
        }
        Random r = new Random();

        // will return value between min(inclusive) and max(inclusive)

        return r.nextInt((max - min) + 1) + min;

    }

    // getters

    public State getState() {
        return state;
    }

    @Override
    public String toString() {
        return "Motor{" +
                ", motorSerialNumber=" + motorSerialNumber +
                ", motorState=" + state +
                '}';
    }

    @Override
    public boolean validate16bitSpecification(short serialNumber) {
        Preconditions.checkArgument(serialNumber > 0 && serialNumber < Short.MAX_VALUE ,
                "Simulation cannot be started as Motor serial number  provided is not a positive 16 bit value ");

        System.out.println("Motor Specifications 16 bit  serial number is valid ");
        System.out.println();

        return true;
    }

}
