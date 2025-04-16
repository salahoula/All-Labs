Annotations utilisées :

@SpringBootApplication : C'est une annotation combinant 
@Configuration, @EnableAutoConfiguration et @ComponentScan. 
Elle marque la classe principale pour la configuration 
automatique de Spring Boot.

@RestController : Indique que la classe est un contrôleur
qui renvoie des objets de domaine (au lieu de vues).

@GetMapping : Marque une méthode pour gérer les requêtes HTTP GET.

Comment Spring Boot convertit-il les objets Java en JSON ?
-Spring Boot utilise la bibliothèque Jackson pour convertir 
automatiquement les objets Java en JSON lorsqu'ils sont 
retournés par des méthodes annotées avec @RestController.

Importance de @SpringBootApplication : Cette annotation est 
cruciale car elle permet à Spring Boot de configurer 
automatiquement l'application avec les paramètres nécessaires 
pour le démarrage de l'application.

Modifier le numéro de port : Le numéro de port de l'application 
peut être modifié en définissant server.port dans le 
fichier application.properties.

Avantages de Spring Boot par rapport aux applications Web Java traditionnelles :

Configuration automatique simplifiée.

Pas besoin de fichiers de configuration complexes comme web.xml.

Démarrage rapide grâce à l'intégration avec des serveurs embarqués comme Tomcat.

Moins de code et de configuration manuelle grâce aux annotations.