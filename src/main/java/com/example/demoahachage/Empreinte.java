package com.example.demoahachage;

import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;

public class Empreinte {

	public static final Random r= new Random();
	public static final String alphabet = "0123456789abcdefghijklmnopqrstuvwxyz";


	public String getHash(String toHash){
		return DigestUtils.sha256Hex(toHash);
	}


    public String getHashSalt(String toHash, String salt){
	return DigestUtils.sha256Hex(toHash+salt);
    }



	public String getSalt(int taille){
		String s =""; // On initialise une chaine vide.
		for (int i=0; i < taille; i++){
			s += alphabet.charAt(r.nextInt(alphabet.length())); // On pioche un caractère au hasard dans la chaine "alphabet", et nous le concaténons à s.
		}

		return s; //On renvoie s
	}

	public String getToken(int taille){
		String s= ""; // On initialise une chaine vide

		for(int i= 0; i <taille; i++){
			s += alphabet.charAt(r.nextInt(alphabet.length()));

		}
		return s;
	}

	public boolean isValidPassword(String hash, String password, String salt){
		//return (getHash(password + salt).equals(hash));
		return (getHashSlow(password+ salt, 3)).equals(hash);
	}

	public String getHashSlow(String toHash, int number){
		for(int i=0; i< number; i++){
			toHash= getHash(toHash);
		}
		return toHash;
	}

	public void test (String s){
		
		System.out.println("mot de passe : " + s);
		System.out.println("hash du mot de passe généré : "+ getHash(s));

		String salt= getSalt(8);
		

		System.out.println("sel généré : "+ salt);

		System.out.println("mot de passe haché et salé : "+ getHashSalt(s, salt));

		System.out.println("mot de passe haché et salé : "+ getHashSlow(s, 5));
	}



}
