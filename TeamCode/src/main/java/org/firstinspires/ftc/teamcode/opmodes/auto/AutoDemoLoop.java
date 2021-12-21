package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Auto: Demo Loop", group = "12000")
public class AutoDemoLoop extends AbstractAutoOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        super.runOpMode();
        while (opModeIsActive()) {
            drive(1.5);
            sleep(500);
            turn(90);
            sleep(500);
            drive(0.5);
            sleep(500);
            drive(-1);
            sleep(500);
            drive(0.5);
            sleep(500);
            turn(-90);
            drive(-1.5);
            sleep(500);
        }
    }
}