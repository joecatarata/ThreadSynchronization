package main_prog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Semaphore;

import synchro.Car;

public class Main {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Input N and C (N C): ");
		String[] input = br.readLine().split(" ");
		//input like
		//10 5 
		Semaphore mutex = new Semaphore(1);
		Semaphore mutex2 = new Semaphore(1);
		Semaphore boarding = new Semaphore(0);
		Semaphore unboarding = new Semaphore(0);
		Semaphore doneBoarding = new Semaphore(0);
		Semaphore doneUnboarding = new Semaphore(0);
		
		int N = Integer.parseInt(input[0]);
		int C = N/2;
		Car car = new Car(C, boarding, unboarding, doneBoarding, doneUnboarding);
//		int C = Integer.parseInt(input[1]);
		
	}

}
	