package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware.Robot;

@Autonomous(name = "Auto: Basic", group = "12000")
public class BasicAutoOpMode extends LinearOpMode {
    Robot robot = new Robot();

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);

        robot.setLeftDrivePower(1);
        robot.setRightDrivePower(1);

        sleep(500);

        robot.setLeftDrivePower(-1);
        robot.setRightDrivePower(1);

        sleep(500);

        robot.setLeftDrivePower(1);
        robot.setRightDrivePower(1);
    }
}
