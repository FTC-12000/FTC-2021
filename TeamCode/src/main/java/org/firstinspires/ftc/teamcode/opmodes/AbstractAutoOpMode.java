package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.hardware.Robot;

/*
* The is a base class for our AutoOp OpModes, to hold universal control methods
* For new Outlaw Chassis
*/
public abstract class AbstractAutoOpMode extends OpMode {
    private enum Direction {
        FORWARDS,
        BACKWARDS,
        LEFT,
        RIGHT,
        STOP
    }

    // these are temp values, not even close lol
    private static final double SPEED = 10; // Meters / Second
    private static final double TURN_SPEED = 10; // Degrees / Second

    private final Robot robot = new Robot();

    private void turn(double degrees) {
        if (degrees > 180) {
            degrees = -180 + (degrees - 180);
        }

        double startTime = getRuntime();
        double currentTime = startTime;

        if (degrees > 0) {
            robot.setLeftDrivePower(1);
            robot.setRightDrivePower(-1);
        } else {
            robot.setLeftDrivePower(-1);
            robot.setRightDrivePower(1);
        }

        while (currentTime - startTime < degrees / TURN_SPEED) {
            currentTime = getRuntime();
        }

        robot.setLeftDrivePower(0);
        robot.setRightDrivePower(0);
    }

    private void turn(Direction direction) {
        switch (direction) {
            case LEFT:
                turn(-90);
                break;
            case RIGHT:
                turn(90);
                break;
        }
    }

    private void drive(Direction direction) {
        switch (direction) {
            case FORWARDS:
                robot.setLeftDrivePower(1);
                robot.setRightDrivePower(1);
                break;
            case BACKWARDS:
                robot.setLeftDrivePower(-1);
                robot.setRightDrivePower(-1);
                break;
            case STOP:
                robot.setLeftDrivePower(0);
                robot.setRightDrivePower(0);
                break;
        }
    }
    private void drive(Direction direction, double distance) {
        double startTime = getRuntime();
        double currentTime = startTime;

        drive(direction);
        drive(direction);

        while (currentTime - startTime < distance / SPEED) {
            currentTime = getRuntime();
        }

        drive(Direction.STOP);
        drive(Direction.STOP);
    }

    private void wait(double seconds) {
        double startTime = getRuntime();
        while (getRuntime() - startTime <= seconds) { }
    }
}
