# Step-04 : Refactoring with contoler

## Les contrôleurs, l'autre façon de faire

Ce que l'on vient de voir avec Jooby permet de créer rapidement et simplement une API Rest dans le ùême style que ExpressJS.  
Pour faire un Poc rapide c'est très bien, mais lors que l'application grossit cela devient vite compliqué de s'y retrouver.  

Jooby propose avec les contrôleurs une façon de mieux structurer son API pour plus 
de lisibilité et de facilitée de modification.  

Nous allons donc refactorer notre application pour les utiliser.  
Un contrôleur est une simple classe Java décorée des annotations `@Path`, `@GET`, `@POST`, ...

Nous alons d'abord crééer le contrôleur pour la ressource `/beers` : 
```Java

```