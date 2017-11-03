/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

#include "Claw.h"

#include <LiveWindow/LiveWindow.h>

Claw::Claw()
    : frc::Subsystem("Claw") {
	// Let's show everything on the LiveWindow
	// frc::LiveWindow::GetInstance()->AddActuator("Claw", "Motor", &motor);
}

void Claw::InitDefaultCommand() {}

void Claw::Open() {
	m_motor.Set(-1);
}

void Claw::Close() {
	m_motor.Set(1);
}

void Claw::Stop() {
	m_motor.Set(0);
}

bool Claw::IsGripping() {
	return m_contact.Get();
}

void Claw::Log() {}
