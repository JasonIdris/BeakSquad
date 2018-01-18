package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by connorespenshade on 1/18/18.
 */

@Autonomous(name = "Jewel Red Autonomous")
public class JewelRed extends LinearOpMode {

    private ColorSensor colorSensor;
    private Servo armServo;

    @Override
    public void runOpMode() throws InterruptedException {

        colorSensor = hardwareMap.colorSensor.get("color");

        waitForStart();

        while (opModeIsActive()) {

            telemetry.addData("Red", colorSensor.red());
            telemetry.addData("Green", colorSensor.green());
            telemetry.addData("Blue", colorSensor.blue());
            telemetry.update();

            if (((colorSensor.red()-colorSensor.blue()) / colorSensor.red()) >= 0.4) {
                //Red

                telemetry.addData("Activated Color", "Red");
            } else if (((colorSensor.red()-colorSensor.blue()) / colorSensor.red()) <= 0.15) {
                //Blue

                telemetry.addData("Activated Color", "Blue");
            } else {
                telemetry.addData("Activated Color", "None");
            }

        }
    }
}
