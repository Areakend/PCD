# CodingWeek 2017 - Sample project

This project provides a sample application that demonstrates the use of [Gradle](https://gradle.org/) for building a JavaFX application.

It provides basic dependencies such as [Log4j2](https://logging.apache.org/log4j/2.x/) for logging messages, [JUnit](http://junit.org/junit4/) for unit testing and [GitLab API for Java](https://github.com/gmessner/gitlab4j-api) for conversing with a GitLab server.

All these dependencies are defined in the `build.gradle` file. You can freely edit this file in order to add your own dependencies or replace GitLab API for Java library with [Java GitLab API](https://github.com/timols/java-gitlab-api) (by uncommenting `org.gitlab:java-gitlab-api:1.2.8`)

## Basic Gradle commands

Gradle will help you to build and distribute your project. It will allow you to easily manage your dependencies.

Gradle supports the following basic tasks on this project:
- `gradle test` to run the unit tests
- `gradle run` to run the project as a Java application
- `gradle assembleDist` to bundle the project as distribution in a `.zip` and a `.tar` files.
- `gradle clean` to clean up the project (deletes the `build` and `dist` directories)
- `gradle tasks` to list all the available tasks

**Note**: if `gradle` is not installed on your system, you can use the provided wrapper by using the `gradlew` command instead of the `gradle` one.

## Importing this project configuration in an IDE

Gradle is supported in mainstream IDEs such as IntelliJ, Eclipse or NetBeans. Therefore, you can easily import this project in your favorite IDE without having to reconfigure your build process.

### IntelliJ

IntelliJ allows to import gradle project. You just have to import the project and select the `build.gradle` file during the importation.

### Eclipse

Eclipse allows to import a gradle project.

### NetBeans

You first have to install the gradle plugin in NetBeans before being able to import a gradle project.


POUR LA RELEASE :
- TELECHARGER LE .JAR
- EXECUTER LE .JAR EN DOUBLE CLIQUANT DESSUS

#RELEASE
## RELEASE_DAY_1

Dans la version 1.0, notre projet se limite à un écran de connexion. Sur cet écran, on peut entrer un token. Ce token va permettre d'accéder au compte gitlab associé à celui-ci.

## RELEASE_DAY_2

Dans la version 2.0, notre projet permet de changer d'écran après avoir rentré un token valide. 
On accède à une vue avec 2 boutons. Le premier bouton "Créer un devoir" change de vue et on arrive sur une vue qui permet de créer un devoir.
Dans ce devoir, on peut choisir la matière avec une ChoiceBox, on rentre le titre, une description, une date de début (resp. de rendu) et l'heure de début(resp. de rendu).
On peut aussi choisir un fichier à importer. Il y a un filtre .csv pour que l'utilisateur ne puisse qu'importer des fichiers CSV. Ces fichiers serviront à créer les listes d'élèves.

Avec le deuxième bouton "Accéder au dépôt", on accède à une nouvelle vue qui permet d'accéder à un dépôt pour un élève.
On peut voir le nom de la matière, le titre du projet ainsi que la date et l'heure du rendu.
L'élève peut choisir plusieurs fichiers qu'il veut envoyer sur le dépôt, ceux-ci s'affichent dans le menu au-dessus.
L'élève peut également mettre un message de commit.

Une base de données est en cours d'achèvement mais n'est pas encore au point.

###A implémenter
- Base de donnée complete
- Lier les boutons avec les fonctionnalités de l'API gitlab.

## RELEASE_DAY_3
Dans la version 3.0, les menus permettent une navigation plus intuitive, avec la possibilié de cliquer sur le bouton "Menu" ramenant au menu prncipal.
Des alertes sont présente lors de la création d'un projet permettant à l'utilisateur de ne pas se tormper (mauvaise dâte, horraires dans un mauvais format, ...).
La base de donnée a été finalisée.

## RELEASE_DAY_4
Dans la version 4.0, on a ajouté toute l'interface graphique qui permet de pull un fichier pour l'utilisateur mais n'est pas encore relié à l'API.
Ajout d'une API permettant de push des fichiers dans les dépots des devoirs.
Liaison entre l'interface graphique et l'API ainsi qu'entre l'API et la database.
On peut afficher les données concernant les groupes d'un devoir à partir de la database.

## RELEASE_FINAL
Dans la version finale, l'interface s'ouvre sur un écran de connexion. Cet écran de connexion propose de rentrer son token (on utilise le token de démonstration), si le token a déjà été utilisé sur l'ordinateur il aura été stocké et on pourra donc cliquer sur "reconnexion" plutôt que de le rentrer de nouveau.
Sur l'écran d'accueil de l'application se trouve le nom du compte connecté ainsi que 5 boutons. Le bouton de déconnexion déconnecte l'utilisateur et supprime le token enregistré de l'ordinateur.
Le bouton "créer un devoir" ouvre une nouvelle fenêtre. Sur cette fenêtre se trouve différentes option pour créer un devoir. Ces données sont sauvegardées dans la base de données.
Le bouton "Push un fichier" permet de déposer un dévoir. En choisissant un projet pour lequel on souhaite déposer des fichiers on peut séléctionner des fichiers stockés sur l'ordinateur, choisir un nom de commit et envoyer vers gitlab le rendu en cliquant sur "push". Celui-ci sera effectué au bon endroit directement.
Le bouton "Pull un fichier" permet de récuperer un fichier en ligne et de le télécharger dans le dossier de notre choix sur l'ordinateur.
Le bouton "Supprimer un devoir" permet de choisir de supprimer d'un projet particulier.
Le menu "groupes", apparait après le menu "créer un devoir", il permet d'afficher la liste des membres de chaque groupe. On peut aussi ajouter des élèves grâce au bouton ajouter.
Nous possédons une base de données, celle-ci est en partie accessible à travers l'interface graphique. Nous avons 4 tables : Assignments, Teachers, Students et Groups. Toutes les quatre sont fonctionnelles. Nous avons cependant un problème lors de l'importation des devoirs depuis gitLab vers l'API, de plus l'interface graphique pour la table teachers n'a pas été développée.


Lien vidéo : https://drive.google.com/drive/folders/1OLwQx2UXi9HQhc-bNz_ET9koaNfqolYi?usp=sharing