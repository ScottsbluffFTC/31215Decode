package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import org.firstinspires.ftc.teamcode.Mechanisms.MecanumDrive;

import java.util.List;

@TeleOp(name = "Driver Mode", group = "TeleOp")
public class MecanumFieldOrientedOpMode extends LinearOpMode {
    VisionPortal portal1;
    AprilTagProcessor aprilTagProcessor1;

    // Servo variables
    private Servo myServo;
    private double servoPosition = 0.63;
    private final double step = 0.16;

    // Drive variables
    MecanumDrive drive = new MecanumDrive();
    double forward, strafe, rotate;

    // Intake variables
    DcMotor intake;

    // Flywheel variables
    DcMotor leftFW, rightFW;

    double maxFlywheelSpeed = 1; // Initial max speed
    private final double speedIncrement = 0.05; // how much to change speed by



    @Override
    public void runOpMode() {
        // Initialization for all mechanisms
        myServo = hardwareMap.get(Servo.class, "servo"); // name in config
        myServo.setPosition(servoPosition);

        drive.init(hardwareMap);

        leftFW = hardwareMap.get(DcMotor.class, "fwLeft");
        rightFW = hardwareMap.get(DcMotor.class, "fwRight");
        intake = hardwareMap.get(DcMotor.class, "intake");

        leftFW.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFW.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        
        waitForStart();

        while (opModeIsActive()) {
            // Gamepad 1 controls for driving
            forward = gamepad1.left_stick_y;
            strafe = gamepad1.left_stick_x;
            rotate = gamepad1.right_stick_x;
            drive.drive(forward, strafe, rotate);

            // Gamepad 2 controls for servo and flywheels
            // Press A → move servo
            if (gamepad2.right_bumper) {
                servoPosition -= step;
                servoPosition = Math.max(servoPosition, 0.0); // clamp
                myServo.setPosition(servoPosition);
                sleep(200); // debounce
                servoPosition += step;
                servoPosition = Math.min(servoPosition, 1.0); // clamp
                myServo.setPosition(servoPosition);
                sleep(200); // debounce
            }

            // Adjust max flywheel speed with D-pad
            if (gamepad2.dpad_up) {
                maxFlywheelSpeed += speedIncrement;
                maxFlywheelSpeed = Math.min(maxFlywheelSpeed, 1.0); // Clamp to 1.0
                sleep(200); // Debounce
            } else if (gamepad2.dpad_down) {
                maxFlywheelSpeed -= speedIncrement;
                maxFlywheelSpeed = Math.max(maxFlywheelSpeed, 0.0); // Clamp to 0.0
                sleep(200); // Debounce
            }

            // Flywheel control with max speed
            leftFW.setPower(gamepad2.right_stick_y * maxFlywheelSpeed);
            rightFW.setPower(-gamepad2.right_stick_y * maxFlywheelSpeed);

            //Intake
            intake.setPower(gamepad2.left_stick_y * .8);

            // Telemetry
            telemetry.addData("Servo Position", servoPosition);
            telemetry.addData("Max Flywheel Speed", "%.2f", maxFlywheelSpeed);
            telemetry.addData("Press D-Pad Up/Down on Gampad 2 to adjust", "");
            telemetry.update();
        }
    }
}
