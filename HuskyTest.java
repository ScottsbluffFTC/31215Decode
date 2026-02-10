package org.firstinspires.ftc.teamcode.AprilTags;

import com.qualcomm.hardware.dfrobot.HuskyLens;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.internal.system.Deadline;

import java.util.concurrent.TimeUnit;

@TeleOp(name = "Sensor: HuskyLens + Auto Align", group = "Sensor")
public class HuskyTest extends LinearOpMode {

    // faster updates (was 1 second)
    private final int READ_PERIOD_MS = 50;

    private HuskyLens huskyLens;

    private DcMotor leftFront;
    private DcMotor leftRear;
    private DcMotor rightFront;
    private DcMotor rightRear;

    // ALIGNMENT CONSTANTS
    static final int CAMERA_CENTER_X = 160;
    static final int CENTER_TOLERANCE = 6;

    @Override
    public void runOpMode()
    {
        huskyLens = hardwareMap.get(HuskyLens.class, "huskylens");

        leftFront  = hardwareMap.dcMotor.get("frontLeft");
        rightFront = hardwareMap.dcMotor.get("frontRight");
        leftRear   = hardwareMap.dcMotor.get("backLeft");
        rightRear  = hardwareMap.dcMotor.get("backRight");

        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        rightFront.setDirection(DcMotor.Direction.REVERSE);
        rightRear.setDirection(DcMotor.Direction.REVERSE);

        Deadline rateLimit = new Deadline(READ_PERIOD_MS, TimeUnit.MILLISECONDS);
        rateLimit.expire();

        if (!huskyLens.knock()) {
            telemetry.addData(">>", "Problem communicating with " + huskyLens.getDeviceName());
        } else {
            telemetry.addData(">>", "Press start to continue");
        }

        huskyLens.selectAlgorithm(HuskyLens.Algorithm.TAG_RECOGNITION);

        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {

            if (!rateLimit.hasExpired()) {
                continue;
            }
            rateLimit.reset();

            HuskyLens.Block[] blocks = huskyLens.blocks();
            telemetry.addData("Block count", blocks.length);

            if (blocks.length > 0) {

                HuskyLens.Block tag = blocks[0];

                int xCenter = tag.x;
                int error = xCenter - CAMERA_CENTER_X;

                telemetry.addData("Tag X", xCenter);
                telemetry.addData("Error", error);

                if (gamepad1.a) {

                    double turnPower;

                    // choose direction based on tag position
                    if (Math.abs(error) > 40) {
                        turnPower = Math.signum(error) * 0.30; // fast turn
                    }
                    else if (Math.abs(error) > CENTER_TOLERANCE) {
                        turnPower = Math.signum(error) * 0.15; // slow near center
                    }
                    else {
                        turnPower = 0;
                    }

                    // apply turning
                    leftFront.setPower(-turnPower);
                    rightFront.setPower(turnPower);
                    leftRear.setPower(-turnPower);
                    rightRear.setPower(turnPower);

                } else {
                    leftFront.setPower(0);
                    rightFront.setPower(0);
                    leftRear.setPower(0);
                    rightRear.setPower(0);
                }

            } else {
                leftFront.setPower(0);
                rightFront.setPower(0);
                leftRear.setPower(0);
                rightRear.setPower(0);
            }

            telemetry.update();
        }
    }
}
