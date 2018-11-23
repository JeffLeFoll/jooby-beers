# jooby-beers
Java Jooby version of https://github.com/LostInBrittany/express-beers


## Step-00 : init du projet  
Pour générer la structure du projet Jooby nous allons utiliser Maven, un gestionnaire de dépendance et de construction pour Java (une sorte d'équivalent à NPM).  
Deux choix sont possibles :  
* Vous avez déjà Maven d'installé ? `mvn -version` vous retourne les infos d'instalation de Maven.  
=> dans ce cas vous pouvez lancer la commande suivante dans votre répertoire `workspace` :   
`mvn archetype:generate -B -DgroupId=fr.enib.cai -DartifactId=jooby-beers -Dversion=1.0-SNAPSHOT -DarchetypeArtifactId=jooby-archetype -DarchetypeGroupId=org.jooby -DarchetypeVersion=1.5.1`  
* Vous n'avez pas Maven d'installé, vous pouvez utiliser le maven wrapper fournis dans le répertoire `workspace` :  
`./mvnw archetype:generate -B -DgroupId=fr.enib.cai -DartifactId=jooby-beers -Dversion=1.0-SNAPSHOT -DarchetypeArtifactId=jooby-archetype -DarchetypeGroupId=org.jooby -DarchetypeVersion=1.5.1`  