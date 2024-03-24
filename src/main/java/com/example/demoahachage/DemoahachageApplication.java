package com.example.demoahachage;

//import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoahachageApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(DemoahachageApplication.class, args);

		// On crée trois mots de passe

		String mdp1= "test";
		String mdp2= "bonjour";
		String mdp3= "test";

		
		ValeurApresHashage valeurApresHashage = new ValeurApresHashage();
		String hash1 = valeurApresHashage.hash(mdp1);
		String hash2 = valeurApresHashage.hash(mdp2);
		String hash3 = valeurApresHashage.hash(mdp3);

		System.out.println("Mot de passe : "+ mdp1 + " Hash associé : "+ hash1);
		System.out.println("Mot de passe : "+ mdp2 + " Hash associé : "+ hash2);
		System.out.println("Mot de passe : "+ mdp3 + " Hash associé : "+ hash3);

		System.out.println("Intégration de salage ");
        
		Empreinte valEmpreinte =new Empreinte();

		valEmpreinte.test(mdp2);

     
	}

	
}
