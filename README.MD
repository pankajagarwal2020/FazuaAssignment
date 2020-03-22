# FAZUA Assignment - Production Line Paramater Validations
## Console based Program
## System Specification on which the project was built
 <li> OS - MAC OS</li>
 <li>JDK Version - 1.8</li>
 <li>IDE - Intellij Idea</li>
 <li>Third Party Library - </li>
 <ul>
    <li> Google Guava core library to validate parameters check</li>
    <li> Junit 5</li>
 </ul>
 <li> Main package - com.fazua.system.productionline</li>
 <li> Test Package - com.fazua.system.tests</li>
 <li>Main Method - eBikeMainSimulationApp</li>
 <li> Class Diagram built with Visual Paradigm Community Edition</li>
 
 ### Test Input
 
Enter Remote serial number : 1

Enter Remote HMI board serial number : 1

Enter Motor serial number in short : 2

Enter BOTTOM BRACKET serial number : 3

Enter BOTTOM BRACKET torque Sensor number : pan

Enter drive pack serial number : 4

Enter drive pack software version : 4.19

### Output

**** Enter Remote Specification Below ******

Enter Remote serial number : 1

Enter Remote HMI board serial number : 1

Remote Specifications 32 bit serial numbers is valid 

Remote Specifications 16 bit HMI Board serial number is valid 

**** Enter Motor Specification Below ******

Enter Motor serial number in short : 2

Motor Specifications 16 bit  serial number is valid 

**** Enter Bottom Bracket Specification Below ******

Enter BOTTOM BRACKET serial number : 3

Enter BOTTOM BRACKET torque Sensor number : pan

Bottom bracket Specifications 32 serial number is valid 

Bottom bracket Specifications provided for torque sensor serial number can be converted to a  valid ASCII

Bottom bracket Specifications torque serial number is max 12 digits :11297110

**** Enter Drive Pack Specification Below ******

Enter drive pack serial number : 4

Enter drive pack software version : 4.19

Drive Pack Specifications provided  serial number is a valid 32 bit integer 

Drive Pack Specifications software version is valid .Converted version is 0x1304.0

Support Level for Evation Drive System :WHITE

Remote Turned ON

Printing Evation Driving System Specification 

*** DRIVE PACK SPECIFICATION ***

DrivePack{serialNumber=4, softwareVersion=0x1304.0, state=OFF, level=WHITE, motor=Motor{, motorSerialNumber=2, motorState=OFF}, bottomBracket=BottomBracket{serialNumber=3, torqueSensorSerialNumber='pan', state=OFF, isTorqueSensorSerialAscii=true, isTorqueSensorSerialAscii12Digits=true, torqueSensorInASCII='11297110'}}

*** BOTTOM BRACKET SPECIFICATION ***

BottomBracket{serialNumber=3, torqueSensorSerialNumber='pan', state=OFF, isTorqueSensorSerialAscii=true, isTorqueSensorSerialAscii12Digits=true, torqueSensorInASCII='11297110'}

Is Bottom Bracket Torque Sensor ASCII true

Is Bottom Bracket Torque Sensor ASCII 12 digits true

*** MOTOR SPECIFICATION ***

Motor{, motorSerialNumber=2, motorState=OFF}


Drive Pack turned ON

Bottom Bracket is active now 

Increasing Support Level from WHITE to GREEN

Motor current state :ON

 *** OUTPUT RESULT ****

Motor power generated : 146

Evation Drive System Test Failed

Time period for which motor ran :4003 milliseconds

Shutting down motor 

Motor shut down complete

Remote OFF button pressed . Shutting down the Evation system ....

Motor already in a shut state
Bottom Bracket is deactivated now 

Drive Pack shut down complete .

Evation System Shut down complete.







  

 