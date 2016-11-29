package keyboard.lwjglmodule;

import keyboard.KeyboardHandleModule;
import main.Direction;
import org.lwjgl.input.Keyboard;

/**
 * Created by yulia on 29.11.16.
 */
public class LwjglKeyboardHandleModule implements KeyboardHandleModule{

    private boolean wasEscPressed;
    private Direction lastDirectionKeyPressed;

    @Override
    public void update()
    {
        resetValues();
        lastDirectionKeyPressed = Direction.AWAITING;

        while (Keyboard.next()) {
            if (Keyboard.getEventKeyState()) {
                switch(Keyboard.getEventKey()){
                    case Keyboard.KEY_ESCAPE:
                        wasEscPressed = true;
                        break;
                    case Keyboard.KEY_UP:
                        lastDirectionKeyPressed = Direction.UP;
                        break;
                    case Keyboard.KEY_RIGHT:
                        lastDirectionKeyPressed = Direction.RIGHT;
                        break;
                    case Keyboard.KEY_DOWN:
                        lastDirectionKeyPressed = Direction.DOWN;
                        break;
                    case Keyboard.KEY_LEFT:
                        lastDirectionKeyPressed = Direction.LEFT;
                        break;
                }
            }
        }
    }

    private void resetValues() {
        lastDirectionKeyPressed = Direction.AWAITING;
        wasEscPressed = false;
    }

    @Override
    public boolean wasEscPressed() {
        return wasEscPressed;
    }

    @Override
    public Direction lastDirectionKeyPressed() {
        return lastDirectionKeyPressed;
    }
}
