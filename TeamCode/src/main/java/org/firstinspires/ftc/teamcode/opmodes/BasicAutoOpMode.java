package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.settings.SettingUtil;
import org.firstinspires.ftc.teamcode.settings.SettingsMenu;

/*
* Basic AutoOp OpMode, for use in early competition before we finish the main one
* It just spins the spinner to drop a duck, and drives into the storage area, but is highly configurable at runtime
*/
@Autonomous(name = "Auto: Basic", group = "12000")
public class BasicAutoOpMode extends OpMode {
    private float driveTime;
    private float waitTime;
    private int turnDirection;
    private int spinner;

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
        turnDirection = settings.getSetting("turn_direction");
        spinner = settings.getSetting("spinner");
        runtime.reset();
    }

    @Override
    public void loop() {
        robot.spinner.setPower(spinner);
        if (runtime.time() > waitTime) {
            if (runtime.time() < driveTime + waitTime) {
                switch (turnDirection) {
                    case 0: // no turn
                        robot.leftDrive.setPower(1);
                        robot.rightDrive.setPower(1);
                        break;
                    case 1: // left turn
                        robot.leftDrive.setPower(0.2);
                        robot.rightDrive.setPower(1);
                        break;
                    case 3: // right turn
                        robot.leftDrive.setPower(1);
                        robot.rightDrive.setPower(0.2);
                        break;
                }

            } else {
                robot.leftDrive.setPower(0);
                robot.rightDrive.setPower(0);
            }
        }
    }
}
