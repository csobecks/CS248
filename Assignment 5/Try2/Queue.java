public class Queue implements Interface
{
    Object [] waitlist;
    int front, back, count;

    public Queue(int maxsize)
    {
        waitlist= new Object[maxsize];
        front=0;
        back=maxsize-1;
        count=0;
    }

    public Queue()
    {
        this(50);
    }

    public void enqueue(Object x)
    {
        if(isFull()) return;
        back++;
        if(back>=waitlist.length) back=0;
        waitlist[back]=x;
        count++;
    }
    
    public Object dequeue()
    {
        if(isEmpty()) return null;
        int oldfront=front;
        front++;
        if(front>=waitlist.length) front=0;
        count=count-1;
        return waitlist[oldfront];
    }

    public Object info()
    {
        if(isEmpty()) return null;
        return waitlist[back];
    }

    public Object getFront()
    {
        return isEmpty()?null:waitlist[front];
    }
    public int size() {return count;}
    public boolean isEmpty() {return count<=0;}
    public boolean isFull() {return count>= waitlist.length;}
    public void makeEmpty()
    {
        front=0;
        back=waitlist.length-1;
        count=0;
    }

}