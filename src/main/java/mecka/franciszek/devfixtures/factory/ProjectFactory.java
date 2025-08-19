package mecka.franciszek.devfixtures.factory;

import io.quarkus.arc.profile.IfBuildProfile;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class ProjectFactory implements FixtureFactory{

  private final TaskFactory taskFactory;

  public ProjectFactory(TaskFactory taskFactory) {
    this.taskFactory = taskFactory;
  }

  @Override
  public Uni<Void> generateFixtures() {
    return Uni.createFrom().voidItem();
  }

  @Override
  public FixtureFactory getNextFactory() {
    return taskFactory;
  }
}
