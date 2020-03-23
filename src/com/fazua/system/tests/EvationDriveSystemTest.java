package com.fazua.system.tests;


import com.fazua.system.productionline.BottomBracket;
import com.fazua.system.productionline.DrivePack;
import com.fazua.system.productionline.Motor;
import com.fazua.system.productionline.Remote;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EvationDriveSystemTest {

    Remote remote;
    Motor motor;
    BottomBracket bracket;
    DrivePack drivePack;

    @BeforeEach
    void init(){

        remote = new Remote(1,(short)1);
        motor = new Motor((short)1);
        bracket = new BottomBracket(1,"abc12");
        drivePack = new DrivePack(1,4.19);
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

        assertThrows(IllegalArgumentException.class,()->bracket.validate32bitSpecification(-1),
                "Negative bracket serial throws exception");

        assertThrows(IllegalArgumentException.class,()->bracket.validate32bitSpecification(0),
                "zero as bracket serial throws exception");
        assertThrows(IllegalArgumentException.class,()->bracket.validate32bitSpecification(Integer.MAX_VALUE + 1),
                "Anything that exceeds Integer max for bracket serial value throws exception");


        assertThrows(IllegalArgumentException.class,()->bracket.validateTorqueSerialNumber("€€abc12"),
                "Torque serial number cannot be converted to ASCII");

        assertThrows(IllegalArgumentException.class,()->bracket.validateTorqueSerialNumber("abcdef123"),
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

        fail("yet to be implemented");
    }



}
