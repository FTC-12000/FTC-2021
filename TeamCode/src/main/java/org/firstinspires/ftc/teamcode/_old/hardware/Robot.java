package org.firstinspires.ftc.teamcode._old.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

/*
* This class holds all of the hardware (and setup code) for the robot. The arm is a separate class, Arm.java
*/
@Deprecated
public class Robot {
    private static Robot instance;
    private static HardwareMap hardwareMap;

    public DcMotor leftDrive;
    public DcMotor rightDrive;

    public DcMotor spinner;

    public Arm arm = new Arm();

    public Robot(HardwareMap hardwareMap) {
        instance = this;
        Robot.hardwareMap = hardwareMap;
    }

    public void init(HardwareMap hardwareMap) {
        leftDrive = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");

        spinner = hardwareMap.get(DcMotor.class, "spinner");

        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);

        spinner.setDirection(DcMotorSimple.Direction.FORWARD);

        arm.init(hardwareMap);
    }

    public static Robot getInstance() {
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
