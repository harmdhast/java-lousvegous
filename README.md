# LousVegous

LousVegous est une application de jeu Java qui simule une expérience de jeu de casino. Elle comprend un système de
connexion, la gestion des joueurs et un jeu de correspondance de symboles avec animations et effets sonores.

## Prérequis

Avant de commencer, assurez-vous de répondre aux exigences suivantes :

- Java JDK 11 ou supérieur installé
- Maven installé
- IntelliJ IDEA ou tout autre IDE Java pour exécuter et modifier le projet

## Pour commencer

Pour obtenir une copie locale opérationnelle, suivez ces étapes simples :

1. **Clonez le dépôt**

```bash
git clone https://github.com/votre-nom-utilisateur/LousVegous.git
```

2. **Naviguez vers le répertoire du projet**

```bash
cd LousVegous
```

3. **Construisez le projet avec Maven**

```bash
mvn clean install
```

Cette commande construira le projet et générera un fichier JAR exécutable dans le répertoire `target`.

4. **Exécutez l'application**

Vous pouvez exécuter l'application en utilisant la commande suivante :

```bash
java -jar target/LousVegous-1.0-SNAPSHOT.jar
```

## Utilisation

### Connexion ou Inscription

Lorsque vous lancez LousVegous pour la première fois, vous serez dirigé vers l'écran de connexion. Ici, vous pouvez soit
vous connecter avec un compte existant, soit créer un nouveau compte.

- Pour vous inscrire, entrez un nouveau nom d'utilisateur et mot de passe, puis cliquez sur le bouton "S'inscrire".
- Pour vous connecter, entrez votre nom d'utilisateur et mot de passe, puis cliquez sur le bouton "Connexion".

### Jouer au jeu

Après vous être connecté, vous serez dirigé vers l'écran principal du jeu.

- Cliquez sur le bouton "Spin" pour commencer le jeu. Les symboles vont tourner et finalement s'arrêter, montrant si
  vous avez gagné ou perdu.
- Votre solde est mis à jour selon le résultat du jeu.

### Sauvegarde et Chargement des Données du Joueur

Votre progression dans le jeu est automatiquement sauvegardée lorsque vous quittez le jeu. Lorsque vous vous reconnectez
avec le même nom d'utilisateur, votre progression (y compris le solde) est restaurée.