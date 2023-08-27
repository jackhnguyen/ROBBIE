// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    
    public static final int kDriverControllerPort = 0;
  }
  public static class ArmConstants{
    public static final double kArmSpeed = 0.5;
    public static final double kUpperArmSpeed = 0.5;
  }
  public static class DTConstants{
    public static final int kLEFT_MOTOR_ID_1 = 4;
    public static final int kLEFT_MOTOR_ID_2 = 3;
    public static final int kRIGHT_MOTOR_ID_1 = 2;
    public static final int kRIGHT_MOTOR_ID_2 = 1;

    public static final int kWHEEL__DIAMETER_INCHES = 6;
    public static final int kCOUNTS_PER_REV = 42;
    public static final double kENCODER_COUNT_TO_INCHES = Math.PI*DTConstants.kWHEEL__DIAMETER_INCHES;

    public static final int kP = 0;
    public static final int kI = 0;
    public static final int kD = 0;

  }
}
