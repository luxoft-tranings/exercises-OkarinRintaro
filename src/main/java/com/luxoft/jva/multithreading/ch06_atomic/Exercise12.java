package com.luxoft.jva.multithreading.ch06_atomic;

/**
 * In this exercise we will play volatile ping-pong:
 * <ul>
 * <li>Create classes {@link Ping} and {@link Pong} that implements {@link Runnable}.</li>
 * <li>Create class {@link Ball} that has two volatile fields ping and pong.</li>
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
public class Exercise12 {

	public static void main(String[] args) {
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
        while(true)
        {
            if(!item.CheckStrike())
            {
                System.out.println("Ping");
                try {
             Thread.sleep(300);
                }
             catch (InterruptedException ex) {
                ex.printStackTrace();
            }
                item.isStrike = true;
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
        while(true)
        {
            if(item.CheckStrike())
            {
                System.out.println("Pong");
                          try {
                Thread.sleep(300);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
                item.isStrike = false;
            }
        }
    }
}

class Ball
{
    volatile boolean isStrike = false;
    boolean CheckStrike()
    {
        return isStrike;
    }
}
