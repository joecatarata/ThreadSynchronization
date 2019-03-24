package synchro;

import java.util.concurrent.Semaphore;

public class Car implements Runnable{
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
	
	public void load() {
		return;
	}
	
	public void unload() {
		return;
	}
	
	public void runTrack() {
		return;
	}
}