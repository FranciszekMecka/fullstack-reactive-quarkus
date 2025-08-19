package mecka.franciszek;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/non-blocking-endpoint")
public class GreetingResource {

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public Uni<String> hello() {
    return Uni.createFrom().item("Hello").onItem().transform(s -> s + " World!");
  }
}
