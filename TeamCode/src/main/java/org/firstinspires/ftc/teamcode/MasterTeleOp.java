package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by albusdumbledore on 11/6/17.
 */

@TeleOp(name = "Master")
public class MasterTeleOp extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        //ENSURES ONLY ONE OBJECT
        Chassis chassis = Chassis.getInstance(hardwareMap);
        Lift lift = Lift.getInstance(hardwareMap);
        Vuforia vuforia = Vuforia.getInstance(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {

            chassis.drive(-gamepad1.left_stick_y, gamepad1.right_stick_x, gamepad1.left_stick_x);
            lift.controlLift(gamepad2);

            idle();
        }

    }
}
