package org.firstinspires.ftc.teamcode.AutoCodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "AutonomousBlueSniper", group = "Auto")
public class AutonomousBlueSniper extends LinearOpMode {

    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftRear;
    private DcMotor rightRear;
    private DcMotor leftFW;
    private DcMotor rightFW;
    private DcMotor intake;
    private Servo myServo;

    private double servoPosition = 0.63;
    private final double step = 0.16;



    //Seconds version
    private void Drive(double speed, double milliseconds) {
        speed = Math.max(-1.0, Math.min(1.0, speed)); // clamp speed between -1 and 1

        leftFront.setPower(speed);
        rightFront.setPower(speed);
        leftRear.setPower(speed);
        rightRear.setPower(speed);

        sleep((long) milliseconds);
    }

    private void TurnLeft(double speed, double milliseconds) {
        speed = Math.max(-1.0, Math.min(1.0, speed)); // clamp speed between -1 and 1

        leftFront.setPower(-speed);
        rightFront.setPower(speed);
        leftRear.setPower(-speed);
        rightRear.setPower(speed);

        sleep((long) milliseconds);
    }

    private void TurnRight(double speed, double milliseconds) {
        speed = Math.max(-1.0, Math.min(1.0, speed)); // clamp speed between -1 and 1

        leftFront.setPower(speed);
        rightFront.setPower(-speed);
        leftRear.setPower(speed);
        rightRear.setPower(-speed);

        sleep((long) milliseconds);
    }

    private void Stop(double milliseconds) {
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftRear.setPower(0);
        rightRear.setPower(0);

        sleep((long) milliseconds);
    }






