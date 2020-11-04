import java.util.*;
public class List
{
    //data members(variables)
    String event;
    int time;
    int size;
    String name;

    public static int count=0;

    //constructor
    public List(Scanner reader)
    {  
        event=reader.next();
        if(event.equals("Q"))
        {
            
        }
        else if(event.equals("T"))
        {
            time=reader.nextInt();
            reader.nextLine();
        }
        else
        {
            time=reader.nextInt();
            size=reader.nextInt();
            name=reader.nextLine();
        }
    }

    public void print()
    {
        if(event.equals("A"))
        {
            System.out.println("party "+name+" of "+size+" people");
        }
        else{}
    }

    public String getEvent() {return event;}
    public int getTime()
    {
        if(event.equals("Q"))
        {
            return -1;
        }
        return time;
    }
    public int getSize()
    {
        if(event.equals("A"))
        {return size;}
        return -1;
    }
    public String getName()
    {
        if(event.equals("A"))
        {return name;}
        return null;
    }
}