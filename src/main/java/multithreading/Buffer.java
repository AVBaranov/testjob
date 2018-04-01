package multithreading;

public class Buffer {
    private StringBuffer buffer = new StringBuffer();

    public void add(int value) {
        System.out.print(value);
        this.buffer.append(value);
        /*try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public String toString() {
        return this.buffer.toString();
    }
}