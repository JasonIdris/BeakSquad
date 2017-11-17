package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by connorespenshade on 11/4/17.
 */

@TeleOp(name = "LiftTest")
public class LiftMechanism extends LinearOpMode {

    //TWO SERVOS (GRABBER)
    //MAKE ONE SERVO REVERSE
    //LIFT MOTOR (DC MOTOR)
    //GAMEPAD 2

    private double minServoPos = 0.5;
    private double maxServoPos = -0.5;

    private Servo leftServo;
    private Servo rightServo;
    private DcMotor liftMotor;

    @Override
    public void runOpMode() throws InterruptedException {

        initMotors();

        //leftServo.setPosition(minServoPos);
        //rightServo.setPosition(maxServoPos);
/*
        liftMotor.setPower(-0.5);
        wait(2000);
        liftMotor.setPower(0);
*/
        waitForStart();

        while (opModeIsActive()) {

            if (gamepad2.left_bumper) {
                if (leftServo.getPosition() == minServoPos) {
                    leftServo.setPosition(maxServoPos);
                } else {
                    leftServo.setPosition(minServoPos);
                }
            }

            if (gamepad2.right_bumper) {
                if (rightServo.getPosition() == maxServoPos) {
                    rightServo.setPosition(minServoPos);
                } else {
                    rightServo.setPosition(maxServoPos);
                }
            }

            if (gamepad2.a) {
                if (rightServo.getPosition() == maxServoPos) {
                    rightServo.setPosition(minServoPos);
                } else {
                    rightServo.setPosition(maxServoPos);
                }

                if (leftServo.getPosition() == minServoPos) {
                    leftServo.setPosition(maxServoPos);
                } else {
                    leftServo.setPosition(minServoPos);
                }
            }

            if (gamepad2.right_trigger > 0.1) {
                liftMotor.setPower(gamepad2.right_trigger * .3);
            } else if (gamepad2.left_trigger > 0.1) {
                liftMotor.setPower(-gamepad2.left_trigger * .3);
            } else {
                liftMotor.setPower(0);
            }


            idle();
        }
    }

    private void initMotors() {

        leftServo = hardwareMap.servo.get("ls");
        rightServo = hardwareMap.servo.get("rs");
        liftMotor = hardwareMap.dcMotor.get("lift");

    }
}
