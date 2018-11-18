package rastrigin.solutions;

import java.util.Random;

import genetic.Solution;

public class RastriginTraditionalSolution implements Solution
{
	private String bitString;
	private int precision = 2;
	private static final Random rand = new Random();
	
	public RastriginTraditionalSolution(int precision)
	{
		if(precision > 2)
		{
			this.precision = precision;
		}
		
		String signBit1 = getSignBit();
		String signBit2 = getSignBit();
		
		String number1 	= getNumber();
		String number2	= getNumber();
		
		bitString = signBit1 + number1 + signBit2 + number2;
	}
	
	public RastriginTraditionalSolution(String bitString)
	{
		this.bitString = bitString;
	}
	
	private String getSignBit()
	{
		return "" + rand.nextInt(2);
	}
	
	private String getNumber()
	{
		String digit1 = "" + rand.nextInt(6);
		String [] digits = new String[precision];
		
		if(digit1.equals("5"))
		{
			digits[0] = "" + rand.nextInt(2);
			
			if(digits[0].equals("1"))
			{
				digits[1] = "" + rand.nextInt(3);
				
				if(digits[1].equals("1"))
				{
					for(int digitIndex = 2; digitIndex < digits.length; digitIndex++)
					{
						digits[digitIndex] = "0";
					}
				}
				
				else
				{
					for(int digitIndex = 2; digitIndex < digits.length; digitIndex++)
					{
						digits[digitIndex] = "" + rand.nextInt(9);
					}
				}
			}
			
			else
			{
				for(int digitIndex = 1; digitIndex < digits.length; digitIndex++)
				{
					digits[digitIndex] = "" + rand.nextInt(9);
				}
			}
		}
		
		else
		{
			for(int digitIndex = 0; digitIndex < digits.length; digitIndex++)
			{
				digits[digitIndex] = "" + rand.nextInt(9);
			}
		}
		
		String number = digit1;
		
		for(String digit : digits)
		{
			number += digit;
		}
		
		return number;
	}
	
	@Override
	public String getBitString() 
	{
		return bitString;
	}
	
	@Override
	public String toString()
	{
		String number1 = bitString.substring(0, precision + 2);
		String number2 = bitString.substring(precision + 2);
		
		String result = "(";
		
		if(number1.startsWith("1"))
			result += "-";
		
		result += number1.charAt(1) + "." + number1.substring(2) + ", ";
		
		if(number2.startsWith("1"))
			result += "-";
		
		result += number2.charAt(1) + "." + number2.substring(2) + ")";
		
		return result;
	}
}
