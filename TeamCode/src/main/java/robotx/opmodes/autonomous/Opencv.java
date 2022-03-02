
package robotx.opmodes.autonomous;
        /*
         * Copyright (c) 2020 OpenFTC Team
         *
         * Permission is hereby granted, free of charge, to any person obtaining a copy
         * of this software and associated documentation files (the "Software"), to deal
         * in the Software without restriction, including without limitation the rights
         * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
         * copies of the Software, and to permit persons to whom the Software is
         * furnished to do so, subject to the following conditions:
         *
         * The above copyright notice and this permission notice shall be included in all
         * copies or substantial portions of the Software.
         * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
         * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
         * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
         * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
         * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
         * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
         * SOFTWARE.
         */

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;

import robotx.modules.DuckRotation;
import robotx.modules.IntakeSystem;
import robotx.modules.EncoderTester;
import robotx.modules.MecanumDrive;
import robotx.modules.OrientationDrive;

/*
The goal
 */

@Autonomous (name = "OpenCv\uD83D\uDE24")

public class Opencv extends LinearOpMode {

    OpenCvWebcam phoneCam;
    SkystoneDeterminationPipeline pipeline;
    MecanumDrive mecanumDrive;
    DuckRotation duckRotation;
    IntakeSystem intakeSystem;
    EncoderTester liftSystem;
    OrientationDrive orientationDrive;

    @Override
    public void runOpMode() {

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Importing Modules

        duckRotation = new DuckRotation(this);
        duckRotation.init();

        intakeSystem = new IntakeSystem(this);
        intakeSystem.init();

        orientationDrive = new OrientationDrive(this);
        orientationDrive.init();

        mecanumDrive = new MecanumDrive(this);
        mecanumDrive.init();

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

        //Var X is used to determine placement of the block
        int x = 0;

        //Experimental SleepTime Variable for testing sleeps
        int SleepTime = 1500;

        //Camera setup
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        phoneCam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam1"), cameraMonitorViewId);
        pipeline = new SkystoneDeterminationPipeline();
        phoneCam.setPipeline(pipeline);

        // We set the viewport policy to optimized view so the preview doesn't appear 90 deg
        // out when the RC activity is in portrait. We do our actual image processing assuming
        // landscape orientation, though.
        phoneCam.setViewportRenderingPolicy(OpenCvCamera.ViewportRenderingPolicy.OPTIMIZE_VIEW);

        phoneCam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                phoneCam.startStreaming(320, 240, OpenCvCameraRotation.SIDEWAYS_LEFT);
            }

