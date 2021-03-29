package com.prueba.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data

@Entity
@Table(name = "tcus_clientes")
public class Cliente implements Serializable {


	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer serial;

	@Column(name = "cus_nmcliente")
	private Integer cliente;

	@Column(name = "cus_dsnombres", length = 120)
	private String nombres;

	@Column(name = "cus_dsapellidos", length = 120)
	private String apellidos;

	@Column(name = "cus_dsdireccion", length = 120)
	private String direccion;

	@Column(name = "cus_dscorreo", length = 120)
	private String correo;

	@Column(name = "cus_cdtelefono", length = 20)
	private String telefono;

	@Column(name = "cus_cdtelefonoalter", length = 20)
	private String telefonoAlter;

	@Column(name = "cus_cdcelular", length = 20)
	private String celular;

	@Column(name = "cus_nmcargo")
	private Integer cargo;

	@Column(name = "cus_dscargo", length = 120)
	private String dsCargo;

	@Column(name = "cus_nmciudad")
	private Integer ciudad;

	@Column(name = "cus_dsciudad", length = 60)
	private String dsCiudad;

	@Column(name = "cus_fenacimiento")
	private Date feNacimiento;

	@Column(name = "cus_genero")
	private Character genero;

	@Column(name = "cus_nmcomunidad")
	private Integer comunidad;

	@Column(name = "cus_dscomunidad", length = 120)
	private String dsComunidad;

	@Column(name = "cus_dsempresalabora", length = 200)
	private String empresaLaboral;

	@Column(name = "cus_nmdivision")
	private Integer division;

	@Column(name = "cus_dsdivision", length = 120)
	private String dsDivision;

	@Column(name = "cus_nmpais")
	private Integer pais;

	@Column(name = "cus_dspais", length = 120)
	private String dsPais;

	@Column(name = "cus_hobbies")
	private String hobbies;

	@Column(name = "cus_febaja")
	@Temporal(TemporalType.TIMESTAMP)
	private Date feBaja;

	@Column(name = "cus_feregistro")
	@Temporal(TemporalType.TIMESTAMP)
	private Date feRegistro;

	@PrePersist
	public void prePersist() {
		feRegistro = new Date();
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
