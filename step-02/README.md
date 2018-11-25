# Step-02 : Basic routing

## Récupérer le répertoir des images

Nous avons besoin de récupérer le répertoir `img` pour le mettre dans le répertoire `workspace`.
Une fois cela fait nous pouvons continuer.

## Définir les routes

Nous allons définir les routes dont nous avons besoin pour notre API.  
Comme nous voulons développer un backend pour [Polymer Beers](https://github.com/LostInBrittany/polymer-beers), nous devons définir les routes :
* `GET /beers ` : la liste des bières avec nom, description, degré d'alcool et l'URL de l'image pour chaque bière
* `GET /beer/<beerId> ` : pour récupérer le détail d'une bière

De plus nous voulons également servir comme fichiers statiques le contenu du répertoire `public` ainsi que le contenu du répertoire `img` sur le path `img`.

Pour vous aider la documentation de jooby est bien faite, avec beaucoup d'exemples : [jooby.org/doc](https://jooby.org/doc/)

Commençons par définir les routes : 
```Java
    get("/beers", (req, res) -> {
      log.info("Received request for beers from", req.ip());
      res.send("Hello beers");
    });

    get("/beer/:beerId", (req, res) -> {
      log.info("Received request for "+req.param("beerId").value() + " from", req.ip());
      res.send("Hello beer " + req.param("beerId").value());
    });
```

Pour pouvoir utiliser le Logger, il faut déclarer avant l'IIB : 
```Java
  private final Logger log = LoggerFactory.getLogger(getClass());
```
Et ne pas oublier les imports (si votre IDE ne vous les mets pas automatiquement)
```Java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
```

Maintenant nous allons servir les documents statiques
```Java
    assets("/img/**", Paths.get("../img"));
    assets("/**");
```