package synchro;
import java.util.concurrent.Semaphore;

public class Passenger extends Thread{
	public static volatile int totalInCar = 0;
	private int C;
	private int ID;
	private Semaphore mutex1;
	private Semaphore mutex2;
	private Semaphore boarding;
	private Semaphore unboarding;
	private Semaphore doneBoarding;
	private Semaphore doneUnboarding;
	
	public Passenger(int C, int id, Semaphore mutex1, Semaphore mutex2, Semaphore boarding, Semaphore unboarding, Semaphore doneBoarding, Semaphore doneUnboarding){
		this.C = C;
		this.ID = id;
		this.mutex1 = mutex1;
		this.mutex2 = mutex2;
		this.boarding = boarding;
		this.unboarding = unboarding;
		this.doneBoarding = doneBoarding;
		this.doneUnboarding = doneUnboarding;
	}
	public void run() {
		// Boarding phase
		try {
			boarding.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		board();
		
		try {
			mutex1.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.totalInCar++;
		if(totalInCar == this.C) {
			doneBoarding.release(1);
		}
		mutex1.release();
		
		//Unboarding phase
		try {
			unboarding.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		unboard();
		
		//Decrement car passengers and signal to car all passengers are dropped off
		//if last passenger
		try {
			mutex2.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		totalInCar--;
		if(totalInCar == 0)
			doneUnboarding.release();
		mutex2.release();
	}
	/**
	 * This function simulates the boarding of this passenger in the car.
	 */
	public void board() {
		System.out.println("Passenger " + this.ID + ": I'm going on a trip in my favorite rocket ship!");
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * This function simulates the passenger getting off the car.
	 */
	public void unboard() {
		System.out.println("Passenger " + this.ID + ": I'm unboarding!");
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
