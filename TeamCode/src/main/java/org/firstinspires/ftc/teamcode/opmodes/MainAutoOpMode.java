package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.robocol.RobocolConfig;

import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.settings.SettingsMenu;
import org.firstinspires.ftc.teamcode.settings.SettingsUtil;

/*
 * This will eventually be our AutoOp, but for now we are using BasicAutoOpMode.java
 */
@Disabled
@Autonomous(name = "Auto: Main", group = "12000")
public class MainAutoOpMode extends AbstractAutoOpMode {
    private Robot robot = new Robot();
    private SettingsMenu settings;

    @Override
    public void init() {
        robot.init(hardwareMap);
        settings = SettingsUtil.createMainAutoOpSettings(telemetry, gamepad1);
    }

    @Override
    public void init_loop() {
        settings.loop();
    }

    @Override
    public void start() {

    }

    @Override public void loop() {

    }
}