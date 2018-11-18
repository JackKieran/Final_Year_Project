package maths;

import java.util.Random;

import genetic.Solution;

public class MathsSolution implements Solution 
{
	public static final int BITLENGTH = 8;
	private String bits;
	
	private static final int SHORT = 0, LONG = 1;
	
	public MathsSolution()
	{
		Random rand = new Random();
		StringBuilder builder = new StringBuilder();
		for(int bitCount = 0; bitCount < BITLENGTH; bitCount++)
		{
			builder.append("" + rand.nextInt(2));
		}
		
		bits = builder.toString();
	}
	
	public MathsSolution(String bitString) throws Exception
	{
		int check = validateString(bitString);
		
		switch(check)
		{
			case SHORT: 	throw new ShortBitStringException();
			case LONG: 		throw new LongBitStringException();
		}
		
		bits = bitString;
	}
	
	@Override
	public String getBitString() 
	{
		return bits.toString();
	}
	
	private int validateString(String bitString)
	{
		int result = 1;
		
		if(bitString.length() < BITLENGTH)
			result = SHORT;
		
		else if(bitString.length() > BITLENGTH)
			result = LONG;
		
		return result;
	}
	
	public class ShortBitStringException extends Exception
	{
		private static final long serialVersionUID = 1L;
	}
	
	public class LongBitStringException 	extends Exception
	{
		private static final long serialVersionUID = 1L;
	}
}
