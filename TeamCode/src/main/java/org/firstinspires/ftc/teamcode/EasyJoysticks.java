package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by connorespenshade on 11/17/17.
 */

@TeleOp(name = "Easy Mecanum")
public class EasyJoysticks extends LinearOpMode {

    private DcMotor leftFront;
    private DcMotor leftBack;
    private DcMotor rightFront;
    private DcMotor rightBack;

    private Servo leftServo;
    private Servo rightServo;
    private DcMotor liftMotor;

    private double DEADZONE = 0.1;

    DigitalChannel bottomButton;  // Hardware Device Object
    DigitalChannel topButton;

    @Override
    public void runOpMode() throws InterruptedException {
//thhing
        leftFront = hardwareMap.dcMotor.get(UniversalConstants.LEFT1NAME);
        leftBack = hardwareMap.dcMotor.get(UniversalConstants.LEFT2NAME);
        rightFront = hardwareMap.dcMotor.get(UniversalConstants.RIGHT1NAME);
        rightBack = hardwareMap.dcMotor.get(UniversalConstants.RIGHT2NAME);

        leftServo = hardwareMap.servo.get("ls");
        rightServo = hardwareMap.servo.get("rs");
        liftMotor = hardwareMap.dcMotor.get("lift");

        leftServo.setDirection(Servo.Direction.FORWARD);

        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);

        bottomButton = hardwareMap.get(DigitalChannel.class, "bottom");
        topButton = hardwareMap.get(DigitalChannel.class, "top");

        // set the digital channel to input.
        bottomButton.setMode(DigitalChannel.Mode.INPUT);
        topButton.setMode(DigitalChannel.Mode.INPUT);

        waitForStart();

        while (opModeIsActive()) {

            //moveMecanum(gamepad1.right_stick_x, gamepad1.left_stick_y, gamepad1.left_stick_x);

            while (topButton.getState()) {
                liftMotor.setPower(0.1);
                idle();
            }

            double Ch3 = gamepad1.right_stick_x;
            double Ch1 = -gamepad1.left_stick_y;
            double Ch4 = gamepad1.left_stick_x;

            double FrontLeft = Ch3 + Ch1 + Ch4;
            double RearLeft = Ch3 + Ch1 - Ch4;
            double RearRight = Ch3 - Ch1 - Ch4;
            double FrontRight = Ch3 - Ch1 + Ch4;

            //ADD DEADZONE
            leftFront.setPower(java.lang.Math.abs(FrontLeft) > DEADZONE ? FrontLeft : 0);
            leftBack.setPower(java.lang.Math.abs(RearLeft) > DEADZONE ? RearLeft : 0);
            rightFront.setPower(java.lang.Math.abs(FrontRight) > DEADZONE ? FrontRight : 0);
            rightBack.setPower(java.lang.Math.abs(RearRight) > DEADZONE ? RearRight : 0);

            telemetry.addData("LF Power", leftFront.getPower());
            telemetry.addData("LB Power", leftBack.getPower());
            telemetry.addData("RF Power", rightFront.getPower());
            telemetry.addData("RB Power", rightBack.getPower());

            liftMotor.setPower(gamepad2.right_stick_y);

            leftServo.setPosition(gamepad2.left_stick_x * 0.3 + 0.5);
            rightServo.setPosition(-(gamepad2.left_stick_x * 0.3) + 0.5);

            telemetry.addData("Left Servo Actual Position", leftServo.getPosition());

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

