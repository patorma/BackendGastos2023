package com.pcontreras.gastos.nuevo.models.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "gastos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NonNull
public class Gasto implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@NotEmpty
	@Size(min=30, max = 250)
	private String description;
	
	@Column(nullable = false)
	private int price;

	
	@Column(name = "fecha_gasto")
	@NotNull(message = "no puede estar vacio")
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	//Faltaria el tipo de gasto
	@NotNull(message = "no puede estar vacio")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipo_gasto_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","hadler"})
	private TipoGasto tipoGasto;
	
	
	private static final long serialVersionUID = 1L;

}
