package org.firstinspires.ftc.teamcode.AutoCodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "AutonomousBlueSniperV2", group = "Auto")
public class AutonomousBlueSniperV2 extends LinearOpMode {

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


    static final double TICKS_PER_ROTATION = 537.7; // GoBILDA 5203 312RPM


    private void Stop(){

        leftFront.setPower(0);
        rightFront.setPower(0);
        leftRear.setPower(0);
        rightRear.setPower(0);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        sleep(10000);
    }

    private void Drive(double rotations, double speed) {

        int ticks = (int)(rotations * TICKS_PER_ROTATION);

        leftFront.setTargetPosition(leftFront.getCurrentPosition() + ticks);
        rightFront.setTargetPosition(rightFront.getCurrentPosition() + ticks);
        leftRear.setTargetPosition(leftRear.getCurrentPosition() + ticks);
        rightRear.setTargetPosition(rightRear.getCurrentPosition() +  ticks);

        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftFront.setPower(speed);
        rightFront.setPower(speed);
        leftRear.setPower(speed);
        rightRear.setPower(speed);

        while (opModeIsActive() &&
                (leftFront.isBusy() || rightFront.isBusy() ||
                        leftRear.isBusy() || rightRear.isBusy())) {

            telemetry.addData("LF", leftFront.getCurrentPosition());
            telemetry.addData("RF", rightFront.getCurrentPosition());
            telemetry.update();
            idle();

        }

    }


    private void Turn(double rotations, double speed) {

        int ticks = (int)(rotations * TICKS_PER_ROTATION);

        leftFront.setTargetPosition(leftFront.getCurrentPosition() + ticks);
        leftRear.setTargetPosition(leftRear.getCurrentPosition() + ticks);
        rightFront.setTargetPosition(rightFront.getCurrentPosition() - ticks);
        rightRear.setTargetPosition(rightRear.getCurrentPosition() - ticks);


        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftFront.setPower(speed);
        leftRear.setPower(speed);
        rightFront.setPower(speed);
        rightRear.setPower(speed);

        while (opModeIsActive() &&
                (leftFront.isBusy() || rightFront.isBusy() || leftRear.isBusy() || rightRear.isBusy())) {
            idle();
        }

    }


    private void Launch() {

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

    }

    private void Reload(){
        intake.setPower(-0.7);

        sleep(700);

        intake.setPower(0);

        sleep(500);
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

        leftFW.setDirection(DcMotor.Direction.REVERSE);

        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        waitForStart();

        //Start


        // --- MOVE FORWARD ---
        Drive(1, .8);

        // --- STOP ---
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftRear.setPower(0);
        rightRear.setPower(0);

        sleep(10000);

        // --- ROTATE ---
        Turn(1, .5);

        // --- STOP ---
        Stop();

       Launch();

       Reload();

        Launch();

        Reload();

        Launch();

        telemetry.addData("Servo Position", servoPosition);
        telemetry.update();

        //Stop launcher

        leftFW.setPower(0);
        rightFW.setPower(0);

        // --- ETATOR ---
        Turn(1, .5);

        // --- STOP ---
        Stop();

        // --- MOVE UP ---
        Drive(1, .8);

        // --- ROTATE 90 LEFT
        Turn(1, .8);

        leftFW.setPower(-0.2);
        rightFW.setPower(-0.2);

        intake.setPower(-1);

        // --- MOVE BACK AND COLLECT
        Drive(-1, .8);

        // --- STOP
        Stop();

        intake.setPower(0);

        leftFW.setPower(0);
        rightFW.setPower(0);

        sleep(100);

        // --- MOVE FORWARD
        Drive(1, .8);

        // --- STOP
        Stop();

        // --- ROTATE 90 RIGHT
    Turn(1, .8);

        // --- Stop
        Stop();

        // --- MOVE BACKWARD ---
        Drive(-1, .8);

        // --- ROTATE LEFT ---
       Turn(1, .8);

        // --- STOP
        Stop();

        Launch();

        Reload();

        Launch();

        telemetry.addData("Servo Position", servoPosition);
        telemetry.update();

        Reload();

        Launch();

        Reload();

        telemetry.addData("Servo Position", servoPosition);
        telemetry.update();

        //Stop launcher
        leftFW.setPower(0);
        rightFW.setPower(0);

        // --- ETATOR ---
        Turn(1, .5);

        // --- STOP ---
        Stop();

        // --- MOVE UP ---
        Drive(1, .8);

        // --- ROTATE 90 LEFT
        Turn(1, .8);

        intake.setPower(-0.7);

        // --- MOVE BACK AND COLLECT
        Drive(-1, .8);

        // --- STOP
        Stop();

        // --- MOVE FORWARD
        Drive(1, .8);

        // --- STOP
        Stop();

        // --- ROTATE 90 RIGHT
        Turn(1, .8);

        // --- MOVE BACKWARD ---
        Drive(-1, .8);

        // --- ROTATE ---
        Turn(1, .8);

        //stop
        Stop();

        //Launcher speed
        leftFW.setPower(-1);
        rightFW.setPower(-1);

        Launch();

        Reload();

        Launch();

        Reload();

        Launch();

        telemetry.addData("Servo Position", servoPosition);
        telemetry.update();

        //Stop launcher

        leftFW.setPower(0);
        rightFW.setPower(0);

        sleep(500);

        // --- ETATOR ---
        Turn(1, .8);

        // --- STOP ---
        Stop();

        // --- MOVE UP ---
        Drive(1, .8);

        // --- ROTATE 90 LEFT
        Turn(1, .8);

        // --- MOVE BACK AND COLLECT
        Drive(-1, .8);

        // --- STOP
        Stop();

        // --- MOVE FORWARD
        Drive(1, .8);

        // --- STOP
        Stop();

        // --- ROTATE 90 RIGHT
        Turn(1, .8);

        // --- MOVE BACKWARD ---
        Drive(-1, .8);

        // --- ROTATE ---
        Turn(1, .8);

        //stop
        Stop();

        //Launcher speed
        leftFW.setPower(-1);
        rightFW.setPower(-1);

        sleep(500);

        Launch();

        Reload();

        Launch();

        Reload();

        Launch();

        Reload();

        telemetry.addData("Servo Position", servoPosition);
        telemetry.update();

        //Stop launcher

        leftFW.setPower(0);
        rightFW.setPower(0);
    }
}
