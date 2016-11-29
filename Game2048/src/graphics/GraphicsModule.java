package graphics;

import main.GameField;

/**
 * Created by yulia on 28.11.16.
 */
public interface GraphicsModule
{
    void draw(GameField field);

    void destroy();

    boolean isCloseRequested();
}
