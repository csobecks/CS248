import java.util.Scanner;

public class Room 
{
    //data members
    int current;
    int adjac1;
    int adjac2;
    int adjac3;
    String message;
    char status;

    //constructor

    /** @param current current room
     * @param adjac1 first adjacent room
     * @param adjac2 second adjacent room
     * @param adjac3 third adjacent room
     * @param message the message of the room
     * @param status the status of the room if it has anything in it
    */

    public Room(Scanner roomread) //read in from a file and fill in the appropriate data
    {
        current=roomread.nextInt();
        adjac1=roomread.nextInt();
        adjac2=roomread.nextInt();
        adjac3=roomread.nextInt();
        roomread.next();
        message=roomread.nextLine();
        status='0';
    }

    //accessor
    public void print() //display current room info
    {
        System.out.println("Your current room:"+current+" The Adjacent rooms:" 
        +adjac1+" "+adjac2+" "+adjac3+"\n"+message);
    }
}