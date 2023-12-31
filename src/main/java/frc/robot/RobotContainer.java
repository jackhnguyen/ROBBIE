// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.Drive;
import frc.robot.commands.Drop;
import frc.robot.commands.Foward;
import frc.robot.commands.Lift;
import frc.robot.commands.PIDFoward;
import frc.robot.commands.Reverse;
import frc.robot.commands.Rotate;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.UpperArm;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveTrain m_dt = new DriveTrain();
  private final Arm m_arm = new Arm();
  private final UpperArm m_UpperArm = new UpperArm();
  private final Claw m_claw = new Claw();
  private final ExampleSubsystem m_ExampleSubsystem = new ExampleSubsystem();
  //Commands
  private final Lift cLift = new Lift(m_arm);
  private final Drop cDrop = new Drop(m_arm);
  
  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController = new CommandXboxController(OperatorConstants.kDriverControllerPort);
  private final XboxController m_Controller = new XboxController(OperatorConstants.kDriverControllerPort);
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();    
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    // new Trigger(m_exampleSubsystem::exampleCondition)
        // .onTrue(new ExampleCommand(m_exampleSubsystem));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    //work but no limit set
    m_driverController.b().whileTrue(cLift);
    m_driverController.a().whileTrue(cDrop);
    //x and y work but opposite LMAOOO
    m_driverController.x().whileTrue(m_UpperArm.Extend());
    m_driverController.y().whileTrue(m_UpperArm.Retract());
    //no work
    m_driverController.button(7).whileTrue(m_claw.Clasp());
    m_driverController.button(8).whileTrue(m_claw.Open());
    //WORKS but doesn't read encoder value
    m_driverController.button(6).onTrue(new Foward(m_dt, 5));
    m_driverController.button(5).onTrue(new Reverse(m_dt, 5));
    m_driverController.button(9).onTrue(new Rotate(m_dt, 5));
    //doesn't work
    // m_driverController.button(9).onTrue(new PIDFoward(m_dt, 120));

    //WORKS
    m_dt.setDefaultCommand(new Drive(m_dt, m_Controller));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_ExampleSubsystem);
  }
}
