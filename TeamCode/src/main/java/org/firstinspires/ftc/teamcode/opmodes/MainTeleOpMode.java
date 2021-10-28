// i forgor
package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.fun.KonamiCode;
import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.settings.SettingsUtil;
import org.firstinspires.ftc.teamcode.settings.SettingsMenu;

/*
* Main TeleOp OpMode. This class is mostly complete, and will get better documentation later.
*/
@TeleOp(name = "Tele: Main", group = "12000")
public class MainTeleOpMode extends OpMode
{
    // Big Bois
    private final KonamiCode konamiCode = new KonamiCode();
    private final ElapsedTime runtime = new ElapsedTime();
    private final Robot robot = new Robot(hardwareMap);
    private SettingsMenu settings;

    // Constants
    private static final double DIRECTIONAL_STABILITY_RATIO = 1;

    // Settings Variables
    private int driveMode;
    private int armMode;
    private int directionStability;

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
        // boolean dancing = konamiCode.loop(gamepad1);
        boolean dancing = false;
        if (dancing) {
            telemetry.addData("Dancing", ")");
            konamiCode.dance(robot);
        } else {
            settings.loop();
        }
    }
// me coding hard
    // Code to run ONCE when the driver hits PLAY
    @Override
    public void start() {
        driveMode = settings.getSetting("drive_mode");
        armMode = settings.getSetting("arm_mode");
        directionStability = settings.getSetting("directional_stability");
        runtime.reset();
    }

    // Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
    @Override
    public void loop() {
        if (paused) {
            if (loop > 100 && gamepad1.start) {
                paused = false;

                driveMode = settings.getSetting("drive_mode");
                armMode = settings.getSetting("arm_mode");

                loop = 0;
                return;
            }
            settings.loop();
        } else {
            // Pause (Re-opens the settings menu)
            if (loop > 100 && gamepad1.start) {
                paused = true;

                robot.arm.armBase.setPower(0);
                robot.arm.armExtender.setPower(0);
                robot.leftDrive.setPower(0);
                robot.rightDrive.setPower(0);

                loop = 0;
                return;
            }

            // Drive Controls
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

            if (directionStability == 0) {
                // Uncomment these after measuring and setting constant DIRECTIONAL_STABILITY_RATIO
                //leftPower *= DIRECTIONAL_STABILITY_RATIO;
                //rightPower *= (1 - DIRECTIONAL_STABILITY_RATIO);
            }
            robot.leftDrive.setPower(leftPower);
            robot.rightDrive.setPower(rightPower);

            // Arm Controls
            robot.arm.armBase.setPower(gamepad1.right_trigger - gamepad1.left_trigger);
            robot.arm.armExtender.setPower(((gamepad1.right_bumper) ? 1 : 0) - ((gamepad1.left_bumper) ? 1 : 0));

            switch (armMode) {
                case 1: // compact
                    if (loop > 250 && (gamepad1.a)) {
                        robot.arm.toggleGrab();
                        loop = 0;
                    }
                    break;
                case 2: // expanded
                    if (loop > 250 && (gamepad1.a)) {
                        robot.arm.setGrab(true);
                        loop = 0;
                    } else if (loop > 250 && (gamepad1.b)) {
                        robot.arm.setGrab(false);
                        loop = 0;
                    }
                    break;
            }

            // Spinner Controls
            if (gamepad1.y) {
                robot.spinner.setPower(1);
            } else {
                robot.spinner.setPower(0);
            }

            // Telemetry
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
        }

        loop++;
    }

    // Code to run ONCE after the driver hits STOP
    @Override
    public void stop() {
        // Bobot has been eliminated
    }
}
