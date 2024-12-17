package initializer;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import model.queue.SubmissionConsumer;

import java.util.ArrayList;
import java.util.List;

@WebListener
public class AppInitializer implements ServletContextListener {
    private final List<Thread> consumerThreads = new ArrayList<>();
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        int consumerNumber = Runtime.getRuntime().availableProcessors(); //co the thay doi tuy y

        for (int i = 1; i <= consumerNumber; i++) {
            Thread consumerThread = new Thread(new SubmissionConsumer());
            consumerThread.setDaemon(true);
            consumerThread.start();
            System.out.println("Consumer Thread number " + i + " started...");
            consumerThreads.add(consumerThread);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        for (Thread thread : consumerThreads) {
            thread.interrupt();
        }
    }

}
