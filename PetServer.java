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
            // --- Original 30 Pets ---
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
            "{\"id\":30,\"name\":\"Bella\",\"type\":\"Dog\",\"breed\":\"Shih Tzu\",\"age\":3,\"imageURL\":\"https://placedog.net/500/400?id=120\"}," +

            // --- Extra 30 Pets Added ---
            "{\"id\":31,\"name\":\"Leo\",\"type\":\"Cat\",\"breed\":\"Siamese\",\"age\":2,\"imageURL\":\"https://loremflickr.com/500/400/cat?lock=301\"}," +
            "{\"id\":32,\"name\":\"Misty\",\"type\":\"Cat\",\"breed\":\"Persian\",\"age\":3,\"imageURL\":\"https://loremflickr.com/500/400/cat?lock=302\"}," +
            "{\"id\":33,\"name\":\"Oreo\",\"type\":\"Cat\",\"breed\":\"British Shorthair\",\"age\":1,\"imageURL\":\"https://loremflickr.com/500/400/cat?lock=303\"}," +
            "{\"id\":34,\"name\":\"Whiskers\",\"type\":\"Cat\",\"breed\":\"Ragdoll\",\"age\":4,\"imageURL\":\"https://loremflickr.com/500/400/cat?lock=304\"}," +
            "{\"id\":35,\"name\":\"Simba\",\"type\":\"Dog\",\"breed\":\"German Shepherd\",\"age\":3,\"imageURL\":\"https://placedog.net/500/400?id=121\"}," +
            "{\"id\":36,\"name\":\"Teddy\",\"type\":\"Dog\",\"breed\":\"Pomeranian\",\"age\":2,\"imageURL\":\"https://placedog.net/500/400?id=122\"}," +
            "{\"id\":37,\"name\":\"Lucky\",\"type\":\"Dog\",\"breed\":\"Cocker Spaniel\",\"age\":4,\"imageURL\":\"https://placedog.net/500/400?id=123\"}," +
            "{\"id\":38,\"name\":\"Shadow\",\"type\":\"Dog\",\"breed\":\"Doberman\",\"age\":5,\"imageURL\":\"https://placedog.net/500/400?id=124\"}," +
            "{\"id\":39,\"name\":\"Blue\",\"type\":\"Parrot\",\"breed\":\"African Grey\",\"age\":2,\"imageURL\":\"https://loremflickr.com/500/400/parrot?lock=305\"}," +
            "{\"id\":40,\"name\":\"Sunny\",\"type\":\"Parrot\",\"breed\":\"Lovebird\",\"age\":1,\"imageURL\":\"https://loremflickr.com/500/400/parrot?lock=306\"}," +
            "{\"id\":41,\"name\":\"Marble\",\"type\":\"Fish\",\"breed\":\"Angelfish\",\"age\":1,\"imageURL\":\"https://loremflickr.com/500/400/fish?lock=307\"}," +
            "{\"id\":42,\"name\":\"Flash\",\"type\":\"Turtle\",\"breed\":\"Painted Turtle\",\"age\":4,\"imageURL\":\"https://loremflickr.com/500/400/turtle?lock=308\"}," +
            "{\"id\":43,\"name\":\"Tiny\",\"type\":\"Hamster\",\"breed\":\"Roborovski\",\"age\":1,\"imageURL\":\"https://loremflickr.com/500/400/hamster?lock=309\"}," +
            "{\"id\":44,\"name\":\"Fluffy\",\"type\":\"Rabbit\",\"breed\":\"Mini Rex\",\"age\":2,\"imageURL\":\"https://loremflickr.com/500/400/rabbit?lock=313\"}," +
            "{\"id\":45,\"name\":\"Chirpy\",\"type\":\"Parrot\",\"breed\":\"Budgerigar\",\"age\":1,\"imageURL\":\"https://loremflickr.com/500/400/parrot?lock=311\"}," +
            "{\"id\":46,\"name\":\"Pumpkin\",\"type\":\"Cat\",\"breed\":\"Maine Coon\",\"age\":3,\"imageURL\":\"https://loremflickr.com/500/400/cat?lock=312\"}," +
            "{\"id\":47,\"name\":\"Shadow\",\"type\":\"Dog\",\"breed\":\"Labrador\",\"age\":2,\"imageURL\":\"https://placedog.net/500/400?id=125\"}," +
            "{\"id\":48,\"name\":\"Ruby\",\"type\":\"Dog\",\"breed\":\"Golden Retriever\",\"age\":4,\"imageURL\":\"https://placedog.net/500/400?id=126\"}," +
            "{\"id\":49,\"name\":\"Lily\",\"type\":\"Rabbit\",\"breed\":\"Angora\",\"age\":2,\"imageURL\":\"https://loremflickr.com/500/400/rabbit?lock=313\"}," +
            "{\"id\":50,\"name\":\"Cinnamon\",\"type\":\"Hamster\",\"breed\":\"Winter White\",\"age\":1,\"imageURL\":\"https://loremflickr.com/500/400/hamster?lock=314\"}," +
            "{\"id\":51,\"name\":\"Sparky\",\"type\":\"Dog\",\"breed\":\"Beagle\",\"age\":3,\"imageURL\":\"https://placedog.net/500/400?id=127\"}," +
            "{\"id\":52,\"name\":\"Angel\",\"type\":\"Cat\",\"breed\":\"Bengal\",\"age\":2,\"imageURL\":\"https://loremflickr.com/500/400/cat?lock=315\"}," +
            "{\"id\":53,\"name\":\"Mocha\",\"type\":\"Dog\",\"breed\":\"Pug\",\"age\":1,\"imageURL\":\"https://placedog.net/500/400?id=128\"}," +
            "{\"id\":54,\"name\":\"Pearl\",\"type\":\"Fish\",\"breed\":\"Guppy\",\"age\":1,\"imageURL\":\"https://loremflickr.com/500/400/fish?lock=316\"}," +
            "{\"id\":55,\"name\":\"Snowflake\",\"type\":\"Rabbit\",\"breed\":\"Holland Lop\",\"age\":2,\"imageURL\":\"https://loremflickr.com/500/400/rabbit?lock=317\"}," +
            "{\"id\":56,\"name\":\"Bolt\",\"type\":\"Dog\",\"breed\":\"Border Collie\",\"age\":3,\"imageURL\":\"https://placedog.net/500/400?id=129\"}," +
            "{\"id\":57,\"name\":\"Poppy\",\"type\":\"Cat\",\"breed\":\"Ragdoll\",\"age\":4,\"imageURL\":\"https://loremflickr.com/500/400/cat?lock=318\"}," +
            "{\"id\":58,\"name\":\"Bluebell\",\"type\":\"Goat\",\"breed\":\"Nubian\",\"age\":3,\"imageURL\":\"https://loremflickr.com/500/400/goat?lock=319\"}," +
            "{\"id\":59,\"name\":\"Muffin\",\"type\":\"Guinea Pig\",\"breed\":\"Teddy\",\"age\":2,\"imageURL\":\"https://loremflickr.com/500/400/guineapig?lock=320\"}," +
            "{\"id\":60,\"name\":\"Lucky\",\"type\":\"Duck\",\"breed\":\"Call Duck\",\"age\":1,\"imageURL\":\"https://loremflickr.com/500/400/duck?lock=321\"}" +
            "]";

            byte[] bytes = json.getBytes("UTF-8");
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Content-Type", "application/json; charset=utf-8");
            exchange.sendResponseHeaders(200, bytes.length);
            try (OutputStream os = exchange.getResponseBody()) { os.write(bytes); }
        }
    }
}

