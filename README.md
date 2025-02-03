# Jetpacks Mod

Allows you to convert your chestplate into a jetpack!
## Features

- Highly customizable config
- Jetpack chestplate (Jetplate?) for every material
- Durability based fuel system
- Jetpack has the same protection stats as a chestplate
## Config

Edit the config at `server/config/jetpacks.json`

```json
{
    "iron_jetpack": {
        "hover": false,
        "speed": 0.08,
        "durability": 80
    },
    "gold_jetpack": {
        "hover": false,
        "speed": 0.10,
        "durability": 50
    },
    "diamond_jetpack": {
        "hover": true,
        "speed": 0.12,
        "durability": 100
    },
    "netherite_jetpack": {
        "hover": true,
        "speed": 0.14,
        "durability": 200
    }
}
```

## Acknowledgements

 - [Repository that I based my mod structure around](https://github.com/Tutorials-By-Kaupenjoe/Fabric-Tutorial-1.21.X)
