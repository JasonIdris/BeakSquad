package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
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

    private Boolean top = false;
    private Boolean bottom = false;

    DigitalChannel bottomButton;  // Hardware Device Object
    DigitalChannel topButton; //Hardware Device Object

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

            // get a reference to our digitalTouch object.
            bottomButton = hardwareMap.get(DigitalChannel.class, "bottom");
            topButton = hardwareMap.get(DigitalChannel.class, "top");

            // set the digital channel to input.
            bottomButton.setMode(DigitalChannel.Mode.INPUT);
            topButton.setMode(DigitalChannel.Mode.INPUT);

            // wait for the start button to be pressed.
            waitForStart();

            // while the op mode is active, loop and read the light levels.
            // Note we use opModeIsActive() as our loop condition because it is an interruptible method.
            while (opModeIsActive()) {

                // send the info back to driver station using telemetry function.
                // if the digital channel returns true it's HIGH and the button is unpressed.

                while (topButton.getState()) {
                    liftMotor.setPower(0.1);
                    idle();
                }

                if (bottomButton.getState() == true) {
                    telemetry.addData("Back Touch", "Is Not Pressed");
                } else {
                    telemetry.addData("Back Touch", "Is Pressed");
                    liftMotor.setPower(0);
                }

                if (topButton.getState() == true) {
                    telemetry.addData("Front Touch", "Is Not Pressed");
                } else {
                    telemetry.addData("Front Touch", "Is Pressed");
                    liftMotor.setPower(0);
                }

                telemetry.update();

                idle();
            }
        }
    }
    private void initMotors() {

        leftServo = hardwareMap.servo.get("ls");
        rightServo = hardwareMap.servo.get("rs");
        liftMotor = hardwareMap.dcMotor.get("lift");

    }
}
