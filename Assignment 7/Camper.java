import java.util.*;
import java.io.FileReader;
import java.io.IOException;
import javax.lang.model.util.ElementScanner6;

public class Camper implements Comparable
{
    //data members
    String name;
    int age;
    String diet;

    //constructor
    /**@param name name of camper
     * @param age age of camper
     * @param diet type of diet of the camper
     */
    public Camper (String n, int a, String d)
    {
        name=n;
        age=a;
        diet=d;
    }  
    
    public Camper(String n,int d) //d used to differentiate between a name search camper and a diet search camper
    {
        if(d==1)
        {
            name=n;
        }
        else if(d==0)
        {
            diet=n;
        }
    }  

    public String toString() //output of camper
    {
        return getName()+" "+getAge()+" "+getDiet();
    }

    public String getName() {return name;}
    public String getDiet() {return diet;}
    public int getAge() {return age;}

    public int compareTo(Object x)
    {
        if(x instanceof Camper)
        {
            Camper y=(Camper) x;
            if(y.getName()!=null) //name search
            {
                return this.name.compareTo(y.getName());
            } 
            else if(y.getDiet()!=null) //diet search
            {
                return this.diet.compareTo(y.getDiet());
            }
        }
        else //there's no other options so not a camper
        {
            System.out.println("Not a camper!");
        }
        return -1;
    }
}