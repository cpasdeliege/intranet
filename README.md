# intranet

Intranet du CPAS de Liège

## Installation

On récupère le repository depuis Github, on se déplace dans la racine du projet, puis on installe les dépendances et on compile le fichier .war (placé dans target/cpas.war). 

Voici les commandes :

```
git clone https://github.com/cpasdeliege/intranet.git
cd intranet
mvn install
```

### Autres

Pour clean le dossier target :
```
mvn clean
```

Pour clean le dossier target, réinstaller les dépendances et compiler :
```
mvn clean install
```

Pour compiler uniquement le fichier target/intranet.war :
```
mvn package
```

Pour compiler et déployer sur le Tomcat de CPL-APP-30 :
```
mvn tomcat7:redeploy 
```

Pour déployer sur le Tomcat de PROD : on déploie manuellement intranet.war sur le /manager de Tomcat.