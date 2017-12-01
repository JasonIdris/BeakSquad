package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.hardware.logitech.LogitechGamepadF310;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;



/**
 * Created by albusdumbledore on 11/6/17.
 */

public class Chassis extends TestAuton {
    private static Chassis instance = new Chassis();

    public static Chassis getInstance() {
        return instance;
    }

    private DcMotor leftFront;
    private DcMotor leftBack;
    private DcMotor rightFront;
    private DcMotor rightBack;

    private double DEADZONE = 0.05;

    private Chassis() {
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

    public void drive(float Ch1, float Ch3, float Ch4) {
        double frontLeft = Ch3 + Ch1 + Ch4;
        double rearLeft = Ch3 + Ch1 - Ch4;
        double frontRight = Ch3 - Ch1 - Ch4;
        double rearRight = Ch3 - Ch1 + Ch4;

        //ADD DEADZONE
        leftFront.setPower(java.lang.Math.abs(frontLeft) > DEADZONE ? frontLeft : 0);
        leftBack.setPower(java.lang.Math.abs(rearLeft) > DEADZONE ? rearLeft : 0);
        rightFront.setPower(java.lang.Math.abs(frontRight) > DEADZONE ? frontRight : 0);
        rightBack.setPower(java.lang.Math.abs(rearRight) > DEADZONE ? rearRight : 0);

        telemetry.addData("LF Power", leftFront.getPower());
        telemetry.addData("LB Power", leftBack.getPower());
        telemetry.addData("RF Power", rightFront.getPower());
        telemetry.addData("RB Power", rightBack.getPower());
    }
}