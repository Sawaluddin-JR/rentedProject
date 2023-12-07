package com.rented.demouasfadillah.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String username;
	private String password;
	private String email;


	// @Temporal(TemporalType.TIMESTAMP)
    // // @Column(name = "rentalDate", nullable = false)
    // @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	// private Date tanggalLogin;
}
