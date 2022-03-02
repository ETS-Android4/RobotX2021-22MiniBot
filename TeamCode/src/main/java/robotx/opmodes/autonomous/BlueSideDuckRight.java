package robotx.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import robotx.modules.DuckRotation;
import robotx.modules.IntakeSystem;
import robotx.modules.EncoderTester;
import robotx.modules.MecanumDrive;
import robotx.modules.OrientationDrive;

@Autonomous(name = "BlueSideDuckRight", group = "Default")

public class BlueSideDuckRight extends LinearOpMode {

    //private ElapsedTime runtime = new ElapsedTime();

    //Modules being imported
    MecanumDrive mecanumDrive;
    DuckRotation duckRotation;
    IntakeSystem intakeSystem;
    OrientationDrive orientationDrive;
    EncoderTester liftSystem;

    public Servo duckServo;

    @Override

    public void runOpMode () {

        //Text at bottom of phone
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        mecanumDrive = new MecanumDrive(this);
        mecanumDrive.init();

        duckRotation = new DuckRotation(this);
        duckRotation.init();

        intakeSystem = new IntakeSystem(this);
        intakeSystem.init();

        orientationDrive = new OrientationDrive(this);
        orientationDrive.init();

        liftSystem = new EncoderTester(this);
        liftSystem.init();

        mecanumDrive.start();
        duckRotation.start();
        intakeSystem.start();
        orientationDrive.start();

        mecanumDrive.frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        mecanumDrive.frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        mecanumDrive.backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        mecanumDrive.backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        liftSystem.liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        liftSystem.liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mecanumDrive.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mecanumDrive.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mecanumDrive.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mecanumDrive.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData(">", "Press Play to Start Op Mode");
        telemetry.update();

        waitForStart();
        //runtime.reset();

        int SleepTime = 10000;

        if (opModeIsActive()) {
            //Movement
            DriveForwardDistance(0.1,2000);
            sleep(SleepTime);

            DriveBackwardDistance(0.5,300);
            sleep(SleepTime);

            StrafeLeftDistance(0.5,300);
            sleep(SleepTime);

            StrafeRightDistance(0.5,300);
            sleep(SleepTime);


        }
    }

    //Controls
    public void DriveForward(double power, int time) {
        mecanumDrive.frontLeft.setPower(power);  //top left when rev is down and ducky wheel is right
        mecanumDrive.frontRight.setPower(power); //bottom left
        mecanumDrive.backLeft.setPower(-power);   //top right
        mecanumDrive.backRight.setPower(power); // bottom right
        sleep(time);
        mecanumDrive.frontLeft.setPower(0);
        mecanumDrive.frontRight.setPower(0);
        mecanumDrive.backLeft.setPower(0);
        mecanumDrive.backRight.setPower(0);
    }

    public void DriveBackward(double power, int time) {
        mecanumDrive.frontLeft.setPower(-power);
        mecanumDrive.frontRight.setPower(-power);
        mecanumDrive.backLeft.setPower(power);
        mecanumDrive.backRight.setPower(-power);
        sleep(time);
        mecanumDrive.frontLeft.setPower(0);
        mecanumDrive.frontRight.setPower(0);
        mecanumDrive.backLeft.setPower(0);
        mecanumDrive.backRight.setPower(0);
    }

    public void StrafeLeft(double power, int time) {
        mecanumDrive.frontLeft.setPower(-power);
        mecanumDrive.frontRight.setPower(power);
        mecanumDrive.backLeft.setPower(-power);
        mecanumDrive.backRight.setPower(-power);
        sleep(time);
        mecanumDrive.frontLeft.setPower(0);
        mecanumDrive.frontRight.setPower(0);
        mecanumDrive.backLeft.setPower(0);
        mecanumDrive.backRight.setPower(0);
    }

