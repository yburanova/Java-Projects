/*
 * Copyright (c) Yulia Buranova
 */

package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yulia on 25.09.16.
 */
public class LevelLoader
{
    private Path levels;

    public LevelLoader(Path levels)
    {
        this.levels = levels;
    }

    public GameObjects getLevel(int level)
    {
        while(level > 60)
            level = level - 60;

        Set<Wall> walls = new HashSet<>();
        Set<Home> homes = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Player player = new Player(50, 50);

        try
        {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(levels.toFile()));
            String line;
            int readenLevel = 1;

            while ((line = bufferedReader.readLine()) != null)
            {
                if (line.contains("Maze: "))
                {
                    readenLevel = Integer.parseInt(line.split(" ")[1]);

                    if (readenLevel == level)
                    {
                        // draw the level
                        bufferedReader.readLine();
                        int width = Integer.parseInt(bufferedReader.readLine().split(" ")[2]);
                        int height = Integer.parseInt(bufferedReader.readLine().split(" ")[2]);
                        bufferedReader.readLine();
                        bufferedReader.readLine();
                        bufferedReader.readLine();

                        for(int y = 0; y < height; y++)
                        {
                            String in = bufferedReader.readLine();
                            char[] array = in.toCharArray();

                            for (int x = 0; x < width; x++)
                            {
                                int xPosition = x * Model.FIELD_SELL_SIZE + Model.FIELD_SELL_SIZE/2;
                                int yPosition = y * Model.FIELD_SELL_SIZE + Model.FIELD_SELL_SIZE/2;

                                char character = array[x];
                                switch (character)
                                {
                                    case ' ':
                                        break;
                                    case '@':
                                        player = new Player(xPosition, yPosition);
                                        break;
                                    case 'X':
                                        walls.add(new Wall(xPosition, yPosition));
                                        break;
                                    case '.':
                                        homes.add(new Home(xPosition, yPosition));
                                        break;
                                    case '*':
                                        boxes.add(new Box(xPosition, yPosition));
                                        break;
                                    case '&':
                                        homes.add(new Home(xPosition, yPosition));
                                        boxes.add(new Box(xPosition, yPosition));
                                        break;
                                }
                            }
                        }

                        break;
                    }
                }

            }

        }
        catch (IOException e)
        {

        }

        GameObjects gameObjects = new GameObjects(walls,boxes,homes, player);
        return gameObjects;
    }
}
