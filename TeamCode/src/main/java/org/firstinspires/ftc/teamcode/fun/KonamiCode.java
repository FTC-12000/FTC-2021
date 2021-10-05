package org.firstinspires.ftc.teamcode.fun;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.hardware.Robot;

public class KonamiCode {
    boolean dancing, up, up2, down, down2, left, right, left2, right2, b, a = false;
    int direction = 1;
    int loop = 0;

    public boolean loop(Gamepad gamepad) {
        if (!dancing) {
            if (up) {
                if (up2) {
                    if (down) {
                        if (down2) {
                            if (left) {
                                if (right) {
                                    if (left2) {
                                        if (right2) {
                                            if (b) {
                                                if (a) {
                                                    dancing = true;
                                                    return true;
                                                } else if (gamepad.a) {
                                                    a = true;
                                                } else if (buttonPressed(gamepad)) {
                                                    up = false;
                                                }
                                            } else if (gamepad.b) {
                                                b = true;
                                            } else if (buttonPressed(gamepad)) {
                                                up = false;
                                            }
                                        } else if (gamepad.dpad_right) {
                                            right2 = true;
                                        } else if (buttonPressed(gamepad)) {
                                            up = false;
                                        }
                                    } else if (gamepad.dpad_left) {
                                        left2 = true;
                                    } else if (buttonPressed(gamepad)) {
                                        up = false;
                                    }
                                } else if (gamepad.dpad_right) {
                                    right = true;
                                } else if (buttonPressed(gamepad)) {
                                    up = false;
                                }
                            } else if (gamepad.dpad_left) {
                                left = true;
                            } else if (buttonPressed(gamepad)) {
                                up = false;
                            }
                        } else if (gamepad.dpad_down) {
                            down2 = true;
                        } else if (buttonPressed(gamepad)) {
                            up = false;
                        }
                    } else if (gamepad.dpad_down) {
                        down = true;
                    } else if (buttonPressed(gamepad)) {
                        up = false;
                    }
                } else if (gamepad.dpad_up) {
                    up2 = true;
                } else if (buttonPressed(gamepad)) {
                    up = false;
                }
            } else if (gamepad.dpad_up) {
                up = true;
            }
        }

        if (!up) {
            up2 = false;
            down = false;
            down2 = false;
            left = false;
            right = false;
            left2 = false;
            right2 = false;
            b = false;
            a = false;
        }

        return false;
    }

    public boolean buttonPressed(Gamepad gamepad) {
        // this is the worst line of code ive ever written
        return (gamepad.dpad_up || gamepad.dpad_down || gamepad.dpad_left || gamepad.dpad_right || gamepad.a || gamepad.b || gamepad.x || gamepad.y || gamepad.left_bumper || (gamepad.left_trigger != 0) || gamepad.right_bumper || (gamepad.right_trigger != 0) || gamepad.left_stick_button || gamepad.right_stick_button || gamepad.back || gamepad.start || gamepad.atRest());
    }

    public void dance(Robot robot) {
        loop++;
        if (loop == 250) {
            switch (direction) {
                case 1:
                    direction = -1;
                    break;
                case -1:
                    direction = 1;
                    break;
            }
            loop = 0;
        }

        //robot.leftDrive.setPower(direction);
        //robot.rightDrive.setPower(-direction);
    }
}