    public void StrafeRight(double power, int time) {
        mecanumDrive.frontLeft.setPower(power);
        mecanumDrive.frontRight.setPower(-power);
        mecanumDrive.backLeft.setPower(power);
        mecanumDrive.backRight.setPower(power);
        sleep(time);
        mecanumDrive.frontLeft.setPower(0);
        mecanumDrive.frontRight.setPower(0);
        mecanumDrive.backLeft.setPower(0);
        mecanumDrive.backRight.setPower(0);
    }

    public void TurnLeft(double power, int time) {
        mecanumDrive.frontLeft.setPower(power);
        mecanumDrive.frontRight.setPower(-power);
        mecanumDrive.backLeft.setPower(-power);
        mecanumDrive.backRight.setPower(-power);
        sleep(time);
        mecanumDrive.frontLeft.setPower(0);
        mecanumDrive.frontRight.setPower(0);
        mecanumDrive.backLeft.setPower(0);
        mecanumDrive.backRight.setPower(0);
    }

    public void TurnRight(double power, int time) {
        mecanumDrive.frontLeft.setPower(-power);
        mecanumDrive.frontRight.setPower(power);
        mecanumDrive.backLeft.setPower(power);
        mecanumDrive.backRight.setPower(power);
        sleep(time);
        mecanumDrive.frontLeft.setPower(0);
        mecanumDrive.frontRight.setPower(0);
        mecanumDrive.backLeft.setPower(0);
        mecanumDrive.backRight.setPower(0);
    }

    public void DuckSpin(double power, int time){
        duckRotation.duckMotor.setPower(power);
        sleep(time);
        duckRotation.duckMotor.setPower(0);
    }

    public void LiftPlatform(double power, int time){
        liftSystem.liftMotor.setPower(power);
        sleep(time);
        liftSystem.liftMotor.setPower(0);
    }
    public void LowerPlatform(double power, int time){
        liftSystem.liftMotor.setPower(-power);
        sleep(time);
        liftSystem.liftMotor.setPower(0);
    }
    public void DriveForwardDistance(double power, int EncoderTicks){

        //int EncoderTicks = (distance * 12);


        mecanumDrive.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mecanumDrive.backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mecanumDrive.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mecanumDrive.backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        mecanumDrive.frontLeft.setTargetPosition(EncoderTicks);
        mecanumDrive.frontRight.setTargetPosition(EncoderTicks);
        mecanumDrive.backLeft.setTargetPosition(EncoderTicks);
        mecanumDrive.backRight.setTargetPosition(EncoderTicks);

        // Set drive power
        mecanumDrive.frontRight.setPower(-power);
        mecanumDrive.frontLeft.setPower(power);
        mecanumDrive.backRight.setPower(power);
        mecanumDrive.backLeft.setPower(power);

        // Set to RUN_TO_POSITION_MODE
        mecanumDrive.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        mecanumDrive.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        mecanumDrive.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        mecanumDrive.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);




        while (mecanumDrive.frontLeft.isBusy() && mecanumDrive.frontRight.isBusy() && mecanumDrive.backLeft.isBusy() && mecanumDrive.backRight.isBusy() )
        {
            // Wait until target position is reached
        }

        // Stop and change modes back to normal
        mecanumDrive.frontRight.setPower(0);
        mecanumDrive.frontLeft.setPower(0);
        mecanumDrive.backRight.setPower(0);
        mecanumDrive.backLeft.setPower(0);


