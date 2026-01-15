# ⚔️ BetterPvP - Plugin Hytale

Plugin PvP avancé pour Hytale avec système de statistiques complet, killstreaks et classements.

## ✨ Fonctionnalités

- 📊 **Statistiques complètes** : Kills, Deaths, K/D Ratio
- 🔥 **Killstreaks** avec annonces automatiques
- 🏆 **Classements TOP 10** (Kills, K/D, Killstreaks)
- 💾 **Sauvegarde automatique** toutes les 5 minutes
- 🎨 **Interface colorée** et professionnelle
- 📈 **Statistiques globales** du serveur

## 🎮 Commandes

| Commande | Description |
|----------|-------------|
| `/stats` | Affiche vos statistiques PvP complètes |
| `/top [kills\|kd\|ks]` | Affiche le classement des meilleurs joueurs |
| `/killstreak` | Affiche votre série de kills en cours |
| `/resetstats --confirm` | Réinitialise toutes vos statistiques |
| `/betterpvp` | Informations sur le plugin et stats globales |

## 🔥 Système de Killstreaks

- **3 kills** : "En série !"
- **5 kills** : "Domine !" 
- **10 kills** : "UNSTOPPABLE !"
- **15 kills** : "LÉGENDAIRE !"
- **20 kills** : "GODLIKE !"

## 📦 Installation

### Prérequis
- Java 25 JDK
- IntelliJ IDEA (ou autre IDE)
- Hytale Early Access

### Compilation
```bash
git clone https://github.com/VOTRE-USERNAME/BetterPvP-Hytale.git
cd BetterPvP-Hytale
./gradlew shadowJar
```

Le fichier `.jar` sera généré dans `build/libs/BetterPvP-1.0.0.jar`

### Installation sur le serveur

1. Copiez `BetterPvP-1.0.0.jar` dans le dossier mods de Hytale :
```
   %AppData%/Roaming/Hytale/UserData/Mods/
```
2. Redémarrez votre serveur Hytale
3. Vérifiez les logs pour confirmer le chargement

## ⚠️ Note Importante

**Hytale est en Early Access.** L'API officielle n'est pas encore complètement disponible. 

Ce plugin utilise des noms de classes et méthodes basés sur la structure typique des APIs de serveur de jeux. Une fois l'API Hytale officiellement publiée (prévue mars 2026), les imports devront être mis à jour.

## 🛠️ Structure du Projet
```
BetterPvP/
├── src/main/java/fr/betterpvp/
│   ├── BetterPvPPlugin.java          # Classe principale
│   ├── commands/                      # Toutes les commandes
│   │   ├── StatsCommand.java
│   │   ├── TopCommand.java
│   │   ├── ResetStatsCommand.java
│   │   ├── KillstreakCommand.java
│   │   └── BetterPvPCommand.java
│   ├── data/                          # Gestion des données
│   │   ├── PlayerStats.java
│   │   └── PlayerStatsManager.java
│   └── listeners/                     # Écouteurs d'événements
│       ├── CombatListener.java
│       ├── JoinListener.java
│       └── QuitListener.java
├── manifest.json                      # Métadonnées du plugin
├── build.gradle                       # Configuration Gradle
└── settings.gradle
```

## 📝 Contribution

Les contributions sont les bienvenues ! N'hésitez pas à :

1. Fork le projet
2. Créer une branche (`git checkout -b feature/AmazingFeature`)
3. Commit vos changements (`git commit -m 'Add AmazingFeature'`)
4. Push vers la branche (`git push origin feature/AmazingFeature`)
5. Ouvrir une Pull Request

## 📄 Licence

Ce projet est sous licence MIT. Voir le fichier `LICENSE` pour plus de détails.

## 🔗 Liens Utiles

- [Documentation Hytale](https://hytale.com)
- [Discord Hytale](https://discord.gg/hytale)
- [Documentation Modding Communautaire](https://britakee-studios.gitbook.io/hytale-modding-documentation)

## 💬 Support

Pour toute question ou problème :
- Ouvrez une [Issue](https://github.com/VOTRE-USERNAME/BetterPvP-Hytale/issues)
- Rejoignez le Discord Hytale pour obtenir de l'aide

---

**Créé avec ❤️ pour la communauté Hytale**
