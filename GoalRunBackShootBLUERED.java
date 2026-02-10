package org.firstinspires.ftc.teamcode.AutoCodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "GoalRunBackShootBLUERED", group = "Auto")
public class GoalRunBackShootBLUERED extends LinearOpMode {
    private Servo myServo;
    private double servoPosition = 0.5;
    private final double step = 0.075;

    @Override
    public void runOpMode() throws InterruptedException {

        // Get motors (use the exact names from your DS configuration)
        DcMotor leftFront = hardwareMap.dcMotor.get("frontLeft");
        DcMotor rightFront = hardwareMap.dcMotor.get("frontRight");
        DcMotor leftRear = hardwareMap.dcMotor.get("backLeft");
        DcMotor rightRear = hardwareMap.dcMotor.get("backRight");

        DcMotor leftFW = hardwareMap.dcMotor.get("fwLeft");
        DcMotor rightFW = hardwareMap.dcMotor.get("fwRight");

        DcMotor intake = hardwareMap.dcMotor.get("intake");

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

        sleep(1000);

        // --- STOP ---
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftRear.setPower(0);
        rightRear.setPower(0);

        sleep(1000);

        // set intake back
        intake.setPower(0.2);
        rightFW.setPower(0.2);
        leftFW.setPower(0.2);

        sleep(10);

        rightFW.setDirection(DcMotor.Direction.REVERSE);


        //Launcher speed
        leftFW.setPower(-.65);
        rightFW.setPower(-.65);

        sleep(1000);

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

        sleep(400);

        intake.setPower(0);

        sleep(1800);

        servoPosition -= step;
        servoPosition = Math.max(servoPosition, 0.0); // clamp
        myServo.setPosition(servoPosition);
        sleep(550); // debounce
        servoPosition += step;
        servoPosition = Math.min(servoPosition, 1.0); // clamp
        myServo.setPosition(servoPosition);
        sleep(50);
        intake.setPower(0.2);
        sleep(50);
        intake.setPower(0);
        sleep(550); // debounce

        intake.setPower(-0.7);

        sleep(400);

        intake.setPower(0);

        sleep(1800);


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

        //Strafe right
        leftFront.setPower(-.8);
        rightFront.setPower(.8);
        leftRear.setPower(.8);
        rightRear.setPower(-.8);
    }
}