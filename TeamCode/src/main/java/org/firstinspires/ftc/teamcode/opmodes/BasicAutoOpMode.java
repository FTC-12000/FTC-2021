package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.settings.SettingUtil;
import org.firstinspires.ftc.teamcode.settings.SettingsMenu;

@Autonomous(name = "Main: Basic", group = "12000")
public class BasicAutoOpMode extends OpMode {
    private float driveTime;
    private float waitTime;

    private final ElapsedTime runtime = new ElapsedTime();
    private final Robot robot = new Robot(hardwareMap);
    private SettingsMenu settings;

    @Override
    public void init() {
        robot.init(hardwareMap);
        settings = SettingUtil.createBasicAutoOpSettings(telemetry, gamepad1);
    }

    @Override
    public void init_loop() {
        settings.loop();
    }

    @Override
    public void start() {
        driveTime = settings.getSetting("drive_time");
        waitTime = settings.getSetting("wait_time");
        runtime.reset();
    }

    @Override
    public void loop() {
        if (runtime.time() > waitTime) {
            if (runtime.time() < driveTime + waitTime) {
                robot.leftDrive.setPower(1);
                robot.rightDrive.setPower(1);
            } else {
                robot.leftDrive.setPower(0);
                robot.rightDrive.setPower(0);
            }
        }
    }
}
