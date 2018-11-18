package rastrigin.ga;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import genetic.*;
import rastrigin.fitness.RastriginTraditionalFitnessFunction;
import rastrigin.solutions.RastriginTraditionalSolution;

public class RastriginTraditionalGA implements GeneticAlgorithm
{
	private Random rand = new Random();
	private Solution [] solutions;
	private static final FitnessFunction FITFUNC = new RastriginTraditionalFitnessFunction();
	private int noGenerations;
	private int precision;
	private float mutationChance;
	private float thresholdFitness = 0.0f;
	
	public RastriginTraditionalGA(int noSolutions, int precision, float thresholdFitness)
	{
		this(noSolutions, precision, 0.02f, thresholdFitness);
	}
	
	public RastriginTraditionalGA(int noSolutions, int precision, float mutationChance, float thresholdFitness)
	{
		this(0, noSolutions, precision, mutationChance);
		this.thresholdFitness = thresholdFitness;
	}
	public RastriginTraditionalGA(int noGenerations, int noSolutions)
	{
		this(noGenerations, noSolutions, 2, .02f);
	}
	
	public RastriginTraditionalGA(int noGenerations, int noSolutions, int precision)
	{
		this(noGenerations, noSolutions, precision, .02f);
	}
	
	public RastriginTraditionalGA(int noGenerations, int noSolutions, int precision, float mutationChance)
	{
		this.noGenerations = noGenerations;
		solutions = new RastriginTraditionalSolution[noSolutions];
		this.precision = precision;
		this.mutationChance = mutationChance;
		initialise();
	}
	
	@Override
	public void initialise() 
	{
		for(int solIndex = 0; solIndex < solutions.length; solIndex++)
		{
			solutions[solIndex] = new RastriginTraditionalSolution(precision);
		}
		
		solutions = sortSolutions(solutions);
	}

	@Override
	public void execute(boolean display) 
	{
		if(noGenerations == 0)
		{
			thresholdExecution(display);
		}
		
		else
		{
			if(display)
			{
				for(int genCount = 0; genCount < noGenerations; genCount++)
				{
					System.out.println("\nGeneration " + (genCount + 1) + ":");
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
	}
	
	private void thresholdExecution(boolean display)
	{
		int genCount = 1;
		if(display)
		{
			while(FITFUNC.getFitness(solutions[0]) > thresholdFitness)
			{
				System.out.println("\nGeneration " + genCount + ":");
				display();
				propegate();
				solutions = sortSolutions(solutions);
				genCount++;
			}
		}
		
		else
		{
			while(FITFUNC.getFitness(solutions[0]) > thresholdFitness)
			{
				propegate();
				solutions = sortSolutions(solutions);
				genCount++;
			}
		}
		
		System.out.println("\nGeneration " + genCount + ":");
		display();
	}
	
	@Override
	public void propegate() 
	{
		solutions = sortSolutions(solutions);
		Solution [] childSolutions = new Solution[solutions.length];
		
		//Low to Low, High to High
		for(int solIndex = 0; solIndex < solutions.length - 1; solIndex += 2)
		{
			childSolutions[solIndex] = crossover(solutions[solIndex], solutions[solIndex + 1]);
			childSolutions[solIndex + 1] = crossover(solutions[solIndex + 1], solutions[solIndex]);
			
			float mutation = rand.nextFloat();
			if(mutation < mutationChance)
			{
				childSolutions[solIndex] = mutate(childSolutions[solIndex]);
				childSolutions[solIndex + 1] = mutate(childSolutions[solIndex + 1]);
			}
		}
		
		solutions = sortSolutions(childSolutions);
	}
	
	private Solution crossover(Solution alpha, Solution gamma)
	{
		String alphaString = alpha.getBitString();
		String gammaString = gamma.getBitString();
		
		String number1 = alphaString.substring(0, precision + 2);
		String number2 = gammaString.substring(precision + 2);
		
		String childString = number1 + number2;
		return new RastriginTraditionalSolution(childString);
	}
	
	private Solution mutate(Solution alpha)
	{
		String alphaString 	= alpha.getBitString();
		String number1 		= alphaString.substring(0, precision + 2);
		String number2		= alphaString.substring(precision + 2);
		
		int mutationIndex = rand.nextInt(precision + 1) + 1;
		
		//Mutate number 1
		if(rand.nextInt(2) == 0)
		{
			char mutationBit = number1.charAt(mutationIndex);
			
			if(mutationBit == '9')
			{
				mutationBit--;
			}
			
			else if(mutationBit == '0')
			{
				mutationBit++;
			}
			
			else
			{
				if(rand.nextInt(2) == 0)
				{
					mutationBit--;
				}
				
				else
				{
					mutationBit++;
				}
			}
			
			number1.replace(number1.charAt(mutationIndex), mutationBit);
		}
		
		//Mutate number 2
		else
		{
			char mutationBit = number2.charAt(mutationIndex);
			
			if(mutationBit == '9')
			{
				mutationBit--;
			}
			
			else if(mutationBit == '0')
			{
				mutationBit++;
			}
			
			else
			{
				if(rand.nextInt(2) == 0)
				{
					mutationBit--;
				}
				
				else
				{
					mutationBit++;
				}
			}
			
			number2.replace(number2.charAt(mutationIndex), mutationBit);
		}
		
		return new RastriginTraditionalSolution(number1 + number2);
	}

	@Override
	public void display() 
	{
		solutions = sortSolutions(solutions);
		
		for(int solIndex = 0; solIndex < solutions.length; solIndex++)
		{
			Solution sol = solutions[solIndex];
			System.out.println(	(solIndex + 1) + ":\t" + sol.toString() +
								"->" + FITFUNC.getFitness(sol));
		}
	}
	
	private Solution[] sortSolutions(Solution [] solutions)
	{
		List<Solution> solList = Arrays.asList(solutions);
		solList.sort(
			new Comparator<Solution>()
			{
				@Override
				public int compare(Solution dis, Solution dat)
				{
					return (int) (FITFUNC.getFitness(dis) - FITFUNC.getFitness(dat));
				}
			}
		);
		
		return solList.toArray(new Solution[0]);
	}
}
