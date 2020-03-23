package com.fazua.system.productionline;

import java.util.Scanner;

public class EBikeMainSimulationApp {


    public static void main(String[] args) throws InterruptedException {

        //eBikeMainApp eBike;
        Scanner in = new Scanner(System.in);
        System.out.println("**** Enter Remote Specification Below ******");
        System.out.println();
        System.out.print("Enter Remote serial number : ");
        int remoteSno = in.nextInt();
        System.out.println();
        System.out.print("Enter Remote HMI board serial number : ");
        Short remoteHMISerialNo = in.nextShort();
        System.out.println();
        EvationDriveSystem remote = new Remote(remoteSno,remoteHMISerialNo);

        remote.on();
        remote.activateEvationSystem();

        remote.increaseSupportLevel();
        //remote.on();
        remote.off();

    }




}
