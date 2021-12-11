package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware.Robot;

@Autonomous(name = "Auto: Basic Far Left", group = "12000")
public class BasicFarLeftAutoOpMode extends LinearOpMode {
    Robot robot = new Robot();

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();

        sleep(20000);

        robot.init(hardwareMap);

        robot.setLeftDrivePower(1);
        robot.setRightDrivePower(1);

        sleep(600);

        robot.setLeftDrivePower(-1);
        robot.setRightDrivePower(1);

        sleep(500);

        robot.setLeftDrivePower(1);
        robot.setRightDrivePower(1);

        sleep(3000);

        robot.setLeftDrivePower(0);
        robot.setRightDrivePower(0);
    }
}
