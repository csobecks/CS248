import java.io.*;
import java.util.*;

class LaFood
{
    public static void main(String [] args) throws IOException
    {
        int action=1;
        int waittime[]= new int[10];
        int waitcount=0;
        System.out.println("\n*** Welcome to LaFood Restaurant Simulator ***");
        System.out.println("Enter data file name: ");
        Scanner input=new Scanner(System.in);
        String file=input.next();
        input.close();
        Scanner reader=new Scanner(new FileReader(file));
        Queue X=new Queue();
        

        while(action!=0)
        {
            X.enqueue(new List(reader));
            String a=((List)X.info()).getEvent();
            if(a.equals("A"))
            {
                int b=((List)X.info()).getTime();
                int c=((List)X.info()).getSize();
                String d=((List)X.info()).getName();
                System.out.println("Please wait at the bar, party"+d+" of "+c+" people. (time="+b+")");
            }
            else if(a.equals("T"))
            {
                int weehoo=0;
                int e=((List)X.info()).getTime();
                while(weehoo==0)
                {
                    Object holder=((List)X.dequeue());
                    int f=((List)holder).getTime();
                    String h=((List)holder).getName();
                    if(h==null)
                    {}
                    else
                    {
                        waittime[waitcount]=e-f;
                        waitcount++;
                        System.out.println("Tabel for "+h+"! (time="+e+")");
                        weehoo++;
                    }
                }
            }
            if(a.equals("Q"))
            {
                action=0;
            }
        }
        System.out.println("** Simulation Terminated **\n");

        int hold=0;
        for(int i=0;i<waitcount;i++)
        {
            hold=hold+waittime[i];
        }
        double waitav=hold/waitcount;
        System.out.println("The average waiting time was: "+waitav);
        System.out.println("The following parties were never seated:");
        action=1;
        while(action!=0)
        {
            List g=(List)X.dequeue();
            if(g.getEvent().equals("A"))
            {
                g.print();
            }
            else if(g.getEvent().equals("Q"))
            {
                action=0;
            }            
            else{}
        }
        System.out.println("\n\n");
    }
}