package mecka.franciszek.project;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Version;
import lombok.Getter;
import mecka.franciszek.user.User;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;

@Entity
@Table(
  name = "projects",
  uniqueConstraints = {
    @UniqueConstraint(columnNames = {"name", "user_id"})
  }
)
@Getter
public class Project extends PanacheEntityBase {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @ManyToOne(optional = false)
  private User user;

  @CreationTimestamp
  @Column(updatable = false, nullable = false)
  private ZonedDateTime created;

  @Version
  private int version;

  public void updateUser(User user) {
    this.user = user;
  }
}
