[![Generic badge](https://img.shields.io/badge/version-1.1.4-orange.svg)](https://shields.io/)
[![Generic badge](https://img.shields.io/badge/status-active_development-darkred.svg)](https://shields.io/)

# Stack++

Smart stack + some registers = Stack++.

Looks a bit like assembly but it is not!

Edit: turns out I made a virtual machine and Stack++ is something like bytecode for it

_Still in active development!_

## Build and run

### Build jar from source

````console
$ git clone https://github.com/OliverSchlueter/StackPP.git
$ cd StackPP/
$ gradlew shadowJar
````

### Run jar

````console
$ cd build/libs/
$ java -jar StackPP.jar [path to .spp file]
````

### Use as dependency in gradle project
```console
$ gradlew publishToMavenLocal
```

In your project add the following:
````gradle
repositories {
    mavenLocal()
    ...
}

dependencies {
    implementation 'de.oliver:StackPP:version'
    ...
}
````

## Documentation

You can find the documentation for the Stack++ language in [StackPP.md](StackPP.md).

You can find a bunch of examples in the [examples/](examples) directory.

## IntelliJ plugin

I made a IntelliJ plugin, that adds syntax highlighting and auto-completion for Stack++.

You can find it here: [StackPP-Plugin](https://github.com/OliverSchlueter/StackPP-Plugin)

## Roadmap

- [x] Stack operations
- [x] Simple calculations
- [x] Conditions
- [x] Functions
- [x] Memory access
- [x] Read and write files
- [ ] Simple graphics (wip)
    - [x] import [GameEngine](https://github.com/OliverSchlueter/GameEngine)
    - [x] show window
    - [ ] set title
    - [ ] set width and height
    - [ ] set background color
    - [x] draw rectangle with color
  - [ ] draw texture
  - [ ] draw text
- [ ] Internet (not sure yet)
