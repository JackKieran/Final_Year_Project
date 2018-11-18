package maths_algorithm;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import maths.MathsGA;

public class MathsGATest 
{
	private MathsGA ga;
	
	@Test
	public void initialiseTest()
	{
		System.out.println("\nIn initialiseTest:");
		ga = new MathsGA(1, 1);
		ga.display();
	}
	
	@Test
	public void multiAgentTest()
	{
		System.out.println("\nIn multiAgentTest:");
		ga = new MathsGA(1, 10);
		ga.display();
	}
	
	@Test
	public void executeTest()
	{
		System.out.println("\nIn executionTest:");
		ga = new MathsGA(2, 2);
		ga.execute(true);
	}
	
	@Test
	public void fullRun()
	{
		System.out.println("\nIn fullRun:");
		ga = new MathsGA(10, 10);
		ga.execute(true);
	}
}
