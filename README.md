# Lucky TNT Lib

Welcome to the quick overview of the Forge version Lucky TNT Lib! We have also developed a Fabric and Neoforge version. 
If you are interested in either of those versions, click one of the links below:

- [Fabric](https://github.com/SlimingHD/Fabric-LuckyTNTLib)
- [Neoforge](https://github.com/SlimingHD/Neo-LuckyTNTLib)

### Overview

Lucky TNT Lib offers a variety of features that are specifically designed to help with the development of TNT Mods. It offers help registering and therefore streamlines and quickens the development process by generating most stuff, such as Blocks, Items, Renderers and Entities with the right 'TNT like' behaviors, leaving only the effect of the explosive to be coded by the Mod Developer utilizing the simple to use TNT Effects. Other features include a more performant and scalable explosion and also various simple explosion shapes. Everything in Lucky TNT Lib is documented, making it easy to navigate and understand the code. Due to those features, Lucky TNT Lib is only really useful if you intend to create TNT or have a demand for big explosions.

### Implementation

If you want to include Lucky TNT Lib in your mod, you'll have to declare it as a dependency in your `build.gradle`. You'll first have to find the version of Lucky TNT Lib you want to use. All the versions available for implementation can be found in the Releases Tab or on [Jitpack](https://jitpack.io/#SlimingHD/LuckyTNTLib).

Here are 2 examples of implementation:
1. Minecraft Version 1.20.4. Keep in mind that this version was originally built in Minecraft Version 1.20.3 and still works fine in it, which is why its still named that way
```
repositories {
    maven {
        url "https://jitpack.io"
    }
}

dependencies {
    ...
    implementation fg.deobf("com.github.SlimingHD:LuckyTNTLib:1.20.3-49.0.50.0")
}
```
2. Minecraft Version 1.20.6. Notice that fg.deobf() is missing, as it has been removed (Forge did a terrible job at communicating this change) 
```
repositories {
    maven {
        url "https://jitpack.io"
    }
}

dependencies {
    ...
    implementation "com.github.SlimingHD:LuckyTNTLib:1.20.6-50.0.20.4"
}
```
