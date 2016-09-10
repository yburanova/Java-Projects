package src;

/*
 * Copyright (c) Yulia Buranova
 */


/**
 * Created by yulia on 06.09.16.
 */
public class ExceptionHandler extends Exception
{
    public static void log(Exception e)
    {
        System.out.println(e.toString());
    }
}