    @Override
    public void runOpMode() throws InterruptedException {

        // Get motors (use the exact names from your DS configuration)
        leftFront  = hardwareMap.dcMotor.get("frontLeft");
        rightFront = hardwareMap.dcMotor.get("frontRight");
        leftRear   = hardwareMap.dcMotor.get("backLeft");
        rightRear  = hardwareMap.dcMotor.get("backRight");

        leftFW     = hardwareMap.dcMotor.get("fwLeft");
        rightFW    = hardwareMap.dcMotor.get("fwRight");

        intake     = hardwareMap.dcMotor.get("intake");

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


        // --- MOVE FORWARD ---
        Drive(.8, 140);


        // --- STOP ---
        Stop(100);

        // --- ROTATE ---
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


        leftFW.setDirection(DcMotor.Direction.REVERSE);


        //Launcher speed
        leftFW.setPower(1);
        rightFW.setPower(1);

        sleep(1800);

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

        sleep(800);


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

        sleep(800);


        servoPosition -= step;
        servoPosition = Math.max(servoPosition, 0.0); // clamp
        myServo.setPosition(servoPosition);
        sleep(550); // debounce
        servoPosition += step;
        servoPosition = Math.min(servoPosition, 1.0); // clamp
        myServo.setPosition(servoPosition);
        sleep(550); // debounce


        telemetry.addData("Servo Position", servoPosition);
        telemetry.update();

        //Stop launcher

        leftFW.setPower(0);
        rightFW.setPower(0);

        sleep(500);

        // --- ETATOR ---
        leftFront.setPower(-0.52);
        rightFront.setPower(0.52);
        leftRear.setPower(-0.52);
        rightRear.setPower(0.52);

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

        sleep(425);

        // --- ROTATE 90 LEFT
        leftFront.setPower(0.8);
        rightFront.setPower(-0.8);
        leftRear.setPower(0.8);
        rightRear.setPower(-0.8);

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
        leftFront.setPower(-0.8);
        rightFront.setPower(0.8);
        leftRear.setPower(-0.8);
        rightRear.setPower(0.8);

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

        sleep(400);

        // --- ROTATE LEFT ---
        leftFront.setPower(0.52);
        rightFront.setPower(-0.52);
        leftRear.setPower(0.52);
        rightRear.setPower(-0.52);

        sleep(130);

        // --- STOP
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftRear.setPower(0);
        rightRear.setPower(0);

        sleep(550);

        //Launcher speed
        leftFW.setPower(1);
        rightFW.setPower(1);

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

        sleep(500);


        servoPosition -= step;
        servoPosition = Math.max(servoPosition, 0.0); // clamp
        myServo.setPosition(servoPosition);
        sleep(550); // debounce
        servoPosition += step;
        servoPosition = Math.min(servoPosition, 1.0); // clamp
        myServo.setPosition(servoPosition);
        sleep(550); // debounce


        telemetry.addData("Servo Position", servoPosition);
        telemetry.update();

        sleep(700);


        intake.setPower(-0.7);

        sleep(700);

        intake.setPower(0);

        sleep(500);


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

        sleep(500);


        telemetry.addData("Servo Position", servoPosition);
        telemetry.update();



        //Stop launcher

        leftFW.setPower(0);
        rightFW.setPower(0);

        sleep(1000);

        // --- ETATOR ---
        leftFront.setPower(-0.52);
        rightFront.setPower(0.52);
        leftRear.setPower(-0.52);
        rightRear.setPower(0.52);

        sleep(154);

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
        leftFront.setPower(0.8);
        rightFront.setPower(-0.8);
        leftRear.setPower(0.8);
        rightRear.setPower(-0.8);

        sleep(420);

        // --- MOVE BACK AND COLLECT
        leftFront.setPower(-0.5);
        rightFront.setPower(-0.5);
        leftRear.setPower(-0.5);
        rightRear.setPower(-0.5);

        intake.setPower(-0.7);


        sleep(2151);

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
        leftFront.setPower(-0.8);
        rightFront.setPower(0.8);
        leftRear.setPower(-0.8);
        rightRear.setPower(0.8);

        sleep(500);

        // --- MOVE BACKWARD ---
        leftFront.setPower(-0.8);
        rightFront.setPower(-0.8);
        leftRear.setPower(-0.8);
        rightRear.setPower(-0.8);

        sleep(125);

        // --- ROTATE ---
        leftFront.setPower(0.52);
        rightFront.setPower(-0.52);
        leftRear.setPower(0.52);
        rightRear.setPower(-0.52);

        sleep(450);

        //stop
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftRear.setPower(0);
        rightRear.setPower(0);

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

        sleep(700);


        intake.setPower(-0.7);

        sleep(700);

        intake.setPower(0);

        sleep(500);

        servoPosition -= step;
        servoPosition = Math.max(servoPosition, 0.0); // clamp
        myServo.setPosition(servoPosition);
        sleep(550); // debounce
        servoPosition += step;
        servoPosition = Math.min(servoPosition, 1.0); // clamp
        myServo.setPosition(servoPosition);
        sleep(550); // debounce

        sleep(700);


        intake.setPower(-0.7);

        sleep(700);

        intake.setPower(0);

        sleep(500);

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
        leftFront.setPower(-0.52);
        rightFront.setPower(0.52);
        leftRear.setPower(-0.52);
        rightRear.setPower(0.52);

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
        leftFront.setPower(0.8);
        rightFront.setPower(-0.8);
        leftRear.setPower(0.8);
        rightRear.setPower(-0.8);

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
        leftFront.setPower(-0.8);
        rightFront.setPower(0.8);
        leftRear.setPower(-0.8);
        rightRear.setPower(0.8);

        sleep(500);

        // --- MOVE BACKWARD ---
        leftFront.setPower(-0.8);
        rightFront.setPower(-0.8);
        leftRear.setPower(-0.8);
        rightRear.setPower(-0.8);

        sleep(125);

        // --- ROTATE ---
        leftFront.setPower(0.52);
        rightFront.setPower(-0.52);
        leftRear.setPower(0.52);
        rightRear.setPower(-0.52);

        sleep(450);

        //stop
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftRear.setPower(0);
        rightRear.setPower(0);


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

        servoPosition -= step;
        servoPosition = Math.max(servoPosition, 0.0); // clamp
        myServo.setPosition(servoPosition);
        sleep(550); // debounce
        servoPosition += step;
        servoPosition = Math.min(servoPosition, 1.0); // clamp
        myServo.setPosition(servoPosition);
        sleep(550); // debounce

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
