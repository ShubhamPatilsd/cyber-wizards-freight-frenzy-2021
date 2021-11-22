package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="TeleOpProgram", group="Iterative Opmode")
public class TeleOpProgram extends OpMode
{
    // Declare OpMode members.

    Robot robot = new Robot();
    private ElapsedTime runtime = new ElapsedTime();
    double x = 1.0;
   // private DcMotor intakeLifter = null;
    // this is a comment

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        robot.init(hardwareMap);

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        

    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        // Setup a variable for each drive wheel to save power level for telemetry

        if(gamepad1.left_stick_y<0){
            robot.lbDrive.setPower(-x);
            robot.rbDrive.setPower(-x);
            robot.lfDrive.setPower(-x);
            robot.rfDrive.setPower(-x);
        }else if(gamepad1.left_stick_y>0) {
            robot.lbDrive.setPower(x);
            robot.rbDrive.setPower(x);
            robot.lfDrive.setPower(x);
            robot.rfDrive.setPower(x);
        } else if(gamepad1.left_trigger>0){
            robot.lbDrive.setPower(x);
            robot.rbDrive.setPower(-x);
            robot.lfDrive.setPower(-x);
            robot.rfDrive.setPower(x);
        }else if(gamepad1.right_trigger>0){
            robot.lbDrive.setPower(-x);
            robot.rbDrive.setPower(x);
            robot.lfDrive.setPower(x);
            robot.rfDrive.setPower(-x);
        }else if(gamepad1.right_stick_x<0){
            robot.lbDrive.setPower(-x);
            robot.rbDrive.setPower(x);
            robot.lfDrive.setPower(-x);
            robot.rfDrive.setPower(x);
        }else if(gamepad1.right_stick_x>0){
            robot.lbDrive.setPower(x);
            robot.rbDrive.setPower(-x);
            robot.lfDrive.setPower(x);
            robot.rfDrive.setPower(-x);
        } else {
            robot.lbDrive.setPower(0.0);
            robot.rbDrive.setPower(0.0);
            robot.lfDrive.setPower(0.0);
            robot.rfDrive.setPower(0.0);
        }


        robot.arm.setPower(gamepad1.right_trigger - gamepad1.left_trigger);
        robot.carousel.setPower((gamepad1.dpad_right) ? 1.0 : 0.0);
        robot.carousel.setPower((gamepad1.dpad_left) ? -1.0 : 0.0);

        robot.Dropper.setPosition((gamepad1.a) ? 0.0 : 1.0);
        robot.wrist.setPosition((gamepad1.b) ? 0.0 : 1.0);


        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
