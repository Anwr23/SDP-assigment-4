import java.util.ArrayList;
import java.util.List;


interface Observer {
    void update(String videoTitle);
}

class YouTubeChannel {
    private final List<Observer> subscribers = new ArrayList<>();
    private String latestVideoTitle;

    public void subscribe(Observer subscriber) {
        subscribers.add(subscriber);
    }

    public void unsubscribe(Observer subscriber) {
        subscribers.remove(subscriber);
    }

    public void publishNewVideo(String videoTitle) {
        latestVideoTitle = videoTitle;
        notifySubscribers();
    }

    private void notifySubscribers() {
        for (Observer subscriber : subscribers) {
            subscriber.update(latestVideoTitle);
        }
    }
}

class Subscriber implements Observer {
    private final String name;

    public Subscriber(String name) {
        this.name = name;
    }

    @Override
    public void update(String videoTitle) {
        System.out.println(name + " получил уведомление о новом видео: " + videoTitle);
    }
}

public class Main {
    public static void main(String[] args) {
        YouTubeChannel channel = new YouTubeChannel();
        Subscriber subscriber1 = new Subscriber("Подписчик 1");
        Subscriber subscriber2 = new Subscriber("Подписчик 2");

        channel.subscribe(subscriber1);
        channel.subscribe(subscriber2);

        channel.publishNewVideo("Видео 1");

        channel.unsubscribe(subscriber1);


        channel.publishNewVideo("Видео 2");
    }
}
