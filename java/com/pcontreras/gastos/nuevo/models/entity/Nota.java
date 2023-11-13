package com.pcontreras.gastos.nuevo.models.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "notas")
@Data
public class Nota implements Serializable{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	@NotEmpty
	@Size(min=30, max = 250)
	private String description;
	
	@Column(name = "fecha_nota")
	@NotNull(message = "no puede estar vacia")
	private Date fecha;
	
	@Column(nullable = false)
	@Enumerated(value = EnumType.STRING)
	private Estado estado; 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
