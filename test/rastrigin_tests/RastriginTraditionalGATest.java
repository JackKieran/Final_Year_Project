package rastrigin_tests;

import org.junit.Test;

import genetic.GeneticAlgorithm;
import rastrigin.ga.RastriginTraditionalGA;

public class RastriginTraditionalGATest 
{
	GeneticAlgorithm ga;
	
	@Test
	public void displayTest() 
	{
		System.out.println("\nIn displayTest");
		ga = new RastriginTraditionalGA(1, 1, 2);
		ga.display();
	}
	
	@Test
	public void multiAgentTest()
	{
		System.out.println("\nIn multiAgentTest");
		ga = new RastriginTraditionalGA(1, 10, 2);
		ga.display();
	}
	
	@Test
	public void highPrecisionTest()
	{
		System.out.println("\nIn highPrecisionTest");
		ga = new RastriginTraditionalGA(1, 10, 10);
		ga.display();
	}
	
	@Test
	public void executeTest()
	{
		System.out.println("\nIn executeTest");
		ga = new RastriginTraditionalGA(5, 10, 2);
		ga.execute(true);
	}
	
	
	public void heavyExecutionTest()
	{
		System.out.println("\nIn heavyExecutionTest");
		ga = new RastriginTraditionalGA(100000, 1000, 5, 0.0f);
		ga.execute(false);
	}
	
	@Test
	public void lowThresholdExecutionTest()
	{
		System.out.println("\nIn lowThresholdExecutionTest");
		ga = new RastriginTraditionalGA(100, 2, 2.0f);
		ga.execute(true);
	}
	
	@Test
	public void highThresholdExecutionTest()
	{
		System.out.println("\nIn highThresholdExecutionTest");
		ga = new RastriginTraditionalGA(10, 2, 15f);
		ga.execute(true);
	}
}
