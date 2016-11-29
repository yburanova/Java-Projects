package main;

/**
 * Created by yulia on 28.11.16.
 * A class Constants is used to public static final constants
 */
public class Constants
{
    // the size of the cell
    public static final int CELL_SIZE = 128;

    // field size
    public static final int COUNT_CELLS_X = 4;
    public static final int COUNT_CELLS_Y = 4;

    // screen size
    public static final int SCREEN_WIDTH = COUNT_CELLS_X *CELL_SIZE;
    public static final int SCREEN_HEIGHT = COUNT_CELLS_Y *CELL_SIZE;
    public static final String SCREEN_NAME = "Tproger's 2048";

    // a chance that the next cell will be "4"
    public static final int CHANCE_OF_LUCKY_SPAWN = 17;

    // next cell state
    public static final int LUCKY_INITIAL_CELL_STATE = 4;
    public static final int INITIAL_CELL_STATE = 2;

    // initial amount of cells
    public static final int COUNT_INITIAL_CELLS = 2;
}
