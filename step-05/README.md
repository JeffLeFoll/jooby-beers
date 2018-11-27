# Step-05 : Refactoring with Controler

## Servir les fichiers de l'appli Polymer

Maintenant nous allons reprendre le TD polymer3-beers.  
Soit vous reprennez ce que vous avez fait, soit vous partez de la step-08.

Vous construisez votre application avec la commande `polymer build` (si vous n'avez pas la ligne de commande polymer installez la : `npm i -g polymer-cli`).

Normalement vous devez maintenant avoir un répertoir `build\default`, copiez le contenu de ce répertoir `default` dans votre répertoir `public`.

## Modifier l'application pour appeler l'API Java

Partout où vous faisiez un `fetch('/data/beers/beers.json)`, remplacez le par un appel à l'API `fetch('/beers)`.

Dans *beer-list.js*

```Javascript
  async _getData() {
    try {
      const response = await fetch('/beers');
      this.beers = await response.json();
    } catch (err) {
      console.log('fetch failed', err);
    }
  }
```

Dans *beer-detail.js*

```Javascript
  async _onIdChange() {
    const url = `/beer/${this.id}`;

    try {
      const response = await fetch(url);
      this.beer = await response.json();
    } catch (err) {
      console.log('fetch failed', err);
    }
  }
```

## Où sont mes images ??

Dans le fichier json, l'URL indiquée pour les images est `/beers/img/...` ce qui ne correspond pas à notre URL exposée pour les images `/img/...`.  
Alors oui on pourrait modifier directement les fichiers json, mais ça ne serait pas drôle, faisont le plutôt avec Jooby.

Rien de plus simple, il suffit juste de modifier l'url d'exposition des images : 

```Java
    assets("/beers/img/**", Paths.get("../img"));
```
