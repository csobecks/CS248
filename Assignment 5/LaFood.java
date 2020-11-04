import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class LaFood
{
    public static void main(String [] args) throws IOException
    {
        Scanner listreader=new Scanner(new FileReader("data.txt"));
        int running=1;

        Queue waitlist=new Queue();
        System.out.println("Hello! Welcome to La Food Resturant! Home of the waitlist!");
        
        while(running!=0)
        {
            waitlist.enqueue(new Bob(listreader,running));
        }

        waitlist.getFront();

        for(int i=0;i<waitlist.size();i++)
        System.out.println(waitlist.dequeue());

        

        System.out.println("that's the end of the day.");
    }
}