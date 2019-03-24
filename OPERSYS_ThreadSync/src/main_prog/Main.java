package main_prog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;

import synchro.Car;
import synchro.Passenger;

public class Main {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Input the total number of passengers (N): ");
		String[] input = br.readLine().split(" ");
		
		//input like
		//10 5 
		Semaphore mutex1 = new Semaphore(1);
		Semaphore mutex2 = new Semaphore(1);
		Semaphore boarding = new Semaphore(0);
		Semaphore unboarding = new Semaphore(0);
		Semaphore doneBoarding = new Semaphore(0);
		Semaphore doneUnboarding = new Semaphore(0);
		
		int N = Integer.parseInt(input[0]); //total number of passengers
		int C = N/4; //max number of people in car
		System.out.println("Max number of passengers = " + C);
		Passenger[] threads = new Passenger[N];
		Car car = new Car(C, boarding, unboarding, doneBoarding, doneUnboarding);
//		int C = Integer.parseInt(input[1]);
		System.out.println("Starting threads..");
		car.start();
		
		for(int i=0; i<N; i++) {
			threads[i] = new Passenger(C, i+1, mutex1, mutex2, boarding, unboarding
										, doneBoarding, doneUnboarding);
			threads[i].start();
		}
		
		//Checks if deadlock happened
		Timer t = new Timer();
//		System.out.println("Checking deadlocked threads every 5 seconds starting now: ");
		t.scheduleAtFixedRate(
		    new TimerTask()
		    {
		        public void run()
		        {
		        	int deadLockedCtr = 0;
		        	for(int i=0; i<N; i++) {
		    			if(threads[i].isAlive())
		    					deadLockedCtr++;
		    		}
		        	System.out.println("Checking deadlocked threads every 5 seconds");
		        	System.out.println("Deadlocked threads: " + deadLockedCtr);
		        }
		    }, 0, 5000); 
	}

}
	