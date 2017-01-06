package models;/*
           ,                                             |
          /#\         _         _     _    ___   ___     | Project: Matador - CDIO_final
         /###\       | |__   _ | | __| |  /_  | /_  |    | Version: v0.1.0
        /#####\      | '_ \ / \| |/ _  |    | |   | |    |
       /##,-,##\     | | | | O | | ( | |   _| |_ _| |_   | Anders Wiberg Olsen (s165241), Valentin Leon Christensen (s152735),
      /##(   )##\    |_| |_|\_/|_|\__,_|  |_____|_____|  | Iman Chelhi (s165228), Troels Just Christoffersen (s120052),
     /#.--   --.#\                                       | Sebastian Tibor Bakonyvári (s145918)
    /`           ´\                                      |
 */

import java.util.Random;

/**
 * This class is a die that returns a random value.
 * @version 1.0.2
 * imported from CDIO_del3 develop branch on 2017-01-06.
 */
public class Die
{
    private int faceCount;
    private int result;

    private static Random rand;

    public Die()
    {
        this.faceCount = 6;
        result         = 0;
        rand           = new Random();
    }

    public Die(int faceCount)
    {
        this.faceCount = faceCount;
        result         = 0;
        rand           = new Random();
    }

    public int getRolledDieResult()
    {
        roll();
        return this.result;
    }

    public int getDieResult() {
        return this.result;
    }

    public void roll() {
        this.result = rand.nextInt(faceCount) + 1;
    }

    public void setFaceCount(int faceCount) {
        this.faceCount = faceCount;
    }

    public int getFaceCount() { return this.faceCount; }
}
