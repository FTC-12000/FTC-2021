package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "Main: TeleOp", group = "12000")
public class MainTeleOpMode extends OpMode
{
    private final ElapsedTime runtime = new ElapsedTime();
    private final Robot robot = new Robot();

    private final KonamiCode konamiCode = new KonamiCode();

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
        if (konamiCode.loop(gamepad1)) {
            telemetry.addData("Status", "Dancing");
            konamiCode.dance(robot);
        } else {
            driveModeLoop();
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
                leftX = -leftX;
                float V = (100 - Math.abs(leftX)) * (leftY / 100) + leftY;
                float W = (100 - Math.abs(leftY)) * (leftX/100) + leftX;

                rightPower = (V + W) / 2;
                leftPower = (V - W) / 2;
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
                telemetry.addData("Drive Mode", "Single Stick");
                break;
        }
    }
}
