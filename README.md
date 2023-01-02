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
$ gradlew jar
````

### Run jar

````console
$ cd build/libs/
$ java -jar StackPP.jar [path to .spp file]
````

## Documentation

You can find the documentation for the Stack++ language in ``StackPP.md``.

You can find a bunch of examples in the ``examples/`` directory.

## Roadmap

- [x] Stack operations
- [x] Simple calculations
- [x] Conditions
- [x] Functions
- [x] Memory access
- [x] Read and write files
- [ ] Simple graphics
- [ ] Internet (not sure yet)