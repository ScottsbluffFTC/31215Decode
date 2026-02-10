package org.firstinspires.ftc.teamcode.AprilTags;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;



@TeleOp(name = "HuskyLens AprilTag Distance")
public class AprilTagCode1 extends LinearOpMode {

    I2cDeviceSynch husky;

    static final double TAG_WIDTH_CM = 5.0;
    static final double FOCAL_LENGTH = 720.0;

    @Override
    public void runOpMode() {

        husky = hardwareMap.get(I2cDeviceSynch.class, "hu");
        husky.setI2cAddress(I2cAddr.create7bit(0x32));
        husky.engage();

        telemetry.addLine("HuskyLens Ready (AprilTag Mode)");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            requestBlocks();
            sleep(50);

            byte[] data = husky.read(0x00, 16);

            if (data.length >= 12 && data[0] == (byte) 0x55 && data[1] == (byte) 0xAA) {

                int tagID = data[3] & 0xFF;

                int xCenter = ((data[5] & 0xFF) << 8) | (data[4] & 0xFF);
                int yCenter = ((data[7] & 0xFF) << 8) | (data[6] & 0xFF);

                int widthPixels = ((data[9] & 0xFF) << 8) | (data[8] & 0xFF);

                if (widthPixels > 0) {
                    double distanceCM =
                            (TAG_WIDTH_CM * FOCAL_LENGTH) / widthPixels;

                    telemetry.addData("Tag ID", tagID);
                    telemetry.addData("X", xCenter);
                    telemetry.addData("Y", yCenter);
                    telemetry.addData("Width (px)", widthPixels);
                    telemetry.addData("Distance (cm)", "%.1f", distanceCM);
                } else {
                    telemetry.addLine("Tag detected, width invalid");
                }

            } else {
                telemetry.addLine("No AprilTag detected");
            }

            telemetry.update();
        }
    }

    private void requestBlocks() {
        byte[] request = new byte[]{
                (byte) 0x55, (byte) 0xAA,
                (byte) 0x11, (byte) 0x00,
                (byte) 0x2C
        };
        husky.write(request);
    }
}
