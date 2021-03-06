package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

/*
* This class holds all of the hardware (and setup code) for the robot.
*/
public class Robot {
    // Drive motors
    private DcMotor leftDrive1;
    private DcMotor leftDrive2;
    private DcMotor rightDrive1;
    private DcMotor rightDrive2;

    // Spinner motor
    private DcMotor spinner;

    // Arm Motors
    public DcMotor armActuator;
    private DcMotor armGrabber;

    // Sensors
    private TouchSensor Button;

    public void init(HardwareMap hardwareMap) {
        // Initializing motors
        leftDrive1 = hardwareMap.get(DcMotor.class, "left_drive_1");
        leftDrive2 = hardwareMap.get(DcMotor.class, "left_drive_2");
        rightDrive1 = hardwareMap.get(DcMotor.class, "right_drive_1");
        rightDrive2 = hardwareMap.get(DcMotor.class, "right_drive_2");

        spinner = hardwareMap.get(DcMotor.class, "spinner");

        armActuator = hardwareMap.get(DcMotor.class, "arm_actuator");
        armGrabber = hardwareMap.get(DcMotor.class, "arm_grabber");

        // Setting motor directions
        leftDrive1.setDirection(DcMotor.Direction.FORWARD);
        leftDrive2.setDirection(DcMotor.Direction.FORWARD);
        rightDrive1.setDirection(DcMotor.Direction.REVERSE);
        rightDrive2.setDirection(DcMotor.Direction.FORWARD);

        spinner.setDirection(DcMotor.Direction.FORWARD);

        armActuator.setDirection(DcMotor.Direction.FORWARD);
        armGrabber.setDirection(DcMotor.Direction.FORWARD);

        // Button Map
        Button = hardwareMap.get(TouchSensor.class,"reset_switch");


    }


    // region Wrappers for setting power level because we need to set 2 drive motors and it looks nicer
    public void setLeftDrivePower(double power) {
        leftDrive1.setPower(power);
        leftDrive2.setPower(power);
    }

    public void setRightDrivePower(double power) {
        rightDrive1.setPower(power);
        rightDrive2.setPower(power);
    }

    public void setSpinnerPower(double power) {
        spinner.setPower(power);
    }
    public void setArmActuatorPower(double power) { armActuator.setPower(power);}
    public void setArmGrabberPower(double power) { armGrabber.setPower(power);}
    // endregion

    // Encoder wrappers
    public int getLeftEncoderPos() { return leftDrive1.getCurrentPosition(); }
    public int getRightEncoderPos() { return rightDrive1.getCurrentPosition(); }

    public int getArmActuatorEncoderPos() { return armActuator.getCurrentPosition(); }
    public void calibrateArmActuatorEncoders() {  }

    // Button Wrapper
    public boolean isPressed() { return Button.isPressed(); }

}
