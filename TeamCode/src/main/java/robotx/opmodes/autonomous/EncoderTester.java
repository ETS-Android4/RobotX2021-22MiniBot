package robotx.opmodes.autonomous;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.opMode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


@Autonomous(name = "EncoderSpin")
@Disabled

public class EncoderTester extends OpMode2021v2 {

    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;

    //@Override
    public void main() throws InterruptedException
    {
        //front left motor
        frontLeft = opMode.hardwareMap.dcMotor.get("frontLeft");

        frontRight = opMode.hardwareMap.dcMotor.get("frontRight");
        // frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //back left motor
        backLeft = opMode.hardwareMap.dcMotor.get("backLeft");

        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);             //reverse for same reason as above

        //back right motor
        backRight = opMode.hardwareMap.dcMotor.get("backRight");
        //backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //wait for start


    }

}


