package org.formation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


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
	@JsonIgnore
	private List<Trace> historique = new ArrayList<>();

	private Instant creationDate;

	public void addTrace(Trace trace) {
			historique.add(trace);
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Livraison livraison = (Livraison) o;
		return id == livraison.id;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}
}
