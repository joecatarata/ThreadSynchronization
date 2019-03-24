package synchro;

import java.util.concurrent.Semaphore;

public class Car extends Thread{
	private int C;
	private Semaphore boarding;
	private Semaphore unboarding;
	private Semaphore doneBoarding;
	private Semaphore doneUnboarding;
	public Car(int C, Semaphore boarding, Semaphore unboarding, Semaphore doneBoarding, Semaphore doneUnboarding) {
		this.C = C;
		this.boarding = boarding;
		this.unboarding = unboarding;
		this.doneBoarding = doneBoarding;
		this.doneUnboarding = doneUnboarding;
	}
	@Override
	public void run(){
		// TODO Auto-generated method stub
		while(true) {
			load();
			boarding.release(C);
			try {
				doneBoarding.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			runTrack();
			
			unload();
			unboarding.release(C);
			try {
				doneUnboarding.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void load() {
		System.out.println("Loading passengers..");
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Done loading passengers!");
		return;
	}
	
	public void unload() {
		System.out.println("Unloading passengers..");
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Done unloading passengers!");
		return;
	}
	
	public void runTrack() {
		System.out.println("Running on track...");
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Done running on track!");
		return;
	}
}
