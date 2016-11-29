package main;

import graphics.GraphicsModule;
import graphics.lwjglmodule.LwjglGraphicsModule;
import keyboard.KeyboardHandleModule;
import keyboard.lwjglmodule.LwjglKeyboardHandleModule;

import java.util.Random;

/**
 * Created by yulia on 28.11.16.
 */
public class Main {

    private static boolean isGameOver; // Flag to check the end of the game
    private static boolean is2048;     // Flag to check the win
    private static GameField field;    // Game Field
    private static int score;          // Game Score

    private static KeyboardHandleModule keyboardModule;
    private static GraphicsModule graphicsModule;

    private static Direction direction; // Moving direction

    public static void main(String[] args)
    {
        initFields();
        createInitialCells();

        while(!isGameOver)
        {
            input();
            logic();

            graphicsModule.draw(field);
        }

        graphicsModule.destroy();

        printGameResult();
    }

    /**
     * Initialization of the Field
     */
    private static void initFields()
    {
        score = 0;
        isGameOver = false;
        is2048 = false;
        direction = Direction.AWAITING;
        graphicsModule = new LwjglGraphicsModule();
        keyboardModule = new LwjglKeyboardHandleModule();
        field = new GameField();
    }

    /**
     * Fills the field
     */
    private static void createInitialCells()
    {
        for(int i = 0; i < Constants.COUNT_INITIAL_CELLS; i++){
            generateNewCell();
        }
    }

    private static void generateNewCell() {
        int state = (new Random().nextInt(100) <= Constants.CHANCE_OF_LUCKY_SPAWN)
                ? Constants.LUCKY_INITIAL_CELL_STATE
                : Constants.INITIAL_CELL_STATE;

        // position for a new cell
        int randomX, randomY;

        randomX = new Random().nextInt(Constants.COUNT_CELLS_X);
        int currentX = randomX;

        randomY = new Random().nextInt(Constants.COUNT_CELLS_Y);
        int currentY = randomY;



        boolean placed = false;
        while(!placed){
            if(field.getState(currentX, currentY) == 0) {
                field.setState(currentX, currentY, state);
                placed = true;
            }else{
                if(currentX + 1 < Constants.COUNT_CELLS_X) {
                    currentX++;
                }else{
                    currentX = 0;
                    if(currentY + 1 < Constants.COUNT_CELLS_Y) {
                        currentY++;
                    }else{
                        currentY = 0;
                    }
                }

                if ((currentX == randomX) && (currentY == randomY) ) {  //No place -> Something went wrong
                    ExceptionHandler.cellCreationFailure();
                }
            }
        }

        score += state;
    }

    /**
     * user input
     */
    private static void input()
    {
        keyboardModule.update();

        direction = keyboardModule.lastDirectionKeyPressed();

        isGameOver = isGameOver || graphicsModule.isCloseRequested() || keyboardModule.wasEscPressed();
    }

    /**
     * shifts the block if the direction is defined
     */
    private static void logic() {
        if(direction != Direction.AWAITING){
            if(shift(direction)) generateNewCell();

            direction=Direction.AWAITING;
        }
    }

    /**
     * shifts the block
     * @param direction
     * @return
     */
    private static boolean shift(Direction direction) {
        boolean ret = false;

        switch(direction) {
            case UP:
            case DOWN:

                // summarizing the columns
                for(int i = 0; i< Constants.COUNT_CELLS_X; i++){
                    int[] arg =  field.getColumn(i);

                    /*В зависимости от направления сдвига, меняем или не меняем порядок чисел на противоположный*/
                    if(direction==Direction.UP){
                        int[] tmp = new int[arg.length];
                        for(int e = 0; e < tmp.length; e++){
                            tmp[e] = arg[tmp.length-e-1];
                        }
                        arg = tmp;
                    }

                    /*Пытаемся сдвинуть числа в этом столбце*/
                    ShiftRowResult result = shiftRow (arg);

                    /*Возвращаем линию в исходный порядок*/
                    if(direction==Direction.UP){
                        int[] tmp = new int[result.shiftedRow.length];
                        for(int e = 0; e < tmp.length; e++){
                            tmp[e] = result.shiftedRow[tmp.length-e-1];
                        }
                        result.shiftedRow = tmp;
                    }

                    /*Записываем изменённый столбец*/
                    field.setColumn(i, result.shiftedRow);

                    /*Если хоть одна линия была изменена, значит было изменено всё поле*/
                    ret = ret || result.didAnythingMove;
                }
                break;
            case LEFT:
            case RIGHT:

                /*По очереди сдвигаем числа всех строк в нужном направлении*/
                for(int i = 0; i< Constants.COUNT_CELLS_Y; i++){
                    /*Запрашиваем очередную строку*/
                    int[] arg = field.getLine(i);


                    if(direction==Direction.RIGHT){
                        int[] tmp = new int[arg.length];
                        for(int e = 0; e < tmp.length; e++){
                            tmp[e] = arg[tmp.length-e-1];
                        }
                        arg = tmp;
                    }

                    ShiftRowResult result = shiftRow (arg);

                    if(direction==Direction.RIGHT){
                        int[] tmp = new int[result.shiftedRow.length];
                        for(int e = 0; e < tmp.length; e++){
                            tmp[e] = result.shiftedRow[tmp.length-e-1];
                        }
                        result.shiftedRow = tmp;
                    }

                    // writing the resulting line
                    field.setLine(i, result.shiftedRow);

                     // checks for the changing
                    ret = ret || result.didAnythingMove;
                }

                break;
            default:
                ExceptionHandler.wrongDirectionParam();
                break;
        }

        return ret;
    }

    private static ShiftRowResult shiftRow (int[] oldRow) {
        ShiftRowResult ret = new ShiftRowResult();

        int[] oldRowWithoutZeroes = new int[oldRow.length];
        {
            int q = 0;

            for (int i = 0; i < oldRow.length; i++) {
                if(oldRow[i] != 0){
                    if(q != i){
                        ret.didAnythingMove = true;
                    }

                    oldRowWithoutZeroes[q] = oldRow[i];
                    q++;
                }
            }

            // avoiding null's
            for(int i = q; i < oldRowWithoutZeroes.length; i++) {
                oldRowWithoutZeroes[i] = 0;
            }
        }

        ret.shiftedRow = new int[oldRowWithoutZeroes.length];

        {
            int q = 0;

            {
                int i = 0;


                while (i < oldRowWithoutZeroes.length) {
                    if((i+1 < oldRowWithoutZeroes.length) && (oldRowWithoutZeroes[i] == oldRowWithoutZeroes[i + 1])
                            && oldRowWithoutZeroes[i]!=0) {
                        ret.didAnythingMove = true;
                        ret.shiftedRow[q] = oldRowWithoutZeroes[i] * 2;
                        if(ret.shiftedRow[q] == 2048) merged2048();
                        i++;
                    } else {
                        ret.shiftedRow[q] = oldRowWithoutZeroes[i];
                    }

                    q++;
                    i++;
                }

            }
            // avoiding null's
            for(int j = q; j < ret.shiftedRow.length; j++) {
                ret.shiftedRow[j] = 0;
            }
        }

        return ret;
    }

    private static class ShiftRowResult{
        boolean didAnythingMove;
        int[] shiftedRow;
    }

    private static void merged2048() {
        isGameOver = true;
        is2048 = true;
    }

    private static void printGameResult() {
        System.out.println("You " + (is2048 ? "won :)" : "lost :(") );
        System.out.println("Your score is " + score);
    }

}
