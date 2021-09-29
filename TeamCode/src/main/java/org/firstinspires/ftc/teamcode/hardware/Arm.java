package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class Arm {
    private boolean grabberClosed;

    private static final double OPEN_POS = 0;
    private static final double CLOSED_POS = 0.5;

    public DcMotor armBase;
    public Servo armGrabber;

    public void init() {
        armBase = Robot.getHardwareMap().get(DcMotor.class, "arm_base");
        armGrabber = Robot.getHardwareMap().get(Servo.class, "arm_grabber");
    }

    public void toggleGrab() {
        if (grabberClosed) {
            armGrabber.setPosition(OPEN_POS);
            grabberClosed = false;
        } else {
            armGrabber.setPosition(CLOSED_POS);
            grabberClosed = true;
        }
    }

    public void setGrabAmount(double amount) {
        armGrabber.setPosition((amount - CLOSED_POS) / (OPEN_POS - CLOSED_POS));
        grabberClosed = armGrabber.getPosition() == CLOSED_POS;
    }
}
