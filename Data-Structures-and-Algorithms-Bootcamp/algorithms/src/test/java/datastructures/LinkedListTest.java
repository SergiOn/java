package datastructures;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {

    private LinkedList linkedList;

    @Before
    public void SetUp() throws Exception {
        linkedList = new LinkedList();
    }

    @Test
    public void AddFront() {
        linkedList.addFront(1);
        linkedList.addFront(2);
        linkedList.addFront(3);

//        Assert.assertEquals(3, linkedList.getFirst());
//        Assert.assertEquals(1, linkedList.getLast());
    }

    @Test
    public void GetFirst() {
    }

    @Test
    public void GetLast() {
    }

    @Test
    public void AddBack() {
    }

    @Test
    public void Size() {
    }

    @Test
    public void Clear() {
    }

    @Test
    public void DeleteValue() {
    }
}
