package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import java.util.Arrays;


/**
 * Created by eharwood on 10/2/17.
 *
 * This code was adopted by referencing code from:
 * https://github.com/ethan-schaffer/Sample-FTC-Code/blob/master/ftc_app-master/ftc_app-master/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/MecanumSamples/MecanumTeleOp.java
 *
 *
 */

@TeleOp(name="MecanumTeleOP", group="CS")
//@Disabled


public class GhastineMecanumTeleOp extends OpMode {

    private static final double TRIGGERTHRESHOLD = .2;
    private static final double ACCEPTINPUTTHRESHOLD =  .15;
    private static final double SCALEDPOWER = 1;  // The emphasis is on the current controller reading (vs. current motor power) on the drive train

    private static DcMotor leftFrontWheel, leftBackWheel, rightFrontWheel, rightBackWheel;

    @Override

    public void init () {
        leftFrontWheel = hardwareMap.dcMotor.get(UniversalConstants.LEFT1NAME);
        leftBackWheel = hardwareMap.dcMotor.get(UniversalConstants.LEFT2NAME);
        rightFrontWheel = hardwareMap.dcMotor.get(UniversalConstants.RIGHT1NAME);
        rightBackWheel = hardwareMap.dcMotor.get(UniversalConstants.RIGHT2NAME);
        leftFrontWheel.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFrontWheel.setDirection(DcMotorSimple.Direction.REVERSE);
        //double volts = hardwareMap.voltageSensor.get("MOtor Controller 1").getVoltage();

    }

    @Override
    public void loop() {
        double inputY = Math.abs(gamepad1.left_stick_y) > ACCEPTINPUTTHRESHOLD ? gamepad1.left_stick_y : 0;
        double inputX = Math.abs(gamepad1.left_stick_x) > ACCEPTINPUTTHRESHOLD ? -gamepad1.left_stick_x : 0;
        double inputC = Math.abs(gamepad1.right_stick_x)> ACCEPTINPUTTHRESHOLD ? -gamepad1.right_stick_x: 0;

        arcadeMecanum(inputY, inputX, inputC, leftFrontWheel, rightFrontWheel, leftBackWheel, rightBackWheel);
    }

    // y - forwards
    // x - side
    // c - rotation
    public static void arcadeMecanum(double y, double x, double c, DcMotor leftFront, DcMotor rightFront, DcMotor leftBack, DcMotor rightBack) {
        double leftFrontVal = y + x + c;
        double rightFrontVal = y - x - c;
        double leftBackVal = y - x + c;
        double rightBackVal = y + x - c;

        //Move range to between 0 and +1, if not already
        double[] wheelPowers = {rightFrontVal, leftFrontVal, leftBackVal, rightBackVal};
        Arrays.sort(wheelPowers);
        if (wheelPowers[3] > 1) {
            leftFrontVal /= wheelPowers[3];
            rightFrontVal /= wheelPowers[3];
            leftBackVal /= wheelPowers[3];
            rightBackVal /= wheelPowers[3];
        }
        double scaledPower = SCALEDPOWER;

        leftFront.setPower(leftFrontVal*scaledPower+leftFront.getPower()*(1-scaledPower));
        rightFront.setPower(rightFrontVal*scaledPower+rightFront.getPower()*(1-scaledPower));
        leftBack.setPower(leftBackVal*scaledPower+leftBack.getPower()*(1-scaledPower));
        rightBack.setPower(rightBackVal*scaledPower+rightBack.getPower()*(1-scaledPower));
    }
}