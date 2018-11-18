package maths;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import genetic.FitnessFunction;
import genetic.GeneticAlgorithm;
import genetic.Solution;

public class MathsGA implements GeneticAlgorithm 
{
	private int noGenerations;
	private Solution[] solutions;
	private static final FitnessFunction FITFUNC = new MathsFitnessFunction();
	
	public MathsGA(int noGenerations, int noSolutions)
	{
		this.noGenerations = noGenerations;
		solutions = new Solution[noSolutions];
		
		initialise();
	}
	
	@Override
	public void initialise() 
	{
		for(int solCount = 0; solCount < solutions.length; solCount++)
		{
			solutions[solCount] = new MathsSolution();
		}
	}

	@Override
	public void execute(boolean display) 
	{
		if(display)
		{
			for(int genCount = 0; genCount < noGenerations; genCount++)
			{
				System.out.println("\nGeneration: " + (genCount + 1));
				display();
				propegate();
			}
		}
		
		else
		{
			for(int genCount = 0; genCount < noGenerations; genCount++)
			{
				propegate();
			}
		}
		
		display();
	}

	@Override
	public void propegate() 
	{
		//Tops and Tails
		solutions = sortSolutions();
		
		for(int solIndex = 0; solIndex < solutions.length - 1; solIndex++)
		{
			int childIndex = solutions.length - (solIndex + 1);
			solutions[childIndex] = crossover(solutions[solIndex], solutions[childIndex]);
		}
	}
	
	private Solution crossover(Solution alpha, Solution gamma)
	{
		String alphaString = alpha.getBitString();
		String gammaString = gamma.getBitString();
		
		String childString = alphaString.substring(0, 5) + gammaString.substring(5);
		
		Solution result = null;
		try 
		{
			result = new MathsSolution(childString);
		} 
		catch (Exception e) 
		{
			result = new MathsSolution();
		}
		
		return result;
	}

	@Override
	public void display() 
	{
		solutions = sortSolutions();
		
		for(int solCount = 0; solCount < solutions.length; solCount++)
		{
			Solution sol = solutions[solCount];
			System.out.println((solCount + 1) + ":\t" + sol.getBitString() + 
								" -> " + FITFUNC.getFitness(sol));
		}
	}
	
	private Solution[] sortSolutions()
	{
		List<Solution> solList = Arrays.asList(solutions);
		solList.sort(
			new Comparator<Solution>()
			{
				@Override
				public int compare(Solution dis, Solution dat)
				{
					FitnessFunction fitfunc = new MathsFitnessFunction();
					
					return (int) (fitfunc.getFitness(dat) - fitfunc.getFitness(dis));
				}
			}
		);
		
		return solList.toArray(new Solution[0]);
	}
}
