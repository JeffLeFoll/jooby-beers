# Step-03 : 

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
