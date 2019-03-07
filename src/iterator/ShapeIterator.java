package iterator;

import java.util.Iterator;

public class ShapeIterator implements Iterator<Shape> {

    int pos;
    private Shape[] shapes;

    public ShapeIterator(Shape[] shapes) {
        this.shapes = shapes;
    }

    @Override
    public boolean hasNext() {
        return pos < shapes.length && shapes[pos] != null;
    }

    @Override
    public Shape next() {
        return shapes[pos++];
    }

    @Override
    public void remove() {
        if (pos <= 0)
            throw new IllegalStateException("Illegal position");
        if (shapes[pos - 1] != null) {
            for (int i = pos - 1; i < (shapes.length - 1); i++) {
                shapes[i] = shapes[i + 1];
            }
            shapes[shapes.length - 1] = null;
        }
    }
}