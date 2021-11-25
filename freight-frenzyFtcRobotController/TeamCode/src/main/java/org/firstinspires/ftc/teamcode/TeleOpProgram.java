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
        double lfPower;
        double lbPower;
        double rfPower;
        double rbPower;

        // Choose to drive using either Tank Mode, or POV Mode
        // Comment out the method that's not used.  The default below is POV.

        // POV Mode uses left stick to go forward, and right stick to turn.
        // - This uses basic math to combine motions and is easier to drive straight.
        double drive = gamepad1.left_stick_y;
        double turn  =  gamepad1.right_stick_x;
        if (gamepad1.a) {
            robot.Dropper.setPosition(0.5);
        } else {
            robot.Dropper.setPosition(1.0);
        }

        if(gamepad1.b){
            robot.wrist.setPosition(0.5);
        }else{
            robot.wrist.setPosition(1.0);
        }

//        if(gamepad1.right_trigger>0){
//            intakeLifter.setPower(gamepad1.right_trigger);
//        }
        lfPower    = Range.clip(drive + turn, -1.0, 1.0) ;
        lbPower    = Range.clip(drive + turn, -1.0, 1.0) ;
        rfPower   =  Range.clip(drive - turn, -1.0, 1.0) ;
        rbPower   =  Range.clip(drive - turn, -1.0, 1.0) ;
        // Tank Mode uses one stick to control each wheel.
        // - This requires no math, but it is hard to drive forward slowly and keep straight.
        // leftPower  = -gamepad1.left_stick_y ;
        // rightPower = -gamepad1.right_stick_y ;

        // Send calculated power to wheels
        robot.lfDrive.setPower(lfPower);
        robot.lbDrive.setPower(lbPower);
        robot.rbDrive.setPower(rbPower);
        robot.rfDrive.setPower(rfPower);


        robot.arm.setPower((gamepad1.right_trigger/2) - (gamepad1.left_trigger/2));


        if(gamepad1.dpad_up){
            robot.carousel.setPower(1.0);
        }else{
            robot.carousel.setPower(0.0);
        }



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
