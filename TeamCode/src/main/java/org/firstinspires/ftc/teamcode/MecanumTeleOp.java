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

    @Override
    public void runOpMode() throws InterruptedException {

        init();

        initMotors();

        waitForStart();

        while (opModeIsActive()) {

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

            idle();
        }
    }

    private void initMotors() {
        leftFront = hardwareMap.dcMotor.get("leftFront");
        leftBack = hardwareMap.dcMotor.get("leftBack");
        rightFront = hardwareMap.dcMotor.get("rightFront");
        rightBack = hardwareMap.dcMotor.get("rightBack");

        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
    }
}
