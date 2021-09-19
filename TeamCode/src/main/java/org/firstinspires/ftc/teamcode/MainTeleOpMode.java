package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Main", group="12000")
public class MainTeleOpMode extends OpMode
{
    private ElapsedTime runtime = new ElapsedTime();
    private Hardware robot = new Hardware();

    private KonamiCode konamiCode = new KonamiCode();
    int danceDir = 1;

    private enum DriveMode {
        DUAL_STICK,
        SINGLE_STICK
    }
    private DriveMode driveMode = DriveMode.DUAL_STICK;

    // Code to run ONCE when the driver hits INIT
    @Override
    public void init() {
        robot.init(hardwareMap);
        telemetry.addData("Drive Mode", "Dual Stick");
    }

    // Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
    @Override
    public void init_loop() {
        driveModeLoop();
        if (konamiCode.loop(gamepad1)) {
            telemetry.addData("Status", "Dancing");
            dance();
        }
    }

    // Code to run ONCE when the driver hits PLAY
    @Override
    public void start() {
        runtime.reset();
    }

    // Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
    @Override
    public void loop() {
        driveModeLoop();

        float leftY = -gamepad1.left_stick_y;
        float rightY = -gamepad1.right_stick_y;
        float leftX = gamepad1.left_stick_x;

        float leftPower = 0;
        float rightPower = 0;

        switch (driveMode) {
            case DUAL_STICK:
                leftPower = leftY;
                rightPower = rightY;
                break;
            case SINGLE_STICK:

                break;
        }


        robot.leftDrive.setPower(leftPower);
        robot.rightDrive.setPower(rightPower);

        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
    }

    // Code to run ONCE after the driver hits STOP
    @Override
    public void stop() {
        
    }

    // this isn't very efficient - Too Bad!
    private void driveModeLoop() {
        if (gamepad1.back) {
            switch (driveMode) {
                case DUAL_STICK:
                    driveMode = DriveMode.SINGLE_STICK;
                    break;
                case SINGLE_STICK:
                    driveMode = DriveMode.DUAL_STICK;
                    break;
            }
        }

        switch (driveMode) {
            case DUAL_STICK:
                telemetry.addData("Drive Mode", "Dual Stick");
                break;
            case SINGLE_STICK:
                telemetry.addData("Drive Mode", "Single Stick (Not Working)");
                break;
        }
    }

    private void dance() {
        if (((int) runtime.time()) % 3 == 0) {
            switch (danceDir) {
                case 1:
                    danceDir = -1;
                case -1:
                    danceDir = 1;
            }
        }

        robot.leftDrive.setPower(danceDir);
        robot.rightDrive.setPower(-danceDir);
    }
}
