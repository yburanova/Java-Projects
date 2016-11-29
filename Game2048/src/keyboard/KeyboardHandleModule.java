package keyboard;

import main.Direction;

/**
 * Created by yulia on 28.11.16.
 */
public interface KeyboardHandleModule
{
    void update();
    boolean wasEscPressed();
    Direction lastDirectionKeyPressed();
}
