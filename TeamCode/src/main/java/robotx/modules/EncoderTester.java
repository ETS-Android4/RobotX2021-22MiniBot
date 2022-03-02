package robotx.modules;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import robotx.libraries.XModule;



@Disabled

public class EncoderTester extends XModule {

    public DcMotor liftMotor;
    public DcMotor liftMotor2;

    public EncoderTester(OpMode op) {
        super(op);
    }

    //@Override
    public void init()
    {
        liftMotor = opMode.hardwareMap.dcMotor.get("LiftMotor");
        liftMotor2 = opMode.hardwareMap.dcMotor.get("LiftMotor2");

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

        liftMotor.setTargetPosition(700);
        liftMotor2.setTargetPosition(700);

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

        liftMotor.setTargetPosition(1200);
        liftMotor2.setTargetPosition(1200);

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

        liftMotor.setTargetPosition(-200);
        liftMotor2.setTargetPosition(-200);

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

        liftMotor.setTargetPosition(-700);
        liftMotor2.setTargetPosition(-700);

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

        liftMotor.setTargetPosition(-1200);
        liftMotor2.setTargetPosition(-1200);

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

    public void loop() {
        if (xGamepad2().a.wasPressed()){
            LiftThirdLevel();
        }
        if (xGamepad2().b.wasPressed()){
            LowerThirdLevel();
        }

    }

    }


