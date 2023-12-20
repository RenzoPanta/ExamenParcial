package com.panta.denuncia.entity;

import java.util.Date;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name="denuncias")
public class Denuncia {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable=false,length=8)
	private String dni;
	
	@Column(nullable=false)
	private Date fecha;
	
	@Column(nullable=false,length=3)
	private String titulo;
	
	@Column(nullable=false, length=200)
	private String direccion;
	
	@Column(nullable=true,length=255)
	private String descripcion;
	
	@Column(name="activo",nullable=false)
	private Boolean activo;
}
