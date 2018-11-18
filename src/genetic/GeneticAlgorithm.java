package genetic;

public interface GeneticAlgorithm 
{
	public abstract void initialise();
	public abstract void execute(boolean display);
	public abstract void propegate();
	public abstract void display();
}
