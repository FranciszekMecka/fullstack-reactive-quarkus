package mecka.franciszek.devfixtures.factory;

import io.smallrye.mutiny.Uni;

public interface FixtureFactory {

  /**
   * Generates fixtures reactively.
   * @return a Uni that completes when fixtures are generated
   */
  Uni<Void> generateFixtures();

  /**
   * Returns the next factory in the chain, or null if last.
   */
  FixtureFactory getNextFactory();

  /**
   * Executes this factory and chains the next factory if present.
   * @return a Uni that completes when this factory and all subsequent factories finish
   */
  default Uni<Void> execute() {
    return generateFixtures()
      .chain(v -> {
        FixtureFactory next = getNextFactory();
        return next != null ? next.execute() : Uni.createFrom().voidItem();
      });
  }
}
