package maths;

import genetic.FitnessFunction;
import genetic.Solution;

public class MathsFitnessFunction implements FitnessFunction {

	@Override
	public float getFitness(Solution solution) 
	{
		String bitString = solution.getBitString();
		
		String no1 = bitString.substring(0, 3);
		String operation = bitString.substring(3, 5);
		String no2 = bitString.substring(5);
		
		return calculate(no1, operation, no2);
	}
	
	private float calculate(String no1, String operation, String no2)
	{
		float alpha = Float.parseFloat(no1);
		float gamma = Float.parseFloat(no2);
		float result = 0.0f;
		
		switch(operation)
		{
			case "00": result = alpha + gamma; break;
			case "01": result = alpha - gamma; break;
			case "10": result = alpha * gamma; break;
			case "11": result = alpha / gamma; break;
		}

		if(Float.isInfinite(result))
			result = Float.MIN_VALUE;
		
		return result;
	}
}
