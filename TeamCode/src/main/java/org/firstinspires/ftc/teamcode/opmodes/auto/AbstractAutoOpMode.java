package org.firstinspires.ftc.teamcode.opmodes.auto;

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
    private static final int DRIVE_RATIO = 2700; // Steps / Meter
    private static final int TURN_RATIO = 815; // Steps / 90 Degrees

    public int sleepTime = 500;

    public final Robot robot = new Robot();
    public SettingsMenu settings;

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        settings = getSettings();
        telemetry.setMsTransmissionInterval(1);
        while (!isStarted())  { if (settings != null) { settings.loop(); telemetry.update(); sleep(1); }}
        waitForStart();
    }

    public SettingsMenu getSettings() { return null; }

    /**
     * Drives the specified distance in meters.
     * A positive distance will go forwards, and a negative distance will go backwards.
     * @param distance Distance in meters to drive
     * @see            AbstractAutoOpMode#move(Direction, int, double)
    **/
    public void drive(double distance) {
        if (distance > 0) { move(Direction.FORWARDS, (int)(distance * DRIVE_RATIO), 0.75); }
        else { move(Direction.BACKWARDS, (int) (-distance * DRIVE_RATIO), 0.75); }
    }

    /**
     * Turns the specified distance in degrees.
     * A positive distance will go right, and a negative distance will go left.
     * @param distance Distance in degrees to turn
     * @see            AbstractAutoOpMode#move(Direction, int, double)
    **/
    public void turn(double distance) {
        if (distance > 180) { distance = -180 + (distance - 180); }
        else if (distance < -180) { distance = 180 + (distance + 180); }

        if (distance > 0) { move(Direction.RIGHT, (int)((distance / 90) * TURN_RATIO), 0.5); }
        else { move(Direction.LEFT, (int) ((-distance / 90) * TURN_RATIO), 0.5); }
    }

    /**
     * Moves or turns the specified distance in steps.
     * Left and right turn the robot, as it cannot strafe.
     * There is no direction checking, so a negative forwards distance is equal to a positive backwards distance.
     * There is no degree overflow, so turning past 360 degrees will make a full circle.
     * @param direction Direction to move
     * @param steps     Number of steps to move
     * @param speed     Motor speed (Recommended: 0.75 driving, 0.5 turning)
     * @see             Direction
     * @see             AbstractAutoOpMode#drive(double)
     * @see             AbstractAutoOpMode#turn(double)
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

                robot.setLeftDrivePower(speed);
                robot.setRightDrivePower(speed);
                break;
            case BACKWARDS:
                targetStepsLeft = startStepsLeft - steps;
                targetStepsRight = startStepsRight - steps;

                robot.setLeftDrivePower(-speed);
                robot.setRightDrivePower(-speed);
                break;
            case LEFT:
                targetStepsLeft = startStepsLeft - steps;
                targetStepsRight = startStepsRight + steps;

                robot.setLeftDrivePower(-speed);
                robot.setRightDrivePower(speed);
                break;
            case RIGHT:
                targetStepsLeft = startStepsLeft + steps;
                targetStepsRight = startStepsRight - steps;

                robot.setLeftDrivePower(speed);
                robot.setRightDrivePower(-speed);
                break;
            default:
                return;
        }

        // Wait until both motors reach targets then stop driving
        while (opModeIsActive() && (!leftDone || !rightDone)) {

            if (getRemainingSteps(direction, targetStepsLeft, robot.getLeftEncoderPos(), Direction.LEFT) < 1000 && !leftDone) {
                robot.setLeftDrivePower(getSlowSpeed(direction, Direction.LEFT));
            }
            if (getRemainingSteps(direction, targetStepsRight, robot.getRightEncoderPos(), Direction.RIGHT) < 1000 && !rightDone) {
                robot.setRightDrivePower(getSlowSpeed(direction, Direction.RIGHT));
            }

            if (getRemainingSteps(direction, targetStepsLeft, robot.getLeftEncoderPos(), Direction.LEFT) <= 0 && !leftDone) {
                robot.setLeftDrivePower(0);
                leftDone = true;
            }
            if (getRemainingSteps(direction, targetStepsRight, robot.getRightEncoderPos(), Direction.RIGHT) <= 0 && !rightDone) {
                robot.setRightDrivePower(0);
                rightDone = true;
            }
        }

        sleep(sleepTime);
    }

    private double getSlowSpeed(Direction direction, Direction motorSide) {
        switch (direction) {
            case FORWARDS: return 0.25;
            case BACKWARDS: return -0.25;
            case LEFT:
                switch (motorSide) {
                    case LEFT: return -0.5;
                    case RIGHT: return 0.5;
                }
            case RIGHT:
                switch (motorSide) {
                    case LEFT: return 0.5;
                    case RIGHT: return -0.5;
                }
            default: return 0;
        }
    }

    private int getRemainingSteps(Direction direction, int target, int current, Direction motorSide) {
        switch (direction) {
            case FORWARDS: return target - current;
            case BACKWARDS: return current - target;
            case LEFT:
                switch (motorSide) {
                    case LEFT: return current - target;
                    case RIGHT: return target - current;
                }
            case RIGHT:
                switch (motorSide) {
                    case LEFT: return target - current;
                    case RIGHT: return current - target;
                }
            default: return 0;
        }
    }
}
