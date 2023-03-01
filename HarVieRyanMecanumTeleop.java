package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

@TeleOp(name = "HarVieRyanMecanumTeleop (Blocks to Java)")
public class HarVieRyanMecanumTeleop extends LinearOpMode {

    private DcMotor frontLeft;
    private DcMotor backLeft;
    private DcMotor frontRight;
    private DcMotor backRight;
    private DcMotor leftArmMotor;
    private DcMotor rightArmMotor;
    private TouchSensor limitRetract;
    private Servo intakeServo;

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        double speed;
        float horizontal;
        float vertical;
        float pivot;

        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        leftArmMotor = hardwareMap.get(DcMotor.class, "leftArmMotor");
        rightArmMotor = hardwareMap.get(DcMotor.class, "rightArmMotor");
        limitRetract = hardwareMap.get(TouchSensor.class, "limitRetract");
        intakeServo = hardwareMap.get(Servo.class, "intakeServo");

        // Put initialization blocks here.
        speed = 0.7;
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();
        if (opModeIsActive()) {
            while (opModeIsActive()) {
                horizontal = gamepad1.left_stick_x;
                vertical = -gamepad1.left_stick_y;
                pivot = gamepad1.right_stick_x;
                frontRight.setPower((-pivot + (vertical - horizontal)) * 0.3);
                backRight.setPower((-pivot + vertical + horizontal) * 0.3);
                frontLeft.setPower((pivot + vertical + horizontal) * 0.3);
                backLeft.setPower((pivot + (vertical - horizontal)) * 0.3);
                if (gamepad2.dpad_up) {
                    leftArmMotor.setPower(0.5);
                    rightArmMotor.setPower(-0.5);
                } else if (gamepad2.dpad_down && !limitRetract.isPressed()) {
                    leftArmMotor.setPower(-0.5);
                    rightArmMotor.setPower(0.5);
                } else {
                    leftArmMotor.setPower(0);
                    rightArmMotor.setPower(0);
                }
                if (gamepad2.left_bumper) {
                    intakeServo.setPosition(2);
                } else if (gamepad2.right_bumper) {
                    intakeServo.setPosition(-2);
                }
                // Put run blocks here.
                // Put loop blocks here.
                telemetry.update();
            }
        }
    }
}