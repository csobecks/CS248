import java.util.*;
public class Bob //implements QueueInterface
{
    //data members
    static String event;
    int size;
    int time;
    String name;

    //constructor
    /** @param event info about arrival, table ready, or end of day
     *  @param size size of the party
     *  @param time time of even occurring
     *  @param name name of party
    */
    public Bob(Scanner x,int y)
    {
        if(x.next()==null)
        {
            y=0;
        }
        else
        {
            event=x.next();
            if(event=="Q")
            {
                
            }
            else if(event=="T")
            {
                time=x.nextInt();
            }
            else
            {
                time=x.nextInt();
                size=x.nextInt();
                name=x.next();
            }
        }
    }

    public void print()
    {
        System.out.println(name+ " party of "+size+" people. (time="+time+")");
    }
}