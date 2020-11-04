import java.util.*;
/*
This is the Class file for the Dating Game Assignment 
CS248 Object Oriented Programming
@author Casey Sobecks
*/

public class MyDate implements DateInterface
{
    //data memers(variables)
    int month;
    int day;
    int year;
    int dayofweek;
    String dayname;
    String monthname;

    //constructor
    /** @param m month
     * @param d day
     * @param y year
     * @param dow day of the week */
    public void set (int m, int d, int y, int dow)
    {
        month=m; //months in 1-12
        day=d;  //day in 1-31
        year=y; //year four digits long
        dayofweek=dow; //day in 0-6
    }

    //accessors
    public int getDay()
    {
        return day; //returns the day
    }
    
    public int getDow()
    {
        return dayofweek; //returns the day of the week  
    }

    public int getMonth()
    {
        return month; //returns the month
    }

    public int getYear()
    {
        return year; //returns the year
    }

    //mutators
    public void tomorrow()
    {
        //First checks the day of the week 
        if(getDow()==6)
        {
            dayofweek=0;
        }
        else
        {
            dayofweek++;
        }
        
        //Next checks the day of the month

        if(getDay()==28) //February
        {
            if(getMonth()==2) //Check for Feb
            {
                if((getYear()%100==0) && (getYear()%400==0)) //Check for leap year
                {day++;}
                else if((getYear()%100!=0) && (getYear()%4==0)) //check for a leap year
                {day++;}
                else //if not a leap year
                {
                    day=1;
                    month++;
                }
            }
            else //not february
            {
                day++;
            }
        }
        else if(getDay()==29) //leap year
        {
            if(getMonth()==2) //check for Feb
            {
                    day=1;
                    month++;

            }
            else //not february
            {
                day++;
            }
        }
        else if(getDay()==30) //30 day months
        {
            if((getMonth()==4)||(getMonth()==6)||(getMonth()==9)||(getMonth()==11)) //check if month has 30 days
            {
                day=1;
                month++;
            }
            else //month has 31 days
            {
                day++;
            }
        }
        else if(getDay()==31) //31 day months
        {
            if(getMonth()==12) //check if it's a new year
            {
                day=1;
                month=1;
                year++;
            }
            else //not a new year
            {
                day=1;
                month++;
            }
        }
        else //if not end of the month, add a day 
        {
            day++;
        }
    }

    public String toString()
    {
        //convert the day of the week number to a string name of the week  
        if(getDow()==0)
        {
            dayname="Sunday";
        }
        if(getDow()==1)
        {
            dayname="Monday";
        }
        if(getDow()==2)
        {
            dayname="Tuesday";
        }
        if(getDow()==3)
        {
            dayname="Wednesday";
        }
        if(getDow()==4)
        {
            dayname="Thursday";
        }
        if(getDow()==5)
        {
            dayname="Friday";
        }
        if(getDow()==6)
        {
            dayname="Saturday";
        }

        //convert the month number into a string name of the month
        if(getMonth()==1)
        {
            monthname="January";
        }
        if(getMonth()==2)
        {
            monthname="February";
        }
        if(getMonth()==3)
        {
            monthname="March";
        }
        if(getMonth()==4)
        {
            monthname="April";
        }
        if(getMonth()==5)
        {
            monthname="May";
        }
        if(getMonth()==6)
        {
            monthname="June";
        }
        if(getMonth()==7)
        {
            monthname="July";
        }
        if(getMonth()==8)
        {
            monthname="August";
        }
        if(getMonth()==9)
        {
            monthname="September";
        }
        if(getMonth()==10)
        {
            monthname="October";
        }
        if(getMonth()==11)
        {
            monthname="November";
        }
        if(getMonth()==12)
        {
            monthname="December";
        }

        //print out the current date that the program has stored
        System.out.println("The date is " +dayname +" " +monthname +" " +day +", " +year);
        return " "; //a nice space to separate the lines of the outputted code for easier reading
    }

    public void today() //get the current date being used by the computer that is running this program
    {
        Date x= new Date(); //get current date in variable
        int yr=x.getYear()+1900; //extract year from date (year0=1900 so add 1900)
        int mn=x.getMonth()+1; //extract month from date (January =0 so add one)
        int da=x.getDate(); //extract date of day from date
        int daowe=x.getDay(); //extract day of week from date
        set(mn,da,yr,daowe); //set current date to supplied info from the computer
        
    }

    public void yesterday() //this code will take the code to the day before
    {
        //day of the week
        if(getDow()==0) //beginning of week
        {
            dayofweek=6;
        }
        else //during the week
        {
            dayofweek--;
        }
        
        //day of the month        
        if(getDay()==1) //first day of the month, so moving back to the last day of another month
        {
            if(getMonth()==3)
            {
                if(getYear()%100==0 && getYear()%400==0) //check for leap year
                {
                    day=29;
                    month--;
                }
                else if(getYear()%100!=0 && getYear()%4==0) //check for leap year
                {
                    day=29;
                    month--;
                }
                else //not leap year
                {
                    day=28;
                    month--;
                }
            }
            else if(getMonth()==12||getMonth()==10||getMonth()==7||getMonth()==5) //going to 30 day months
            {
                day=30;
                month--;
            }
            else //going to 31 day months
            {
                if(getMonth()==1)
                {
                    day=31;
                    month=12;
                    year--;
                }
                else
                {
                    day=31;
                    month--;
                }
            }
        }
        else //some day in the month, not the first
        {
            day--;
        }
    }
}