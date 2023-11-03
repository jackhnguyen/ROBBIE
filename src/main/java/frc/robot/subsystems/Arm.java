// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import frc.robot.Constants.ArmConstants;

import com.revrobotics.SparkMaxAbsoluteEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxAbsoluteEncoder.Type;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {
  /** Creates a new Arm. */
  private CANSparkMax m_rightMotor = new CANSparkMax(5, MotorType.kBrushless);
  private CANSparkMax m_leftMotor = new CANSparkMax(6, MotorType.kBrushless);
  private SparkMaxAbsoluteEncoder m_absoluteEncoder = m_leftMotor.getAbsoluteEncoder(Type.kDutyCycle);
  //uses an absolute encoder
  //0.255 is high
  //0.6 is low
  public Arm() {
    m_rightMotor.setInverted(true);
    
  }
  public void lift() {
    m_leftMotor.set(ArmConstants.kArmSpeed);
    m_rightMotor.set(ArmConstants.kArmSpeed);
  }
  public void drop() {
    m_leftMotor.set(-ArmConstants.kArmSpeed);
    m_rightMotor.set(-ArmConstants.kArmSpeed);
  }
  public void killMotors(){
    m_leftMotor.set(0);
    m_rightMotor.set(0);
  }
  
  public double getAbsoluteEncoderPosition(){
    return m_absoluteEncoder.getPosition();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Abs Encoder: ", m_absoluteEncoder.getPosition());
  }
}
