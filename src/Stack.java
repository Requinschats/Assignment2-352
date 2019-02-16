public class Stack {
    private int topIndex;
    private Object[] storage;

    Stack(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Stack's capacity must be bigger than zero");
        }
        storage = new Object[capacity];
        topIndex = 0;
    }

    public void push(Object  value) {
        if (topIndex == storage.length) {
            throw new StackException("No space left in storage");
        }
        topIndex++;
        storage[topIndex] = value;
    }

    Object peek() {
        if (topIndex == 0) {
            throw new StackException("Stack is empty");
        }
        return storage[topIndex];
    }

    Object pop() {
        if (topIndex == 0) {
            throw new StackException("Stack is empty");
        }
        topIndex--;
        return storage[topIndex+1];
    }

    boolean isEmpty() {
        return (topIndex == 0);
    }

    public class StackException extends RuntimeException {
        public StackException(String message) {
            super(message);
        }
    }
}
