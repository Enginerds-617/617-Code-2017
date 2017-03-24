package org.usfirst.frc.team617.robot;

import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Spark;
import com.ctre.CANTalon;

/**
 * This is a demo program showing the use of the RobotDrive class. The
 * SampleRobot class is the base of a robot application that will automatically
 * call your Autonomous and OperatorControl methods at the right time as
 * controlled by the switches on the driver station or the field controls.
 *
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SampleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 *
 * WARNING: While it may look like a good choice to use for your code if you're
 * inexperienced, don't. Unless you know what you are doing, complex code will
 * be much more difficult under this system. Use IterativeRobot or Command-Based
 * instead if you're new.
 */
public class Robot extends SampleRobot {

	Joystick stick = new Joystick(0);
	final String straight = "Default";
	final String middle = "My Auto";
	SendableChooser<String> chooser = new SendableChooser<>();
	Joystick  stick1;
	Joystick  stick2;
	CANTalon  right1;
	CANTalon  left1;
	Spark     right2;
	Spark     left2;
	Talon     winch1;
	Talon     winch2;

	public Robot() {
		stick1 = new Joystick(0);
		stick2 = new Joystick(1);
		right1 = new CANTalon(0);
		left1  = new CANTalon(1);
		right2 = new Spark(2);
		left2  = new Spark(3);
		winch1 = new Talon(0);
		winch2 = new Talon(1);
	}

	@Override
	public void robotInit() {
		chooser.addDefault("Default Auto", straight);
		chooser.addObject("My Auto", middle);
		SmartDashboard.putData("Auto modes", chooser);
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * if-else structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomous() {
		String autoSelected = chooser.getSelected();
		// String autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + autoSelected);

		switch (autoSelected) {
		case middle:
			left1.set(.5);
			left2.set(.5);
			right1.set(.5);
			right2.set(.5);
			try {
			Thread.sleep(3000);
			}catch(Exception e){
			e.printStackTrace();
			}
			left1.set(0);
			left2.set(0);
			right1.set(0);
			right2.set(0);
			break;
		case straight:
		default:
			left1.set(.5);
			left2.set(.5);
			right1.set(.5);
			right2.set(.5);
			try {
			Thread.sleep(6000);
			}catch(Exception e){
			e.printStackTrace();
			}
			left1.set(0);
			left2.set(0);
			right1.set(0);
			right2.set(0);
			break;
		}
	}

	/**
	 * Runs the motors with arcade steering.
	 */
	@Override
	public void operatorControl() {
		
		int Comstock = 1;
		
		while (isOperatorControl() && isEnabled()) {
			double Booker    = stick1.getY();
			double Elizabeth = stick2.getY();
			boolean winchPressed = stick1.getRawButton(1);
			
			
			
			if(stick1.getRawButton(4)) {
				Comstock++;
			}
			else if(stick1.getRawButton(5)) {
				Comstock--;
			}
			
			if(Comstock > 1) {
				left1.set(-Booker/Comstock);
				left1.set(-Booker/Comstock);				
				right1.set(-Elizabeth/Comstock);
				right2.set(-Elizabeth/Comstock);
			}
			else if(Comstock < 1) {
				left1.set(Booker/Comstock);
				left2.set(Booker/Comstock);
				right1.set(Elizabeth/Comstock);
				right2.set(Elizabeth/Comstock);
			}
			else {
				left1.set(0);
				left2.set(0);
				right1.set(0);
				right2.set(0);
			}
			
			if(winchPressed) {
				winch1.set(.5);
				winch2.set(.5);
			}
			
			Timer.delay(0.005); // wait for a motor update time
		}
	}

	/**
	 * Runs during test mode
	 */
	@Override
	public void test() {
	}
}
