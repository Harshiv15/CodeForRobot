package org.firstinspires.ftc.teamcode.Auton;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

public class PIDTutorial extends LinearOpMode {

    DcMotorEx FrontLeft;
    DcMotorEx FrontRight;
    DcMotorEx BackLeft;
    DcMotorEx BackRight;

    private BNO055IMU imu;

    //how far each wheel travels in one rotation (~11.8737360136", ~301.592894745mm)
    private final double distanceIN = Math.PI * (96/25.4);
    private final double distanceMM = distanceIN * 25.4;
    private final int reduction = 20;
    /*
    20:1 reduction specifics:
    * Actual ratio: 18:9:1
    * Free speed: 317 rpm
    * Stall torque: 1.98 Nm
    * Weight: 405g
    */

    double integralSumA = 0;
    double integralSumP = 0;
    double Kp = 27.5; //27.5252023490681
    double Ki = 50; //49.9465118211831
    double Kd = -0.05; //-0.0492015005519039

    //FILTERED COEFFICIENT (N): 559.438269977785

    //double Kf = 0; DOES NOT WORK FOR POS CONTROL

    ElapsedTime timer = new ElapsedTime();
    private double lastError = 0;

    @Override
    public void runOpMode() throws InterruptedException{
        FrontLeft = hardwareMap.get(DcMotorEx.class,"FrontLeft");
        FrontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FrontRight = hardwareMap.get(DcMotorEx.class,"FrontRight");
        FrontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        BackLeft = hardwareMap.get(DcMotorEx.class,"BackLeft");
        BackLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BackRight = hardwareMap.get(DcMotorEx.class,"BackRight");
        BackRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        BackLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        FrontLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.mode = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        imu.initialize(parameters);

        waitForStart();
        double referenceAngle = Math.toRadians(90);
        /*
        MECANUM MATRIX FOR REFERENCE (Z = HEADING, Y = VERT, X = HORIZ):
              Z   Y   X
        FL  [ +   +   + ]
        BL  [ +   +   - ]
        FR  [ -   +   - ]
        BR  [ -   +   + ]
        */
        /*
        MOVEMENT PATTERN MATRICES:
            [ FR, BR, FL, BL ]

        F   [ +,  +,  +,  +  ]
        B   [ -,  -,  -,  -  ]
        SL  [ +,  -,  -,  +  ]
        SR  [ -,  +,  +,  -  ]
        PL  [ +,  -,  +,  -  ]
        PR  [ -,  +,  -,  +  ]
        */
        while(opModeIsActive()){
            double power = PIDControlA(referenceAngle);
            FrontLeft.setPower(power);
            BackLeft.setPower(power);
            FrontRight.setPower(-power);
            BackRight.setPower(-power);
        }
    }

    public double PIDControlA(double reference) //angular movement (pivot)
    {
        double error = angleWrap(reference - imu.getAngularOrientation().firstAngle);
        integralSumA += error * timer.seconds();
        double derivative = (error - lastError) / timer.seconds();
        lastError = error;

        timer.reset();

        double output = (error * Kp) + (derivative * Kd) + (integralSumA * Ki);
        return output;
    }

    public double PIDControlP(double reference, double state) //normal movement (f, b, sl, sr) - to be fixed later
    {
        double error = reference - state;
        integralSumP += error * timer.seconds();
        double derivative = (error - lastError) / timer.seconds();
        lastError = error;

        timer.reset();

        double output = (error * Kp) + (derivative * Kd) + (integralSumP * Ki);
        return output;
    }

    public double angleWrap(double radians){
        while(radians > Math.PI){
            radians -= Math.PI * 2;
        }
        while(radians > Math.PI){
            radians += Math.PI * 2;
        }
        return radians;
    }

}
