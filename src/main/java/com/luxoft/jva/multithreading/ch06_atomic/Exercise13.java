package com.luxoft.jva.multithreading.ch06_atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * In this exercise we will play atomic ping-pong again:
 * <ul>
 * <li>Create classes {@link Ping} and {@link Pong} that implements {@link Runnable}.</li>
 * <li>Create class {@link Ball} that has two {@link AtomicInteger} fields ping and pong.</li>
 * </ul>
 * <p>
 * <p>
 * In loop
 * {@link Ping}:
 * <ul>
 * <li>Increase ping value by 1</li>
 * <li>Do nothing while current step != pong</li>
 * </ul>
 * <p>
 * <p>
 * {@link Pong}:
 * <ul>
 * <li>Do nothing while ping != current step</li>
 * <li>Increase pong value by 1</li>
 * </ul>
 *
 * @author BKuczynski.
 */
public class Exercise13 {

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
            count = item.ping.incrementAndGet();
            ++count;
                System.out.println("Ping");
                          try {
                Thread.sleep(300);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
                while(item.pong.get() != count)
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
            while(item.ping.get() != count)
            {
            } 
            count = item.pong.incrementAndGet();
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
    AtomicInteger ping = new AtomicInteger(-1);
    AtomicInteger pong = new AtomicInteger(-1);
}