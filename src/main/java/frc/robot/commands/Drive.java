// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class Drive extends CommandBase {
  /** Creates a new Drive. */
  private DriveTrain m_dt;
  private double tangentialSpeed;
  private double rotateSpeed;
  public Drive(DriveTrain dt,double verticalSpeed, double rotationSpeed) {
    m_dt = dt;
    tangentialSpeed = verticalSpeed;
    rotationSpeed = rotateSpeed;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_dt);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_dt.resetEncoders();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_dt.arcadeDrive(tangentialSpeed, rotateSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
