// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.Constants.DTConstants;

public class PIDFoward extends CommandBase {
  private DriveTrain m_dt;
  private int m_distance;
  private PIDController m_pidController = new PIDController(DTConstants.kP, DTConstants.kI, DTConstants.kD);
  /** Creates a new Foward. */
  public PIDFoward(DriveTrain dt, int distance) {
    m_dt = dt;
    m_distance = distance;
    addRequirements(dt);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_dt.resetEncoders();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double kMotorOutput = m_pidController.calculate(m_dt.getAverageDistance(), m_distance);
    m_dt.arcadeDrive(kMotorOutput, 0);
    SmartDashboard.putNumber("Motor Output", kMotorOutput);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(m_dt.getAverageDistance() >= m_distance){
      return true;
    }
    return false;
  }
}
