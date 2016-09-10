package src;

/*
 * Copyright (c) Yulia Buranova
 */

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Created by yulia on 10.09.16.
 */
public class HTMLFileFilter extends FileFilter
{
    @Override
    public boolean accept(File file)
    {
        return file.isDirectory() || file.getName().toLowerCase().endsWith(".html") || file.getName().toLowerCase().endsWith(".htm");
    }

    @Override
    public String getDescription()
    {
        return "HTML and HTM files";
    }
}
