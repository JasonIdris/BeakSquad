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
    private ColorType activatedColor;

    private Double[] reds = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
    private Double[] blues = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};

    @Override
    public void runOpMode() throws InterruptedException {
        colorSensor = hardwareMap.colorSensor.get("color");

        waitForStart();

        while (opModeIsActive()) {
            Double red_sum = 0;
            Double blue_sum = 0;
            for (int i = reds.length-1; i > 0; i--) {
                reds[i] = reds[i - 1];
                red_sum += reds[i];
                blues[i] = blues[i - 1];
                blue_sum += blues[i];
            }
            
            telemetry.addData("Red", colorSensor.red());
            telemetry.addData("Green", colorSensor.green());
            telemetry.addData("Blue", colorSensor.blue());
            
            reds[0] = (Double) colorSensor.red();
            blues[0] = ((Double) colorSensor.blue());

            red_sum += reds[0];
            blue_sum += blues[0];
            Double red_avg = red_sum/reds.length;
            Double blue_avg = blue_sum/blues.length;
            telemetry.addData("RED_AVG", red_avg);
            telemetry.addData("BLUE_AVG", blue_avg);
            
            private Double identifier = (red_avg-blue_avg) / red_avg;
            
            if (identifier >= 0.4) {
                //Red
                activatedColor = ColorType.Red;
            } else if (identifier <= 0.15) {
                //Blue
                activatedColor = ColorType.Blue;
            } else {
                activatedColor = ColorType.Unknown;
            }
            telemetry.addData("Activated Color", activatedColor);
            telemetry.update();
            
            idle();
        }
    }
}

enum ColorType {
    Red, Blue, Unknown
}