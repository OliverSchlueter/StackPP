package de.oliver.stackpp.virtualMachine.syscalls.impl.graphics;

import de.oliver.gameEngine.GameObject;
import de.oliver.gameEngine.components.SpriteComponent;
import de.oliver.stackpp.Program;
import de.oliver.stackpp.virtualMachine.Machine;
import de.oliver.stackpp.virtualMachine.syscalls.Syscall;

public class GetSpritePropertiesSyscall extends Syscall {

    public GetSpritePropertiesSyscall(int id, Machine machine) {
        super(id, machine);
    }

    @Override
    public void execute(Program program) {
        int id = machine.getRegister("a").getValue();

        GameObject gameObject = MyScene.getInstance().getGameObjectIds().get(id);
        SpriteComponent sprite = gameObject.getComponent(SpriteComponent.class);

        machine.getRegister("b").setValue((int) gameObject.transform.position.x);
        machine.getRegister("c").setValue((int) gameObject.transform.position.y);
        machine.getRegister("d").setValue((int) gameObject.transform.scale.x);
        machine.getRegister("e").setValue((int) gameObject.transform.scale.y);
        machine.getRegister("f").setValue(EditSpriteSyscall.colorToInt(sprite.getColor()));
    }
}
