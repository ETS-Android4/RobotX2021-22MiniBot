package robotx.modules;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


@Autonomous(name = "EncoderLift")
@Disabled

public class EncoderTester extends LinearOpMode {

    public DcMotor liftMotor;
    public DcMotor liftMotor2;

    //@Override
    public void runOpMode() throws InterruptedException
    {
        liftMotor = hardwareMap.dcMotor.get("liftMotor");
        liftMotor2 = hardwareMap.dcMotor.get("liftMotor2");

    }

    public void LiftSystem(){

        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        liftMotor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        liftMotor.setPower(0);
        liftMotor2.setPower(0);
    }

    //One full revolution is 300 ticks

    public void LiftFirstLevel(){

        liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        liftMotor.setTargetPosition(200);
        liftMotor2.setTargetPosition(200);

        liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        liftMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        liftMotor.setPower(0.7);
        liftMotor2.setPower(0.7);

        while (liftMotor.isBusy() && liftMotor2.isBusy() )
        {

        }

        liftMotor.setPower(0);
        liftMotor2.setPower(0);

        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void LiftSecondLevel(){

        liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        liftMotor.setTargetPosition(400);
        liftMotor2.setTargetPosition(400);

        liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        liftMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        liftMotor.setPower(0.7);
        liftMotor2.setPower(0.7);

        while (liftMotor.isBusy() && liftMotor2.isBusy() )
        {

        }

        liftMotor.setPower(0);
        liftMotor2.setPower(0);

        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void LiftThirdLevel(){

        liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        liftMotor.setTargetPosition(600);
        liftMotor2.setTargetPosition(600);

        liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        liftMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        liftMotor.setPower(0.7);
        liftMotor2.setPower(0.7);

        while (liftMotor.isBusy() && liftMotor2.isBusy() )
        {

        }

        liftMotor.setPower(0);
        liftMotor2.setPower(0);

        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void LowerFirstLevel(){

        liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        liftMotor.setTargetPosition(200);
        liftMotor2.setTargetPosition(200);

        liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        liftMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        liftMotor.setPower(-0.7);
        liftMotor2.setPower(-0.7);

        while (liftMotor.isBusy() && liftMotor2.isBusy() )
        {

        }

        liftMotor.setPower(0);
        liftMotor2.setPower(0);

        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void LowerSecondLevel(){

        liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        liftMotor.setTargetPosition(400);
        liftMotor2.setTargetPosition(400);

        liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        liftMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        liftMotor.setPower(-0.7);
        liftMotor2.setPower(-0.7);

        while (liftMotor.isBusy() && liftMotor2.isBusy() )
        {

        }

        liftMotor.setPower(0);
        liftMotor2.setPower(0);

        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void LowerThirdLevel(){

        liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        liftMotor.setTargetPosition(600);
        liftMotor2.setTargetPosition(600);

        liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        liftMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        liftMotor.setPower(-0.7);
        liftMotor2.setPower(-0.7);

        while (liftMotor.isBusy() && liftMotor2.isBusy() )
        {

        }

        liftMotor.setPower(0);
        liftMotor2.setPower(0);

        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }



}


