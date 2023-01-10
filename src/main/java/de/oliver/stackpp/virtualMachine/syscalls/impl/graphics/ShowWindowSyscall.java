package de.oliver.stackpp.virtualMachine.syscalls.impl.graphics;

import de.oliver.gameEngine.Window;
import de.oliver.stackpp.Program;
import de.oliver.stackpp.virtualMachine.Machine;
import de.oliver.stackpp.virtualMachine.syscalls.Syscall;
import org.joml.Vector4f;

public class ShowWindowSyscall extends Syscall {

    private Thread windowThread;

    public ShowWindowSyscall(int id, Machine machine) {
        super(id, machine);
    }

    @Override
    public void execute(Program program) {
        int width = machine.getRegister("a").getValue();
        int height = machine.getRegister("b").getValue();

        int pathPtr = machine.getRegister("c").getValue();
        String path = "";

        int i = pathPtr;
        while (true){
            char c = (char) machine.getMemory().getAt(i);

            if(c == '\0'){
                break;
            }

            path += c;

            i++;
        }

        String finalPath = path;
        windowThread = new Thread(() -> {
            Window.create(width, height, finalPath, new Vector4f(0, 0, 0, 1));
            Window.get().setCurrentScene(MyScene.getInstance());
            Window.get().run();
        });

        windowThread.start();
    }

    public Thread getWindowThread() {
        return windowThread;
    }
}