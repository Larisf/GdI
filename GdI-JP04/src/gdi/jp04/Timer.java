/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdi.jp04;

/**
 * Klasse zum erstellen eines Timers
 * @author Bambi
 */
public class Timer 
{
	private Thread t1;
	/**
	 * Methode zum starten eines neuen Threads in dem der Counterläuft
	 * @param start startzeit übergeben
	 */
	public void countDown(long start)
	{
		t1 = new Thread(new Runnable()
		{
			public void run() 
			{
				try 
				{
					Thread.sleep(start);
                } 
				catch (InterruptedException e) 
				{
					System.exit(0);
                }
				System.out.printf("Zeit abgelaufen, die Bombe ist explodiert!\n");
				System.exit(0);
            }
        });
		t1.start();
	}
	public void stopTimer()
	{
		t1.interrupt();
	}
}
