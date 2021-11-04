package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

/*
* This class holds all of the hardware (and setup code) for the robot. The arm is a separate class, Arm.java
* For new Outlaw Chassis
*/
public class NewRobot {
    private static NewRobot instance;
    private static HardwareMap hardwareMap;

    private DcMotor leftDrive1;
    private DcMotor leftDrive2;
    private DcMotor rightDrive1;
    private DcMotor rightDrive2;

    public DcMotor spinner;

    public NewRobot(HardwareMap hardwareMap) {
        instance = this;
        NewRobot.hardwareMap = hardwareMap;
    }

    public void init(HardwareMap hardwareMap) {
        leftDrive1 = hardwareMap.get(DcMotor.class, "left_drive_1");
        leftDrive2 = hardwareMap.get(DcMotor.class, "left_drive_2");
        rightDrive1 = hardwareMap.get(DcMotor.class, "right_drive_1");
        rightDrive2 = hardwareMap.get(DcMotor.class, "right_drive_2");

        spinner = hardwareMap.get(DcMotor.class, "spinner");

        leftDrive1.setDirection(DcMotor.Direction.FORWARD);
        leftDrive2.setDirection(DcMotor.Direction.FORWARD);
        rightDrive1.setDirection(DcMotor.Direction.REVERSE);
        rightDrive2.setDirection(DcMotor.Direction.REVERSE);

        spinner.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void setLeftDrivePower(double power) {
        leftDrive1.setPower(power);
        leftDrive2.setPower(power);
    }

    public void setRightDrivePower(double power) {
        rightDrive1.setPower(power);
        rightDrive2.setPower(power);
    }

    public static NewRobot getInstance() {
        return instance;
    }

    public static HardwareMap getHardwareMap() {
        if (hardwareMap != null) {
            return hardwareMap;
        } else {
            System.out.println("NULL, RIP");
            return null;
        }
    }
}
