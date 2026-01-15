# ⚔️ BetterPvP - Hytale Plugin

Advanced PvP plugin for Hytale with a complete statistics system, killstreaks, and leaderboards.

## ✨ Features

- 📊 **Complete Statistics**: Kills, Deaths, K/D Ratio
- 🔥 **Killstreaks** with automatic announcements
- 🏆 **Top 10 Leaderboards** (Kills, K/D, Killstreaks)
- 💾 **Automatic Saving** every 5 minutes
- 🎨 **Colorful** and professional interface
- 📈 **Overall Server Statistics**

## 🎮 Commands

| Command | Description |

|----------|-------------|

| `/stats` | Displays your complete PvP statistics |

| `/top [kills|kd|ks]` | Displays the leaderboard of top players |

| `/killstreak` | Displays your current killstreak |

| `/resetstats --confirm` | Resets all your stats |

| `/betterpvp` | Plugin information and overall stats |

## 🔥 Killstreak System

- **3 kills**: "Streak!"

- **5 kills**: "Dominates!"

- **10 kills**: "UNSTOPPABLE!"

- **15 kills**: "LEGENDARY!"

- **20 kills**: "GODLIKE!"

## 📦 Installation

### Prerequisites
- Java 25 JDK
- IntelliJ IDEA (or other IDE)
- Hytale Early Access

### Compilation
```bash
git clone https://github.com/ynsmod/BetterPvP-Hytale
cd BetterPvP-Hytale
./gradlew shadowJar
```

The `.jar` file will be generated in `build/libs/BetterPvP-1.0.0.jar`

### Server Installation

1. Copy `BetterPvP-1.0.0.jar` to the Hytale mods folder:
```
%AppData%/Roaming/Hytale/UserData/Mods/
```
2. Restart your Hytale server
3. Check the logs to confirm the installation

## ⚠️ Note Important

**Hytale is in Early Access.** The official API is not yet fully available.

This plugin uses class and method names based on the typical structure of game server APIs. Once the Hytale API is officially released (expected in March 2026), imports will need to be updated.

## 🛠️ Project Structure
```
BetterPvP/
├── src/main/java/fr/betterpvp/
│ ├── BetterPvPPlugin.java # Main Class
│ ├── commands/ # All Commands
│ │ ├── StatsCommand.java
│ │ ├── TopCommand.java
│ │ ├── ResetStatsCommand.java
│ │ ├── KillstreakCommand.java
│ │ └── BetterPvPCommand.java
│ ├── data/ # Data Management
│ │ ├── PlayerStats.java
│ │ └── PlayerStatsManager.java
│ └── listeners/ # Event Listeners
│ ├── CombatListener.java
│ ├── JoinListener.java
│ └── QuitListener.java
├── manifest.json # Plugin Metadata
├── build.gradle # Gradle Configuration
└── settings.gradle
```

## 📝 Contribution

Contributions are welcome! Feel free to:

1. Fork the project
2. Create a branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License. See the `LICENSE` file for more details.

## 🔗 Useful Links

- [Hytale Documentation](https://hytale.com)

- [Hytale Discord](https://discord.gg/hytale)

- [Community Modding Documentation](https://britakee-studios.gitbook.io/hytale-modding-documentation)

## 💬 Support

For any questions or issues:

- Open an [Issue](https://github.com/ynsmod/BetterPvP-Hytale/issues)
- Join the Hytale Discord for help

---

**Created with ❤️ for the Hytale community**