            @Override
            public void onError(int errorCode) {

            }
        });

        waitForStart();

        while (opModeIsActive()) {

            telemetry.addData("Analysis", pipeline.getAnalysis());
            telemetry.update();

            // Don't burn CPU cycles busy-looping in this sample - Sleep is used for testing purposes, simply left in to leave a slight delay at the start.
            sleep(50);

            // initial drive forward
            DriveForward(0.5,600);
            sleep(SleepTime);

            // code for three movements
            // all end up in the same place

            if (pipeline.avg1 >= 130) {
                DriveForward(1,100);
                sleep(SleepTime);
                x=1;

                // rest of first movement
                StrafeRight(0.5,600);
                sleep(SleepTime);

            }else {
                StrafeRight(0.5,200);
                sleep(SleepTime);

                if (pipeline.avg1 >= 131){

                    x=2;
                    StrafeRight(0.5,400);
                    sleep(SleepTime);
                    // rest of second movement

                }
                else {
                    x=3;
                    // movement for rest of third
                    StrafeRight(0.5,400);
                    sleep(SleepTime);

                }
            }

            //Experimental variable used as a placeholder for tested movements
            int TestTime = 100;

            if (x==1){

                /*
                Code is w/o encoder for drive train - hopefully will use
                Basic movements set up
                Back up to wall after duckspin to square off
                 */

                DriveForward(0.5,TestTime);
                StrafeRight(0.5,TestTime);
                DriveBackward(0.5,TestTime);

                DuckSpin(0.5,TestTime);

                StrafeLeft(0.5,TestTime);
                DriveBackward(0.5,TestTime);
                DriveForward(0.5,TestTime);
                StrafeLeft(0.5,TestTime);
                DriveForward(0.5,TestTime);

                // encoder lift code:
                LiftFirstLevel();
                sleep(SleepTime);

                DriveBackward(0.5,TestTime);

                LowerFirstLevel();
                sleep(SleepTime);

                // Drive to Warehouse
                TurnLeft(0.5,TestTime);
                DriveForward(0.5,TestTime);

                sleep(1000000);

            }
            if (x==2){
                /*
                Code is w/o encoder for drive train - hopefully will use
                Basic movements set up
                Back up to wall after duckspin to square off
                 */

                DriveForward(0.5,TestTime);
                StrafeRight(0.5,TestTime);
                DriveBackward(0.5,TestTime);

                DuckSpin(0.5,TestTime);

                StrafeLeft(0.5,TestTime);
                DriveBackward(0.5,TestTime);
                DriveForward(0.5,TestTime);
                StrafeLeft(0.5,TestTime);
                DriveForward(0.5,TestTime);

                // encoder lift code:
                LiftSecondLevel();
                sleep(SleepTime);

                DriveBackward(0.5,TestTime);

                LowerSecondLevel();
                sleep(SleepTime);

                // Drive to Warehouse
                TurnLeft(0.5,TestTime);
                DriveForward(0.5,TestTime);

                sleep(1000000);
            }
            if (x==3){
               /*
                Code is w/o encoder for drive train - hopefully will use
                Basic movements set up
                Back up to wall after duckspin to square off
                 */

                DriveForward(0.5,TestTime);
                StrafeRight(0.5,TestTime);
                DriveBackward(0.5,TestTime);

                DuckSpin(0.5,TestTime);

                StrafeLeft(0.5,TestTime);
                DriveBackward(0.5,TestTime);
                DriveForward(0.5,TestTime);
                StrafeLeft(0.5,TestTime);
                DriveForward(0.5,TestTime);

                // encoder lift code:
                LiftThirdLevel();
                sleep(SleepTime);

                DriveBackward(0.5,TestTime);

                LowerThirdLevel();
                sleep(SleepTime);

                // Drive to Warehouse
                TurnLeft(0.5,TestTime);
                DriveForward(0.5,TestTime);

                sleep(1000000);
            }



        }




    }

    public static class SkystoneDeterminationPipeline extends OpenCvPipeline {

        /*
         * Some color constants
         */
        static final Scalar BLUE = new Scalar(0, 0, 255);
        static final Scalar GREEN = new Scalar(0, 255, 0);

        static final int duckThreshold = 100;

        /*
         * The core values which define the location and size of the sample regions
         */
        static final Point REGION1_TOPLEFT_ANCHOR_POINT = new Point(181, 98);

        static final int REGION_WIDTH = 35;
        static final int REGION_HEIGHT = 25;

        final int FOUR_RING_THRESHOLD = 150;
        final int ONE_RING_THRESHOLD = 135;

        Point region1_pointA = new Point(
                REGION1_TOPLEFT_ANCHOR_POINT.x,
                REGION1_TOPLEFT_ANCHOR_POINT.y);
        Point region1_pointB = new Point(
                REGION1_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
                REGION1_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);

        /*
         * Working variables
         */
        Mat region1_Cb;
        Mat YCrCb = new Mat();
        Mat Cb = new Mat();
        int avg1;
        int x;

        /*
         * This function takes the RGB frame, converts to YCrCb,
         * and extracts the Cb channel to the 'Cb' variable
         */
        void inputToCb(Mat input) {
            Imgproc.cvtColor(input, YCrCb, Imgproc.COLOR_RGB2YCrCb);
            Core.extractChannel(YCrCb, Cb, 1);
        }

        public void init(Mat firstFrame) {
            inputToCb(firstFrame);

            region1_Cb = Cb.submat(new Rect(region1_pointA, region1_pointB));
        }






        @Override
        public Mat processFrame(Mat input) {
            inputToCb(input);

            avg1 = (int) Core.mean(region1_Cb).val[0];

            Imgproc.rectangle(
                    input, // Buffer to draw on
                    region1_pointA, // First point which defines the rectangle
                    region1_pointB, // Second point which defines the rectangle
                    BLUE, // The color the rectangle is drawn in
                    2); // Thickness of the rectangle lines


            Imgproc.rectangle(
                    input, // Buffer to draw on
                    region1_pointA, // First point which defines the rectangle
                    region1_pointB, // Second point which defines the rectangle
                    GREEN, // The color the rectangle is drawn in
                    -1); // Negative thickness means solid fill

            return input;
        }


        public int getAnalysis() {
            return avg1;
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
            public void DiagonalDriveUpRight(double power, int time){
                mecanumDrive.frontLeft.setPower(-power);
                mecanumDrive.backRight.setPower(power);
                sleep(time);
                mecanumDrive.frontLeft.setPower(0);
                mecanumDrive.backRight.setPower(0);

            }
            public void DiagonalDriveUpLeft(double power, int time){
                mecanumDrive.frontRight.setPower(power);
                mecanumDrive.backLeft.setPower(power);
                sleep(time);
                mecanumDrive.frontRight.setPower(0);
                mecanumDrive.backLeft.setPower(0);

            }
            public void DiagonalDriveBackRight(double power, int time){
                mecanumDrive.frontLeft.setPower(power);
                mecanumDrive.backRight.setPower(-power);
                sleep(time);
                mecanumDrive.frontLeft.setPower(0);
                mecanumDrive.backRight.setPower(0);

            }
            public void DiagonalDriveBackLeft(double power, int time){
                mecanumDrive.frontRight.setPower(-power);
                mecanumDrive.backLeft.setPower(-power);
                sleep(time);
                mecanumDrive.frontRight.setPower(0);
                mecanumDrive.backLeft.setPower(0);

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


