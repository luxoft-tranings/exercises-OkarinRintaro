package com.luxoft.jva.multithreading.ch06_atomic;

/**
 * EXTRA!
 *
 * In this exercise we will play ping-pong again but this time we will implement
 * whole stuff using synchronization.
 *
 * @author BKuczynski.
 */
public class Exercise14 {

    public static void main(String[] args)
    {
        Ball item = new Ball();
        Thread Pi = new Thread(new Ping(item));
        Thread Po = new Thread(new Pong(item));
        Pi.start();
        Po.start();
    }

}

class Ping implements Runnable
{
    Ball item;
    Ping(Ball b)
    {
        item = b;
    }
    @Override
    public void run()
    {
        int count = 0;
        while(true)
        {
            count = item.incrementAndGet(true);
            ++count;
                System.out.println("Ping");
                          try {
                Thread.sleep(300);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
                while(item.pong != count)
                {
                }              
        }
    }
}

class Pong implements Runnable
{
    Ball item;
    
    Pong(Ball b)
    {
        item = b;
    }
    @Override
    public void run()
    {
        int count = 0;
        while(true)
        {
            while(item.ping != count)
            {
            } 
            count = item.incrementAndGet(false);
                System.out.println("Pong");
                          try {
                Thread.sleep(300);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}

class Ball
{
    volatile int ping = -1;
    volatile int pong = -1;
    
    public synchronized int incrementAndGet(boolean p)
    {
        if(p)
        {
            ping++;
            return ping;
        }
        else
        {
            pong++;
            return pong;
        }
    }
}
