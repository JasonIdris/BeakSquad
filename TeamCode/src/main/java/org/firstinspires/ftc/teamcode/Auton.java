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

        //SPIN

        //leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        //rightFront.setDirection(DcMotorSimple.Direction.REVERSE);


        //FOWARD
        //leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        //rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
/*
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
*/
        waitForStart();

        //HELLO

        //code to run while waiting
/*
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
*/
        leftFront.setPower(1);
        leftBack.setPower(1);
        rightFront.setPower(1);
        rightBack.setPower(1);

        telemetry.addData("LF", leftFront.getCurrentPosition());
        telemetry.addData("LB", leftBack.getCurrentPosition());
        telemetry.addData("RF", rightFront.getCurrentPosition());
        telemetry.addData("RB", rightBack.getCurrentPosition());

        telemetry.update();
        try {
            Thread.sleep(2000);

            leftFront.setPower(0);
            leftBack.setPower(0);
            rightFront.setPower(0);
            rightBack.setPower(0);

            telemetry.addData("LF", leftFront.getCurrentPosition());
            telemetry.addData("LB", leftBack.getCurrentPosition());
            telemetry.addData("RF", rightFront.getCurrentPosition());
            telemetry.addData("RB", rightBack.getCurrentPosition());

            telemetry.update();

            Thread.sleep(10000);
        }catch(InterruptedException e){
            System.out.println("got interrupted!");
        }

    }
}
