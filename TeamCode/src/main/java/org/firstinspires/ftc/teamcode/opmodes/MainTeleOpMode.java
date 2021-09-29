package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.fun.KonamiCode;
import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.settings.SettingUtil;
import org.firstinspires.ftc.teamcode.settings.SettingsMenu;

@TeleOp(name = "Main: TeleOp", group = "12000")
public class MainTeleOpMode extends OpMode
{
    private final ElapsedTime runtime = new ElapsedTime();
    private final Robot robot = new Robot(hardwareMap);

    private final KonamiCode konamiCode = new KonamiCode();
    private SettingsMenu settings;
    private int loop = 0;

    // Code to run ONCE when the driver hits INIT
    @Override
    public void init() {
        robot.init(hardwareMap);
        settings = SettingUtil.createTeleOpSettings(telemetry, gamepad1);
    }

    // Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
    @Override
    public void init_loop() {
        if (konamiCode.loop(gamepad1)) {
            telemetry.addData("Dancing", ")");
            konamiCode.dance(robot);
        } else {
            settings.loop(); //broken!
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
        float leftY = -gamepad1.left_stick_y;
        float rightY = -gamepad1.right_stick_y;
        float leftX = -gamepad1.left_stick_x;
        float rightX = -gamepad1.right_stick_x;

        float leftPower = 0;
        float rightPower = 0;

        switch (settings.getSetting("drive_mode")) {
            case 0: // single stick
                leftPower = leftY;
                rightPower = rightY;
                break;
            case 1: // dual stick
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

        robot.arm.armBase.setPower(gamepad1.right_trigger - gamepad1.left_trigger);


        if (loop > 250 && (gamepad1.a || gamepad1.b || gamepad1.x || gamepad1.y)) {
            robot.arm.toggleGrab();
            loop = 0;
        }

        loop++;
    }

    // Code to run ONCE after the driver hits STOP
    @Override
    public void stop() {
        
    }
}
