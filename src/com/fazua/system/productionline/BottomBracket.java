package com.fazua.system.productionline;

import com.google.common.base.Preconditions;

import java.nio.charset.Charset;

public class BottomBracket implements Validation32BitsIntegerSerialNumber {

    private final int serialNumber;
    private final String torqueSensorSerialNumber;
    private State state;
    private boolean isTorqueSensorSerialAscii;
    private boolean isTorqueSensorSerialAscii12Digits;
    private String torqueSensorInASCII;

    // Constructor
    public BottomBracket(int serialNumber, String torqueSensorSerialNumber) {


        validateSpecification(serialNumber);

        validateTorqueSerialNumber(torqueSensorSerialNumber);

        this.serialNumber = serialNumber;
        this.torqueSensorSerialNumber = torqueSensorSerialNumber;
        this.state = State.OFF;
        this.isTorqueSensorSerialAscii = checkTorqueSensorSerialAscii(torqueSensorSerialNumber);
        this.torqueSensorInASCII = convertTorqueSensorSerialToAscii(torqueSensorSerialNumber);
        this.isTorqueSensorSerialAscii12Digits = isTorqueSensorSerialAscii12Digits(torqueSensorSerialNumber);
    }

    private boolean validateTorqueSerialNumber(String torqueSensorSerialNumber) {

        Preconditions.checkArgument(checkTorqueSensorSerialAscii(torqueSensorSerialNumber),
                "Simulation cannot be started as torque sensor serial number can not be converted to ASCII format");

        System.out.println("Bottom bracket Specifications provided for torque sensor serial number can be converted to a  valid ASCII");

        System.out.println();

        Preconditions.checkArgument(isTorqueSensorSerialAscii12Digits(torqueSensorSerialNumber),
                "Simulation cannot be started as torque sensor serial number in ASCII is not less than equal to 12 digits" );

        System.out.println("Bottom bracket Specifications torque serial number is max 12 digits :" + convertTorqueSensorSerialToAscii(torqueSensorSerialNumber));

        System.out.println();

        return true;
    }

    // Getters

    public boolean isTorqueSensorSerialAscii() {
        return isTorqueSensorSerialAscii;
    }

    public boolean isTorqueSensorSerialAscii12Digits() {
        return isTorqueSensorSerialAscii12Digits;
    }


    // Utility method to check if torqueSensorSerialNumber can be converted to ASCII
    public boolean checkTorqueSensorSerialAscii(String torqueSensorSerialNumber){

        return Charset.forName("US-ASCII").newEncoder().canEncode(torqueSensorSerialNumber);
    }



    // Utility method to convert torque sensor into ASCII
    public String convertTorqueSensorSerialToAscii(String torqueSensorSerialNumber){


        String sensorSerialNumber = "";


        byte[] bytes = torqueSensorSerialNumber.getBytes();

        //System.out.println("Bytes Array " + Arrays.toString(bytes));

        for (byte aByte : bytes) {
            sensorSerialNumber = sensorSerialNumber + String.format("%s", aByte);
        }

        return sensorSerialNumber;


    }

    // Utility method to check with torque serila number when converted to ASCII is max 12 digits
    public boolean isTorqueSensorSerialAscii12Digits(String torqueSensorSerialNumber) {
        String torqueSensorSerialInAscii = convertTorqueSensorSerialToAscii(torqueSensorSerialNumber);
        isTorqueSensorSerialAscii12Digits = torqueSensorSerialInAscii.length()>=0 && torqueSensorSerialInAscii.length() <=12 ;

        return isTorqueSensorSerialAscii12Digits;
    }

    // Activate Bottom Bracket
    public void activate(){

        this.state = State.ON;

        System.out.println("Bottom Bracket is active now ");
        System.out.println();
    }

    // Deactivate Bottom Bracket
    public void deactivate(){

        this.state = State.OFF;

        System.out.println("Bottom Bracket is deactivated now ");
        System.out.println();


    }

    @Override
    public boolean validateSpecification(int serialNumber) {
        Preconditions.checkArgument(serialNumber > 0 && serialNumber < Integer.MAX_VALUE
                ,"Simulation cannot be started .Not a valid positive 32 bit serial number(between 1 to 0x7fffffff) for Bottom Bracket");

        System.out.println("Bottom bracket Specifications 32 serial number is valid ");
        System.out.println();

        return true;
    }

    @Override
    public String toString() {
        return "BottomBracket{" +
                "serialNumber=" + serialNumber +
                ", torqueSensorSerialNumber='" + torqueSensorSerialNumber + '\'' +
                ", state=" + state +
                ", isTorqueSensorSerialAscii=" + isTorqueSensorSerialAscii +
                ", isTorqueSensorSerialAscii12Digits=" + isTorqueSensorSerialAscii12Digits +
                ", torqueSensorInASCII='" + torqueSensorInASCII + '\'' +
                '}';
    }


}

