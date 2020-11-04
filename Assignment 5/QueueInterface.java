public interface QueueInterface
{
    public void enqueue(Object x);
    public Object dequeue();
    public Object getFront();
    public int size();
    public boolean isEmpty();
    public boolean isFull();
    public void makeEmpty();
}