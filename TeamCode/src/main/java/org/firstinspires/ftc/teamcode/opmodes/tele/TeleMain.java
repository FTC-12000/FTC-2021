// i forgor
// i forgot i wrote this
package org.firstinspires.ftc.teamcode.opmodes.tele;

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
public class TeleMain extends OpMode
{
    // Big Bois
    private final ElapsedTime runtime = new ElapsedTime();
    private final Robot robot = new Robot();
    private SettingsMenu settings;

    // Settings Variables
    private int driveMode;
    private int driveSpeed;

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
        driveSpeed = settings.getSetting("drive_speed");
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

            if (gamepad1.a) { robot.setSpinnerPower(1); }
            else if (gamepad1.b) { robot.setSpinnerPower(-1); }
            else { robot.setSpinnerPower(0); }

            telemetry.clear();
            telemetry.addData("Left Encoder", robot.getLeftEncoderPos());
            telemetry.addData("Right Encoder", robot.getRightEncoderPos());

            drive();
        }

        telemetry.clear();

        loop++;
    }

    private void drive() {
        float leftY = -gamepad1.left_stick_y;
        float rightY = -gamepad1.right_stick_y;
        float leftX = -gamepad1.left_stick_x;
        //float rightX = -gamepad1.right_stick_x;  This isn't used, too bad!
        float RT = -gamepad1.right_trigger;
        float LT = -gamepad1.left_trigger;
        float leftPower = 0;
        float rightPower = 0;
        float speedMultiplier = 0.25f * driveSpeed + 0.25f;

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
            case 3: // battlefield controls (cursed) (2 hours later edit: this took way too long to figure out something this simple)
                float TP = -RT + LT;
                leftX = -leftX;
                V = (100 - Math.abs(leftX)) * (TP / 100) + TP;
                W = (100 - Math.abs(TP)) * (leftX/100) + leftX;
                leftPower = (V + W);
                rightPower = (V - W);
                break;

        }

        robot.setLeftDrivePower(leftPower * speedMultiplier);
        robot.setRightDrivePower(rightPower * speedMultiplier);
    }
}
