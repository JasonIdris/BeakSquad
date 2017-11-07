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

    private double minServoPos = 0.2;
    private double maxServoPos = 0.8;

    private Servo leftServo;
    private Servo rightServo;
    private DcMotor liftMotor;

    @Override
    public void runOpMode() throws InterruptedException {

        initMotors();

        leftServo.setPosition(minServoPos);
        rightServo.setPosition(maxServoPos);

        liftMotor.setPower(-0.5);
        wait(2000);
        liftMotor.setPower(0);

        waitForStart();

        while (opModeIsActive()) {




            idle();
        }
    }

    private void initMotors() {

        leftServo = hardwareMap.servo.get("ls");
        rightServo = hardwareMap.servo.get("rs");
        liftMotor = hardwareMap.dcMotor.get("lift");

    }
}
