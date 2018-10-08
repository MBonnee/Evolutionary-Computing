import java.util.Collections;

public class Island {
  public Population population;
  private double migrationRate;
  private double islandFitness;

  public Island(int populationSize) {
    population = new Population(populationSize, true);
    migrationRate = 0.0;
    islandFitness = 0.0;
  }

  public double getIslandFitness() {
      return islandFitness;
  }

  public Population getPopulation() {
      return population;
  }
}
