/* CS248 Assignment 4: Hunt the Wumpus
    Author: Casey Sobecks
    This program is a text based game that will have the user move through room looking for a wumpus to kill. 
    It will implement rooms as objects and fill them with obstacles accordingly.
*/

import java.io.*;
import java.util.*;

class HuntTheWumpus
{
    static void Delay (int a)
    {
        try{Thread.sleep(a*1000);} //stop program for "a" seconds
        catch(InterruptedException ie){Thread.currentThread().interrupt();} //return from sleep
        
    }
    
    public static void main(String [] args) throws IOException
    {
        //variables
        int moveshoot=0;
        int arrows=3;
        int shotroom=0;
        int done=0; 
        int spiderroom[]= new int [2];
        int pitroom[]= new int[2];
        int wumpusroom=0;  
        int arrowroom=0;
        int prevlocal=0;
        int game=1;
        int location=0;  

        //start of program
        Scanner input=new Scanner(System.in); 
        Scanner roomread= new Scanner(new FileReader("rooms.txt"));//read the the text file with room information
        int roomtot=roomread.nextInt(); //get total room count

        Room [] cave; //initialize the cave full of rooms
        cave=new Room[roomtot]; 

        for(int i=0; i<cave.length; i++) //fill the rooms with their respective values
        {
            cave[i]=new Room(roomread);
        }
   
        while(done==0) //obstacle/enemy placement
        {
            wumpusroom=(int)(roomtot*Math.random()); //generate random location for wumpus, spiders, and bottomless pit
            arrowroom=1; //(int)(roomtot*Math.random());
            for(int i=0; i<2; i++)
            {
                spiderroom[i]=(int)(roomtot*Math.random());
                pitroom[i]=(int)(roomtot*Math.random());
            }
            //check if any of the locations are forbidden i.e. overlap or starting room
            if(wumpusroom==0||spiderroom[0]==0||spiderroom[1]==0||pitroom[0]==0||pitroom[1]==0||wumpusroom==spiderroom[0]||wumpusroom==spiderroom[1]
                ||wumpusroom==pitroom[0]||wumpusroom==pitroom[1]||spiderroom[0]==pitroom[0]||spiderroom[0]==pitroom[1]||spiderroom[0]==spiderroom[1]
                ||spiderroom[1]==pitroom[0]||spiderroom[1]==pitroom[1]||pitroom[0]==pitroom[1]||arrowroom==wumpusroom||arrowroom==spiderroom[0]
                ||arrowroom==spiderroom[1]||arrowroom==pitroom[0]||arrowroom==pitroom[1])
            {}
            else//all locations are different and not first room
            {done=1;}
        }
        //set room locations with proper status message
        cave[spiderroom[0]].status='s'; //s for spider
        cave[spiderroom[1]].status='s';
        cave[wumpusroom].status='w'; //w for wumps
        cave[pitroom[0]].status='p'; //p for pit
        cave[pitroom[1]].status='p';
        cave[arrowroom].status='a'; //a for arrows
        
        //Startup screen    
        System.out.println("\n\nWelcome to Hunt the Wumpus!!!!!\n\n");
        Delay(1);
        System.out.println("The wumpus is hiding out in the cave somewhere! You are a brave warrior with three arrows."+
        " You decide to enter its cave which is filled with deadly spiders and holes in the ground. Will you be able to kill the wumpus????\n\n\n");
        Delay(3);
        System.out.println("you enter the cave!\n");
        Delay(1);
        //print current status
        cave[location].print();
        System.out.println("You have "+ arrows+ " arrows.");
        
        //check if any adjacent rooms have a status
        if(cave[cave[prevlocal].adjac1-1].status=='s'||cave[cave[prevlocal].adjac2-1].status=='s'||cave[cave[prevlocal].adjac3-1].status=='s')
        {
            System.out.println("you hear some crawly boys"); //spiders nearby
        }
        
        if(cave[cave[prevlocal].adjac1-1].status=='a'||cave[cave[prevlocal].adjac2-1].status=='a'||cave[cave[prevlocal].adjac3-1].status=='a')
        {
            System.out.println("you hear angelic singing coming from nearby? It's a gorgeous sound!"); //arrows nearby
        }

        if (cave[cave[prevlocal].adjac1-1].status=='p'||cave[cave[prevlocal].adjac2-1].status=='p'||cave[cave[prevlocal].adjac3-1].status=='p')
        {
            System.out.println("you hear some echos deep within"); //pit nearby
        }
        
        if(cave[cave[prevlocal].adjac1-1].status=='w'||cave[cave[prevlocal].adjac2-1].status=='w'||cave[cave[prevlocal].adjac3-1].status=='w')
        {
            System.out.println("a wumpus is definately nearby...maybe"); //wumpus nearby
        }
        else{} //nothing nearby
        
        System.out.println(arrowroom);


        //begin game
        while(game==1)
        {
            
            Delay(1);
            //check for movement or shooting
            System.out.println("\nDo you want to move or shoot??\n1 for move 0 for shoot");
            moveshoot=input.nextInt(); 
            
            if(arrows==0) //out of arrows
            {
                if(cave[arrowroom].status!='a') //there are no more arrows left in the game
                {
                    System.out.println("you have run out of arrows. \nYou get lost in the cave and slowly slide into a state of madness.\n"+
                                        "As you wander the cave you slowly realize that you were the wumpus all along! OMG!");
                    Delay(1);
                    game=0;
                    break;
                }
                else //you can still get some arrows from the arrow room
                {
                    System.out.println("you are out of arrows, but maybe there's something in the cave you can use...");
                    Delay(1);
                }
            }

            if(moveshoot==0) //shoot the arrow
            {
                System.out.println("Okay!\nIt's go time!");
                Delay(1);
                System.out.println("which room do you want to shoot into?? Choose wisely...");

                shotroom=input.nextInt(); //pick shooting room
                if(shotroom!=cave[location].adjac1&&shotroom!=cave[location].adjac2&&shotroom!=cave[location].adjac3) //shotroom not adjacent to current room
                {
                    System.out.println("you shoot at the wall. How stupid of you.\n"); //insult
                    Delay(1);
                    arrows--; //subtract arrows

                    cave[location].print(); 
                    System.out.println("You have "+ arrows+ " arrows.");

                }
                else if(cave[shotroom-1].status=='w') //you shot the wumpus
                {
                    Delay(1);
                    System.out.println("A scream comes from inside the cave!");
                    Delay(1);
                    System.out.println("you run in to see what happened.......");
                    Delay(1);
                    System.out.println("It's the Wumpus! You've killed it!\nCongratulations!");
                    game=0;
                }
                else //you shoot in a room not with wumpus
                {
                    Delay(1);
                    System.out.println("\nyou hear your arrow thud onto the ground.\n");
                    arrows--;
                    cave[location].print();
                    System.out.println("You have "+ arrows+ " arrows.");

                }

            }
            else if(moveshoot==1) //move
            {
                System.out.println("\nwhich room do you want to move to??");
                location=input.nextInt()-1;//receive new room location

                if(location!=cave[prevlocal].adjac1-1&&location!=cave[prevlocal].adjac2-1&&location!=cave[prevlocal].adjac3-1) //the number chosen isnt an adjacent room
                {
                    //insult
                    System.out.println("you walk into a wall, try again");
                }
                else //move to adjacent room
                {
                    if(cave[location].status=='w') //walked into wumpus room
                    {
                        System.out.println("\nyou got eated by wumpus");
                        game=0;
                    }
                    else if(cave[location].status=='s') //walked into spider room
                    {
                        System.out.println("\nyou got eated by spidey");
                        game=0;
                    }
                    else if(cave[location].status=='p') //walk into pit room
                    {
                        System.out.println("\nyou got eated by hole");
                        game=0;
                    }
                    else //move into a safe room
                    {
                        if(cave[location].status=='a') //enter an arrow room
                        {
                            System.out.println("you find some sticks and flint on the ground. Using your big brain, you fashion them into arrows.");
                            arrows=arrows+3; //increase arrow supply
                            cave[location].status='0'; //set status of room to empty because you picked up the arrows
                            System.out.println("You now have "+ arrows+ " arrows.\n");
                            Delay(2);
                        }


                        prevlocal=location;
                        cave[location].print();
                        System.out.println("You have "+ arrows+ " arrows.");
                        
                        //spider room near by
                        if(cave[cave[prevlocal].adjac1-1].status=='s'||cave[cave[prevlocal].adjac2-1].status=='s'||cave[cave[prevlocal].adjac3-1].status=='s')
                        {
                            System.out.println("you hear some crawly boys");
                        }
                        //pit room nearby
                        if (cave[cave[prevlocal].adjac1-1].status=='p'||cave[cave[prevlocal].adjac2-1].status=='p'||cave[cave[prevlocal].adjac3-1].status=='p')
                        {
                            System.out.println("you hear some echos deep within");
                        }
                        //wumpus room nearby
                        if(cave[cave[prevlocal].adjac1-1].status=='w'||cave[cave[prevlocal].adjac2-1].status=='w'||cave[cave[prevlocal].adjac3-1].status=='w')
                        {
                            System.out.println("a wumpus is definately nearby...maybe");
                        }
                        //arrow room nearby
                        if(cave[cave[prevlocal].adjac1-1].status=='a'||cave[cave[prevlocal].adjac2-1].status=='a'||cave[cave[prevlocal].adjac3-1].status=='a')
                        {
                            System.out.println("you hear angelic singing coming from nearby? It's a gorgeous sound!"); //arrows nearby
                        }
                        else //nothing nearby
                        {}
                    }
                }   

            }
            else //you enter a number that's not 0 or 1 to move or shoot
            {
                System.out.println("that's not an option, stick to the rules");
            }
        }
        
        //endgame sequence
        input.close(); //close input so java stops yelling at me
        Delay(3);
        System.out.println("\nand that's the tale of the wumpus!");
        Delay(2);
        System.out.println("\ncredits:\n1.me, I made the game\n2.you, player of the game\n");
        Delay(2);
        System.out.println("\nthanks for playing!\n");
        Delay(1);
    }
}