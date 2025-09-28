package org.firstinspires.ftc.teamcode.Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Mechanisms.RobotIMU;

@TeleOp
public class ExampleOpmode extends OpMode {

    RobotIMU imu = new RobotIMU();
    double heading;

    @Override
    public void init() {
        imu.init(hardwareMap);

    }

    @Override
    public void loop() {
        heading = imu.getHeading();
        telemetry.addData("Heading", heading);

    }
}
