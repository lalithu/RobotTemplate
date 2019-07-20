package org.ghrobotics.frc2020.subsytems

import asSource
import com.ctre.phoenix.motorcontrol.FeedbackDevice
import com.ctre.phoenix.sensors.PigeonIMU
import com.team254.lib.physics.DifferentialDrive
import org.ghrobotics.frc2020.Constants
import org.ghrobotics.frc2020.commands.TeleopDriveCommand
import org.ghrobotics.lib.localization.Localization
import org.ghrobotics.lib.localization.TankEncoderLocalization
import org.ghrobotics.lib.mathematics.twodim.control.RamseteTracker
import org.ghrobotics.lib.mathematics.twodim.control.TrajectoryTracker
import org.ghrobotics.lib.mathematics.units.amp
import org.ghrobotics.lib.mathematics.units.millisecond
import org.ghrobotics.lib.motors.LinearFalconMotor
import org.ghrobotics.lib.motors.ctre.FalconSRX
import org.ghrobotics.lib.subsystems.drive.TankDriveSubsystem

object DriveSubsystem : TankDriveSubsystem() {

    override val leftMotor = FalconSRX(Constants.kDriveLeftMasterId, Constants.kDriveNativeUnitModel)
    override val rightMotor = FalconSRX(Constants.kDriveRightMasterId, Constants.kDriveNativeUnitModel)

    private val leftSlave = FalconSRX(Constants.kDriveLeftSlaveId, Constants.kDriveNativeUnitModel)
    private val rightSlave = FalconSRX(Constants.kDriveRightSlaveId, Constants.kDriveNativeUnitModel)

    private val gyro = PigeonIMU(17)

    init {
        leftSlave.follow(leftMotor)
        rightSlave.follow(rightMotor)

        leftMotor.outputInverted = false
        leftSlave.outputInverted = false

        rightMotor.outputInverted = false
        rightSlave.outputInverted = false


        leftMotor.configCurrentLimit(
                true, FalconSRX.CurrentLimitConfig(
        70.amp,
        500.millisecond,
        38.amp
        )
        )

        rightMotor.configCurrentLimit(
                true, FalconSRX.CurrentLimitConfig(
                70.amp,
                500.millisecond,
                38.amp
        )
        )

        leftMotor.feedbackSensor = FeedbackDevice.QuadEncoder
        rightMotor.feedbackSensor = FeedbackDevice.QuadEncoder

        leftMotor.talonSRX.config_kP(0, 2.0)
        rightMotor.talonSRX.config_kP(0, 2.0)

        defaultCommand = TeleopDriveCommand()



    }



    override val differentialDrive get() = TODO()

    override val localization = TankEncoderLocalization(
            gyro.asSource(),
    { leftMotor.encoder.position },
    { rightMotor.encoder.position}

    )


    override val trajectoryTracker = RamseteTracker(2.0, 0.7)

}