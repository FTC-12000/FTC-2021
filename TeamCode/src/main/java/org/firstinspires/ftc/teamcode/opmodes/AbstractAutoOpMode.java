package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.settings.SettingsMenu;
import org.firstinspires.ftc.teamcode.settings.SettingsUtil;

/*
* The is a base class for our AutoOp OpModes, to hold universal control methods
*/
public abstract class AbstractAutoOpMode extends LinearOpMode {
    public enum Direction {
        FORWARDS,
        BACKWARDS,
        LEFT,
        RIGHT,
        STOP
    }

    // these are temp values, not even close lol
    private static final double DRIVE_RATIO = 10; // Steps / Meter
    private static final double TURN_RATIO = 10; // Steps / Degree

    public final Robot robot = new Robot();
    public SettingsMenu settings;

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        robot.init(hardwareMap);
        settings = SettingsUtil.createMainAutoOpSettings(telemetry, gamepad1);
    }

    /**
     * Drives the specified distance in meters.
     * A positive distance will go forwards, and a negative distance will go backwards.
     * @param distance Distance in meters to drive
     * @param speed    Motor speed
     * @see            AbstractAutoOpMode#move(Direction, int, double)
    **/
    public void drive(double distance, double speed) {
        Direction direction;
        if (distance > 0) { direction = Direction.FORWARDS; }
        else { direction = Direction.BACKWARDS; }

        move(direction, (int)(distance * DRIVE_RATIO), speed);
    }

    /**
     * Turns the specified distance in degrees.
     * A positive distance will go right, and a negative distance will go left.
     * @param distance Distance in degrees to turn
     * @param speed    Motor speed
     * @see            AbstractAutoOpMode#move(Direction, int, double)
    **/
    public void turn(double distance, double speed) {
        if (distance > 180) { distance = -180 + (distance - 180); }
        else if (distance < -180) { distance = 180 + (distance + 180); }

        Direction direction;
        if (distance > 0) { direction = Direction.RIGHT; }
        else { direction = Direction.LEFT; }

        move(direction, (int)(distance * TURN_RATIO), speed);
    }

    /**
     * Moves or turns the specified distance in steps.
     * Left and right turn the robot, as it cannot strafe.
     * There is no direction checking, so a negative forwards distance is equal to a positive backwards distance.
     * There is no degree overflow, so turning past 360 degrees will make a full circle.
     * @param direction Direction to move
     * @param steps     Number of steps to move
     * @param speed     Motor speed
     * @see             Direction
     * @see             AbstractAutoOpMode#drive(double, double)
     * @see             AbstractAutoOpMode#turn(double, double)
     **/
    public void move(Direction direction, int steps, double speed) {
        int startStepsLeft = robot.getLeftEncoderPos();
        int startStepsRight = robot.getRightEncoderPos();
        boolean leftDone = false;
        boolean rightDone = false;
        int targetStepsLeft;
        int targetStepsRight;

        // Set targets based off of direction and start driving
        switch (direction) {
            case FORWARDS:
                targetStepsLeft = startStepsLeft + steps;
                targetStepsRight = startStepsRight + steps;

                robot.setLeftDrivePower(+speed);
                robot.setLeftDrivePower(+speed);
                break;
            case BACKWARDS:
                targetStepsLeft = startStepsLeft - steps;
                targetStepsRight = startStepsRight - steps;

                robot.setLeftDrivePower(-speed);
                robot.setLeftDrivePower(-speed);
                break;
            case LEFT:
                targetStepsLeft = startStepsLeft - steps;
                targetStepsRight = startStepsRight + steps;

                robot.setLeftDrivePower(-speed);
                robot.setLeftDrivePower(+speed);
                break;
            case RIGHT:
                targetStepsLeft = startStepsLeft + steps;
                targetStepsRight = startStepsRight - steps;

                robot.setLeftDrivePower(+speed);
                robot.setLeftDrivePower(-speed);
                break;
            default:
                return;
        }

        // Wait until both motors reach targets then stop driving
        while (opModeIsActive() && (!leftDone || !rightDone)) {
            if (robot.getLeftEncoderPos() >= targetStepsLeft && !leftDone) {
                robot.setLeftDrivePower(0);
                leftDone = true;
            }
            if (robot.getRightEncoderPos() >= targetStepsRight && !rightDone) {
                robot.setRightDrivePower(0);
                rightDone = true;
            }
        }
    }
}
