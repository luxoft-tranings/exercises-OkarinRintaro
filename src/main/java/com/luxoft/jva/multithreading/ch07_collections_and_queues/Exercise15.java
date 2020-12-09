package com.luxoft.jva.multithreading.ch07_collections_and_queues;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.ArrayList;

/**
 * In this exercise we will play with {@link Collections#synchronizedList(List)} methods and compare them with {@link CopyOnWriteArrayList}.
 * <ul>
 * <li>Create instance of {@link List} and fill it.</li>
 * <li>Create class {@link Printer} that implements {@link Runnable} and will remove values to list and sleep for some random time.</li>
 * <li>Run this classes and observe results</li>
 * </ul>
 *
 * @author BKuczynski.
 */
public class Exercise15 {

	public static void main(String[] args) {
   //   List<Integer> myList = new ArrayList<>();
        List<Integer> myList = new CopyOnWriteArrayList<>();
        Thread p1 = new Thread(new Printer(myList));
        Thread p2 = new Thread(new Printer(myList));
        Thread p3 = new Thread(new Printer(myList));
        
        Thread cl1 = new Thread(new Clerk(myList));
        Thread cl2 = new Thread(new Clerk(myList));
        
        cl1.start();
        cl2.start();
        p1.start();
        p2.start();
        p3.start();
	}

}

class Printer implements Runnable
{
    List<Integer> m_list;
    Printer(List<Integer> l)
    {
        m_list = l;
    }
    @Override
    public void run()
    {
        while(true)
        {
        while(!m_list.isEmpty())
        {
            int index = (int)(Math.random()*m_list.size());
            Integer task = m_list.get(index);
            System.out.println("Task number for printer: " + task + "  Index:" + index);
            m_list.remove(task);
        }
        try
        {
            Thread.sleep(10);
        }
        catch (InterruptedException ex) 
        {
            ex.printStackTrace();
        }
        }
    }
}
    
class Clerk implements Runnable
{
    List<Integer> m_list;
    Clerk(List<Integer> l)
    {
        m_list = l;
    }
    @Override
    public void run()
    {
        while(true)
        {
            Integer task = (int)(Math.random()*100);
            System.out.println("Task number was added: " + task);
            m_list.add(task);
            try
            {
                Thread.sleep(10);
            }
            catch (InterruptedException ex) 
            {
                ex.printStackTrace();
            }
        }      
    }
}