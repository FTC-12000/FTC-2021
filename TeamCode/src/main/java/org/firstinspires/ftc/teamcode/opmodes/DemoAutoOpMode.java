package org.firstinspires.ftc.teamcode.opmodes;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Auto: Demo", group = "12000")
public class DemoAutoOpMode extends AbstractAutoOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        super.runOpMode();
        drive(1.5);
        turn(90);
        drive(0.5);
        drive(-1);
        drive(0.5);
        turn(-90);
        drive(-1.5);
    }
}
