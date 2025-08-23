package mecka.franciszek.devfixtures.factory;

import io.quarkus.arc.profile.IfBuildProfile;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.vertx.ConsumeEvent;
import io.smallrye.mutiny.Uni;
import io.vertx.core.Vertx;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import io.vertx.mutiny.core.eventbus.EventBus;

@ApplicationScoped
@Slf4j
public class InitFixtureFactoryManager {

  private final UserFactory userFactory;

  @Inject
  public InitFixtureFactoryManager(UserFactory userFactory) {
    this.userFactory = userFactory;
  }

  public Uni<Void> generateFixtures() {
    return userFactory.generateFixtures();
  }

  public Uni<Void> generateFixturesMulti() {
    return userFactory.generateFixturesMulti();
  }
}
