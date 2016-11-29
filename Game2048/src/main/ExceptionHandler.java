package main;

/**
 * Created by yulia on 28.11.16.
 * a class ExceptionHandler contains a set of static methods which are used to handle exceptions
 */
public class ExceptionHandler
{
    /**
     * An error occurred during the cell creation
     */
    public static void cellCreationFailure() {
        System.err.println("Main class failed to create new cell.");
        System.exit(-1);
    }

    /**
     * Wrong direction parameter
     */
    public static void wrongDirectionParam() {
        System.err.println("Main class failed to shift cells on field. Wrong parameter.");
        System.exit(-2);
    }

    /**
     * Problems with Graphics
     * @param e
     */
    public static void graphicsFailure(Exception e) {
        System.err.println("GraphicsModule failed.");
        e.printStackTrace();
        System.exit(-3);
    }
}
