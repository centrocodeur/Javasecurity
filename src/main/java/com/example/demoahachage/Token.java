package com.example.demoahachage;

import java.time.LocalDateTime;



public class Token {

	private String username;
	private LocalDateTime validite;
	private int autorisation;

	//private Empreinte empreinte = new Empreinte();

	// Génération d'une date de fin de validité du jeton d'accès, qui n'est valable que temporairement.

	private void genererValidite(){
		// Validité du jeton d'accès défininie à 24 heures
		this.validite= LocalDateTime.now().plusHours(24);
	}

	// Constructeur qui utilialise les deux attributs de classe
	public Token (String username, int autorisation){
		//Initialisation du nom d'utilisateur
		this.username = username;
		this.autorisation= autorisation;
		// Initialisation de la validité du jeton d'accès via la méthode genererValidite()
		genererValidite();
	}
	public String getUsername(){
		return this.username;
	}

	public int getAutorisation(){
		return this.autorisation;
	}

	// Méthode qui retourne si le jeton d'accès fourni est encore valide 
	public boolean tokenValide(){
		return LocalDateTime.now().isBefore(validite);
	}

}
