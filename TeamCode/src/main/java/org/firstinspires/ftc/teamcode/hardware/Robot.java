package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Robot {
    private static Robot instance;
    private static HardwareMap hardwareMap;

    public DcMotor leftDrive;
    public DcMotor rightDrive;

    public DcMotor eyeball;

    public Arm arm = new Arm();

    public Robot(HardwareMap hardwareMap) {
        instance = this;
        Robot.hardwareMap = hardwareMap;
    }

    public void init(HardwareMap hardwareMap) {
        leftDrive = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");

        eyeball = hardwareMap.get(DcMotor.class, "eyeball");

        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);

        eyeball.setDirection(DcMotorSimple.Direction.FORWARD);

        arm.init();
    }

    public static Robot getInstance() {
        return instance;
    }

    public static HardwareMap getHardwareMap() {
        return hardwareMap;
    }
}
