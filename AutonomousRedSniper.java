package org.firstinspires.ftc.teamcode.AutoCodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "AutonomousRedSniper", group = "Auto")
public class AutonomousRedSniper extends LinearOpMode {

    private Servo myServo;
    private double servoPosition = 0.63;
    private final double step = 0.16;

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

        leftFW.setDirection(DcMotor.Direction.REVERSE);

        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        //Start

        // --- MOVE FORWARD ---
        leftFront.setPower(0.8);
        rightFront.setPower(0.8);
        leftRear.setPower(0.8);
        rightRear.setPower(0.8);

        sleep(125);

        // --- STOP ---
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftRear.setPower(0);
        rightRear.setPower(0);

        sleep(100);

        // --- ROTATE ---
        leftFront.setPower(-0.52);
        rightFront.setPower(0.52);
        leftRear.setPower(-0.52);
        rightRear.setPower(0.52);

        sleep(165);

        // --- STOP ---
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftRear.setPower(0);
        rightRear.setPower(0);

        sleep(100);



        //Launcher speed
        leftFW.setPower(1);
        rightFW.setPower(1);

        sleep(1500);

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

        sleep(700);

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

        sleep(700);

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

        sleep(700);

        intake.setPower(0);

        sleep(1050);

        telemetry.addData("Servo Position", servoPosition);
        telemetry.update();

        //Stop launcher

        leftFW.setPower(0);
        rightFW.setPower(0);

        sleep(1000);

        // --- ETATOR ---
        leftFront.setPower(0.52);
        rightFront.setPower(-0.52);
        leftRear.setPower(0.52);
        rightRear.setPower(-0.52);

        sleep(165);

        // --- STOP ---
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftRear.setPower(0);
        rightRear.setPower(0);

        sleep(100);

        // --- MOVE UP ---
        leftFront.setPower(0.8);
        rightFront.setPower(0.8);
        leftRear.setPower(0.8);
        rightRear.setPower(0.8);

        sleep(380);

        // --- ROTATE 90 LEFT
        leftFront.setPower(-0.8);
        rightFront.setPower(0.8);
        leftRear.setPower(-0.8);
        rightRear.setPower(0.8);

        sleep(410);

        // --- MOVE BACK AND COLLECT
        leftFront.setPower(-0.4);
        rightFront.setPower(-0.4);
        leftRear.setPower(-0.4);
        rightRear.setPower(-0.4);

        leftFW.setPower(-0.2);
        rightFW.setPower(-0.2);


        intake.setPower(-1);

        sleep(3000);

        // --- STOP
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftRear.setPower(0);
        rightRear.setPower(0);

        intake.setPower(0);

        leftFW.setPower(0);
        rightFW.setPower(0);


        sleep(100);

        // --- MOVE FORWARD
        leftFront.setPower(0.8);
        rightFront.setPower(0.8);
        leftRear.setPower(0.8);
        rightRear.setPower(0.8);

        sleep(1400);

        // --- STOP
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftRear.setPower(0);
        rightRear.setPower(0);

        sleep(100);

        // --- ROTATE 90 RIGHT
        leftFront.setPower(0.8);
        rightFront.setPower(-0.8);
        leftRear.setPower(0.8);
        rightRear.setPower(-0.8);

        sleep(450);

        // --- Stop
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftRear.setPower(0);
        rightRear.setPower(0);

        sleep(100);


        // --- MOVE BACKWARD ---
        leftFront.setPower(-0.8);
        rightFront.setPower(-0.8);
        leftRear.setPower(-0.8);
        rightRear.setPower(-0.8);

        sleep(350);

        // --- ROTATE LEFT ---
        leftFront.setPower(-0.52);
        rightFront.setPower(0.52);
        leftRear.setPower(-0.52);
        rightRear.setPower(0.52);

        sleep(120);

        // --- STOP
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftRear.setPower(0);
        rightRear.setPower(0);

        sleep(550);

        //Launcher speed
        leftFW.setPower(1);
        rightFW.setPower(1);

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

        sleep(700);

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

        sleep(700);

        intake.setPower(0);

        sleep(50);

        telemetry.addData("Servo Position", servoPosition);
        telemetry.update();

        sleep(200);
        servoPosition -= step;
        servoPosition = Math.max(servoPosition, 0.0); // clamp
        myServo.setPosition(servoPosition);
        sleep(550); // debounce
        servoPosition += step;
        servoPosition = Math.min(servoPosition, 1.0); // clamp
        myServo.setPosition(servoPosition);
        sleep(550); // debounce


