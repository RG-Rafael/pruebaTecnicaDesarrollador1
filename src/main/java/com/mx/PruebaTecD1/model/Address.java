package com.mx.PruebaTecD1.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Address {

	private Long id;
	private String name;
	private String street;
	
	@JsonProperty("country_code")
	private String countryCode;
	
}
