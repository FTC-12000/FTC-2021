// i forgor
package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.settings.SettingsMenu;
import org.firstinspires.ftc.teamcode.settings.SettingsUtil;

/*
* Main TeleOp OpMode. This class is mostly complete, and will get better documentation later.
* For new Outlaw Chassis.
*/
@TeleOp(name = "Tele: Main", group = "12000")
public class MainTeleOpMode extends OpMode
{
    // Big Bois
    private final ElapsedTime runtime = new ElapsedTime();
    private final Robot robot = new Robot();
    private SettingsMenu settings;

    // Settings Variables
    private int driveMode;

    // Working Global Variables
    private int loop = 0;
    private boolean paused = false;

    // Code to run ONCE when the driver hits INIT
    @Override
    public void init() {
        robot.init(hardwareMap);
        settings = SettingsUtil.createTeleOpSettings(telemetry, gamepad1);
    }

    // Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
    @Override
    public void init_loop() {
        settings.loop();
    }

    private void updateSettings() {
        driveMode = settings.getSetting("drive_mode");
    }

    // Code to run ONCE when the driver hits PLAY
    @Override
    public void start() {
        updateSettings();
        runtime.reset();
    }

    // Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
    @Override
    public void loop() {
        if (paused) {
            if (loop > 100 && gamepad1.start) {
                paused = false;

                updateSettings();

                loop = 0;
                return;
            }
            settings.loop();
        } else {
            // Pause (Re-opens the settings menu)
            if (loop > 100 && gamepad1.start) {
                paused = true;

                robot.setLeftDrivePower(0);
                robot.setRightDrivePower(0);

                loop = 0;
                return;
            }

            if (gamepad1.start) { robot.setSpinnerPower(1); }
            else if (gamepad1.back) { robot.setSpinnerPower(-1); }
            else { robot.setSpinnerPower(0); }

            drive();
        }

        loop++;
    }

    private void drive() {
        float leftY = -gamepad1.left_stick_y;
        float rightY = -gamepad1.right_stick_y;
        float leftX = -gamepad1.left_stick_x;
        //float rightX = -gamepad1.right_stick_x; // This isn't used, too bad!

        float leftPower = 0;
        float rightPower = 0;

        switch (driveMode) {
            case 0: // dual stick
                leftPower = leftY;
                rightPower = rightY;
                break;
            case 1: // single stick
                leftX = -leftX;
                float V = (100 - Math.abs(leftX)) * (leftY / 100) + leftY;
                float W = (100 - Math.abs(leftY)) * (leftX/100) + leftX;

                leftPower = (V + W) / 2;
                rightPower = (V - W) / 2;
                break;
            case 2: // dpad - this is stupid, but zachs forcing my hand
                leftX = 0;
                leftY = 0;
                if (gamepad1.dpad_up) {
                    leftY += 1;
                }
                if (gamepad1.dpad_down) {
                    leftY -= 1;
                }
                if (gamepad1.dpad_left) {
                    leftX -= 1;
                }
                if (gamepad1.dpad_right) {
                    leftX += 1;
                }
                V = (100 - Math.abs(leftX)) * (leftY / 100) + leftY;
                W = (100 - Math.abs(leftY)) * (leftX/100) + leftX;
                leftPower = (V + W) / 2;
                rightPower = (V - W) / 2;
                break;
        }

        robot.setLeftDrivePower(leftPower);
        robot.setRightDrivePower(rightPower);
    }
}
