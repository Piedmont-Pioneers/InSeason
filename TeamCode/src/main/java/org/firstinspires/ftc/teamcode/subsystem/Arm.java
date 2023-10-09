package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Config;

public class Arm extends SubSystem {

    private DcMotor lowerArmMotor;
    private DcMotor upperArmMotor;
    public Arm (Config config) {
        super(config);
    }

    @Override
    public void init() {
        lowerArmMotor = config.hardwareMap.get(DcMotor.class, Config.LOW_ARM_MOTOR);
        upperArmMotor = config.hardwareMap.get(DcMotor.class, Config.UP_ARM_MOTOR);


    }

    @Override
    public void update() {

        lowerArmMotor.setPower(config.gamePad1.left_stick_y);
        upperArmMotor.setPower(config.gamePad1.left_stick_y);

    }
}
