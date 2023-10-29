import java.util.ArrayList;
import java.util.List;

// Интерфейс для подписчиков (наблюдателей)
interface Observer {
    void update(String videoTitle);
}

// Класс для канала (субъекта)
class YouTubeChannel {
    private List<Observer> subscribers = new ArrayList<>();
    private String latestVideoTitle;

    // Метод для добавления подписчика
    public void subscribe(Observer subscriber) {
        subscribers.add(subscriber);
    }

    // Метод для удаления подписчика
    public void unsubscribe(Observer subscriber) {
        subscribers.remove(subscriber);
    }

    // Метод для опубликования нового видео
    public void publishNewVideo(String videoTitle) {
        latestVideoTitle = videoTitle;
        notifySubscribers();
    }

    // Метод для уведомления всех подписчиков
    private void notifySubscribers() {
        for (Observer subscriber : subscribers) {
            subscriber.update(latestVideoTitle);
        }
    }
}

// Класс для подписчика
class Subscriber implements Observer {
    private String name;

    public Subscriber(String name) {
        this.name = name;
    }

    // Метод, вызываемый при получении уведомления о новом видео
    @Override
    public void update(String videoTitle) {
        System.out.println(name + " получил уведомление о новом видео: " + videoTitle);
    }
}

public class Main {
    public static void main(String[] args) {
        // Создаем канал и подписчиков
        YouTubeChannel channel = new YouTubeChannel();
        Subscriber subscriber1 = new Subscriber("Подписчик 1");
        Subscriber subscriber2 = new Subscriber("Подписчик 2");

        // Подписываем подписчиков на канал
        channel.subscribe(subscriber1);
        channel.subscribe(subscriber2);

        // Опубликовываем новое видео
        channel.publishNewVideo("Видео 1");

        // Отписываем одного из подписчиков
        channel.unsubscribe(subscriber1);


        channel.publishNewVideo("Видео 2");
    }
}
