package com.nozomisoft.rest.model;

import java.math.BigInteger;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "LOCATION")
public class Location {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private BigInteger id;
  @NotNull
  @NotEmpty
  @Size(max = 4)
  private String country;
  @NotNull
  @NotEmpty
  @Size(max = 4)
  private String language;
}