package org.firstinspires.ftc.teamcode;

public class Arm {
    private Robot robot;

    private boolean grabberClosed;

    private final double OPEN_POS = 0;
    private final double CLOSED_POS = 1;

    public Arm(Robot robot) {
        this.robot = robot;
    }

    public void toggleGrab() {
        if (grabberClosed) {
            robot.armGrabber.setPosition(OPEN_POS);
        } else {
            robot.armGrabber.setPosition(CLOSED_POS);
        }
    }

    public void setGrabAmount(double amount) {
        robot.armGrabber.setPosition((amount - CLOSED_POS) / (OPEN_POS - CLOSED_POS));
        if (robot.armGrabber.getPosition() == CLOSED_POS) {
            grabberClosed = true;
        } else {
            grabberClosed = false;
        }
    }
}
