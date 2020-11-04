import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class CampPosanivee 
{
    public static void main(String [] args) throws IOException
    {
        //declare variables
        int running=1; 
        String input, n, d;
        int a;

        //read from text file
        Scanner reader=new Scanner(new FileReader("input.txt"));
        BST camplist=new BST();

        //start program
        System.out.println("Welcome to Camp Posanivee!!\n");
        while(running==1)
        {
            //read the command
            input=reader.next();
            
            //each input case
            if(input.compareTo("H")==0) //help function 
            {
                System.out.println("Command: H\nCommands:\nEnroll camper: E <name> <age> <diet>\nWithdraw camper: W <name>\n"+
                            "Display Camper: D <name>\nAverage Age: A\nList campers alphabetically: L\n"+
                            "Number of Vegan Campers: V\nCampers in Preorder: P\nQuit: Q\n");
            }
            else if(input.compareTo("E")==0) //enroll new camper
            {
                n=reader.next();
                a=reader.nextInt();
                d=reader.next();
                camplist.insert(new Camper(n,a,d)); 
                System.out.println("Command: "+input+" "+n+" "+a+" "+d+"\nNew camper added.\n");
            }
            else if(input.compareTo("A")==0) //average age of campers
            {
                System.out.println("Command: "+input);
                avgfunc(camplist);
            }
            else if(input.compareTo("D")==0) //display a specified camper
            {
                n=reader.next();
                System.out.println("Command: "+input+" "+n);
                dispfunc(camplist,n);
            }
            else if(input.compareTo("W")==0) //withdraw a specified camper
            {
                n=reader.next();
                System.out.println("Command: "+input+" "+n);
                camplist=withdraw(camplist,n);
                System.out.println("Camper withdrawn.\n");
            }
            else if(input.compareTo("L")==0) //alphabetically list all campers
            {
                System.out.println("Command: "+input);
                System.out.println("Alphabetical List of Campers:");
                listfunc(camplist);
            }
            else if(input.compareTo("V")==0) //output the number of vegan campers
            {
                System.out.println("Command: "+input);
                vegfunc(camplist);
            }
            else if(input.compareTo("P")==0) //output the campers in preorder
            {
                System.out.println("Command: "+input);
                preord(camplist);
            }
            else if(input.compareTo("Q")==0) //quit the program
            {
                System.out.println("Command: "+input+"\nEnd of program.");
                running=0;
            }
            else //not a command that has been programmed
            {
                System.out.println("invalid command!");
            }
        }

        reader.close(); //seriously java gets pissed if I dont put this in i dunno why but believe me I'm tired of the debugger/compiler yelling at me
        System.out.println("Bring plenty of calomine!\n"); //say goodbye
        return;
    }

    static void avgfunc(BST a) //averaging function
    {
        BST c=a; 
        if(c.isEmpty()) //empty tree case
        {
            System.out.println("There are no campers.\n");
            return;
        }

        //non empty tree case
        float tot=0;
        float avg=0;
        c.reset(BST.INORDER);
        while(c.hasNext())
        {
            Camper y=(Camper)c.getNext();
            tot+=y.getAge();
        }
        avg=tot/c.size();
        System.out.println("The average age of campers is: "+avg+"\n"); 
    }

    static void dispfunc(BST a,String n) //displaying function
    {
        BST c=a;
        Camper x=new Camper(n,1);
        Camper y=(Camper) c.lookup(x);
        if(y!=null) //camper found in list
        {
            System.out.println(y+"\n");
        }
        else //camper is not in list
        {
            System.out.println("Could not find someone named "+n+"\n");
        }
    }

    static BST withdraw(BST c, String n) //withdrawing function
    {
        Camper x=new Camper(n,1); //make camper to remove
        c.delete(x); //remove
        return c; //return the new tree
    }

    static void listfunc(BST a) //listing function
    {
        BST c=a;
        c.reset(BST.INORDER);
        while(c.hasNext()) {System.out.println(c.getNext());} //print out the tree in order
        System.out.println();
    }

    static void vegfunc(BST a) //vegan function
    {
        BST c=a;
        int count=0;
        Camper x=new Camper("V",0);
        Camper y=(Camper) c.lookup(x);
        if(y==null) //no vegan campers 
        {
            System.out.println("Number of Vegan Campers: "+count+"\n");
            return;
        }
        //some vegan campers
        c.reset(BST.INORDER);
        while(c.hasNext()) //search through tree
        {
            y=(Camper)c.getNext();
            if(y.compareTo(x)==0) //check for diet V
            {
                count++;
            }
        }
        System.out.println("Number of Vegan Campers: "+count+"\n");
        return;
    }

    static void preord(BST a) //preorder function
    {
        BST c=a;
        System.out.println("Preorder Traversal:");
        c.reset(BST.PREORDER);
        while(c.hasNext()) {System.out.println(c.getNext());} //print tree in preorder
        System.out.println();
    }
}
//go dawgs