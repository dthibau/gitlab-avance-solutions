package org.formation.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
public class Livraison {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String noCommande;
	
	@OneToOne
	private Livreur livreur;
	
	private Status status;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Trace> historique = new ArrayList<>();

	private Instant creationDate;

	public void addTrace(Trace trace) {
			historique.add(trace);
	}




}
