package robotx.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import robotx.libraries.XOpMode;
import robotx.modules.DuckRotation;
import robotx.modules.IntakeSystem;
import robotx.modules.EncoderTester;
import robotx.modules.OrientationDrive;

@TeleOp(name = "OpMode2021v2", group = "Default")

public class OpMode2021v2 extends XOpMode {

    OrientationDrive orientationDrive;
    IntakeSystem intakeSystem;
    EncoderTester liftSystem;
    DuckRotation duckRotation;
    EncoderTester encoderTester;


    public void initModules() {                                                                 

        super.initModules();

        intakeSystem= new IntakeSystem(this);
        activeModules.add(intakeSystem);

        liftSystem = new EncoderTester(this);
        activeModules.add(liftSystem);

        duckRotation = new DuckRotation(this);
        activeModules.add(duckRotation);

        orientationDrive = new OrientationDrive(this);
        activeModules.add(orientationDrive);

        encoderTester = new EncoderTester(this);
        activeModules.add(encoderTester);

    }

    public void init() {
        super.init();
    }
}

/*
Controls
GamePad 1:
Joysticks to move
B to reset robot orientation
Left bumper to toggle slow mode
Right bumper to toggle super slow mode

Gamepad 2:
intake x and y
lift a and b
duck motor right back and left back


}*/