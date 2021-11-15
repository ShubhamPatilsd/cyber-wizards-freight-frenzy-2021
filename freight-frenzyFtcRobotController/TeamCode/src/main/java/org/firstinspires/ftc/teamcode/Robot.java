package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.HardwareMap;

import com.qualcomm.robotcore.util.Range;


public class Robot {
    // Declare OpMode members.
    public ElapsedTime runtime = new ElapsedTime();
    public DcMotor lfDrive = null;
    public DcMotor rfDrive = null;
    public DcMotor lbDrive = null;
    public DcMotor rbDrive = null;
    public DcMotor arm = null;
    public DcMotor carousel = null;
    public Servo Dropper = null;
    public Servo wrist = null;

    public void init(HardwareMap hardwareMap){
        rfDrive = hardwareMap.get(DcMotor.class, "rfDrive");
        rbDrive = hardwareMap.get(DcMotor.class, "rbDrive");
        lfDrive  = hardwareMap.get(DcMotor.class, "lfDrive");
        lbDrive  = hardwareMap.get(DcMotor.class, "lbDrive");
        Dropper  = hardwareMap.get(Servo.class, "dropper");
        arm  = hardwareMap.get(DcMotor.class, "arm");
        carousel  = hardwareMap.get(DcMotor.class, "carousel");
        wrist = hardwareMap.get(Servo.class, "wrist");

        //intakeLifter  = hardwareMap.get(DcMotor.class, "lifter");
        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        lfDrive.setDirection(DcMotor.Direction.FORWARD);
        lbDrive.setDirection(DcMotor.Direction.FORWARD);
        rfDrive.setDirection(DcMotor.Direction.REVERSE);
        rbDrive.setDirection(DcMotor.Direction.REVERSE);

        arm.setDirection(DcMotorSimple.Direction.FORWARD);
        carousel.setDirection(DcMotorSimple.Direction.FORWARD);
        // intakeLifter.setDirection(DcMotor.Direction.FORWARD);
        Dropper.setPosition(1.0);
    }

}
