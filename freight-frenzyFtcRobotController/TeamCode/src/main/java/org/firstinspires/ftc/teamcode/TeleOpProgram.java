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
    double xNew = 1.0;
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
    boolean bumperWasHeld = false;
    boolean slow = false;
    String currentMode = "Speed";

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
        double x = 1.0;


      //  boolean wasPressed = gamepad1.right_bumper && !bumperWasHeld;
//
//        if(wasPressed){
//
////            if(slow){
////                x*=3;
////                slow = !slow;
////                currentMode = "Speed";
////            }else{
////                x/=3;
////                slow = !slow;
////                currentMode = "Precision";
////            }
//
//            currentMode = "Very Amogus";
//
//            if(slow){
//                slow = false;
//                x=1.0;
//                currentMode = "Speed";
//            }else{
//                slow = true;
//                x/=3;
//                currentMode="Precision";
//            }
//        }else{
//            currentMode = "Not Amogus";
//        }
//
//        bumperWasHeld = gamepad1.right_bumper;

        if(gamepad1.right_bumper){
            x/=3;
            currentMode = "Precision";
        }else{
            x = 1.0;
            currentMode = "Sped. I am sped";
        }

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

//        double y = gamepad1.left_stick_y; // Remember, this is reversed!
//        double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
//        double rx = gamepad1.right_stick_x;
//
//        // Denominator is the largest motor power (absolute value) or 1
//        // This ensures all the powers maintain the same ratio, but only when
//        // at least one is out of the range [-1, 1]
//
//        if(Math.abs(x)>0.0 && (Math.abs(y)<Math.abs(0.5))){
//            if(x<0){
//            robot.lbDrive.setPower(xNew);
//            robot.rbDrive.setPower(-xNew);
//            robot.lfDrive.setPower(-xNew);
//            robot.rfDrive.setPower(xNew);
//        }else if(x>0){
//            robot.lbDrive.setPower(-xNew);
//            robot.rbDrive.setPower(xNew);
//            robot.lfDrive.setPower(xNew);
//            robot.rfDrive.setPower(-xNew);
//        }
//        }
//        else{
//            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
//            double frontLeftPower = (y + x + rx) / denominator;
//            double backLeftPower = (y - x + rx) / denominator;
//            double frontRightPower = (y - x - rx) / denominator;
//            double backRightPower = (y + x - rx) / denominator;
//            robot.lfDrive.setPower(frontLeftPower);
//            robot.lbDrive.setPower(backLeftPower);
//            robot.rfDrive.setPower(frontRightPower);
//            robot.rbDrive.setPower(backRightPower);
//        }





        robot.arm.setPower((gamepad2.right_trigger/2) - (gamepad2.left_trigger/2));
        //robot.carousel.setPower(((gamepad2.right_bumper) ? 1.0: 0.0)-((gamepad2.left_bumper) ? -1.0: 0.0));
        if(gamepad2.right_bumper){
            robot.carousel.setPower(1.0);
        }else{
            robot.carousel.setPower(0.0);
        }

        if(gamepad2.left_bumper){
            robot.carousel.setPower(-1.0);
        }else{
            robot.carousel.setPower(0.0);
        }

        robot.Dropper.setPosition((gamepad2.a) ? 0.0 : 1.0);
        robot.wrist.setPosition((gamepad2.b) ? 0.0 : 1.0);


        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Current Mode:", currentMode);

    }


    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

    }


