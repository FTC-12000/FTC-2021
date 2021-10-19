package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.fun.KonamiCode;
import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.hardware.Arm;
import org.firstinspires.ftc.teamcode.settings.SettingUtil;
import org.firstinspires.ftc.teamcode.settings.SettingsMenu;

@TeleOp(name = "Tele: Main", group = "12000")
public class MainTeleOpMode extends OpMode
{
    private final ElapsedTime runtime = new ElapsedTime();
    private final Robot robot = new Robot(hardwareMap);
    private SettingsMenu settings;

    private int driveMode;
    private int armMode;

    private final KonamiCode konamiCode = new KonamiCode();
    private int loop = 0;
    private boolean paused = false;

    // Code to run ONCE when the driver hits INIT
    @Override
    public void init() {
        robot.init(hardwareMap);
        settings = SettingUtil.createTeleOpSettings(telemetry, gamepad1);
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

    // Code to run ONCE when the driver hits PLAY
    @Override
    public void start() {
        driveMode = settings.getSetting("drive_mode");
        armMode = settings.getSetting("arm_mode");
        runtime.reset();
    }

    // Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
    @Override
    public void loop() {
        if (paused) {
            System.out.println(loop);
            if (loop > 100 && gamepad1.start) {
                paused = false;

                driveMode = settings.getSetting("drive_mode");
                armMode = settings.getSetting("arm_mode");

                loop = 0;
                return;
            }
            settings.loop();
        } else {
            if (loop > 100 && gamepad1.start) {
                paused = true;

                robot.arm.armActuator.setPower(0);
                robot.leftDrive.setPower(0);
                robot.rightDrive.setPower(0);

                loop = 0;
                return;
            }

            float leftY = -gamepad1.left_stick_y;
            float rightY = -gamepad1.right_stick_y;
            float leftX = -gamepad1.left_stick_x;
            //float rightX = -gamepad1.right_stick_x; // This isn't used, too bad!

            float leftPower = 0;
            float rightPower = 0;

            switch (driveMode) {
                case 0: // single stick
                    leftPower = leftY;
                    rightPower = rightY;
                    break;
                case 1: // dual stick
                    leftX = -leftX;
                    float V = (100 - Math.abs(leftX)) * (leftY / 100) + leftY;
                    float W = (100 - Math.abs(leftY)) * (leftX/100) + leftX;

                    leftPower = (V + W) / 2;
                    rightPower = (V - W) / 2;
                    break;
            }

            robot.leftDrive.setPower(leftPower);
            robot.rightDrive.setPower(rightPower);

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);

            //robot.arm.armBase.setPower(gamepad1.right_trigger - gamepad1.left_trigger);

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

            if (gamepad1.y) {
                robot.eyeball.setPower(1);
            } else {
                robot.eyeball.setPower(0);
            }
        }

        loop++;
    }

    // Code to run ONCE after the driver hits STOP
    @Override
    public void stop() {
        
    }
}
