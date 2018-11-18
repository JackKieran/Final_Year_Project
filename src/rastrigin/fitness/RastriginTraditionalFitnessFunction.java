package rastrigin.fitness;

import genetic.FitnessFunction;
import genetic.Solution;

public class RastriginTraditionalFitnessFunction implements FitnessFunction 
{
	private static final int A = 10;
	
	@Override
	public float getFitness(Solution solution) 
	{
		String bitString = solution.getBitString();
		
		String number1 = bitString.substring(0, 4);
		String number2 = bitString.substring(4);
		
		float value1 = getValue(number1);
		float value2 = getValue(number2);
		
		return substituteIntoFormula(value1, value2);
	}
	
	private float getValue(String number)
	{
		int digit1 = Character.getNumericValue(number.charAt(1));
		int digit2 = Character.getNumericValue(number.charAt(2));
		int digit3 = Character.getNumericValue(number.charAt(3));
		
		float value = digit1 + (digit2/10.0f) + (digit3/100.0f);
		
		//Sign bit, 0 -> +|1 -> -
		if(number.charAt(0) == '1')
			value = 0 - value;
		
		return value;
	}
	
	private float substituteIntoFormula(float... values)
	{
		float result = A * values.length;
		
		for(float x : values)
		{
			result += x * x;
			
			result += A * Math.cos(2 * Math.PI * x);
		}
		
		return result;
	}
}
