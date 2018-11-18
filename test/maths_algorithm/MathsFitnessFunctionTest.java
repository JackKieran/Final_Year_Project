package maths_algorithm;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import genetic.FitnessFunction;
import maths.MathsFitnessFunction;
import maths.MathsSolution;

public class MathsFitnessFunctionTest 
{
	private FitnessFunction fitFunc;
	
	@Before
	@Test
	public void init()
	{
		fitFunc = new MathsFitnessFunction();
	}
	
	@Test
	public void additionTest()
	{
		String addingString = "00100001"; //1 + 1 = 2
		
		try 
		{
			MathsSolution sol = new MathsSolution(addingString);
			assertEquals(fitFunc.getFitness(sol), 2, 0.0f);
		} 
		
		catch (Exception e) 
		{
			fail("Should nopt have thrown exception");
		}
	}
	
	@Test
	public void subtractionTest()
	{
		String subtractionString = "10001001"; //4 - 1 = 3
		
		try
		{
			MathsSolution sol = new MathsSolution(subtractionString);
			assertEquals(fitFunc.getFitness(sol), 3, 0.0f);
		}
		
		catch(Exception e)
		{
			fail("Should not have thrown exception.");
		}
	}
	@Test
	public void multiplicationTest()
	{
		String multiplicationString = "10010010"; //4 * 2 = 8
		
		try
		{
			MathsSolution sol = new MathsSolution(multiplicationString);
			assertEquals(fitFunc.getFitness(sol), 8, 0.0f);
		}
		
		catch(Exception e)
		{
			fail("Should not have thrown exception.");
		}
	}
	
	@Test
	public void divisionTest()
	{
		String divisionString = "11111011";	//7 / 3 = 2
		
		try
		{
			MathsSolution sol = new MathsSolution(divisionString);
			assertEquals(fitFunc.getFitness(sol), 2, 0.0f);
		}
		
		catch(Exception e)
		{
			fail("Should not have thrown exception.");
		}
	}
}
