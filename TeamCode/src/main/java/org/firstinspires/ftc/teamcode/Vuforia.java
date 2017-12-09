package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuMarkInstanceId;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by connorespenshade on 12/7/17.
 */

public class Vuforia {

    //VUFORIA
    private OpenGLMatrix lastLocation = null;
    private VuforiaLocalizer vuforia;
    private RelicRecoveryVuMark relicTemplate;
    private VuforiaTrackables relicTrackablesMaster;
    private VuforiaTrackable template;

    private static Vuforia instance = null;
    protected Vuforia() {
        // Exists only to defeat instantiation.
    }

    public static Vuforia getInstance(HardwareMap hardware) {
        if(instance == null) {
            instance = new Vuforia(hardware);
        }
        return instance;
    }

    public Vuforia(HardwareMap hardwareMap) {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = "AR3UNn//////AAAAGWw+kvrhWEQ/n8JGCEaAl3lHjtRjVuwoY6pyIsg6Fc1fYaZbyySiQYqRxF29tMJufsu1X91zq+pfrk7qUb49WyQcME7VPLelNQj4I/8QV4nYk/8MqwfVFKqidKnYX2XGxyeLnH2wbOK04Ot9lpDYhBgjs7crF8Lbw/LEv21h54owkSRCsT4SuH0EKIztAlQfhUkwEtZyJ7QGzwtBJ3du06z4MMZcjPX56vPHf6ov4q+4yz2Z3i9RtDGAmIKxl+b31KX50XsZphctCQs5ig16Ho3Anux7E4dQ3/cq2dYGTwzUUiVl4sduiDjrU7O7rlu46X/F4CaTn6Iw7/PKBRe+/jC5iXwcin4U8cDWWxcsVagJ";

        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.FRONT;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

        relicTrackablesMaster = relicTrackables;
        template = relicTemplate;

        relicTrackables.activate();
    }

    private void track() {
        //RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
        relicTrackablesMaster.activate();
        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(template);
        if (vuMark != RelicRecoveryVuMark.UNKNOWN) {

                /* Found an instance of the template. In the actual game, you will probably
                 * loop until this condition occurs, then move on to act accordingly depending
                 * on which VuMark was visible. */

                /* For fun, we also exhibit the navigational pose. In the Relic Recovery game,
                 * it is perhaps unlikely that you will actually need to act on this pose information, but
                 * we illustrate it nevertheless, for completeness. */
            OpenGLMatrix pose = ((VuforiaTrackableDefaultListener)template.getListener()).getPose();

                /* We further illustrate how to decompose the pose into useful rotational and
                 * translational components */
            if (pose != null) {
                VectorF trans = pose.getTranslation();
                Orientation rot = Orientation.getOrientation(pose, AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);

                // Extract the X, Y, and Z components of the offset of the target relative to the robot
                double tX = trans.get(0);
                double tY = trans.get(1);
                double tZ = trans.get(2);

                tX = Math.round(tX * 100);
                tX = tX/100;

                tY = Math.round(tY * 100);
                tY = tY/100;

                tZ = Math.round(tZ * 100);
                tZ = tZ/100;

                // Extract the rotational components of the target relative to the robot
                double rX = rot.firstAngle;
                double rY = rot.secondAngle;
                double rZ = rot.thirdAngle;

                rX = Math.round(rX * 100);
                rX = rX/100;

                rY = Math.round(rY * 100);
                rY = rY/100;

                rZ = Math.round(rZ * 100);
                rZ = rZ/100;

                //telemetry.addData("Rotation", (Double.toString(rX)) + ", " + (Double.toString(rY)) + ", " + (Double.toString(rZ)));
            }
        }
        else {
            //telemetry.addData("VuMark", "not visible");
        }
    }

}
