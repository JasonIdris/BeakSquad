package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.hardware.logitech.LogitechGamepadF310;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;



/**
 * Created by albusdumbledore on 11/6/17.
 */

public class Chassis {

    private DcMotor leftFront;
    private DcMotor leftBack;
    private DcMotor rightFront;
    private DcMotor rightBack;

    private double DEADZONE = 0.05;

    public Chassis(HardwareMap hardwareMap) {
        leftFront = hardwareMap.dcMotor.get("lf");
        leftBack = hardwareMap.dcMotor.get("lb");
        rightFront = hardwareMap.dcMotor.get("rf");
        rightBack = hardwareMap.dcMotor.get("rb");

        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void drive(double throttle, double turn, double strafe) {
        double frontLeft = turn + throttle + strafe;
        double rearLeft = turn + throttle - strafe;
        double frontRight = turn - throttle - strafe;
        double rearRight = turn - throttle + strafe;

        //ADD DEADZONE
        leftFront.setPower(java.lang.Math.abs(frontLeft) > DEADZONE ? frontLeft : 0);
        leftBack.setPower(java.lang.Math.abs(rearLeft) > DEADZONE ? rearLeft : 0);
        rightFront.setPower(java.lang.Math.abs(frontRight) > DEADZONE ? frontRight : 0);
        rightBack.setPower(java.lang.Math.abs(rearRight) > DEADZONE ? rearRight : 0);
/*
        telemetry.addData("LF Power", leftFront.getPower());
        telemetry.addData("LB Power", leftBack.getPower());
        telemetry.addData("RF Power", rightFront.getPower());
        telemetry.addData("RB Power", rightBack.getPower());
        */
    }
}