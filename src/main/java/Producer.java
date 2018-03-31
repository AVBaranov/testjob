package concat;


import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class Producer extends Thread {
    private final Buffer buffer;
    private final int value;
    private final Lock lock;
    private final AtomicInteger previous;

    public Producer(final Buffer buffer, final int value, Lock lock, AtomicInteger previous) {
        this.buffer = buffer;
        this.value = value;
        this.lock = lock;
        this.previous = previous;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            lock.lock();
            try {
                if (this.previous.get() != this.value) {
                    IntStream.range(0, 10).forEach(
                            value -> buffer.add(this.value)
                    );
                    this.previous.set(this.value);
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        final Buffer buffer = new Buffer();
        final ReentrantLock lock = new ReentrantLock();
        final AtomicInteger flag = new AtomicInteger(2);
        new Producer(buffer, 1, lock, flag).start();
        new Producer(buffer, 2, lock, flag).start();
    }
}