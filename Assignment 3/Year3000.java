import java.io.*;

/** 
 * Driver class for The Dating Game programming assignment.
 * @author Jon Sorenson
 * editted by Casey Sobecks to include extra credit
 */

public class Year3000 
{
    public static void main(String [] args) throws IOException
    {
        DateInterface d= new MyDate();
        d.today(); //sets the date to current date used by computer
        System.out.println(d); //prints the current day
        while(d.getYear()<3000) //moves forward until the day Jan 1 3000 is reached
        {
            d.tomorrow(); 
        }
        System.out.println(d); //print out the date and day of week of Jan 1, 3000
        
        d.today(); //reset the date to today
        while(d.getYear()>1799) //move backwards until Dec 31 1799 is reached
        {
            d.yesterday();
        }
        d.tomorrow(); //move forward a day to reach Jan 1, 1800
        System.out.println(d); //print out the day of the week of Jan 1, 1800

    }
}