package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Config;
// This code is based on the GM0 code for a macanum teleop found here: https://gm0.org/en/latest/docs/software/tutorials/mecanum-drive.html
public class Drive extends SubSystem {
    private DcMotor leftFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor rightBackDrive = null;

    public Drive(Config cfg) {
        super(cfg);
    }

    public Drive(Config cfg, boolean isOneController) {
        super(cfg, isOneController);
    }

    public void init() {

        // Initialize the hardware variables. Note that the strings used here must correspond
        // to the names assigned during the robot configuration step on the DS or RC devices.
        leftFrontDrive = config.hardwareMap.get(DcMotor.class, Config.LEFT_FRONT_DRIVE);
        rightFrontDrive = config.hardwareMap.get(DcMotor.class, Config.RIGHT_FRONT_DRIVE);
        leftBackDrive = config.hardwareMap.get(DcMotor.class, Config.LEFT_BACK_DRIVE);
        rightBackDrive = config.hardwareMap.get(DcMotor.class, Config.RIGHT_BACK_DRIVE);

        // Most robots need the motors on one side to be reversed to drive forward.
        // When you first test your robot, push the left joystick forward
        // and flip the direction ( FORWARD <-> REVERSE ) of any wheel that runs backwards
        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE); // DO NOT CHANGE
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE); // DO NOT CHANGE
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD); // DO NOT CHANGE
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD); // DO NOT CHANGE
    }

    public void update() {

        // POV Mode uses left joystick to go forward & strafe, and right joystick to rotate.
        double axial = -config.gamePad1.left_stick_y;  // Note: pushing stick forward gives negative value
        double lateral = config.gamePad1.left_stick_x * 1.1; // 1.1 fixes strafing issues
        // MUST BE INVERTED!
        double yaw = -config.gamePad1.right_stick_x;
        // Take the average of the 2 triggers
        double speed = 1 - (config.gamePad1.right_trigger + config.gamePad1.left_trigger) / 2;

        // Combine the joystick requests for each axis-motion to determine each wheel's power.
        // Set up a variable for each drive wheel to save the power level for telemetry.
        double leftFrontPower = (axial + lateral - yaw) * speed; // DO NOT CHANGE
        double rightFrontPower = (axial - lateral + yaw) * speed; // DO NOT CHANGE
        double leftBackPower = (axial - lateral - yaw) * speed; // DO NOT CHANGE
        double rightBackPower = (axial + lateral + yaw) * speed; // DO NOT CHANGE

        // Normalize the values so no wheel power exceeds 100%
        // This ensures that the robot maintains the desired motion.
        double max;
        max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
        max = Math.max(max, Math.abs(leftBackPower));
        max = Math.max(max, Math.abs(rightBackPower));

        if (max > 1.0) {
            leftFrontPower /= max;
            rightFrontPower /= max;
            leftBackPower /= max;
            rightBackPower /= max;
        }

        // Send calculated power to wheels`
        leftFrontDrive.setPower(leftFrontPower);
        rightFrontDrive.setPower(rightFrontPower);
        leftBackDrive.setPower(leftBackPower);
        rightBackDrive.setPower(rightBackPower);

        // Show the elapsed game time and wheel power.
        config.telemetry.addData("Front left/Right", "%4.2f, %4.2f", leftFrontPower, rightFrontPower);
        config.telemetry.addData("Back  left/Right", "%4.2f, %4.2f", leftBackPower, rightBackPower);
        config.telemetry.addData("Right Stick x Position", "%4.2f", yaw);
    }
}