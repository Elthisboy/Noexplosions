# NoExplosions

## Project Identity
- **Name:** NoExplosions
- **Mod ID:** `noexplosions`
- **Version:** `${version}` (Resolved at build time)

## Technical Summary
The **NoExplosions** mod provides a lightweight, global kill-switch for all explosion events on a Fabric server. By utilizing Mixins (`noexplosions.mixins.json`), the mod intercepts the vanilla explosion processing logic and evaluates a centralized static boolean flag (`NoExplosions.explosionsBlocked`). This allows server operators to dynamically enable or completely disable explosion damage and block destruction (from TNT, Creepers, etc.) in real-time without needing a server restart or editing configuration files.

## Feature Breakdown
- **Global Explosion Nullification:** When active, entirely prevents all forms of explosions from destroying blocks or inflicting entity damage across all dimensions.
- **Real-Time State Management:** Explosion state is stored in memory (`NoExplosions.explosionsBlocked`) and updates instantaneously when commands are executed.
- **Mixin-Driven Interception:** Securely hooks directly into the core Minecraft explosion calculations rather than relying on standard event listeners, ensuring absolute cancellation.

## Command Registry
*Note: All commands require OP Permission Level 2.*

| Command | Description | Permission Level |
| :--- | :--- | :--- |
| `/explosions` | Toggles the current explosion state (Enable <-> Disable). | OP (2) |
| `/explosions enable` | Explicitly allows explosions to function normally. | OP (2) |
| `/explosions disable` | Explicitly blocks all explosions from occurring. | OP (2) |
| `/explosions status` | Displays whether explosions are currently ENABLED or DISABLED. | OP (2) |

## Configuration Schema
*Note: This specific mod does not generate a JSON configuration file in the `config/` folder. All functionality is managed dynamically in-memory via the `/explosions` command suite.*

## Developer Info
- **Author:** el_this_boy
- **Platform:** Fabric 1.21.1
