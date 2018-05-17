/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdi.jp03;

/**
 * Klasse zum erstellen eines Timers
 * @author Bambi
 */
public class Timer {
	/**
	 * Methode zum starten eines neuen Threads in dem der Counterläuft
	 * @param start startzeit übergeben
	 */
	public void countDown(long start)
	{
		new Thread(new Runnable() {
					public void run() {
				try {
                        Thread.sleep(start-System.currentTimeMillis());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
						System.out.printf("Zeit abgelaufen, die Bombe ist explodiert!\n");
						System.exit(0);
                }
        }).start();
	}
}
