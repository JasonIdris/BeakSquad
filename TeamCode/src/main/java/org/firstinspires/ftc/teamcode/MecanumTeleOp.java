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

    private int DEADZONE = 20;

    @Override
    public void runOpMode() throws InterruptedException {

        init();

        initMotors();

        waitForStart();

        while (opModeIsActive()) {
/*
            //RIGHT STICK TURNS
            if (gamepad1.right_stick_x > 0) {
                rightFront.setPower(gamepad1.right_stick_x);
                rightBack.setPower(gamepad1.right_stick_x);
                leftFront.setPower(-gamepad1.right_stick_x);
                leftBack.setPower(-gamepad1.right_stick_x);
            } else if (gamepad2.right_stick_x < 0) {
                rightFront.setPower(-gamepad1.right_stick_x);
                rightBack.setPower(-gamepad1.right_stick_x);
                leftFront.setPower(gamepad1.right_stick_x);
                leftBack.setPower(gamepad1.right_stick_x);
            }
*/
            moveMecanum(gamepad1.right_stick_x, gamepad1.left_stick_y, gamepad1.left_stick_x);

            idle();
        }
    }

    private void initMotors() {
        leftFront = hardwareMap.dcMotor.get("lf");
        leftBack = hardwareMap.dcMotor.get("lb");
        rightFront = hardwareMap.dcMotor.get("rf");
        rightBack = hardwareMap.dcMotor.get("rb");
    }

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
    }

}
