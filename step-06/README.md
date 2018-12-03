# Step-06 : Mongo Beers

Let's say you already have your beers in a MongoDB database. Now we are going to replace our local JSON files with calls to MongoDB.

> In order to do this step you need to have your beer data in a MongoDB database.
> How to do it is outside the scope of this tutorial, but if you only want to do a quicktest, you could:
>
> - Install MongoDB (see http://mongodb.com/)
> - Start the MongoDB daemon (usually with the command `mongod`)
> - Use `mongoimport` command line tool to import the detailed JSON datafiles
>
>    ```
>      mongoimport --db test --collection beers beers/AffligemBlond.json
>      mongoimport --db test --collection beers beers/AffligemDubbel.json
>      ...
>   ```  
## Ajouter les dépendances à Mongo

Nous allons avoir besoin d'ajouter la dépendance vers le driver Mongo DB, pour ce faire, il suffit d'ajouter le [module mongodb de Jooby](https://jooby.org/doc/mongodb/) :  
```xml
    <dependency>
      <groupId>org.jooby</groupId>
      <artifactId>jooby-mongodb</artifactId>
      <version>1.5.1</version>
    </dependency>
```
Ensuite, vous devez modifier le fichier de configiguration qui se situe dans le répertoir `/conf/application.conf`.  
C'est dans ce fichier que nous allons mettre les paramètre de connection à notre db :  
```properties
db = "mongodb://localhost/test"
```

Enfin nous n'avons plus qu'à déclarer dans notre code que l'on utilise Jooby.  
Dans `App.java` :  
```Java
use(new Mongodb());
```

Et voilà, nous sommes pret  à utiliser mongo dans notre code.  
Le soucis est que le driver mongodb Java n'est pas très facile à utiliser, alors nous allons utiliser [Jongo](http://jongo.org/) qui est une petite librairie Java qui permet d'écrire nos requetes presque comme si on le faisait dans le shell mongo.  
En plus il y a un [module Jongo pour Jooby :)](https://jooby.org/doc/jongo/).  
On ajoute donc la dépendance :  
```xml
<dependency>
  <groupId>org.jooby</groupId>
  <artifactId>jooby-jongo</artifactId>
  <version>1.5.1</version>
</dependency>
```
Et on déclare son utilisation :  
```Java
use(new Jongoby());
```

## Demander une liste de bierres
Nous allons maintenant ecrire une requete sur la base mongo pour récuperer la liste de bières :  
```Java
    get("/beers", req -> {
      log.info("Received request for beers from", req.ip());

      Jongo jongo = require(Jongo.class);
      MongoCursor<Beer> beers = jongo.getCollection("beers").find().as(Beer.class);

      return beers;
    });
```

## Et pour le détail d'une bière ?
Nous allons construire une requète pour faire une recherche sur l'ID :  
```Java
    get("/beer/:beerId", req -> {
      log.info("Received request for "+req.param("beerId").value() + " from", req.ip());

      String beerId = req.param("beerId").value();

      Jongo jongo = require(Jongo.class);
      Beer beer = jongo.getCollection("beers").findOne("{id: #}", beerId).as(Beer.class);

      return beer;
    });
```

Si vous avez utilisé la méthode avec les contrôleurs, vous pouvez voir dans les fichiers `BeerControler.java` and `BeersControler.java` comment utiliser Jongo.

