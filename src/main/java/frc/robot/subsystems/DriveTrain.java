// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxAlternateEncoder.Type;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DTConstants;

public class DriveTrain extends SubsystemBase {
  /** Creates a new DriveTrain. */
  private CANSparkMax m_leftMaster = new CANSparkMax(DTConstants.kLEFT_MOTOR_ID_1, MotorType.kBrushless);
  private CANSparkMax m_leftSlave1 = new CANSparkMax(DTConstants.kLEFT_MOTOR_ID_2, MotorType.kBrushless);
  private CANSparkMax m_rightMaster = new CANSparkMax(DTConstants.kRIGHT_MOTOR_ID_1, MotorType.kBrushless);
  private CANSparkMax m_rightSlave1 = new CANSparkMax(DTConstants.kRIGHT_MOTOR_ID_2, MotorType.kBrushless);
  private DifferentialDrive m_dt = new DifferentialDrive(m_leftMaster, m_rightMaster);
  //change the slave and master if it works
  private RelativeEncoder m_leftEncoder = m_leftSlave1.getAlternateEncoder(Type.kQuadrature, DTConstants.kCOUNTS_PER_REV);
  //test anf change this after
  private RelativeEncoder m_rightEncoder = m_rightMaster.getAlternateEncoder(Type.kQuadrature, DTConstants.kCOUNTS_PER_REV);
  //encoder counts per rev 4096
  //uses an alternate cancoder
  //neo has 48 counts

  public DriveTrain() {
    m_rightMaster.setInverted(false);
    m_leftMaster.setInverted(true);
    m_leftSlave1.follow(m_leftMaster);
    m_rightSlave1.follow(m_rightMaster);
    resetEncoders();
  }
  public CommandBase limitedArcadeDriveCommand(double xspeed, double yspeed) {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return run(
        () -> {
          m_dt.arcadeDrive(xspeed*0.2, yspeed*0.2);
        });
  }  
  public void arcadeDrive(double xspeed, double yspeed) {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    m_dt.arcadeDrive(xspeed, yspeed);
  }
  public void resetEncoders(){
    m_leftEncoder.setPosition(0);
    m_rightEncoder.setPosition(0);
  }

  //add gear ratio to formula
  public double getLeftDistance(){
    return ((m_leftEncoder.getPosition() / DTConstants.kCOUNTS_PER_REV)*DTConstants.kENCODER_COUNT_TO_INCHES);
  }

  public double getRightDistance(){
    return ((m_rightEncoder.getPosition() / DTConstants.kCOUNTS_PER_REV)*DTConstants.kENCODER_COUNT_TO_INCHES);
  }
  public double getAverageDistance(){
    return (getLeftDistance()+getRightDistance())/2;
  }
  

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Encoder Value: ", m_leftEncoder.getPosition());
  }
}
