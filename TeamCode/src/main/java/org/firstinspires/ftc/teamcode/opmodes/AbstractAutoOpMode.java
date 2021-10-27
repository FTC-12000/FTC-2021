package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.hardware.Robot;

public abstract class AbstractAutoOpMode extends OpMode {
    // these are temp values, not even close lol
    private static final double TURN_LENGTH = 10;
    private static final double SPEED = 10;

    private final Robot robot = new Robot(hardwareMap);

    @Override public void init() {}
    @Override public void loop() {}

    private void turn(double degrees) {
        if (degrees > 180) {
            degrees = -180 + (degrees - 180);
        }

        double startTime = getRuntime();
        double currentTime = startTime;

        if (degrees > 0) {
            robot.leftDrive.setPower(1);
            robot.rightDrive.setPower(-1);
        } else {
            robot.leftDrive.setPower(-1);
            robot.rightDrive.setPower(1);
        }

        while (currentTime - startTime < TURN_LENGTH) {
            currentTime = getRuntime();
        }

        robot.leftDrive.setPower(0);
        robot.rightDrive.setPower(0);
    }
    private void turnLeft() { turn(-90); }
    private void turnRight() { turn(90); }
}
