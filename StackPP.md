# Stack++

## Operations

### move

Syntax: ``move <int32> <reg>``

1. writes the int32 into the register

### push

Syntax: ``push <int32>``

1. pushes the int32 onto the stack

### pop

Syntax: ``pop <reg>``

1. pops the int32 from the stack
2. writes the int32 into the register

### or

Syntax: ``or <reg> <int32>``

1. read value from reg
2. does 'bitwise or' with the two values
3. put the result back into the reg

### xor

Syntax: ``xor <reg> <int32>``

1. read value from reg
2. does 'bitwise xor' with the two values
3. put the result back into the reg

### and

Syntax: ``and <reg> <int32>``

1. read value from reg
2. does 'bitwise and' with the two values
3. put the result back into the reg

### lshift

Syntax: ``lshift <reg> <int32>``

1. read value from reg
2. shifts the bits of the value x times to the left
3. put the result back into the reg

### rshift

Syntax: ``rshift <reg> <int32>``

1. read value from reg
2. shifts the bits of the value x times to the right
3. put the result back into the reg

### add

Syntax: ``add <reg> <int32>``

1. read value from reg
2. add two values
3. put the result back into the reg

### subtract

Syntax: ``subtract <reg> <int32>``

1. read value from reg
2. subtract two values
3. put the result back into the reg

### multiply

Syntax: ``multiply <reg> <int32>``

1. read value from reg
2. multiply two values
3. put the result back into the reg

### divide

Syntax: ``divide <reg> <int32>``

1. read value from reg
2. divide two values
3. put the result back into the reg

### modulo

Syntax: ``modulo <reg> <int32>``

1. read value from reg
2. divide two values
3. put the rest back into the reg

### malloc

Syntax: ``malloc <int32>``

1. allocates x bytes of the memory
2. pushes the pointer to the start onto the stack

### free

Syntax: ``free <int32>``

1. frees the chunk of allocated memory at the address

### memset

Syntax: ``memset <int32> <byte>``

1. sets the byte at the address

### memget

Syntax: ``memget <int32>``

1. read value in memory at the address
2. pushes the value onto the stack