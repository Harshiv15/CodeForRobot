package org.firstinspires.ftc.teamcode.Auton;

//import AprilTagDetection;
//import AprilTagIdCode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import java.util.ArrayList;
//import org.openftc.apriltag.AprilTagDetection;

@Autonomous(name = "HarVieRyanMecanumAuton (Blocks to Java)")
public class HarVieRyanMecanumAuton extends LinearOpMode {

    private DcMotor frontLeft;
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor frontRight;

    double finalID;
    double moveSpeed;
    double armSpeed;
    long armDuration;

    /**
     * Describe this function...
     */
    /*private void signalPark() {
        telemetry.addData("Zone", finalID);
        if (finalID == 1) {
            Forward(1000);
            sleep(50);
            StrafeLeft(1350);
        } else if (finalID == 2) {
            Forward(1000);
        } else if (finalID == 3) {
            Forward(1000);
            sleep(50);
            StrafeRight(1350);
        }
        requestOpModeStop();
    }*/

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        //AprilTagIdCode.BlocksContext myDetector;
        boolean detected_;
        ArrayList allDetections;
        int numberOfDetections;
        //AprilTagDetection singleDetection;
        double detectedID;

        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");

        // Put initialization blocks here.
        moveSpeed = 0.5;
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        // Create a pipeline/webcam object for AprilTag ID code detection. Edit configured webcam name as needed.  Use this myBlock in INIT section of OpMode, before startAprilTagDetector.
        //myDetector = AprilTagIdCode.createAprilTagDetector(hardwareMap, "Webcam 1");
        // Begin operating camera/stream/pipeline for AprilTag
        // detection. Must specify a resolution supported by the camera;
        // edit these default values as desired. Use this myBlock
        // in INIT section of OpMode, after createAprilTagDetector.
        //AprilTagIdCode.startAprilTagDetector(myDetector, 640, 480);
        waitForStart();
        if (opModeIsActive()) {
            // Put run blocks here.
            //detected_ = false;
            while (/*!detected_ && */opModeIsActive()) {
                // Put loop blocks here.
                // Provide the data from any and all detetected AprilTags. Use this myBlock anywhere in the OpMode, after startAprilTagDetector.
                //allDetections = AprilTagIdCode.getAllDetections(myDetector);
                // Provide the number of detections in the current batch. Use this myBlock anywhere in the OpMode, after getAllDetections.
                /*numberOfDetections = AprilTagIdCode.getHowManyDetections(allDetections);
                if (numberOfDetections == 0) {
                    telemetry.addData("No detections", "KEEP LOOKING");
                } else if (numberOfDetections >= 2) {
                    telemetry.addData("Multiple detections", "WAIT FOR ONE ONLY");
                } else {
                    // Provide the data from only the first detection in the current batch.  This myBlock is helpful when you know there's only one AprilTag detected.  Use it anywhere, after getAllDetections. This will crash if the input detections list is empty.
                    singleDetection = AprilTagIdCode.getOneDetection(allDetections, 0);
                    // Provide the AprilTag ID code from the designated detection. Use this myBlock anywhere, after getOneDetection.
                    detectedID = AprilTagIdCode.getID(singleDetection);
                    telemetry.addData("key", detectedID);
                    detected_ = true;
                    finalID = detectedID;*/
                }
                telemetry.update();
            }
        while (opModeIsActive()) {
            Forward(2000);
            sleep(1000);
            PivotLeft(100);
            sleep(10000);
            telemetry.update();
        }
    }
    /**
     * Describe this function...
     */
    private void Forward(int ms) {
        backLeft.setPower(moveSpeed);
        backRight.setPower(moveSpeed);
        frontLeft.setPower(moveSpeed);
        frontRight.setPower(moveSpeed);
        sleep(ms);
        backLeft.setPower(0);
        backRight.setPower(0);
        frontLeft.setPower(0);
        frontRight.setPower(0);
    }

    /**
     * Describe this function...
     */
    private void Backward(long ms) {
        backLeft.setPower(-moveSpeed);
        backRight.setPower(-moveSpeed);
        frontLeft.setPower(-moveSpeed);
        frontRight.setPower(-moveSpeed);
        sleep(ms);
        backLeft.setPower(0);
        backRight.setPower(0);
        frontLeft.setPower(0);
        frontRight.setPower(0);
    }

    /**
     * Describe this function...
     */
    private void PivotRight(long ms) {
        backLeft.setPower(-moveSpeed);
        backRight.setPower(moveSpeed);
        frontLeft.setPower(-moveSpeed);
        frontRight.setPower(moveSpeed);
        sleep(ms);
        backLeft.setPower(0);
        backRight.setPower(0);
        frontLeft.setPower(0);
        frontRight.setPower(0);
    }

    /**
     * Describe this function...
     */
    private void PivotLeft(int ms) {
        backLeft.setPower(moveSpeed);
        backRight.setPower(-moveSpeed);
        frontLeft.setPower(moveSpeed);
        frontRight.setPower(-moveSpeed);
        sleep(ms);
        backLeft.setPower(0);
        backRight.setPower(0);
        frontLeft.setPower(0);
        frontRight.setPower(0);
    }

    /**
     * Describe this function...
     */
    private void StrafeRight(int ms) {
        backLeft.setPower(-moveSpeed);
        backRight.setPower(moveSpeed);
        frontLeft.setPower(moveSpeed);
        frontRight.setPower(-moveSpeed);
        sleep(ms);
        backLeft.setPower(0);
        backRight.setPower(0);
        frontLeft.setPower(0);
        frontRight.setPower(0);
    }

    /**
     * Describe this function...
     */
    private void StrafeLeft(int ms) {
        backLeft.setPower(moveSpeed);
        backRight.setPower(-moveSpeed);
        frontLeft.setPower(-moveSpeed);
        frontRight.setPower(moveSpeed);
        sleep(ms);
        backLeft.setPower(0);
        backRight.setPower(0);
        frontLeft.setPower(0);
        frontRight.setPower(0);
    }

    /**
     * Describe this function...
     */
    private void ArmDown() {
        backLeft.setPower(-armSpeed);
        backRight.setPower(armSpeed);
        sleep(armDuration);
        backLeft.setPower(0);
        backRight.setPower(0);
    }

    /**
     * Describe this function...
     */
    private void ArmUp() {
        backLeft.setPower(armSpeed);
        backRight.setPower(-armSpeed);
        sleep(armDuration);
        backLeft.setPower(0);
        backRight.setPower(0);
    }
}