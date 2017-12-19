package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by connorespenshade on 12/19/17.
 */

@Autonomous(name = "TestAutonConnor")
public class Auton extends LinearOpMode {

    DcMotor leftFront;
    DcMotor leftBack;
    DcMotor rightFront;
    DcMotor rightBack;

    @Override
    public void runOpMode() throws InterruptedException {

        leftFront = hardwareMap.dcMotor.get(UniversalConstants.LEFT1NAME);
        leftBack = hardwareMap.dcMotor.get(UniversalConstants.LEFT2NAME);
        rightFront = hardwareMap.dcMotor.get(UniversalConstants.RIGHT1NAME);
        rightBack = hardwareMap.dcMotor.get(UniversalConstants.RIGHT2NAME);
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();



        this.resetStartTime();

        if (this.getRuntime() > 2.0) { //this will wait 1 second; change the value to wait a different amount of tim
            //code to run once wait is complete

            leftFront.setPower(0);
            leftBack.setPower(0);
            rightFront.setPower(0);
            rightBack.setPower(0);
        }
        else {
            //code to run while waiting

            leftFront.setPower(0.5);
            leftBack.setPower(0.5);
            rightFront.setPower(0.5);
            rightBack.setPower(0.5);
        }

    }
}
