package com.fazua.system.productionline;


import com.google.common.base.Preconditions;

public class DrivePack implements Validation32BitsIntegerSerialNumber,ValidateSoftwareversion {

    private final int serialNumber;
    private final double softwareVersion;
    private State state;
    private SupportLevel level;
    private Motor motor;
    private BottomBracket bottomBracket;


    // Constructor
    public DrivePack(int serialNumber, double softwareVersion) {

        validateSpecification(serialNumber);
        validateSpecification(softwareVersion);
        this.serialNumber = serialNumber;
        this.softwareVersion = versionAs16Bit(softwareVersion);
        this.state = State.OFF;
    }


    public void activate() {

        printSpecification();
        System.out.println();

        this.state = State.ON;

        System.out.println("Drive Pack turned ON");
        System.out.println();

        bottomBracket.activate();
        // start motor
        startMotor();
    }


    public void deactivate() {
        motor.shutDownMotor();
        bottomBracket.deactivate();
        this.state = State.OFF;
        System.out.println("Drive Pack shut down complete .");
        System.out.println();
    }


    // start motor through drive pack
    public void startMotor(){

        int motorPower = motor.runMotor(level);


        System.out.println("Motor current state :" + motor.getState());
        System.out.println();

        System.out.println(" *** OUTPUT RESULT ****");
        System.out.println();

        System.out.println("Motor power generated : " + motorPower);
        System.out.println();

        if(motorPower > 85 && motorPower < 140){

            System.out.println("Evation Drive System Test Passed");
        }
        else{

            System.out.println("Evation Drive System Test Failed");
        }

        System.out.println();
    }

    public void printSpecification() {

        System.out.println("Printing Evation Driving System Specification ");
        System.out.println();

        System.out.println("*** DRIVE PACK SPECIFICATION ***");
        System.out.println();

        System.out.println(this);
        System.out.println();

        System.out.println("*** BOTTOM BRACKET SPECIFICATION ***");
        System.out.println();

        System.out.println(bottomBracket.toString());
        System.out.println();

        System.out.println("Is Bottom Bracket Torque Sensor ASCII " + bottomBracket.isTorqueSensorSerialAscii());
        System.out.println();

        System.out.println("Is Bottom Bracket Torque Sensor ASCII 12 digits "
                + bottomBracket.isTorqueSensorSerialAscii12Digits());
        System.out.println();

        System.out.println("*** MOTOR SPECIFICATION ***");
        System.out.println();

        System.out.println(motor.toString());
        System.out.println();


    }

    /*
           Software version: positive double internally represented by max 16 bits:
        - MSB=before comma
        - LSB=after comma,
        - E.g. introducing 4,19 will translate to 0x1304
     */
    private static double versionAs16Bit(double softwareVersion){

        String MSB,LSB;
        String[] version = String.valueOf(softwareVersion).split("\\.");


        if(Integer.parseInt(version[0]) < 10)
             MSB = "0" + Integer.toHexString(Integer.parseInt(version[0])) ;
        else
            MSB = Integer.toHexString(Integer.parseInt(version[0]));

        //System.out.println("MSB in hex: " + MSB );

        if(Integer.parseInt(version[1]) < 10)
            LSB = "0" + Integer.toHexString(Integer.parseInt(version[1])) ;
        else
            LSB = Integer.toHexString(Integer.parseInt(version[1]));

        //System.out.println("LSB in hex: " + LSB );


        String version16Bit = LSB + MSB ;


        return Double.parseDouble(version16Bit);


    }

    // Getters and Setters



    public void setBottomBracket(BottomBracket bottomBracket) {
        this.bottomBracket = bottomBracket;
    }

    public SupportLevel getLevel() {
        return level;
    }

    public void setLevel(SupportLevel level) {
        this.level = level;
    }


    public void setMotor(Motor motor) {
        this.motor = motor;
    }



    @Override
    public String toString() {
        return "DrivePack{" +
                "serialNumber=" + serialNumber +
                ", softwareVersion=0x" + softwareVersion +
                ", state=" + state +
                ", level=" + level +
                ", motor=" + motor +
                ", bottomBracket=" + bottomBracket +
                '}';
    }


    @Override
    public boolean validateSpecification(double version) {
        Preconditions.checkArgument(version > 0 && version<Double.MAX_VALUE ,
                "Cannot start Simulation as Drive Pack softwareVersion is not a valid version.Version should be positive in format x.xx");

        System.out.println("Drive Pack Specifications software version is valid .Converted version is " + "0x" + versionAs16Bit(version));
        System.out.println();

        return true;
    }

    @Override
    public boolean validateSpecification(int serialNumber) {

        Preconditions.checkArgument(serialNumber > 0 && serialNumber < Integer.MAX_VALUE,
                "Cannot start Simulation as Drive Pack serial number is not a valid positive 32 bit number"
        );

        System.out.println("Drive Pack Specifications provided  serial number is a valid 32 bit integer ");
        System.out.println();

        return true;
    }
}
