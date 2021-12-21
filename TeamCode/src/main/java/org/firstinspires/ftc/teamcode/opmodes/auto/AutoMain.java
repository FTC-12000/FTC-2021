package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.settings.SettingsMenu;
import org.firstinspires.ftc.teamcode.settings.SettingsUtil;

/*
 * Main AutoOp
 * Currently needs spinner pathing implemented, but everything else is good
 */
@Autonomous(name = "Auto: Main", group = "12000")
public class AutoMain extends AbstractAutoOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        super.runOpMode();
        sleep(settings.getSetting("delay") * 5000L);

        int turnDir = 1;
        if (settings.getSetting("team") == 1) { turnDir = -1; }

        if (settings.getSetting("carousel") == 1) { telemetry.addData("Warning","Carousel not implemented, ignoring"); }
        drive(0.4);
        turn(90 * turnDir);
        if (settings.getSetting("position") == 0) {
            if (settings.getSetting("team") == 0) { drive(2.5); }
            else { drive(1.7); }
        } else {
            if (settings.getSetting("team") == 0) { drive(1.7); }
            else { drive(2.5); }
        }
    }

    @Override public SettingsMenu getSettings() { return SettingsUtil.createMainAutoOpSettings(telemetry, gamepad1); }
}