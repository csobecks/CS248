import java.io.*;
import java.util.*;

class Assignment1
{
    static int randominsult()
    {
        insult=(int)(1+5*Math.random());
        if(insult==0)
        {
            system.out.println("butt boy");
        }
        else if(insult==1)
        {
            system.out.println("stinky boy");
        }
        else if(insult==2)
        {
            system.out.println("bad boy");
        }
        else if(insult==3)
        {
            system.out.println("dirty boy");
        }
        else
        {
            system.out.println("BRUH");
        }
        return 0;
    }

    public static void main(String [] arg)
    {
        int answer=1;
        int game=1;
        number=(int)(1+100*Math.random());
        system.out.println("Hello Gamer,"+"Let's play a game");

        while(game=1)
        {
            number=(int)(1+100*Math.random());
            system.out.println("I have a number between 1 and 100. Let's see if you can guess it")
        
            Scanner cin=new Scanner(System.in);
            while(answer=1)
            {
                int guess=cin.nextInt();
                
                if(guess==number)
                {
                    answer=0;
                }
                else if(guess>number)
                {
                    system.out.println("Smaller! ");
                    randominsult(); 
                }
                else
                {
                system.out.println("Bigger! ");
                randominsult();
                }
            }
        
            system.out.println("damn boy you got it!"+"play again??"+"1=yes, 0=no");
            guess=cin.nextInt();
            if(guess==1)
            {
                system.out.println("yeehaw gamer lets do it!");
            }
            else
            {
                system.out.println("you'll come crawling back eventually");
                game=0;
            }
        }
    }
}