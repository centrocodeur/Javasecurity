package com.example.demoahachage;

import java.util.HashMap;
import java.util.Map;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RestServices {

	private static final Logger logger = LoggerFactory.getLogger(RestServices.class);
	private static Map<String, Token> tokens =new HashMap<>();


	@GetMapping(value = "/")
	public ResponseEntity <String> pong() {
		 logger.info("Démarrage des services OK......");
		 return new ResponseEntity<String>("Réponse du serveur : "+ HttpStatus.OK.name(), HttpStatus.OK);

	}

	@GetMapping("/connexion")
    @ResponseBody
	public String connexion(@RequestParam(value = "identifiant") String identifiant, @RequestParam(value ="motdepasse") String motdepasse ){
		Empreinte empreinte = new Empreinte();

		// Récupération du sel et de l'empreinte 
		String salt = "jfhdsjkfhezlkjfezlkjf";
         String hashSlow = empreinte.getHashSlow("marien"+salt, 3);

		 // Vérification de la validité du mot de passe
		 if(empreinte.isValidPassword(hashSlow, motdepasse, salt)){
			// Authentification OK, nous créons le jeton d'accès
			String tokenEnclaire = empreinte.getToken(12);
            
			// Hachage simple, pas lent
			String token = empreinte.getHash(tokenEnclaire);

			// Enregistrement du couple user/jeton d'accès haché dans un HashMap
			tokens.put(token, new Token(identifiant, 3));

			System.out.println("vous êtes bien connecté");
			return token;

		 } else{
			// Authentification non réussie
			return "mot de passe erroné";
		 }
	}
	
	@GetMapping("/commande")
	@ResponseBody
	public String commande(@RequestParam(value = "article") String article, @RequestParam(value = "nombre", defaultValue = "1") String nombre,
	                    @RequestParam(value = "token")String token){
					// si la valeur du jeton d'accès existe bien et correspond bien à celle de l'utilisateur en question
					if (tokens.containsKey(token))
					{
			   // si le jeton d'accès n'est plus valide
			     if (!tokens.get(token).tokenValide()) {
				   return "validité du jeton d'accès dépassé, veuillez vous reconnecter!";
			   }
			   // si le jeton d'accès est toujours en cours de validité, nous pouvons exécuter le code métier relatif au passage de commande
			   else {
				   //code lié à la commande
				   return "poursuivez la commande";
			   }
		   }
		   // si le jeton d'accès n'est pas celui associé à l'utilisateur en question
		   else {
			   return "token incorrect, veuillez vous reconnecter!";
		   }
		   
	   }
	   
}
