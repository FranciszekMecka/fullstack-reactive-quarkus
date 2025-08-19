package mecka.franciszek.task;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Getter;
import mecka.franciszek.project.Project;
import mecka.franciszek.user.User;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;

@Entity
@Table(name = "tasks")
@Getter
public class Task extends PanacheEntityBase {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(length = 1000)
  private String description;

  private Integer priority;

  @ManyToOne(optional = false)
  private User user;

  private ZonedDateTime complete;

  @ManyToOne
  private Project project;

  @CreationTimestamp
  @Column(updatable = false, nullable = false)
  private ZonedDateTime created;

  @Version
  private int version;

  public void updateUser(User user) {
    this.user = user;
  }

  public void updateComplete(ZonedDateTime complete) {
    this.complete = complete;
  }
}
