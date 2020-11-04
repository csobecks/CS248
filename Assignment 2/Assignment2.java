import java.io.*;
import java.util.*;

class Assignment2
{
	static int [] sortArray(int [] aR, int a)
	{
		int temp=0;
		for (int n=0; n<a;n++) //bubble sort array
		{
			for(int m=n+1; m<a;m++)
			{
				if(aR[m]<aR[n])
				{
					temp=aR[m];
					aR[m]=aR[n];
					aR[n]=temp;
				}
			}
		}
		return aR;
	}
	
	static double average(int [] aR, int a)
	{
		double average;
		double sum=0;
		for(int n=0; n<a;n++)
		{
			sum=sum+aR[n]; //sum over all values
		}
		average=sum/a; //compute average
		return average;
	}

	static int [] grades(int [] aR, int a, int [] gradar)
	{
		for(int n=0;n<a;n++)
		{
			if(aR[n]<=100 && aR[n]>=90) //check for As
				gradar[0]++;
			else if(aR[n]<=89 && aR[n]>=80) //check for Bs
				gradar[1]++;
			else if(aR[n]<=79 && aR[n]>=70) //check for Cs
				gradar[2]++;
			else if(aR[n]<=69 && aR[n]>=60) //check for Ds
				gradar[3]++;
			else if(aR[n]<60)
				gradar[4]++;
		}
		return gradar;
	}
	
	static double medi(int [] aR, int a)
	{
		int middle=0;
		if(a%2==0)
		{
			int b=a/2;
			int c=a/2;
			c--;
			middle=(aR[b]+aR[c])/2; //get median for even number of scores
		}
		if(a%2!=0)
		{
			int d=(a/2);
			middle=aR[d]; //get median for odd number of scores
		}
		return middle;
	}
	
	public static void main(String [] args) throws IOException
	{
		String infile; //set file variable
		
		Scanner cin=new Scanner(System.in); //set scanner
		System.out.println("Hello kiddo! Please enter your file name\n"); //greet the user
		infile=cin.next(); //get the file name
		
		Scanner in=new Scanner(new FileReader(infile)); //scan the file
		
		int i=in.nextInt(); //get the number of scores
		int array[]=new int[i]; //set up the array for the scores
		
		for(int n=0;n<i;n++)
		{
			array[n]=in.nextInt(); //fill array
		}
		int aR[]=new int[i];
		aR=sortArray(array, i); //sort the array
		double avg=average(aR,i); //get the average
		
		int gradar[]=new int[5]; //set up array for grades
		
		grades(aR,i,gradar); //count the grades

		double med=medi(aR,i); //get the median

		System.out.println("okay! here's your info!\nThe minimum score is " + aR[0]
		+ "\nThe maximum score is " + aR[i-1]+"\nThe average score is " + avg +
		"\nThe median score is " + med +
		"\nThe corresponding letter grades for total scores are below\nA: "+gradar[0]
		+"\nB: " +gradar[1]+"\nC: " +gradar[2]+"\nD: " +gradar[3]+"\nF: " +gradar[4]+
		"\nCool! Now you don't have to calculated it yourself.\nBye!");
		return;
	}
}
