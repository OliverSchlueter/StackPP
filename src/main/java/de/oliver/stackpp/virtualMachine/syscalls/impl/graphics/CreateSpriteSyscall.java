package de.oliver.stackpp.virtualMachine.syscalls.impl.graphics;

import de.oliver.gameEngine.GameObject;
import de.oliver.gameEngine.Transform;
import de.oliver.gameEngine.components.SpriteComponent;
import de.oliver.stackpp.Program;
import de.oliver.stackpp.virtualMachine.Machine;
import de.oliver.stackpp.virtualMachine.syscalls.Syscall;
import org.joml.Vector2f;
import org.joml.Vector4f;

public class CreateSpriteSyscall extends Syscall {


    public CreateSpriteSyscall(int id, Machine machine) {
        super(id, machine);
    }

    /**
     * Creates a new gameobject with a sprite
     *  'a' - x position
     *  'b' - y position
     *  'c' - width
     *  'd' - height
     */
    @Override
    public void execute(Program program) {
        int xPos = machine.getRegister("a").getValue();
        int yPos = machine.getRegister("b").getValue();
        int width = machine.getRegister("c").getValue();
        int height = machine.getRegister("d").getValue();

        GameObject gameObject = new GameObject(
                "Obj-" + System.currentTimeMillis(),
                new Transform(
                        new Vector2f(xPos, yPos),
                        new Vector2f(width, height)
                ),
                3
        );

        gameObject.addComponent(new SpriteComponent(new Vector4f(1, 1, 1, 1)));

        int id = MyScene.getInstance().addGameObjectFromProgram(gameObject);
        machine.getStack().push(id);
    }
}
