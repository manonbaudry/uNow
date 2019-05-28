# uNow
[![Build Status](https://travis-ci.org/manonbaudry/uNow.svg?branch=master)](https://travis-ci.org/manonbaudry/uNow)
[![codecov](https://codecov.io/gh/manonbaudry/uNow/branch/master/graph/badge.svg)](https://codecov.io/gh/manonbaudry/uNow)

Erasmus project of my 4th semester of DUT in computer science

# API description 

## User Controller

Resource    |Path  |Method |Name | Content|Query|Code|Contenu|JUnit                                   
------------|------|-------|-----|--------|-----|----|-------|--------
/user       |/     |GET    |getAll|       |     |200 |I1*    |testGetAlluser             
/user       |/     |POST   |I2     |     |204 |I1     |testCreateIngredient              
/user       |/     |POST   |I2     |     |406 |M1     |testCreateIngredient_406    
/user       |/     |POST   |I2     |     |409 |M2     |testCreateIngredient_409      
/user       |/{id} |GET    |       |     |200 |I1     |testGetOneIngredient            
/user       |/{id} |GET    |       |     |404 |       |testGetOneIngredient_404   
/user       |/{id} |PUT    |I1     |     |200 |I1     |testUpdateIngredient              
/user       |/{id} |PUT    |I1     |     |404 |       |testUpdateIngredient_404      
/user       |/{id} |PUT    |I1     |     |409 |M2     |testUpdateIngredient_409       
/user       |/{id} |DELETE |       |     |204 |       |testDeleteOneIngredient       
/user       |/{id} |DELETE |       |     |404 |       |testDeleteOneIngredient_404

### messages

Msg | Texte
------|-----------------------------------------
 M1 | Name not specified
 M2 | Duplicated name

### application.json

JSON |  Syntaxe         | Description
-----|------------------|------------------------
I1\* |[ { id, nom }* ]  | Liste complète ( id + nom )
I1   |{ id, nom }       | 1 seul ingrédient ( id + nom )
I2   |{ nom }           | 1 seul ingredient (nom seulement )

## Ressource Pizzas

Ressource    |Chemin			|Méthode|Contenu|Query|Code|Contenu|JUnit                      		|http              
-----------|:----------------:|:-----:|:-----:|:---:|:--:|:-----:|:------------------------------:|:----------------:
/pizzas		 |/     			|GET    |       |     |200 |P1*    |testGetAllPizzas   		 	  	|get.http 
/pizzas 	 |/     			|POST   |P3     |     |204 |P1     |testCreatePizzas	       		|post.http         
/pizzas 	 |/     			|POST   |P2     |     |406 |       |testCreatePizzas_406	   		|post_406.http     
/pizzas 	 |/     			|POST   |I1     |     |409 |M2     |testCreatePizzas_409	   		|post_409.http     
/pizzas 	 |/{id} 			|GET    |P2     |     |200 |P1     |testGetOnePizzas	       		|get_id.http       
/pizzas		 |/{id} 			|GET    |       |     |404 |M1     |testGetOnePizzas_404	   		|get_id_404.http        
/pizzas		 |/{id} 			|DELETE |       |     |204 |       |testDeleteOnePizzas		   		|delete_id.http    
/pizzas		 |/{id} 			|DELETE |       |     |404 |M1     |testDeleteOnePizzas_404	   		|delete_id_404.http
/pizzas		 |/{id}/ingredients |GET    |       |     |404 |M1     |testGetAllPizzaIngredients_404	|get_id_ingredients_404.http
/pizzas		 |/{id}/ingredients |GET    |       |     |200 |I1     |testGetAllPizzaIngredients_404	|get_id_ingredients.http

### messages

Msg | Texte
----|-----------------------------------------
 M1 | Pizza not found
 M2 | Duplicated name

### application.json

JSON |  Syntaxe         									  | Description
-----|--------------------------------------------------------|------------------------
P1\* | [ {base, id, nom, prix_grande, prix_petite}*]		  | Liste complète pizzas ( base + id + nom + prix_grande + prix_petite)
P1   | {base, id, nom, prix_grande, prix_petite, [{id, nom}*]}| 1 seule pizza  ( base + id + nom + prix_grande + prix_petite + liste ingrédients)
I1	 | [ { id, nom }* ]										  | Liste des ingrédients ( id + nom )
P2	 | [ {base, id, prix_grande,[{id}*]]					  | 1 seule pizza (base, id, prix_grande, liste  id ingrédients)
P3	 | {base, nom, prix_grande, prix_petite, [{id}*]}		  | 1 seule pizza (base, nom, prix_grande, prix_petite, liste  id ingrédients)
	
##Ressource Commande 

Ressource    |Chemin			|Méthode|Contenu|Query|Code|Contenu|JUnit                      		|http              
:-----------:|:----------------:|:-----:|:-----:|:---:|:--:|:-----:|:--------------------------:|:----------------:
/commandes	 |/     			|GET    |       |     |200 |P1*    |testGetAllcommandes   		|get.http 
/commandes 	 |/     			|POST   |P2     |     |204 |P1     |testCreatecommandes	       	|post.http         
/commandes 	 |/     			|POST   |P2     |     |406 |       |testCreatecommandes_406	   	|post_406.http     
/commandes 	 |/     			|POST   |I1     |     |409 |M2     |testCreatecommandes_409	   	|post_409.http     
/commandes 	 |/{id} 			|GET    |P2     |     |200 |P1     |testGetOnecommandes	       	|get_id.http       
/commandes	 |/{id} 			|GET    |       |     |404 |M1     |testGetOnecommandes_404	   	|get_id_404.http        
/commandes	 |/{id} 			|DELETE |       |     |204 |       |testDeleteOnecommandes		|delete_id.http    
/commandes	 |/{id} 			|DELETE |       |     |404 |M1     |testDeleteOnecommandes_404	|delete_id_404.http
/commandes	 |/{id}/pizzas 		|GET    |       |     |404 |M1     |testGetAllPizza_404			|get_id_pizzas_404.http
/commandes	 |/{id}/pizzas		|GET    |       |     |200 |I1     |TestGetAllPizza_404			|get_id_pizzas.http  

### messages

Msg | Texte
----|-----------------------------------------
 M1 | commande not found
 M2 | Duplicated name


### application .json
JSON |  Syntaxe         									  | Description
-----|--------------------------------------------------------|------------------------
P1\* | [{id, nom, prenom, [{id, nom}*]}*]					  | Liste complète commandes (id + nom + prenom + liste pizzas (id + nom))
P1   | {id, nom, prenom, [{id, nom}*]}						  | 1 seule commande (id + nom + prenom + liste pizzas (id + nom))
I1	 | [ { id, nom }* ]										  | Liste des pizzas ( id + nom )
P2	 | [{id, nom, prenom, [{id, nom}*]}*]					  | 1 seule commande (id + nom + prenom + liste pizzas (id + nom))
