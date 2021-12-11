package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware.Robot;

@Autonomous(name = "Auto: Basic Close Right", group = "12000")
public class BasicCloseRightAutoOpMode extends LinearOpMode {
    Robot robot = new Robot();

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();

        robot.init(hardwareMap);

        robot.setLeftDrivePower(1);
        robot.setRightDrivePower(1);

        sleep(600);

        robot.setLeftDrivePower(1);
        robot.setRightDrivePower(-1);

        sleep(500);

        robot.setLeftDrivePower(1);
        robot.setRightDrivePower(1);

        sleep(2000);

        robot.setLeftDrivePower(0);
        robot.setRightDrivePower(0);
    }
}
