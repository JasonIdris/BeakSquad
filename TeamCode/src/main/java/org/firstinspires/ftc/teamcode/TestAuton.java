package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by connorespenshade on 11/9/17.
 */

@Autonomous(name = "Test Autonomous")
public class TestAuton extends LinearOpMode{
    Chassis chassis = new Chassis(hardwareMap);
    Lift lift = new Lift(hardwareMap);
    Vuforia vuforia = new Vuforia(hardwareMap);

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();

        lift.initLift();

        while (opModeIsActive()) {
            chassis.drive(-gamepad1.left_stick_y, gamepad1.right_stick_x, gamepad1.left_stick_x);

            lift.controlLift(gamepad2);

            idle();
        }
    }
}
