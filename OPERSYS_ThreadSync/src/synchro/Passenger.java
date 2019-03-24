package synchro;
import java.util.concurrent.Semaphore;

public class Passenger implements Runnable{
	public static int totalInCar = 0;
	private int C;
	private Semaphore mutex1;
	private Semaphore mutex2;
	private Semaphore boarding;
	private Semaphore unboarding;
	private Semaphore doneBoarding;
	private Semaphore doneUnboarding;
	
	public Passenger(int C, Semaphore mutex, Semaphore mutex2, Semaphore boarding, Semaphore unboarding, Semaphore doneBoarding, Semaphore doneUnboarding){
		this.C = C;
		this.mutex1 = mutex1;
		this.mutex2 = mutex2;
		this.boarding = boarding;
		this.unboarding = unboarding;
		this.doneBoarding = doneBoarding;
		this.doneUnboarding = doneUnboarding;
	}
	public void run() {
		// TODO Auto-generated method stub
		try {
			boarding.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			mutex1.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.totalInCar++;
		if(totalInCar == this.C) {
			doneBoarding.release(1);
		}
		mutex1.release();
		
		try {
			unboarding.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		unboard();
		
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
	
	public void board() {
		
	}
	
	public void unboard() {
		
	}

}
