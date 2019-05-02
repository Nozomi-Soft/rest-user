package com.nozomisoft.rest.model;

import java.time.LocalDateTime;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "users")
public class User {

  @Id
  private String id;
  @NotNull
  @NotEmpty
  @Indexed(direction = IndexDirection.ASCENDING)
  private String username;
  @NotNull
  @NotEmpty
  private String password;
  @NotNull
  @NotEmpty
  private String name;
  @NotNull
  @NotEmpty
  private String lastName;
  @NotNull
  @NotEmpty
  @Pattern(regexp = "^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$")
  private String email;
  @NotNull
  @Valid
  private Location location;
  @NotNull
  @Size(min = 1)
  private List<String> roles;
  @NotNull
  @NotEmpty
  private String status;

  private LocalDateTime created;

  @Getter
  @Setter
  public static class Location {

    @NotNull
    @NotEmpty
    @Size(max = 4)
    private String country;
    @NotNull
    @NotEmpty
    @Size(max = 4)
    private String language;
  }
}
