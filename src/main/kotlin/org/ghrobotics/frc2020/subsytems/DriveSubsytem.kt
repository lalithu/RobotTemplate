package org.ghrobotics.frc2020.subsytems

import org.ghrobotics.frc2020.Constants
import org.ghrobotics.frc2020.commands.TeleopDriveCommand
import org.ghrobotics.lib.commands.FalconSubsystem
import org.ghrobotics.lib.mathematics.units.amp
import org.ghrobotics.lib.mathematics.units.millisecond
import org.ghrobotics.lib.motors.ctre.FalconSRX

object DriveSubsytem : FalconSubsystem() {


    private val leftMaster = FalconSRX(Constants.kDriveLeftMasterId, Constants.kDriveNativeUnitModel )
    private val leftSlave = FalconSRX(Constants.kDriveLeftSlaveId, Constants.kDriveNativeUnitModel )

    private val rightMaster = FalconSRX(Constants.kDriveRightMasterId, Constants.kDriveNativeUnitModel )
    private val rightSlave = FalconSRX(Constants.kDriveRightSlaveId, Constants.kDriveNativeUnitModel )

    init {

        //Assigning the Slave Motors to the Masters
        leftSlave.follow(leftMaster)
        rightSlave.follow(rightMaster)

        //To Correct the Clockwise and Counter-Clockwise directions of the Rightside and Leftside Wheels
        leftSlave.outputInverted = false
        leftMaster.outputInverted = false

        rightSlave.outputInverted = true
        rightMaster.outputInverted = true

        //Limiting the Voltage that can be sent to each of the motors
        leftMaster.configCurrentLimit(true, FalconSRX.CurrentLimitConfig(
          90.amp,
                500.millisecond,
                38.amp

        )
        )

        rightMaster.configCurrentLimit(true, FalconSRX.CurrentLimitConfig(
                90.amp,
                500.millisecond,
                38.amp

        )
        )

        defaultCommand = TeleopDriveCommand()




    }

    fun set(left: Double, right: Double) {
        leftMaster.setDutyCycle(left)
        rightMaster.setDutyCycle(right)
    }

}