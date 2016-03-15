package main;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Main {
	public static void main(String[] args){
		Main main = new Main();
		List<Integer> primesWithMaxNumber = main.primeWithMaxNumberOfOnesInBynary(5000);
		System.out.println(primesWithMaxNumber);
	}
	
	public List<Integer> primeWithMaxNumberOfOnesInBynary(int maxValue){
		List<Integer> primesWithMaxNumber = new LinkedList<>();
		Map<Integer, Integer> primesAndNumberOfOnes = new HashMap<Integer, Integer>();
		int maxNumberOfOnes = 0;
		
		for(int i = 2; i <= maxValue; i++)
			if(isPrime(i)){
				int numberOfOnes = Integer.bitCount(i);
				primesAndNumberOfOnes.put(i, numberOfOnes);
				if(numberOfOnes > maxNumberOfOnes)
					maxNumberOfOnes = numberOfOnes;
			}
			
		for(Integer number : primesAndNumberOfOnes.keySet())
			if(primesAndNumberOfOnes.get(number) == maxNumberOfOnes)
				primesWithMaxNumber.add(number);
		
		return primesWithMaxNumber;
	}
	
	public boolean isPrime(int number){
		for(int i = 2; i < Math.sqrt(number); i++)
			if(number % i == 0)
				return false;
		return true;
	}
}
