/*
 * FRC Team 5190
 * Green Hope Falcons
 */

package org.ghrobotics.frc2020

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard
import org.ghrobotics.frc2020.subsytems.DriveSubsystem
import org.ghrobotics.lib.mathematics.twodim.geometry.Pose2d
import org.ghrobotics.lib.mathematics.twodim.trajectory.DefaultTrajectoryGenerator
import org.ghrobotics.lib.mathematics.twodim.trajectory.constraints.CentripetalAccelerationConstraint
import org.ghrobotics.lib.mathematics.units.degree
import org.ghrobotics.lib.mathematics.units.derivedunits.acceleration
import org.ghrobotics.lib.mathematics.units.derivedunits.velocity
import org.ghrobotics.lib.mathematics.units.feet
import org.ghrobotics.lib.wrappers.FalconTimedRobot

object Robot : FalconTimedRobot() {

    val trajectory = DefaultTrajectoryGenerator.generateTrajectory(
            wayPoints = listOf(
                    Pose2d(5.527.feet, 9.771.feet, 0.degree),
                    Pose2d(16.172.feet, 2.465.feet, -28.994.degree)
            ),
            constraints = listOf(
                    CentripetalAccelerationConstraint(9.0.feet.acceleration)

            ),
            startVelocity = 0.0.feet.velocity,
            endVelocity = 0.0.feet.velocity,
            maxVelocity = 12.0.feet.velocity,
            maxAcceleration = 7.0.feet.acceleration,
            reversed = false
    )

    // Constructor of the Robot class.
    init {
        +DriveSubsystem
    }

    // Runs once when robot boots up
    override fun robotInit() {}


    // Runs once when autonomous period starts
    override fun autonomousInit() {
        DriveSubsystem.followTrajectory(trajectory).schedule()
    }

    // Runs once when teleop period starts
    override fun teleopInit() {}

    // Runs once when robot is disabled
    override fun disabledInit() {}


    // Runs every 20 ms when robot is on
    override fun robotPeriodic() {
        Shuffleboard.update()
    }

    // Runs every 20 ms when autonomous is enabled
    override fun autonomousPeriodic() {}

    // Runs every 20 ms when teleop is enabled
    override fun teleopPeriodic() {}

    // Runs every 20 ms when robot is disabled
    override fun disabledPeriodic() {}
}