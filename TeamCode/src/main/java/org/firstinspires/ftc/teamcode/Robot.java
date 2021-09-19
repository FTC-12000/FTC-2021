package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Robot {
    public DcMotor leftDrive;
    public DcMotor rightDrive;

    public DcMotor armBase;
    public Servo armGrabber;

    public void init(HardwareMap hardwareMap) {
        leftDrive = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");

        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);

        /* Arm hardware, not built yet
        armGrabber = hardwareMap.get(DcMotor.class, "arm_base");
        armGrabber = hardwareMap.get(Servo.class, "arm_grabber");
        */
    }
}
