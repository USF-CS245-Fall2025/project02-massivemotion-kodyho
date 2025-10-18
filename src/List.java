/*
* Author: Kody Ho
* Project 02
* ----------
* 10/20/25
* 
* the list interface that came with the code skeleton
* 
*/


public interface List<T> {

    public void add (int index, T element);
    public boolean add (T element);
    public T get (int index);
    public T remove (int index);
    public int size ();
}