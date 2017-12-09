package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by connorespenshade on 12/9/17.
 */

@TeleOp(name = "Test Servo")
public class ServoTest extends LinearOpMode{

    Servo test;

    @Override
    public void runOpMode() throws InterruptedException {

        test = hardwareMap.servo.get("ls");

        waitForStart();

        while (opModeIsActive()) {

            if (gamepad1.a) {
                test.setPosition(1);
            }

            if (gamepad1.b) {
                test.setPosition(-1);
            }

            idle();
        }
    }
}
