package org.ghrobotics.frc2020.commands

import edu.wpi.first.wpilibj.GenericHID
import edu.wpi.first.wpilibj.experimental.command.SendableCommandBase
import org.ghrobotics.frc2020.Controls
import org.ghrobotics.frc2020.subsytems.DriveSubsytem
import org.ghrobotics.lib.wrappers.hid.getX
import org.ghrobotics.lib.wrappers.hid.getY

class TeleopDriveCommand  : SendableCommandBase() {

    init {
        addRequirements(DriveSubsytem)
    }

    //Getting Joystick Return Values
    val leftJoystick = Controls.driverController.getY(GenericHID.Hand.kLeft)
    val rightJoystick = Controls.driverController.getY(GenericHID.Hand.kRight)


    override fun execute() {
        DriveSubsytem.set(-leftJoystick(), -rightJoystick())
    }

    override fun isFinished(): Boolean {
        return false
    }

}