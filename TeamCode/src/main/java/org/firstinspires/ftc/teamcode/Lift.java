package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import junit.framework.Test;

/**
 * Created by albusdumbledore on 11/6/17.
 */


public class Lift {
    private DcMotor liftMotor;
    private Servo leftArm;
    private Servo rightArm;

    private double minServoPos = 0.2;
    private double maxServoPos = 0.8;

    private DigitalChannel sensor;

    private static Lift instance = null;
    protected Lift() {
        // Exists only to defeat instantiation.
    }

    public static Lift getInstance(HardwareMap hardware) {
        if(instance == null) {
            instance = new Lift(hardware);
        }
        return instance;
    }

    public Lift(HardwareMap hardwareMap) {
        leftArm = hardwareMap.servo.get("ls");
        rightArm = hardwareMap.servo.get("rs");
        liftMotor = hardwareMap.dcMotor.get("lift");
        sensor = hardwareMap.digitalChannel.get("sensor");
    }

    public void controlLift(Gamepad gamepad2) {
        //liftMotor.setPower(gamepad2.right_trigger);

        liftMotor.setPower(gamepad2.right_stick_y);

        leftArm.setPosition(gamepad2.left_stick_x * 0.3 + 0.5);
        rightArm.setPosition(-(gamepad2.left_stick_x * 0.3) + 0.5);
    }

    public void initLift() {

        while (sensor.getState()) {
            liftMotor.setPower(-0.5);
        }

        //liftMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
}
