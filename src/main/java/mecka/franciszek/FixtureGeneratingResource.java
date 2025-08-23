package mecka.franciszek;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import mecka.franciszek.devfixtures.factory.InitFixtureFactoryManager;


@Path("/api/v1/fixture-generation")
public class FixtureGeneratingResource {

  private final InitFixtureFactoryManager initFixtureFactoryManager;

  public FixtureGeneratingResource(InitFixtureFactoryManager initFixtureFactoryManager) {
    this.initFixtureFactoryManager = initFixtureFactoryManager;
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Uni<Void> create() {
    return initFixtureFactoryManager.generateFixtures();
  }

  @Path("multi")
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Uni<Void> createMulti() {
    return initFixtureFactoryManager.generateFixturesMulti();
  }
}