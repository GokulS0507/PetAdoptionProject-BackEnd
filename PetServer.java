import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class PetServer {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        String portEnv = System.getenv("PORT");
        if (portEnv != null) {
            try { port = Integer.parseInt(portEnv); } catch (NumberFormatException ignored) {}
        }

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", new RootHandler());
        server.createContext("/pets", new PetHandler());
        server.setExecutor(null);
        System.out.println("PetServer started on port " + port);
        server.start();
    }

    static class RootHandler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {
            String response = "Pet Adoption Backend is running";
            byte[] bytes = response.getBytes("UTF-8");
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Content-Type", "text/plain; charset=utf-8");
            exchange.sendResponseHeaders(200, bytes.length);
            try (OutputStream os = exchange.getResponseBody()) { os.write(bytes); }
        }
    }

    static class PetHandler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {
            String json = "[" +
            "{\"id\":11,\"name\":\"Thumper\",\"type\":\"Rabbit\",\"breed\":\"Dutch Rabbit\",\"age\":1,\"imageURL\":\"https://loremflickr.com/500/400/rabbit?lock=201\"}," +
            "{\"id\":12,\"name\":\"Snowball\",\"type\":\"Rabbit\",\"breed\":\"Lionhead\",\"age\":2,\"imageURL\":\"https://loremflickr.com/500/400/rabbit?lock=202\"}," +
            "{\"id\":13,\"name\":\"Coco\",\"type\":\"Rabbit\",\"breed\":\"Mini Lop\",\"age\":1,\"imageURL\":\"https://loremflickr.com/500/400/rabbit?lock=203\"}," +
            "{\"id\":14,\"name\":\"Rio\",\"type\":\"Parrot\",\"breed\":\"Macaw\",\"age\":3,\"imageURL\":\"https://loremflickr.com/500/400/parrot?lock=204\"}," +
            "{\"id\":15,\"name\":\"Sky\",\"type\":\"Parrot\",\"breed\":\"Cockatiel\",\"age\":2,\"imageURL\":\"https://loremflickr.com/500/400/parrot?lock=205\"}," +
            "{\"id\":16,\"name\":\"Goldie\",\"type\":\"Fish\",\"breed\":\"Goldfish\",\"age\":1,\"imageURL\":\"https://loremflickr.com/500/400/fish?lock=206\"}," +
            "{\"id\":17,\"name\":\"Bubbles\",\"type\":\"Fish\",\"breed\":\"Betta\",\"age\":1,\"imageURL\":\"https://loremflickr.com/500/400/fish?lock=207\"}," +
            "{\"id\":18,\"name\":\"Spike\",\"type\":\"Hamster\",\"breed\":\"Syrian Hamster\",\"age\":1,\"imageURL\":\"https://loremflickr.com/500/400/hamster?lock=208\"}," +
            "{\"id\":19,\"name\":\"Nibbles\",\"type\":\"Hamster\",\"breed\":\"Dwarf Hamster\",\"age\":1,\"imageURL\":\"https://loremflickr.com/500/400/hamster?lock=209\"}," +
            "{\"id\":20,\"name\":\"Shelly\",\"type\":\"Turtle\",\"breed\":\"Red-Eared Slider\",\"age\":5,\"imageURL\":\"https://loremflickr.com/500/400/turtle?lock=210\"}," +
            "{\"id\":21,\"name\":\"Speedy\",\"type\":\"Turtle\",\"breed\":\"Box Turtle\",\"age\":3,\"imageURL\":\"https://loremflickr.com/500/400/turtle?lock=211\"}," +
            "{\"id\":22,\"name\":\"Penny\",\"type\":\"Guinea Pig\",\"breed\":\"American Guinea Pig\",\"age\":2,\"imageURL\":\"https://loremflickr.com/500/400/guineapig?lock=212\"}," +
            "{\"id\":23,\"name\":\"Brownie\",\"type\":\"Guinea Pig\",\"breed\":\"Abyssinian\",\"age\":3,\"imageURL\":\"https://loremflickr.com/500/400/guineapig?lock=213\"}," +
            "{\"id\":24,\"name\":\"Zara\",\"type\":\"Goat\",\"breed\":\"Boer Goat\",\"age\":2,\"imageURL\":\"https://loremflickr.com/500/400/goat?lock=214\"}," +
            "{\"id\":25,\"name\":\"Billy\",\"type\":\"Goat\",\"breed\":\"Pygmy Goat\",\"age\":4,\"imageURL\":\"https://loremflickr.com/500/400/goat?lock=215\"}," +
            "{\"id\":26,\"name\":\"Pepper\",\"type\":\"Sheep\",\"breed\":\"Merino\",\"age\":3,\"imageURL\":\"https://loremflickr.com/500/400/sheep?lock=216\"}," +
            "{\"id\":27,\"name\":\"Snow\",\"type\":\"Sheep\",\"breed\":\"Suffolk\",\"age\":2,\"imageURL\":\"https://loremflickr.com/500/400/sheep?lock=217\"}," +
            "{\"id\":28,\"name\":\"Paco\",\"type\":\"Duck\",\"breed\":\"Pekin Duck\",\"age\":1,\"imageURL\":\"https://loremflickr.com/500/400/duck?lock=218\"}," +
            "{\"id\":29,\"name\":\"Quacky\",\"type\":\"Duck\",\"breed\":\"Mallard\",\"age\":2,\"imageURL\":\"https://loremflickr.com/500/400/duck?lock=219\"}," +
            "{\"id\":30,\"name\":\"Bella\",\"type\":\"Dog\",\"breed\":\"Shih Tzu\",\"age\":3,\"imageURL\":\"https://placedog.net/500/400?id=120\"}" +
            "]";
            byte[] bytes = json.getBytes("UTF-8");
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Content-Type", "application/json; charset=utf-8");
            exchange.sendResponseHeaders(200, bytes.length);
            try (OutputStream os = exchange.getResponseBody()) { os.write(bytes); }
        }
    }
}
