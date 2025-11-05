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
            // --- Pets with fixed images ---
            "{\"id\":11,\"name\":\"Thumper\",\"type\":\"Rabbit\",\"breed\":\"Dutch Rabbit\",\"age\":1,\"imageURL\":\"https://picsum.photos/500/400?lock=111\"}," +
            "{\"id\":12,\"name\":\"Snowball\",\"type\":\"Rabbit\",\"breed\":\"Lionhead\",\"age\":2,\"imageURL\":\"https://picsum.photos/500/400?lock=112\"}," +
            "{\"id\":13,\"name\":\"Coco\",\"type\":\"Rabbit\",\"breed\":\"Mini Lop\",\"age\":1,\"imageURL\":\"https://picsum.photos/500/400?lock=113\"}," +
            "{\"id\":14,\"name\":\"Rio\",\"type\":\"Parrot\",\"breed\":\"Macaw\",\"age\":3,\"imageURL\":\"https://picsum.photos/500/400?lock=114\"}," +
            "{\"id\":15,\"name\":\"Sky\",\"type\":\"Parrot\",\"breed\":\"Cockatiel\",\"age\":2,\"imageURL\":\"https://picsum.photos/500/400?lock=115\"}," +
            "{\"id\":16,\"name\":\"Goldie\",\"type\":\"Fish\",\"breed\":\"Goldfish\",\"age\":1,\"imageURL\":\"https://picsum.photos/500/400?lock=116\"}," +
            "{\"id\":17,\"name\":\"Bubbles\",\"type\":\"Fish\",\"breed\":\"Betta\",\"age\":1,\"imageURL\":\"https://picsum.photos/500/400?lock=117\"}," +
            "{\"id\":18,\"name\":\"Spike\",\"type\":\"Hamster\",\"breed\":\"Syrian Hamster\",\"age\":1,\"imageURL\":\"https://picsum.photos/500/400?lock=118\"}," +
            "{\"id\":19,\"name\":\"Nibbles\",\"type\":\"Hamster\",\"breed\":\"Dwarf Hamster\",\"age\":1,\"imageURL\":\"https://picsum.photos/500/400?lock=119\"}," +
            "{\"id\":20,\"name\":\"Shelly\",\"type\":\"Turtle\",\"breed\":\"Red-Eared Slider\",\"age\":5,\"imageURL\":\"https://picsum.photos/500/400?lock=120\"}," +
            "{\"id\":21,\"name\":\"Speedy\",\"type\":\"Turtle\",\"breed\":\"Box Turtle\",\"age\":3,\"imageURL\":\"https://picsum.photos/500/400?lock=121\"}," +
            "{\"id\":22,\"name\":\"Penny\",\"type\":\"Guinea Pig\",\"breed\":\"American Guinea Pig\",\"age\":2,\"imageURL\":\"https://picsum.photos/500/400?lock=122\"}," +
            "{\"id\":23,\"name\":\"Brownie\",\"type\":\"Guinea Pig\",\"breed\":\"Abyssinian\",\"age\":3,\"imageURL\":\"https://picsum.photos/500/400?lock=123\"}," +
            "{\"id\":24,\"name\":\"Zara\",\"type\":\"Goat\",\"breed\":\"Boer Goat\",\"age\":2,\"imageURL\":\"https://picsum.photos/500/400?lock=124\"}," +
            "{\"id\":25,\"name\":\"Billy\",\"type\":\"Goat\",\"breed\":\"Pygmy Goat\",\"age\":4,\"imageURL\":\"https://picsum.photos/500/400?lock=125\"}," +
            "{\"id\":26,\"name\":\"Pepper\",\"type\":\"Sheep\",\"breed\":\"Merino\",\"age\":3,\"imageURL\":\"https://picsum.photos/500/400?lock=126\"}," +
            "{\"id\":27,\"name\":\"Snow\",\"type\":\"Sheep\",\"breed\":\"Suffolk\",\"age\":2,\"imageURL\":\"https://picsum.photos/500/400?lock=127\"}," +
            "{\"id\":28,\"name\":\"Paco\",\"type\":\"Duck\",\"breed\":\"Pekin Duck\",\"age\":1,\"imageURL\":\"https://picsum.photos/500/400?lock=128\"}," +
            "{\"id\":29,\"name\":\"Quacky\",\"type\":\"Duck\",\"breed\":\"Mallard\",\"age\":2,\"imageURL\":\"https://picsum.photos/500/400?lock=129\"}," +
            "{\"id\":30,\"name\":\"Bella\",\"type\":\"Dog\",\"breed\":\"Shih Tzu\",\"age\":3,\"imageURL\":\"https://placedog.net/500/400?id=120\"}," +

            // --- Extra 30 Pets Added with fixed images ---
            "{\"id\":31,\"name\":\"Leo\",\"type\":\"Cat\",\"breed\":\"Siamese\",\"age\":2,\"imageURL\":\"https://placekitten.com/500/400?image=31\"}," +
            "{\"id\":32,\"name\":\"Misty\",\"type\":\"Cat\",\"breed\":\"Persian\",\"age\":3,\"imageURL\":\"https://placekitten.com/500/400?image=32\"}," +
            "{\"id\":33,\"name\":\"Oreo\",\"type\":\"Cat\",\"breed\":\"British Shorthair\",\"age\":1,\"imageURL\":\"https://placekitten.com/500/400?image=33\"}," +
            "{\"id\":34,\"name\":\"Whiskers\",\"type\":\"Cat\",\"breed\":\"Ragdoll\",\"age\":4,\"imageURL\":\"https://placekitten.com/500/400?image=34\"}," +
            "{\"id\":35,\"name\":\"Simba\",\"type\":\"Dog\",\"breed\":\"German Shepherd\",\"age\":3,\"imageURL\":\"https://placedog.net/500/400?id=121\"}," +
            "{\"id\":36,\"name\":\"Teddy\",\"type\":\"Dog\",\"breed\":\"Pomeranian\",\"age\":2,\"imageURL\":\"https://placedog.net/500/400?id=122\"}," +
            "{\"id\":37,\"name\":\"Lucky\",\"type\":\"Dog\",\"breed\":\"Cocker Spaniel\",\"age\":4,\"imageURL\":\"https://placedog.net/500/400?id=123\"}," +
            "{\"id\":38,\"name\":\"Shadow\",\"type\":\"Dog\",\"breed\":\"Doberman\",\"age\":5,\"imageURL\":\"https://placedog.net/500/400?id=124\"}," +
            "{\"id\":39,\"name\":\"Blue\",\"type\":\"Parrot\",\"breed\":\"African Grey\",\"age\":2,\"imageURL\":\"https://picsum.photos/500/400?lock=139\"}," +
            "{\"id\":40,\"name\":\"Sunny\",\"type\":\"Parrot\",\"breed\":\"Lovebird\",\"age\":1,\"imageURL\":\"https://picsum.photos/500/400?lock=140\"}," +
            "{\"id\":41,\"name\":\"Marble\",\"type\":\"Fish\",\"breed\":\"Angelfish\",\"age\":1,\"imageURL\":\"https://picsum.photos/500/400?lock=141\"}," +
            "{\"id\":42,\"name\":\"Flash\",\"type\":\"Turtle\",\"breed\":\"Painted Turtle\",\"age\":4,\"imageURL\":\"https://picsum.photos/500/400?lock=142\"}," +
            "{\"id\":43,\"name\":\"Tiny\",\"type\":\"Hamster\",\"breed\":\"Roborovski\",\"age\":1,\"imageURL\":\"https://picsum.photos/500/400?lock=143\"}," +
            "{\"id\":44,\"name\":\"Fluffy\",\"type\":\"Rabbit\",\"breed\":\"Mini Rex\",\"age\":2,\"imageURL\":\"https://picsum.photos/500/400?lock=144\"}," +
            "{\"id\":45,\"name\":\"Chirpy\",\"type\":\"Parrot\",\"breed\":\"Budgerigar\",\"age\":1,\"imageURL\":\"https://picsum.photos/500/400?lock=145\"}," +
            "{\"id\":46,\"name\":\"Pumpkin\",\"type\":\"Cat\",\"breed\":\"Maine Coon\",\"age\":3,\"imageURL\":\"https://placekitten.com/500/400?image=46\"}," +
            "{\"id\":47,\"name\":\"Shadow\",\"type\":\"Dog\",\"breed\":\"Labrador\",\"age\":2,\"imageURL\":\"https://placedog.net/500/400?id=125\"}," +
            "{\"id\":48,\"name\":\"Ruby\",\"type\":\"Dog\",\"breed\":\"Golden Retriever\",\"age\":4,\"imageURL\":\"https://placedog.net/500/400?id=126\"}," +
            "{\"id\":49,\"name\":\"Lily\",\"type\":\"Rabbit\",\"breed\":\"Angora\",\"age\":2,\"imageURL\":\"https://picsum.photos/500/400?lock=149\"}," +
            "{\"id\":50,\"name\":\"Cinnamon\",\"type\":\"Hamster\",\"breed\":\"Winter White\",\"age\":1,\"imageURL\":\"https://picsum.photos/500/400?lock=150\"}" +
            "]";

            byte[] bytes = json.getBytes("UTF-8");
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Content-Type", "application/json; charset=utf-8");
            exchange.sendResponseHeaders(200, bytes.length);
            try (OutputStream os = exchange.getResponseBody()) { os.write(bytes); }
        }
    }
}
