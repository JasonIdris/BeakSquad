package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.hardware.logitech.LogitechGamepadF310;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by connorespenshade on 10/12/17.
 */

@TeleOp(name = "Mecanum TeleOp")
public class MecanumTeleOp extends LinearOpMode {

    private DcMotor leftFront;
    private DcMotor leftBack;
    private DcMotor rightFront;
    private DcMotor rightBack;

    private double DEADZONE = 0.1;

    @Override
    public void runOpMode() throws InterruptedException {
//thhing
        leftFront = hardwareMap.dcMotor.get(UniversalConstants.LEFT1NAME);
        leftBack = hardwareMap.dcMotor.get(UniversalConstants.LEFT2NAME);
        rightFront = hardwareMap.dcMotor.get(UniversalConstants.RIGHT1NAME);
        rightBack = hardwareMap.dcMotor.get(UniversalConstants.RIGHT2NAME);

        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive()) {

            //moveMecanum(gamepad1.right_stick_x, gamepad1.left_stick_y, gamepad1.left_stick_x);

            double Ch1 = gamepad1.right_stick_x;
            double Ch3 = gamepad1.left_stick_y;
            double Ch4 = gamepad1.left_stick_x;

            double FrontLeft = Ch3 + Ch1 + Ch4;
            double RearLeft = Ch3 + Ch1 - Ch4;
            double FrontRight = Ch3 - Ch1 - Ch4;
            double RearRight = Ch3 - Ch1 + Ch4;

            //ADD DEADZONE
            leftFront.setPower(java.lang.Math.abs(FrontLeft) > DEADZONE ? FrontLeft : 0);
            leftBack.setPower(java.lang.Math.abs(RearLeft) > DEADZONE ? RearLeft : 0);
            rightFront.setPower(java.lang.Math.abs(FrontRight) > DEADZONE ? FrontRight : 0);
            rightBack.setPower(java.lang.Math.abs(RearRight) > DEADZONE ? RearRight : 0);

            telemetry.addData("LF Power", leftFront.getPower());
            telemetry.addData("LB Power", leftBack.getPower());
            telemetry.addData("RF Power", rightFront.getPower());
            telemetry.addData("RB Power", rightBack.getPower());

            idle();
        }
    }

    /*
    private void moveMecanum(float Ch1, float Ch3, float Ch4) {
        double FrontLeft = Ch3 + Ch1 + Ch4;
        double RearLeft = Ch3 + Ch1 - Ch4;
        double FrontRight = Ch3 - Ch1 - Ch4;
        double RearRight = Ch3 - Ch1 + Ch4;

        //ADD DEADZONE
        leftFront.setPower(java.lang.Math.abs(FrontLeft) > DEADZONE ? FrontLeft : 0);
        leftBack.setPower(java.lang.Math.abs(RearLeft) > DEADZONE ? RearLeft : 0);
        rightFront.setPower(java.lang.Math.abs(FrontRight) > DEADZONE ? FrontRight : 0);
        rightBack.setPower(java.lang.Math.abs(RearRight) > DEADZONE ? RearRight : 0);

        telemetry.addData("LF Power", leftFront.getPower());

        // moar commit
    }
*/

}
