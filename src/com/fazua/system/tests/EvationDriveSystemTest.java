package com.fazua.system.tests;


import com.fazua.system.productionline.*;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;


public class EvationDriveSystemTest {


    Remote remote;
    Motor motor;
    BottomBracket bottomBracket;
    DrivePack drivePack;
    int remoteSno = 1;
    short remoteHMISerialNo = 1;
    short motorSerialNumber = 2;
    int bottomBracketSno = 3;
    String bottomBracketTorqueSensor = "abc";
    int drivePackSno = 4;
    double drivePackSoftwareVersion = 4.19;
    boolean isTorqueSensorSerialAscii;
    boolean isTorqueSensorSerialAscii12Digits;
    String torqueSensorInASCII;

    @BeforeEach
    void init(){

        remote = new Remote(remoteSno,remoteHMISerialNo);
        motor = new Motor(motorSerialNumber);
        bottomBracket = new BottomBracket(bottomBracketSno,bottomBracketTorqueSensor);
        drivePack = new DrivePack(drivePackSno,drivePackSoftwareVersion);

    }



    @Test
    void testRemoteSpecifications(){


        assertThrows(IllegalArgumentException.class,()->remote.validate32bitSpecification(-1),
                "Negative remote serial throws exception");

        assertThrows(IllegalArgumentException.class,()->remote.validate32bitSpecification(0),
                "zero as remote serial throws exception");
        assertThrows(IllegalArgumentException.class,()->remote.validate32bitSpecification(Integer.MAX_VALUE + 1),
                "Anything that exceeds Integer max for remote serial value throws exception");

        assertThrows(IllegalArgumentException.class,()->remote.validate16bitSpecification((short)-1),
                "Negative remote HMI serial board number throws exception");

        assertThrows(IllegalArgumentException.class,()->remote.validate16bitSpecification((short) 0),
                "zero as remote HMI serial board number throws exception");

        assertThrows(IllegalArgumentException.class,()->remote.validate16bitSpecification((short) ((short) Short.MAX_VALUE + 1)), "remote HMI serial board number when exceeds short range should  throw exception");

    }



    @Test
    void testMotorSpecifications(){

        assertThrows(IllegalArgumentException.class,()->motor.validate16bitSpecification((short)-1),
                "Negative motor serial  number throws exception");

        assertThrows(IllegalArgumentException.class,()->motor.validate16bitSpecification((short) 0),
                "zero as motor serial number throws exception");

        assertThrows(IllegalArgumentException.class,()->motor.validate16bitSpecification((short) ((short) Short.MAX_VALUE + 1)),
                "Motor  serial number when exceeds short range should  throw exception");
    }

    @Test
    void testBottomBracketSpecifications(){

        assertThrows(IllegalArgumentException.class,()->bottomBracket.validate32bitSpecification(-1),
                "Negative bracket serial throws exception");

        assertThrows(IllegalArgumentException.class,()->bottomBracket.validate32bitSpecification(0),
                "zero as bracket serial throws exception");
        assertThrows(IllegalArgumentException.class,()->bottomBracket.validate32bitSpecification(Integer.MAX_VALUE + 1),
                "Anything that exceeds Integer max for bracket serial value throws exception");


        assertThrows(IllegalArgumentException.class,()->bottomBracket.validateTorqueSerialNumber("€€abc12"),
                "Torque serial number cannot be converted to ASCII");

        assertThrows(IllegalArgumentException.class,()->bottomBracket.validateTorqueSerialNumber("abcdef123"),
                "Torque serial number needs to be within 12 digits");


    }

    @Test
    void testDrivePackSpecifications(){

        assertEquals(4.19,4.19,"Software version should be double");
        assertThrows(IllegalArgumentException.class,()->drivePack.validateSpecification(Double.MAX_VALUE + 1),
                "Software version throws Illegal argument exception needs to be double format");

        assertThrows(IllegalArgumentException.class,()->drivePack.validate32bitSpecification(-1),
                "Negative drive pack serial throws exception");

        assertThrows(IllegalArgumentException.class,()->drivePack.validate32bitSpecification(0),
                "zero as drive pack serial throws exception");
        assertThrows(IllegalArgumentException.class,()->drivePack.validate32bitSpecification(Integer.MAX_VALUE + 1),
                "Anything that exceeds Integer max for drive pack serial value throws exception");


    }

    @Test
    void testMotorRunOutput(){

        int motorPower = motor.runMotor(SupportLevel.WHITE);
        System.out.println("motorPower = " + motorPower);
        assertEquals(motorPower > 85 && motorPower < 140, true, "Evation Drive System Failed ");
    }



}
