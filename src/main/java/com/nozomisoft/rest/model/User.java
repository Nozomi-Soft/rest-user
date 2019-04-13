package com.nozomisoft.rest.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class User {

  private String id;
  @NotNull
  @NotEmpty
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
  @NotEmpty
  @Size(max = 4)
  private String country;
  @NotNull
  @NotEmpty
  @Size(max = 4)
  private String language;
  @NotNull
  @NotEmpty
  private String role;
  @NotNull
  @NotEmpty
  private String status;

  private LocalDateTime created;
}
