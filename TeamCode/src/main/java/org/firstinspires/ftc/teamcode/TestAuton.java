package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by connorespenshade on 11/9/17.
 */

@Autonomous(name = "Test Autonomous")
public class TestAuton extends LinearOpMode{

    Chassis chassis = Chassis.getInstance();
    Lift lift = Lift.getInstance();

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();

        while (opModeIsActive()) {



            idle();
        }
    }
}
