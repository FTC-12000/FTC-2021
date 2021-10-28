package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/*
* This is a separate hardware class for the arm, because it has its own methods, and a bunch of extra parts/
* Main hardware is under Robot.java. Do not use this outside of Robot.java!
*/
public class Arm {
    private boolean grabberClosed;

    private static final double OPEN_POS = 0;
    private static final double CLOSED_POS = 0.5;

    public DcMotor armBase;
    public Servo armGrabber;
    public DcMotor armExtender;

    public void init(HardwareMap hardwareMap) {
        armBase = hardwareMap.get(DcMotor.class, "arm_base");
        //armGrabber = hardwareMap.get(Servo.class, "arm_grabber");
        armExtender = hardwareMap.get(DcMotor.class, "arm_extender");
    }

    public void toggleGrab() {
    }

    public void setGrab(boolean closed) {
        if (closed != grabberClosed) {
            if (grabberClosed) {
                armGrabber.setPosition(OPEN_POS);
                grabberClosed = false;
            } else {
                armGrabber.setPosition(CLOSED_POS);
                grabberClosed = true;
            }
        }
    }

    public void setGrabAmount(double amount) {
        armGrabber.setPosition((amount - CLOSED_POS) / (OPEN_POS - CLOSED_POS));
        grabberClosed = armGrabber.getPosition() == CLOSED_POS;
    }
}
