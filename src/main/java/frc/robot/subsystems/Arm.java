// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import frc.robot.Constants.ArmConstants;

import com.revrobotics.SparkMaxAbsoluteEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxAbsoluteEncoder.Type;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {
  /** Creates a new Arm. */
  private CANSparkMax m_rightMotor = new CANSparkMax(6, MotorType.kBrushless);
  private CANSparkMax m_leftMotor = new CANSparkMax(7, MotorType.kBrushless);
  private SparkMaxAbsoluteEncoder m_absoluteEncoder = m_leftMotor.getAbsoluteEncoder(Type.kDutyCycle);
  //uses an absolute encoder
  public Arm() {
    m_rightMotor.setInverted(true);
    
  }
  public CommandBase lift() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return run(
        () -> {
          m_leftMotor.set(ArmConstants.kArmSpeed);
          m_rightMotor.set(ArmConstants.kArmSpeed);
        });
  }
  public CommandBase drop() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return run(
        () -> {
          m_leftMotor.set(-ArmConstants.kArmSpeed);
          m_rightMotor.set(-ArmConstants.kArmSpeed);
        });
  }
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
