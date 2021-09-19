package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;

public class KonamiCode {
    boolean up, up2, down, down2, left, right, left2, right2, b, a = false;

    public boolean loop(Gamepad gamepad) {
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
                                                return true;
                                            } else if (gamepad.a) {
                                                a = true;
                                            } else if (!gamepad.atRest()) {
                                                up = false;
                                            }
                                        } else if (gamepad.b) {
                                            b = true;
                                        } else if (!gamepad.atRest()) {
                                            up = false;
                                        }
                                    } else if (gamepad.dpad_right) {
                                        right2 = true;
                                    } else if (!gamepad.atRest()) {
                                        up = false;
                                    }
                                } else if (gamepad.dpad_left) {
                                    left2 = true;
                                } else if (!gamepad.atRest()) {
                                    up = false;
                                }
                            } else if (gamepad.dpad_right) {
                                right = true;
                            } else if (!gamepad.atRest()) {
                                up = false;
                            }
                        } else if (gamepad.dpad_left) {
                            left = true;
                        } else if (!gamepad.atRest()) {
                            up = false;
                        }
                    } else if (gamepad.dpad_down) {
                        down2 = true;
                    } else if (!gamepad.atRest()) {
                        up = false;
                    }
                } else if (gamepad.dpad_down) {
                    down = true;
                } else if (!gamepad.atRest()) {
                    up = false;
                }
            } else if (gamepad.dpad_up) {
                up2 = true;
            } else if (!gamepad.atRest()) {
                up = false;
            }
        } else if (gamepad.dpad_up) {
            up = true;
        }
        return false;
    }
}
