package com.fazua.system.productionline;

import com.google.common.base.Preconditions;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Remote implements EvationDriveSystem, Validation32BitsIntegerSerialNumber,Validation16BitsShortSerialNumber {


    private int serialNumber;
    private short HMIBoardSerialNumber;
    private State state;
    private Motor motor;
    private DrivePack drivePack;
    private SupportLevel supportLevel;
    private Timer timer = null;


    // Constructor
    public Remote(int serialNumber, short HMIBoardSerialNumber) {
        validateSpecification(serialNumber);
        validateSpecification(HMIBoardSerialNumber);

        this.serialNumber = serialNumber;
        this.HMIBoardSerialNumber = HMIBoardSerialNumber;
        this.state = State.OFF;
    }




    @Override
    public void on(){

        initializeSystem();
        System.out.println("Support Level for Evation Drive System :" + getSupportLevel());
        System.out.println();

        System.out.println("Remote Turned ON");
        System.out.println();

        this.state = State.ON;

        drivePack.activate();

    }

    @Override
    public void off() {
            timer = new Timer();
            timer.schedule(new shutDownTask(),4000,4000);
            timer.purge();
    }

    @Override
    public void increaseSupportLevel(){


            SupportLevel current = getSupportLevel();

            if(current == SupportLevel.WHITE){
                setSupportLevel(SupportLevel.BLUE);
            }

            if(current == SupportLevel.WHITE){
                setSupportLevel(SupportLevel.GREEN);
            }

            if(current == SupportLevel.GREEN){
                setSupportLevel(SupportLevel.PINK);
            }

            if(current == SupportLevel.PINK){
                setSupportLevel(SupportLevel.WHITE);
            }

            System.out.println("Increasing Support Level from " + current + " to " + getSupportLevel());
            System.out.println();
        }




    public void initializeSystem() {
            setSupportLevel(SupportLevel.WHITE);

            Scanner in = new Scanner(System.in);

            System.out.println("**** Enter Motor Specification Below ******");
            System.out.println();

            System.out.print("Enter Motor serial number in short : " );
            short motorSerialNumber = in.nextShort();
            System.out.println();

            // Create Motor object
            motor = new Motor(motorSerialNumber);

            System.out.println("**** Enter Bottom Bracket Specification Below ******");
            System.out.println();

            System.out.print("Enter BOTTOM BRACKET serial number : ");
            int bottomBracketSno = in.nextInt();
            System.out.println();

            System.out.print("Enter BOTTOM BRACKET torque Sensor number : ");
            String bottomBracketTorqueSensor = in.next();
            System.out.println();

            BottomBracket bottomBracket = new BottomBracket(bottomBracketSno,bottomBracketTorqueSensor);

            System.out.println("**** Enter Drive Pack Specification Below ******");
            System.out.println();

            System.out.print("Enter drive pack serial number : ");
            int drivePackSno = in.nextInt();
            System.out.println();

            System.out.print("Enter drive pack software version : ");
            double drivePackSoftwareVersion = in.nextDouble();
            System.out.println();

            drivePack = new DrivePack(drivePackSno,drivePackSoftwareVersion);
            drivePack.setLevel(getSupportLevel());
            drivePack.setBottomBracket(bottomBracket);
            drivePack.setMotor(motor);

    }

    // Getters and Setters


    public SupportLevel getSupportLevel() {
        return supportLevel;
    }

    public void setSupportLevel(SupportLevel supportLevel) {
        this.supportLevel = supportLevel;
    }

    @Override
    public boolean validateSpecification(int serialNumber) {
        Preconditions.checkArgument(serialNumber > 0 && serialNumber < Integer.MAX_VALUE,
                "Cannot start Simulation.Not a valid positive serial number(between 1 to 0x7fffffff) for Remote");

        System.out.println("Remote Specifications 32 bit serial numbers is valid ");
        System.out.println();

        return true;

    }

    @Override
    public boolean validateSpecification(short HMIBoardSerialNumber) {

        Preconditions.checkArgument(HMIBoardSerialNumber > 0 && HMIBoardSerialNumber < Short.MAX_VALUE,
                "Cannot start Simulation.Not a valid positive HMIBoard serial number(between 1 to 32767) for Remote");

        System.out.println("Remote Specifications 16 bit HMI Board serial number is valid ");
        System.out.println();

        return true;
    }

    @Override
    public String toString() {
        return "Remote{" +
                "serialNumber=" + serialNumber +
                ", HMIBoardSerialNumber=" + HMIBoardSerialNumber +
                ", state=" + state +
                ", supportLevel=" + supportLevel +
                '}';
    }


    class shutDownTask extends TimerTask {

        @Override
        public void run() {

            if(!drivePack.getState().equals(State.OFF)){

                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Remote OFF button pressed . Shutting down the Evation system ....");
                System.out.println();
                drivePack.deactivate();

                System.out.println("Evation System Shut down complete.");
                System.out.println();
                timer.cancel();
                System.exit(0);
            }
        }
    }


}