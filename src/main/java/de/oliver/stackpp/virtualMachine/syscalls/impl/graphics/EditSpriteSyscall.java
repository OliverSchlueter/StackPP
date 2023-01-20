package de.oliver.stackpp.virtualMachine.syscalls.impl.graphics;

import de.oliver.gameEngine.GameObject;
import de.oliver.gameEngine.components.SpriteComponent;
import de.oliver.stackpp.Program;
import de.oliver.stackpp.virtualMachine.Machine;
import de.oliver.stackpp.virtualMachine.syscalls.Syscall;
import org.joml.Vector4f;

public class EditSpriteSyscall extends Syscall {

    public EditSpriteSyscall(int id, Machine machine) {
        super(id, machine);
    }

    /**
     * This syscall can edit several attributes of a gameobject
     *  'a' - action id
     *  'b' - gameobject id
     *  'c' - value
     */
    @Override
    public void execute(Program program) {
        int actionId = machine.getIntegerRegister("a").getValue();
        int id = machine.getIntegerRegister("b").getValue();
        int val = machine.getIntegerRegister("c").getValue();

        Action action = Action.getActionFromId(actionId);
        GameObject gameObject = MyScene.getInstance().getGameObjectIds().get(id);
        SpriteComponent sprite = gameObject.getComponent(SpriteComponent.class);

        switch (action){
            case X_POS -> gameObject.transform.position.x = val;
            case Y_POS -> gameObject.transform.position.y = val;
            case WIDTH -> gameObject.transform.scale.x = val;
            case HEIGHT -> gameObject.transform.scale.y = val;
            case COLOR -> sprite.setColor(colorFromInt(val));
        }
    }

    public static Vector4f colorFromInt(int color){
        float a = (float) ((color >> 24) & 0xff) / 255.0f;
        float r = (float) ((color >> 16) & 0xff) / 255.0f;
        float g = (float) ((color >> 8)  & 0xff) / 255.0f;
        float b = (float) ((color >> 0)  & 0xff) / 255.0f;

        return new Vector4f(r, g, b, a);
    }

    public static int colorToInt(float r, float g, float b, float a){
        int color = ((int) (a * 255.0f) << 24) |
                    ((int) (r * 255.0f) << 16) |
                    ((int) (g * 255.0f) << 8)  |
                    ((int) (b * 255.0f) << 0);
        return color;
    }

    public static int colorToInt(Vector4f color){
        return colorToInt(color.x, color.y, color.z, color.w);
    }
}

enum Action{
    X_POS(0),
    Y_POS(1),
    WIDTH(2),
    HEIGHT(3),
    COLOR(4)
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