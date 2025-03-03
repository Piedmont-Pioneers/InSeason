package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.subsystem.AirplaneLauncher;
import org.firstinspires.ftc.teamcode.subsystem.Arm;
import org.firstinspires.ftc.teamcode.subsystem.Claw;
import org.firstinspires.ftc.teamcode.subsystem.Drive;
import org.firstinspires.ftc.teamcode.subsystem.Intake;
import org.firstinspires.ftc.teamcode.subsystem.SubSystem;

import java.util.LinkedList;
import java.util.List;

public class ManualRobot {
    // Config class to all hardware controls
    Config config;
    // List of all registered subsystems
    List<SubSystem> subSystems = new LinkedList<>();

    // Constructor
    public ManualRobot(Config cfg, boolean isOneController) {
        config = cfg;
        // Register the subsystem. System will not work if it's not registered
        subSystems.add(new Drive(config, isOneController));
        subSystems.add(new Arm(config, isOneController));
        subSystems.add(new Claw(config, isOneController));
        subSystems.add(new Intake(config, isOneController));
        subSystems.add(new AirplaneLauncher(config, isOneController));
    }

    // Initialize each subsystem
    public void init() {
        for (SubSystem sub : subSystems) {
            sub.init();
        }
    }

    // Update each subsystem. Runs in a loop
    public void update() {
        for (SubSystem sub : subSystems) {
            sub.update();
        }
    }

    public void bSleep(long millis) {

    }


}
