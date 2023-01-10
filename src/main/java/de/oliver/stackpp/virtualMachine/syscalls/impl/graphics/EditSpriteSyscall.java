package de.oliver.stackpp.virtualMachine.syscalls.impl.graphics;

import de.oliver.gameEngine.GameObject;
import de.oliver.gameEngine.components.SpriteComponent;
import de.oliver.stackpp.Program;
import de.oliver.stackpp.virtualMachine.Machine;
import de.oliver.stackpp.virtualMachine.syscalls.Syscall;

public class EditSpriteSyscall extends Syscall {

    public EditSpriteSyscall(int id, Machine machine) {
        super(id, machine);
    }

    @Override
    public void execute(Program program) {
        int actionId = machine.getRegister("a").getValue();
        int id = machine.getRegister("b").getValue();
        int val = machine.getRegister("c").getValue();

        Action action = Action.getActionFromId(actionId);
        GameObject gameObject = MyScene.getInstance().getGameObjectIds().get(id);
        SpriteComponent sprite = gameObject.getComponent(SpriteComponent.class);

        switch (action){
            case X_POS -> gameObject.transform.position.x = val;
            case Y_POS -> gameObject.transform.position.y = val;
            case WIDTH -> gameObject.transform.scale.x = val;
            case HEIGHT -> gameObject.transform.scale.y = val;
        }

    }
}

enum Action{
    X_POS(0),
    Y_POS(1),
    WIDTH(2),
    HEIGHT(3),
    ;

    private final int actionId;

    Action(int actionId) {
        this.actionId = actionId;
    }

    public int getActionId() {
        return actionId;
    }

    public static Action getActionFromId(int id){
        for (Action a : values()) {
            if(a.getActionId() == id){
                return a;
            }
        }

        return null;
    }
}