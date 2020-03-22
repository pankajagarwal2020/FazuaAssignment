package com.fazua.system.productionline;

import java.util.Scanner;

public class eBikeMainSimulationApp {


    /*private Remote remote;
    //private String handle;

    public eBikeMainApp(Remote remote) {

        this.remote = remote;

    }

    public void start(){

        remote.on();
    }*/

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
        Thread.sleep(100000);

        remote.increaseSupportLevel();
        //remote.on();
        remote.off();

    }



}
