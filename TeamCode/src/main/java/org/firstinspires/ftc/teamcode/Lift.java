package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by albusdumbledore on 11/6/17.
 */

public class Lift extends MasterTeleOp {
    private static Lift instance = new Lift();

    public static Lift getInstance() {
        return instance;
    }
    private double minServoPos = 0.2;
    private double maxServoPos = 0.8;

    private Servo leftServo;
    private Servo rightServo;
    private DcMotor liftMotor;

    private Lift() {
        leftServo = hardwareMap.servo.get("ls");
        rightServo = hardwareMap.servo.get("rs");
        liftMotor = hardwareMap.dcMotor.get("lift");
    }

    public void controlLift(Gamepad gamepad) {
        liftMotor.setPower(gamepad2.right_trigger);

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


        liftMotor.setPower(gamepad2.right_trigger);
        liftMotor.setPower(-gamepad2.left_trigger);
    }
}
