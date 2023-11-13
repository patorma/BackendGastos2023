package com.pcontreras.gastos.nuevo.models.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "tipo_gastos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NonNull
public class TipoGasto implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false,unique = true)
	@NotEmpty
	@Size(min = 8, max = 300)
	private String tipo;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
