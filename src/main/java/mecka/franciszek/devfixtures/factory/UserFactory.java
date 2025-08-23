package mecka.franciszek.devfixtures.factory;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import mecka.franciszek.user.User;
import mecka.franciszek.user.UserService;
import org.instancio.Instancio;

import java.util.List;

import static org.instancio.Select.field;

@ApplicationScoped
@Slf4j
public class UserFactory implements FixtureFactory {

  private final UserService userService;

  @Inject
  public UserFactory(UserService userService) {
    this.userService = userService;
  }

  /**
   * Generates users and persists them reactively.
   */
  public Uni<Void> generateFixtures() {

    List<User> users = Instancio.ofList(User.class)
      .size(50)
      .ignore(field(User::getId))
      .create();

    log.info("Generating {} Users", users.size());

    List<Uni<User>> creationUnis = users.stream()
      .map(userService::create) // reactive persist
      .toList();

    return Uni.combine().all().unis(creationUnis)
      .discardItems(); // Returns Uni<Void>
  }

  @WithSession
  public Uni<Void> generateFixturesMulti() {
    List<User> users = Instancio.ofList(User.class)
      .size(50)
      .ignore(field(User::getId))
      .create();

    return Multi.createFrom().iterable(users)
      .onItem().transformToUniAndConcatenate(userService::create)
      .collect().asList()
      .replaceWithVoid();
  }

  @Override
  public FixtureFactory getNextFactory() {
    return null;
  }

}
