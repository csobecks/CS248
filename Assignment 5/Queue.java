public class Queue
{
    private class node
    {
        public Object data;
        public node next;
    }

    node front, back;
    int count;

    public Queue()
    {
        count=0;
        front=back=null;
    }

    public void enqueue(Object x)
    {
        node newnode= new node();
        newnode.data=x;
        newnode.next=null;
        back.next=newnode;

        if(isEmpty())
        {
            front=newnode;
        }
        else
        {
            back.next=newnode;
        }
        back=newnode;
        count++;
    }
    public Object dequeue()
    {
        if(isEmpty()) return null;
        Object oldfront=front.data;
        front=front.next;
        count--;
        if(count==0)
        {
            back=null;
        }
        return oldfront;
    }
    public Object getFront()
    {
        if(isEmpty())
        {
            return null;
        }
        else
        {
            return front.data;
        }
    }
    public int size() 
    {
        return count;
    }
    public boolean isEmpty()
    {
        return count<=0;
    }
    public boolean isFull()
    {
        return false;
    }
    public void makeEmpty()
    {
        count=0;
        front=back=null;
    }
}