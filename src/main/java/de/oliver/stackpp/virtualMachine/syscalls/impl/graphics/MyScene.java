package de.oliver.stackpp.virtualMachine.syscalls.impl.graphics;

import de.oliver.gameEngine.Camera;
import de.oliver.gameEngine.GameObject;
import de.oliver.gameEngine.Scene;
import de.oliver.gameEngine.Transform;
import de.oliver.gameEngine.components.SpriteComponent;
import de.oliver.gameEngine.utils.AssetPool;
import de.oliver.stackpp.utils.RandomHelper;
import org.joml.Vector2f;
import org.joml.Vector4f;

import java.util.HashMap;
import java.util.Map;

public class MyScene extends Scene {

    private static MyScene instance = null;
    private final Map<Integer, GameObject> gameObjectIds;

    private MyScene(){
        gameObjectIds = new HashMap<>();
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

    public Map<Integer, GameObject> getGameObjectIds() {
        return gameObjectIds;
    }

    public int addGameObjectFromProgram(GameObject gameObject){
        int id = -1;
        boolean foundId = false;
        while(!foundId){
            id = (int)RandomHelper.randomInRange(0, 1E6);

            if(!gameObjectIds.containsKey(id)){
                foundId = true;
            }
        }

        if(id == -1){
            throw new RuntimeException("Could not generate gameobject id");
        }

        gameObjectIds.put(id, gameObject);

        addGameObject(gameObject);

        return id;
    }

    public static MyScene getInstance() {
        if(instance == null){
            instance = new MyScene();
        }

        return instance;
    }
}
