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


        // TODO: TUNE SPEED
        double maxSpeed = 0.5;
        double speed = config.gamePad2.left_stick_y / 5;

        if (speed > maxSpeed) {
            speed = maxSpeed;
        }

        lowerArmMotor.setPower(speed);

        if(config.gamePad2.x){
           upperArmMotor.setPower(0.339);
        }
        else if (config.gamePad2.y)
        {
            upperArmMotor.setPower(-0.339);
        }
        else{
            upperArmMotor.setPower(0);
        }

    }
}
