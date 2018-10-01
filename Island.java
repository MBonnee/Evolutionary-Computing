import java.util.Collections;

public class Island {
  public Population population;
  public double migrationRate = 0.0;

  public Island(int populationSize) {
    population = new Population(populationSize, true);
  }
}