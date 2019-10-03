package com.nozomisoft.rest.model;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private BigInteger id;
  @NotNull
  @NotEmpty
  private String username;
  @NotNull
  @NotEmpty
  private String password;
  @NotNull
  @NotEmpty
  private String firstName;
  @NotNull
  @NotEmpty
  private String lastName;
  @NotNull
  @NotEmpty
  @Pattern(regexp = "^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$")
  private String email;
  @NotNull
  @Valid
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "location_id", referencedColumnName = "id")
  private Location location;
  //@NotNull
  @Size(min = 1)
  @Transient
  private List<String> roles;
  @NotNull
  @NotEmpty
  private String status;

  private LocalDateTime created;

}
