package iterator;

import java.util.ArrayList;
import java.util.List;

public class ShapeStorage {
    List<Shape> shapes = new ArrayList<>();


    public void addShape(String polygon) {
        shapes.add(new Shape(shapes.size(), polygon));
    }

    public Shape[] getShapes() {
        return shapes.toArray(new Shape[0]);
    }
}