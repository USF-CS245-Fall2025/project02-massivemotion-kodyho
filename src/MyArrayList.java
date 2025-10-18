/*
* Author: Kody Ho
* Project 02
* ----------
* 10/20/25
* 
* Arraylist implementation of the List interface
* - I believe that Arraylist is the optimal implementation for this project
* - This is because we do not care about the order of bodies, and we will be doing
* - a lot of random access and iteration through the list when deleteing, which Arraylist handles best.
* - nothing new here, just standard arraylist implementation
*/

/**
 * standard arraylist implementation of the list interface
 * @param <T> the type of elements in this list
 * fufills Requirement 2
 */
@SuppressWarnings("unchecked")
public class MyArrayList<T> implements List<T> {

    private Object[] data;
    private int size = 0;

    public MyArrayList() {
        data = new Object[10]; // initial capacity
    }

    private void ensureCapacity() {
        if (size >= data.length) {
            Object[] newData = new Object[data.length * 2];
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        }
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();
        ensureCapacity();
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element;
        size++;
    }

    @Override
    public boolean add(T element) {
        add(size, element);
        return true;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        return (T) data[index]; // casts so  i dont get that stupid compiler warning
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        T removed = (T) data[index]; 
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        size--;
        return removed;
    }

    @Override
    public int size() {
        return size;
    }
}
