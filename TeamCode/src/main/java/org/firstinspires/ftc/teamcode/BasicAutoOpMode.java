package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Main: Basic", group = "12000")
public class BasicAutoOpMode extends OpMode {
    private final float DRIVE_TIME = 5.0f;

    private final ElapsedTime runtime = new ElapsedTime();
    private final Robot robot = new Robot();

    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void start() {
        runtime.reset();
    }

    @Override
    public void loop() {
        if (runtime.time() < DRIVE_TIME) {
            robot.leftDrive.setPower(1);
            robot.rightDrive.setPower(1);
        } else {
            robot.leftDrive.setPower(0);
            robot.rightDrive.setPower(0);
        }
    }
}
