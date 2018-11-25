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


