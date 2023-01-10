package de.oliver.stackpp.virtualMachine.syscalls.impl.graphics;

import de.oliver.gameEngine.Camera;
import de.oliver.gameEngine.GameObject;
import de.oliver.gameEngine.Scene;
import de.oliver.gameEngine.Transform;
import de.oliver.gameEngine.components.SpriteComponent;
import de.oliver.gameEngine.utils.AssetPool;
import org.joml.Vector2f;
import org.joml.Vector4f;

public class MyScene extends Scene {

    private static MyScene instance = null;

    private MyScene(){

    }

    @Override
    public void init() {
        loadResources();
        camera = new Camera(new Vector2f());

        GameObject obj = new GameObject("obj1", new Transform(new Vector2f(100, 100), new Vector2f(100, 100)), 1);
        obj.addComponent(new SpriteComponent(new Vector4f(1, 0, 0, 1)));
        //addGameObject(obj);
    }

    @Override
    public void update(float v) {
        renderer.render();
    }

    public void loadResources(){
        AssetPool.getShader("D:\\Workspace\\GitHub\\GameEngine\\src\\main\\resources\\shaders\\default.glsl");

    }

    public static MyScene getInstance() {
        if(instance == null){
            instance = new MyScene();
        }

        return instance;
    }
}