        mecanumDrive.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mecanumDrive.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mecanumDrive.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mecanumDrive.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


    }
    public void DriveBackwardDistance(double power, int distance) {
        // Reset encoders
        mecanumDrive.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mecanumDrive.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mecanumDrive.backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mecanumDrive.backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Set target position
        mecanumDrive.frontLeft.setTargetPosition(-distance);
        mecanumDrive.frontRight.setTargetPosition(-distance);
        mecanumDrive.backLeft.setTargetPosition(-distance);
        mecanumDrive.backRight.setTargetPosition(-distance);

        // Set to RUN_TO_POSITION_MODE
        mecanumDrive.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        mecanumDrive.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        mecanumDrive.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        mecanumDrive.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Set drive power
        mecanumDrive.frontRight.setPower(power);
        mecanumDrive.frontLeft.setPower(-power);
        mecanumDrive.backRight.setPower(-power);
        mecanumDrive.backLeft.setPower(-power);

        while (mecanumDrive.frontLeft.isBusy() && mecanumDrive.frontRight.isBusy() && mecanumDrive.backLeft.isBusy() && mecanumDrive.backRight.isBusy() )
        {
            // Wait until target position is reached
        }

        // Stop and change modes back to normal
        mecanumDrive.frontRight.setPower(0);
        mecanumDrive.frontLeft.setPower(0);
        mecanumDrive.backRight.setPower(0);
        mecanumDrive.backLeft.setPower(0);

        mecanumDrive.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mecanumDrive.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mecanumDrive.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mecanumDrive.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }
    public void StrafeLeftDistance(double power, int distance) {
        // Reset encoders
        mecanumDrive.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mecanumDrive.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mecanumDrive.backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mecanumDrive.backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Set target position
        mecanumDrive.frontLeft.setTargetPosition(distance);
        mecanumDrive.frontRight.setTargetPosition(distance);
        mecanumDrive.backLeft.setTargetPosition(distance);
        mecanumDrive.backRight.setTargetPosition(-distance);

        // Set to RUN_TO_POSITION_MODE
        mecanumDrive.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        mecanumDrive.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        mecanumDrive.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        mecanumDrive.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Set drive power
        mecanumDrive.frontRight.setPower(power);
        mecanumDrive.frontLeft.setPower(power);
        mecanumDrive.backRight.setPower(power);
        mecanumDrive.backLeft.setPower(power);

        while (mecanumDrive.frontLeft.isBusy() && mecanumDrive.frontRight.isBusy() && mecanumDrive.backLeft.isBusy() && mecanumDrive.backRight.isBusy() )
        {
            // Wait until target position is reached
        }

        // Stop and change modes back to normal
        mecanumDrive.frontRight.setPower(0);
        mecanumDrive.frontLeft.setPower(0);
        mecanumDrive.backRight.setPower(0);
        mecanumDrive.backLeft.setPower(0);

        mecanumDrive.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mecanumDrive.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mecanumDrive.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mecanumDrive.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }
    public void StrafeRightDistance(double power, int distance) {
        // Reset encoders
        mecanumDrive.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mecanumDrive.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mecanumDrive.backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mecanumDrive.backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Set target position
        mecanumDrive.frontLeft.setTargetPosition(-distance);
        mecanumDrive.frontRight.setTargetPosition(distance);
        mecanumDrive.backLeft.setTargetPosition(distance);
        mecanumDrive.backRight.setTargetPosition(-distance);

        // Set to RUN_TO_POSITION_MODE
        mecanumDrive.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        mecanumDrive.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        mecanumDrive.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        mecanumDrive.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Set drive power
        mecanumDrive.frontRight.setPower(-power);
        mecanumDrive.frontLeft.setPower(power);
        mecanumDrive.backRight.setPower(-power);
        mecanumDrive.backLeft.setPower(-power);

        while (mecanumDrive.frontLeft.isBusy() && mecanumDrive.frontRight.isBusy() && mecanumDrive.backLeft.isBusy() && mecanumDrive.backRight.isBusy() )
        {
            // Wait until target position is reached
        }

        // Stop and change modes back to normal
        mecanumDrive.frontRight.setPower(0);
        mecanumDrive.frontLeft.setPower(0);
        mecanumDrive.backRight.setPower(0);
        mecanumDrive.backLeft.setPower(0);

        mecanumDrive.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mecanumDrive.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mecanumDrive.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mecanumDrive.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }


}


