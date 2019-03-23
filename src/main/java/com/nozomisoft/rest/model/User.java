package com.nozomisoft.rest.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

  private String id;
  @NotNull
  @NotEmpty
  private String username;
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
}
