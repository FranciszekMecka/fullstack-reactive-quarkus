package mecka.franciszek.devfixtures.factory;

import io.quarkus.arc.profile.IfBuildProfile;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class TaskFactory implements FixtureFactory {

  @Override
  public Uni<Void> generateFixtures() {
    return Uni.createFrom().voidItem();
  }

  @Override
  public FixtureFactory getNextFactory() {
    return null;
  }
}
