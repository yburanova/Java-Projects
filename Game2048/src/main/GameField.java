package main;

/**
 * Created by yulia on 28.11.16.
 */
public class GameField {

    private int[][] field;

    /**
     * initialization of the game field
     */
    public GameField()
    {
        field = new int[Constants.COUNT_CELLS_X][Constants.COUNT_CELLS_Y];

        for (int i = 0; i < Constants.COUNT_CELLS_Y; i++)
            for(int j = 0; j < Constants.COUNT_CELLS_X; j++)
                field[j][i] = 0;
    }

    /**
     * returns a value in the current cell
     * @param x Coordinate in X
     * @param y Coordinate in Y
     * @return a value in the current cell
     */
    public int getState(int x, int y)
    {
        return field[x][y];
    }

    /**
     * sets a value to the chosen cell
     * @param x Coordinate in X
     * @param y Coordinate in Y
     * @param state
     */
    public void setState(int x, int y, int state)
    {
        field[x][y] = state;
    }

    public void setColumn(int i, int[] newColumn) {
        field[i] = newColumn;
    }

    public int[] getColumn(int i) {
        return field[i];
    }

    public void setLine(int i, int[] newLine) {
        for(int j = 0; j< Constants.COUNT_CELLS_X; j++){
            field[j][i] = newLine[j];
        }
    }

    public int[] getLine(int i) {
        int[] ret = new int[Constants.COUNT_CELLS_X];

        for(int j = 0; j< Constants.COUNT_CELLS_X; j++){
            ret[j] = field[j][i];
        }

        return ret;
    }
}
