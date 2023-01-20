package de.oliver.stackpp.operations.impl.stack;

import de.oliver.stackpp.Program;
import de.oliver.stackpp.operations.Operation;
import de.oliver.stackpp.virtualMachine.Machine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PopOperationTest {

    private Program program;

    @BeforeEach
    void setUp() {
        program = new Program(new Machine());
        program.getMachine().getStack().push(1337);
    }

    @Test
    void execute() {
        Operation operation = new PopOperation(program, 0, p -> p.getMachine().getIntegerRegister("a"));
        operation.execute();

        assert program.getMachine().getIntegerRegister("a").getValue() == 1337;
    }
}