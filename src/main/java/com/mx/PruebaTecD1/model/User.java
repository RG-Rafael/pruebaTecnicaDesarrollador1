package com.mx.PruebaTecD1.model;

import java.util.List;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class User {

	private UUID id;
	private String email;
    private String name;
    private String phone;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    
    @JsonProperty("tax_id")
    private String taxId;
    
    @JsonProperty("created_at")
    private String createdAt;
    
    private List<Address> addresses;
}
