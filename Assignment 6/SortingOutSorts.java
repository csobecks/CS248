// Assignment 6: Sorting Out Sorts
// Author: Casey Sobecks

import java.io.*;
import java.util.*;

class SortingOutSorts 
{
    public static void printfunction(Integer[]list) //commonly used function to print out the sorted lists
    {
        System.out.println("Here's the list.");

        for(int i=0; i<list.length; i++) //loop to print out the list
        {
            System.out.print(list[i]+" ");
        }
        System.out.println();
    }
    
    public static void main(String [] args) throws IOException
    {
        //variables
        Integer [] list;
        Integer [] bubble;
        Integer [] insertion;
        Integer [] selection;
        Integer [] quick;
        Integer [] shell;
        long starttime, finaltime;
        int doprint=0;

        //receive a number from the user/bossman
        System.out.println("Hey Hey Hey, gimme a number!");
        Scanner cin=new Scanner(System.in);
        int size=cin.nextInt();
        cin.close(); //don't forget to close! or java will get upsetty spaghetti

        if(size<=100) //check if we even bother printing out the lists
        {
            doprint=1;
        }
        
        //create each of the lists that will get sorted
        list=new Integer[size];
        quick=new Integer[size];
        bubble=new Integer[size];
        shell=new Integer[size];
        selection=new Integer[size];
        insertion=new Integer[size];

		for(int i=0; i<list.length; i++)                                 //define the starting list
        {	
            list[i]=new Integer((int)(list.length*Math.random()));
        }

        for(int i=0;i<list.length;i++)                                  //set each list to be sorted equal to the starting list. 
        {
            quick[i]=list[i];
            bubble[i]=list[i];
            shell[i]=list[i];
            selection[i]=list[i];
            insertion[i]=list[i];
        }

        System.out.println("Alright time to sort!");                    //Sorting Time!
        if(doprint==1)                                                  //print original list
        {
            System.out.print("Here's the starting list\n");
            printfunction(list);
        }
        
        //quick sort run time
        System.out.print("Lets move onto Quick Sort. This sort takes ");
        starttime=System.currentTimeMillis();                           //get start time
        Sorts.quick(quick);                                             //sort
        finaltime=System.currentTimeMillis()-starttime;                 //get run time
        System.out.print(finaltime+" milliseconds to run. Woo!\n");
        if(doprint==1) printfunction(quick);                            //print out sorted list

        //insertion sort run time
        System.out.print("Now for Insertion Sort. This sort takes ");
        starttime=System.currentTimeMillis();                           //get start time
        Sorts.insertion(insertion);                                     //sort
        finaltime=System.currentTimeMillis()-starttime;                 //get run time
        System.out.print(finaltime+" milliseconds to run. Amazing!\n");
        if(doprint==1) printfunction(insertion);                        //print out sorted list
        
        //selection sort run time
        System.out.print("It's Selection Sort time! This sort takes ");
        starttime=System.currentTimeMillis();                           //get start time
        Sorts.selection(selection);                                     //sort
        finaltime=System.currentTimeMillis()-starttime;                 //get run time
        System.out.print(finaltime+" milliseconds to run. Egads!\n");
        if(doprint==1) printfunction(selection);                        //print out sorted list

        //shell sort run time
        System.out.print("Shell Sort? Sure! This sort takes ");
        starttime=System.currentTimeMillis();                           //get start time
        Sorts.shell(shell);                                             //sort
        finaltime=System.currentTimeMillis()-starttime;                 //get run time
        System.out.print(finaltime+" milliseconds to run. Egads!\n");
        if(doprint==1) printfunction(shell);                            //print out sorted list

        //bubble sort run time
        System.out.print("Now Bubble Sort! This sort takes ");
        starttime=System.currentTimeMillis();                           //get start time
        Sorts.bubble(bubble);                                           //sort
        finaltime=System.currentTimeMillis()-starttime;                 //get run time
        System.out.print(finaltime+" milliseconds to run. Wow!\n");
        if(doprint==1) printfunction(bubble);                           //print out sorted list

        System.out.println("\nI think we learned a lot here today, together :)\nBYE!\n");   //say bye

        return;
    }
}