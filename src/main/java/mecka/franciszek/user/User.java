package mecka.franciszek.user;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
public class User extends PanacheEntityBase {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false)
  private String name;

  @Column(nullable = false)
  private String password;

  @CreationTimestamp
  @Column(updatable = false, nullable = false)
  private ZonedDateTime created;

  @Version
  private int version;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "user_roles")
  @Column(name = "role")
  @Enumerated(EnumType.STRING)
  private List<UserRole> roles;

  public void updatePassword(String password) {
    this.password = password;
  }

}
