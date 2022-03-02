package robotx.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import robotx.modules.DuckRotation;
import robotx.modules.EncoderTester;
import robotx.modules.IntakeSystem;
import robotx.modules.MecanumDrive;
import robotx.modules.OrientationDrive;

@Autonomous(name = "BlueSideDuckLeft", group = "Default")

public class BlueSideDuckLeft extends LinearOpMode {

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

        telemetry.addData(">", "Press Play to Start Op Mode");
        telemetry.update();

        waitForStart();
        //runtime.reset();
        int sleeptime=2000;

        if (opModeIsActive()) {
            //Movement

            DriveForward(0.5,500);


            sleep(sleeptime);
            sleep(sleeptime);
            sleep(sleeptime);
            sleep(sleeptime);
            sleep(sleeptime);
            sleep(sleeptime);
            sleep(sleeptime);


            LiftSystem();
            sleep(sleeptime);

            LiftFirstLevel();
            sleep(sleeptime);
            LowerFirstLevel();
            sleep(sleeptime);

            LiftSecondLevel();
            sleep(sleeptime);
            LowerSecondLevel();
            sleep(sleeptime);

            LiftThirdLevel();
            sleep(sleeptime);
            LowerThirdLevel();
            sleep(sleeptime);

            sleep(100000);

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

    public void LiftSystem(){

        liftSystem.liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        liftSystem.liftMotor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        liftSystem.liftMotor.setPower(0);
        liftSystem.liftMotor2.setPower(0);
    }

    //One full revolution is 300 ticks

    public void LiftFirstLevel(){

        liftSystem.liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftSystem.liftMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        liftSystem.liftMotor.setTargetPosition(300);
        liftSystem.liftMotor2.setTargetPosition(300);

        liftSystem.liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        liftSystem.liftMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        
        liftSystem.liftMotor.setPower(0.7);
        liftSystem.liftMotor2.setPower(0.7);

        while (liftSystem.liftMotor.isBusy() && liftSystem.liftMotor2.isBusy() )
        {

        }

        sleep(10);
        liftSystem.liftMotor.setPower(0);
        liftSystem.liftMotor2.setPower(0);
        sleep(10);

        liftSystem.liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        liftSystem.liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void LiftSecondLevel(){

        liftSystem.liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftSystem.liftMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        liftSystem.liftMotor.setTargetPosition(800);
        liftSystem.liftMotor2.setTargetPosition(800);

        liftSystem.liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        liftSystem.liftMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        liftSystem.liftMotor.setPower(0.7);
        liftSystem.liftMotor2.setPower(0.7);

        while (liftSystem.liftMotor.isBusy() && liftSystem.liftMotor2.isBusy() )
        {

        }

        sleep(10);
        liftSystem.liftMotor.setPower(0);
        liftSystem.liftMotor2.setPower(0);
        sleep(10);

        liftSystem.liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        liftSystem.liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void LiftThirdLevel(){

        liftSystem.liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftSystem.liftMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        liftSystem.liftMotor.setTargetPosition(1900);
        liftSystem.liftMotor2.setTargetPosition(1900);

        liftSystem.liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        liftSystem.liftMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        liftSystem.liftMotor.setPower(0.7);
        liftSystem.liftMotor2.setPower(0.7);

        while (liftSystem.liftMotor.isBusy() && liftSystem.liftMotor2.isBusy() )
        {

        }

        sleep(10);
        liftSystem.liftMotor.setPower(0);
        liftSystem.liftMotor2.setPower(0);
        sleep(10);

        liftSystem.liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        liftSystem.liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void LowerFirstLevel(){

        liftSystem.liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftSystem.liftMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        liftSystem.liftMotor.setTargetPosition(-300);
        liftSystem.liftMotor2.setTargetPosition(-300);

        liftSystem.liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        liftSystem.liftMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        liftSystem.liftMotor.setPower(-0.7);
        liftSystem.liftMotor2.setPower(-0.7);

        while (liftSystem.liftMotor.isBusy() && liftSystem.liftMotor2.isBusy() )
        {

        }

        sleep(10);
        liftSystem.liftMotor.setPower(0);
        liftSystem.liftMotor2.setPower(0);
        sleep(10);

        liftSystem.liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        liftSystem.liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void LowerSecondLevel(){

        liftSystem.liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftSystem.liftMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        liftSystem.liftMotor.setTargetPosition(-800);
        liftSystem.liftMotor2.setTargetPosition(-800);

        liftSystem.liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        liftSystem.liftMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        liftSystem.liftMotor.setPower(-0.7);
        liftSystem.liftMotor2.setPower(-0.7);

        while (liftSystem.liftMotor.isBusy() && liftSystem.liftMotor2.isBusy() )
        {

        }

        sleep(10);
        liftSystem.liftMotor.setPower(0);
        liftSystem.liftMotor2.setPower(0);
        sleep(10);

        liftSystem.liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        liftSystem.liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void LowerThirdLevel(){

        liftSystem.liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftSystem.liftMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        liftSystem.liftMotor.setTargetPosition(-1900);
        liftSystem.liftMotor2.setTargetPosition(-1900);

        liftSystem.liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        liftSystem.liftMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        liftSystem.liftMotor.setPower(-0.7);
        liftSystem.liftMotor2.setPower(-0.7);

        while (liftSystem.liftMotor.isBusy() && liftSystem.liftMotor2.isBusy() )
        {

        }

        sleep(10);
        liftSystem.liftMotor.setPower(0);
        liftSystem.liftMotor2.setPower(0);
        sleep(10);

        liftSystem.liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        liftSystem.liftMotor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }




}


