package org.formation.json;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;

import org.formation.model.Livraison;
import org.formation.model.Status;
import org.formation.model.Trace;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.context.ApplicationContext;

@JsonTest
public class TraceTest {

	@Autowired
	ApplicationContext context;
	
	@Autowired
    private JacksonTester<Livraison> json;

	Livraison aLivraison;
	

	@BeforeEach
	public void setUp() {
		
		aLivraison = new Livraison();
		aLivraison.setId(1);
		aLivraison.setNoCommande("1267905");
		aLivraison.setStatus(Status.EN_COURS);
		Trace t = new Trace();
		t.setDate(Instant.now().minusMillis(10000000l));
		t.setOldStatus(Status.CREE);
		t.setNewStatus(Status.PRODUIT_PRET);
		aLivraison.addTrace(t);
	}
    @Test
    public void testSerialize() throws Exception {
		
        assertThat(this.json.write(aLivraison))
        	.hasJsonPathStringValue("@.noCommande")
        	.hasEmptyJsonPathValue("@.historique")
        	.extractingJsonPathStringValue("@.noCommande").isEqualTo("1267905");
      
    }

    @Test
    public void testDeserialize() throws Exception {
        String content = "{\"id\":\"1\",\"noCommande\":\"dd@dd.fr\"}";
        assertThat(this.json.parse(content))
                .isEqualTo(aLivraison);

        assertThat(this.json.parseObject(content).getNoCommande()).isEqualTo("dd@dd.fr");
    }
}
