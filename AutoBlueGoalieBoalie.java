package org.firstinspires.ftc.teamcode.AutoCodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "AutoBlueGoalieBoalie", group = "Auto")
public class AutoBlueGoalieBoalie extends LinearOpMode {
    private Servo myServo;
    private double servoPosition = 0.5;
    private final double step = 0.075;

    @Override
    public void runOpMode() throws InterruptedException {

        // Get motors (use the exact names from your DS configuration)
        DcMotor leftFront  = hardwareMap.dcMotor.get("frontLeft");
        DcMotor rightFront = hardwareMap.dcMotor.get("frontRight");
        DcMotor leftRear   = hardwareMap.dcMotor.get("backLeft");
        DcMotor rightRear  = hardwareMap.dcMotor.get("backRight");

        DcMotor leftFW     = hardwareMap.dcMotor.get("fwLeft");
        DcMotor rightFW    = hardwareMap.dcMotor.get("fwRight");

        DcMotor intake     = hardwareMap.dcMotor.get("intake");

        myServo = hardwareMap.get(Servo.class, "servo");
        myServo.setPosition(servoPosition);


        // Reverse one side so the robot drives straight
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        rightRear.setDirection(DcMotor.Direction.REVERSE);

        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        //Start

        // --- MOVE BACKWARD ---
        leftFront.setPower(-0.8);
        rightFront.setPower(-0.8);
        leftRear.setPower(-0.8);
        rightRear.setPower(-0.8);

        sleep(1300);

        // --- STOP ---
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftRear.setPower(0);
        rightRear.setPower(0);

        rightFW.setDirection(DcMotor.Direction.REVERSE);


        //Launcher speed
        leftFW.setPower(-.85);
        rightFW.setPower(-.85);

        sleep(500);

        //Fire ball
        servoPosition -= step;
        servoPosition = Math.max(servoPosition, 0.0); // clamp
        myServo.setPosition(servoPosition);
        sleep(550); // debounce
        servoPosition += step;
        servoPosition = Math.min(servoPosition, 1.0); // clamp
        myServo.setPosition(servoPosition);
        sleep(550); // debounce

        intake.setPower(-0.7);

        sleep(500);

        intake.setPower(0);

        sleep(50);

        servoPosition -= step;
        servoPosition = Math.max(servoPosition, 0.0); // clamp
        myServo.setPosition(servoPosition);
        sleep(550); // debounce
        servoPosition += step;
        servoPosition = Math.min(servoPosition, 1.0); // clamp
        myServo.setPosition(servoPosition);
        sleep(550); // debounce

        intake.setPower(-0.7);

        sleep(500);

        intake.setPower(0);

        sleep(50);


        servoPosition -= step;
        servoPosition = Math.max(servoPosition, 0.0); // clamp
        myServo.setPosition(servoPosition);
        sleep(550); // debounce
        servoPosition += step;
        servoPosition = Math.min(servoPosition, 1.0); // clamp
        myServo.setPosition(servoPosition);
        sleep(550); // debounce

        sleep(1000);

        telemetry.addData("Servo Position", servoPosition);
        telemetry.update();

        //Stop launcher
        leftFW.setPower(0);
        rightFW.setPower(0);
        sleep(100);


        //Move back towards H.P. and Collect
        leftFront.setPower(-0.8);
        rightFront.setPower(-0.8);
        leftRear.setPower(-0.8);
        rightRear.setPower(-0.8);

        sleep(1512);

        //Stop for H.P
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftRear.setPower(0);
        rightRear.setPower(0);

        intake.setPower(-0.7);


        sleep(3024);

        intake.setPower(0);

        sleep(50);


        //Move forward towards shooting position
        leftFront.setPower(0.8);
        rightFront.setPower(0.8);
        leftRear.setPower(0.8);
        rightRear.setPower(0.8);

        sleep(1512);

        //STOP!!!
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftRear.setPower(0);
        rightRear.setPower(0);

        sleep(369);

        //rotate towards shooting position
        leftFront.setPower(-0.52);

        sleep(150);

        leftFront.setPower(0);


        //Start spinning launchers
        leftFW.setPower(.85);
        rightFW.setPower(.85);

        sleep(500);

        //Fire ball
        servoPosition += step;
        servoPosition = Math.min(servoPosition, 1.0); // clamp
        myServo.setPosition(servoPosition);
        sleep(500); // debounce
        servoPosition -= step;
        servoPosition = Math.max(servoPosition, 0.0); // clamp
        myServo.setPosition(servoPosition);
        sleep(500); // debounce


        intake.setPower(-0.7);

        sleep(700);

        intake.setPower(0);

        sleep(50);


        servoPosition += step;
        servoPosition = Math.min(servoPosition, 1.0); // clamp
        myServo.setPosition(servoPosition);
        sleep(500); // debounce
        servoPosition -= step;
        servoPosition = Math.max(servoPosition, 0.0); // clamp
        myServo.setPosition(servoPosition);
        sleep(500); // debounce


        intake.setPower(-0.7);

        sleep(700);

        intake.setPower(0);

        sleep(50);


        servoPosition += step;
        servoPosition = Math.min(servoPosition, 1.0); // clamp
        myServo.setPosition(servoPosition);
        sleep(500); // debounce
        servoPosition -= step;
        servoPosition = Math.max(servoPosition, 0.0); // clamp
        myServo.setPosition(servoPosition);
        sleep(500); // debounce

        sleep(3000);

        //Drive backwards to H.P
        leftFront.setPower(-0.8);
        rightFront.setPower(-0.8);
        leftRear.setPower(-0.8);
        rightRear.setPower(-0.8);

        sleep(1512);

        //Stop for H.P
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftRear.setPower(0);
        rightRear.setPower(0);

        intake.setPower(-0.7);

        sleep(1512);

        intake.setPower(0);

        sleep(50);


    }
}
//I like our borot
//               -Timbo[lee]nee