package maths_algorithm;

import static org.junit.Assert.*;

import org.junit.Test;

import maths.MathsSolution;

public class MathsSolutionTest 
{
	private MathsSolution testSolution;
	
	@Test
	public void shortStringTest() 
	{
		String shortString = "01";
		
		try
		{
			testSolution = new MathsSolution(shortString);
			
			fail("Should have thrown 'too small' exception.");
		}
		
		catch(MathsSolution.ShortBitStringException shortEx)
		{
			assertTrue(true);
		} 
		
		catch (Exception e) 
		{
			fail("Should have thrown 'too small' exception.");
		}
	}
	
	@Test
	public void longStringTest()
	{
		StringBuilder longStringBuilder = new StringBuilder();
		
		for(int bitCount = MathsSolution.BITLENGTH + 1; bitCount > 0; bitCount--)
		{
			longStringBuilder.append("0");
		}
		
		try
		{
			testSolution = new MathsSolution(longStringBuilder.toString());
			
			fail("Should have thrown 'too-long' exception.");
		}
		
		catch(MathsSolution.LongBitStringException e)
		{
			assertTrue(true);
		}
		
		catch(Exception e)
		{
			fail("Should have thrown 'too-long' exception.");
		}
	}
	
	@Test
	public void toStringTest()
	{
		try 
		{
			testSolution = new MathsSolution("00000000");
			
			assertEquals("00000000", testSolution.toString());
		} 
		catch (Exception e) 
		{
			fail("Should not have thrown exception.");
		}
	}
}
