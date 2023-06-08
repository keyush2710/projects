import java.util.ArrayList;

public class Queue<T>{
    private int size=0;
    private ArrayList<BinaryTree<Pair>> list;

    public Queue()
    {
        size=0;
        list=new ArrayList<>();
    }

    /**
     * this method is used to check whether queue is empty or not
     * @return
     */
    public boolean isEmpty()
    {
        if(size==0)
        {
            return true;
        }
        return false;
    }
    public int getSize() {
        return size;
    }
    public BinaryTree<Pair> get(int index)
    {
        return list.get(index);
    }

    /**
     * to add binary tree to the queue
     * @param bt
     */
    public void add(BinaryTree<Pair> bt)
    {
        list.add(bt);
        size++;
    }

    /**
     * to rmove elemnt from the queue
     */
    public void remove()
    {
        list.remove(0);
        size--;
    }
}
