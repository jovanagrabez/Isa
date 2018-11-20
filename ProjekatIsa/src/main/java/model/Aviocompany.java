package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "aviocompany")
public class Aviocompany implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aviocompany_id", nullable = false, updatable = false)
    private Long id;
	
	@Column(name = "name", nullable = false, columnDefinition="VARCHAR(40)")
    private String name;
	
	@Column(name = "description", nullable = false, columnDefinition="VARCHAR(50)")
    private String description;
	
	@Column(name = "adress", nullable = false, columnDefinition="VARCHAR(100)")
    private String adress;

	
	
	
	
	public Aviocompany(Long id, String name, String description, String adress) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.adress = adress;
	}

	public Aviocompany() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}
	
	
	
	
	
	
}
