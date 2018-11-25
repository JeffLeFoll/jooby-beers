# Step-03 : Data from JSON files

## Configurer Jooby

En tant que framework modulaire, l'archetype généré est un projet Jooby basic, par défaut il ne gère pas le JSON, nous devons donc récupérer le module spécifique et le mettre dans le `pom.xml` : 
```xml
<dependency>
  <groupId>org.jooby</groupId>
  <artifactId>jooby-jackson</artifactId>
  <version>1.5.1</version>
</dependency>
```
Dans notre classe java, nous devons déclarer son utilisation dans l'IIB :
```Java
use(new Jackson());
```

## Récupérer les fichiers des bières

Nous avons besoin de récupérer le répertoir `beers` pour le mettre dans le répertoire `workspace`.
Une fois cela fait nous pouvons continuer.

## Qu'est-ce qu'une bière ?

Java étant un language statique typé, nous alons commencé par définir ce qu'est une bière.  
Pour ce faire nous allons créer une nouvelle classe appelée `Beer.java`
```Java
public class Beer {
    private String alcohol;
    private String availability;
    private String brewery;
    private String description;
    private String id;
    private String img;
    private String label;
    private String name;
    private String serving;
    private String style;

    public String getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(String alcohol) {
        this.alcohol = alcohol;
    }

    [...]
}
```

## Lire la liste des bières depuis un fichier JSON

Pour lire un fichier JSON et le transformer en object Java nous allons utiliser Jackson et plus particulièrement le `ObjectMapper` : 
```Java
ObjectMapper mapper = new ObjectMapper();
// Si on veut retourner une liste : 
List<Type> maListe =  mapper.readValue(Paths.get("./data.json").toFile(), new TypeReference<List<Type>>(){});
// Pour récuper une instance d'un type
Type monObject mapper.readValue(Paths.get("./data.json").toFile(), Type.class);
```

C'est ce que nous allons utiliser pour retourner des données lors de l'appel à `/beers`.

```Java

    use(new Jackson());

    get("/beers", req -> {
      log.info("Received request for beers from", req.ip());

      ObjectMapper mapper = new ObjectMapper();
      List<Beer> beers = mapper.readValue(Paths.get("./beers/beers.json").toFile(), new TypeReference<List<Beer>>(){});

      return beers;
    });

```

## Récupérer le détail d'une bière

Maintenant nous aimerions retourner le détail d'une bière correspondant à la route `/beer/:beerId`. Pour ce faire nous allons lire les données du fichier json correspondant.

```Java
    get("/beer/:beerId", req -> {
      log.info("Received request for "+req.param("beerId").value() + " from", req.ip());

      ObjectMapper mapper = new ObjectMapper();
      Beer beer = mapper.readValue(Paths.get("./beers/" + req.param("beerId").value() + ".json").toFile(), Beer.class);

      return beer;
    });
```