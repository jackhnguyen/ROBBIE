// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmConstants;

public class UpperArm extends SubsystemBase {
  /** Creates a new UpperArm. */
  private CANSparkMax m_motor = new CANSparkMax(7, MotorType.kBrushless);
  public UpperArm() {}

  public CommandBase Extend() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return run(
        () -> {
          m_motor.set(ArmConstants.kUpperArmSpeed);
        });
  }
  public CommandBase Retract() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return run(
        () -> {
          m_motor.set(-ArmConstants.kUpperArmSpeed);
        });
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
