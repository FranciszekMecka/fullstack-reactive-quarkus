package mecka.franciszek.devfixtures.factory;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import mecka.franciszek.user.User;
import mecka.franciszek.user.UserService;
import org.instancio.Instancio;

import java.util.List;

import static org.instancio.Select.field;

@ApplicationScoped
@Slf4j
public class UserFactory implements FixtureFactory {

  private final ProjectFactory projectFactory;
  private final UserService userService;

  @Inject
  public UserFactory(ProjectFactory projectFactory, UserService userService) {
    this.projectFactory = projectFactory;
    this.userService = userService;
  }

  /**
   * Generates users and persists them reactively.
   */
  public Uni<Void> generateFixtures() {

    List<User> users = Instancio.ofList(User.class)
      .size(20)
      .ignore(field(User::getId))
      .create();

    log.info("Generating {} Users", users.size());

    return Panache.withTransaction(() -> {
      List<Uni<User>> creationUnis = users.stream()
        .map(userService::create) // reactive persist
        .toList();

      return Uni.combine().all().unis(creationUnis)
        .discardItems(); // Returns Uni<Void>
    });
  }

  @Override
  public FixtureFactory getNextFactory() {
    return null;
  }

}
