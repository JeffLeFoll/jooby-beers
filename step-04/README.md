# Step-04 : Refactoring with Controler

## Les contrôleurs, l'autre façon de faire

Ce que l'on vient de voir avec Jooby permet de créer rapidement et simplement une API Rest dans le même style que ExpressJS.  
Pour faire un PoC (Proof of concept) rapide c'est très bien, mais lors que l'application grossit cela devient vite compliqué de s'y retrouver.  

Jooby propose avec les contrôleurs une façon de mieux structurer son API pour plus de lisibilité et de facilitée de modification.  

Nous allons donc refactorer notre application pour les utiliser.  
Un contrôleur est une simple classe Java décorée des annotations `@Path`, `@GET`, `@POST`, ...

Nous alons d'abord créer le contrôleur pour la ressource `/beers` : 

```Java
public class BeersControler {
    private final Logger log = LoggerFactory.getLogger(getClass());
}
```

Pour que ce contrôleur soit appelé sur la ressource `/beers` nous devons ajouter l'annotation `@Path`.

```Java
@Path("/beers")
public class BeersControler {
    private final Logger log = LoggerFactory.getLogger(getClass());
}
```

Pour pouvoir lire notre fichier json nous avons besoin d'un `ObjectMapper`. Ici nous n'allons pas créer une nouvelle instance, nous allons réutiliser celle créer par le module Jackson en injectant notre mapper : 

```Java
@Path("/beers")
public class BeersControler {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final ObjectMapper mapper;

    @Inject
    public BeersControler(ObjectMapper jacksonMapper) {
        mapper = jacksonMapper;
    }
}
```

Maintenant nous allons indiquer que ce que le contrôleur doit faire lorsqu'il recoit une requête de type `/GET`.

```Java
@Path("/beers")
public class BeersControler {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final ObjectMapper mapper;

    @Inject
    public BeersControler(ObjectMapper jacksonMapper) {
        mapper = jacksonMapper;
    }
    
    @GET
    public List<Beer> listsAllBeers(@Header("User-Agent") String ua) throws IOException {
        log.info("Received request for beers from", ua);

        List<Beer> beers = mapper.readValue(Paths.get("./beers/beers.json").toFile(), new TypeReference<List<Beer>>(){});

        return beers;
    }
}
```

Enfin pour que ce contrôleur soit utilisé dans notre classe `App.java`, nous allons remplacer l'instruction `get("/beers", ...)` par `use(BeersControler.class)`.

Maintenant nous pouvons faire la même chose pour la ressource `/beer/:beerId`.