        //Stop launcher

        leftFW.setPower(0);
        rightFW.setPower(0);

        sleep(1000);

        // --- ETATOR ---
        leftFront.setPower(0.52);
        rightFront.setPower(-0.52);
        leftRear.setPower(0.52);
        rightRear.setPower(-0.52);

        sleep(190);

        // --- STOP ---
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftRear.setPower(0);
        rightRear.setPower(0);

        sleep(100);

        // --- MOVE UP ---
        leftFront.setPower(0.8);
        rightFront.setPower(0.8);
        leftRear.setPower(0.8);
        rightRear.setPower(0.8);

        sleep(550);

        // --- ROTATE 90 LEFT
        leftFront.setPower(-0.8);
        rightFront.setPower(0.8);
        leftRear.setPower(-0.8);
        rightRear.setPower(0.8);

        sleep(465);

        // --- MOVE BACK AND COLLECT
        leftFront.setPower(-0.5);
        rightFront.setPower(-0.5);
        leftRear.setPower(-0.5);
        rightRear.setPower(-0.5);

        sleep(1512);

        // --- STOP
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftRear.setPower(0);
        rightRear.setPower(0);

        sleep(100);

        // --- MOVE FORWARD
        leftFront.setPower(0.8);
        rightFront.setPower(0.8);
        leftRear.setPower(0.8);
        rightRear.setPower(0.8);

        sleep(1360);

        // --- STOP
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftRear.setPower(0);
        rightRear.setPower(0);

        sleep(100);

        // --- ROTATE 90 RIGHT
        leftFront.setPower(0.8);
        rightFront.setPower(-0.8);
        leftRear.setPower(0.8);
        rightRear.setPower(-0.8);

        sleep(500);

        // --- MOVE BACKWARD ---
        leftFront.setPower(-0.8);
        rightFront.setPower(-0.8);
        leftRear.setPower(-0.8);
        rightRear.setPower(-0.8);

        sleep(125);

        // --- ROTATE ---
        leftFront.setPower(-0.52);
        rightFront.setPower(0.52);
        leftRear.setPower(-0.52);
        rightRear.setPower(0.52);

        sleep(450);

        //Launcher speed
        leftFW.setPower(-1);
        rightFW.setPower(-1);

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

        sleep(700);

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

        sleep(700);

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

        sleep(1000);

        // --- ETATOR ---
        leftFront.setPower(0.52);
        rightFront.setPower(-0.52);
        leftRear.setPower(0.52);
        rightRear.setPower(-0.52);

        sleep(190);

        // --- STOP ---
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftRear.setPower(0);
        rightRear.setPower(0);

        sleep(100);

        // --- MOVE UP ---
        leftFront.setPower(0.8);
        rightFront.setPower(0.8);
        leftRear.setPower(0.8);
        rightRear.setPower(0.8);

        sleep(750);

        // --- ROTATE 90 LEFT
        leftFront.setPower(-0.8);
        rightFront.setPower(0.8);
        leftRear.setPower(-0.8);
        rightRear.setPower(0.8);

        sleep(450);

        // --- MOVE BACK AND COLLECT
        leftFront.setPower(-0.5);
        rightFront.setPower(-0.5);
        leftRear.setPower(-0.5);
        rightRear.setPower(-0.5);

        sleep(1512);

        // --- STOP
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftRear.setPower(0);
        rightRear.setPower(0);

        sleep(100);

        // --- MOVE FORWARD
        leftFront.setPower(0.8);
        rightFront.setPower(0.8);
        leftRear.setPower(0.8);
        rightRear.setPower(0.8);

        sleep(1400);

        // --- STOP
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftRear.setPower(0);
        rightRear.setPower(0);

        sleep(100);

        // --- ROTATE 90 RIGHT
        leftFront.setPower(0.8);
        rightFront.setPower(-0.8);
        leftRear.setPower(0.8);
        rightRear.setPower(-0.8);

        sleep(500);

        // --- MOVE BACKWARD ---
        leftFront.setPower(-0.8);
        rightFront.setPower(-0.8);
        leftRear.setPower(-0.8);
        rightRear.setPower(-0.8);

        sleep(125);

        // --- ROTATE ---
        leftFront.setPower(-0.52);
        rightFront.setPower(0.52);
        leftRear.setPower(-0.52);
        rightRear.setPower(0.52);

        sleep(450);

        //Launcher speed
        leftFW.setPower(-1);
        rightFW.setPower(-1);

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

        sleep(700);

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

        sleep(700);

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
    }
}

