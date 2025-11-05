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
                // 60 Dog Pets with Static Images
                "{\"id\":1,\"name\":\"Buddy\",\"type\":\"Dog\",\"breed\":\"Labrador\",\"age\":3,\"imageURL\":\"https://placedog.net/500/400?id=101\"}," +
                "{\"id\":2,\"name\":\"Max\",\"type\":\"Dog\",\"breed\":\"Golden Retriever\",\"age\":2,\"imageURL\":\"https://placedog.net/500/400?id=102\"}," +
                "{\"id\":3,\"name\":\"Charlie\",\"type\":\"Dog\",\"breed\":\"Beagle\",\"age\":4,\"imageURL\":\"https://placedog.net/500/400?id=103\"}," +
                "{\"id\":4,\"name\":\"Rocky\",\"type\":\"Dog\",\"breed\":\"German Shepherd\",\"age\":5,\"imageURL\":\"https://placedog.net/500/400?id=104\"}," +
                "{\"id\":5,\"name\":\"Jack\",\"type\":\"Dog\",\"breed\":\"Bulldog\",\"age\":3,\"imageURL\":\"https://placedog.net/500/400?id=105\"}," +
                "{\"id\":6,\"name\":\"Cooper\",\"type\":\"Dog\",\"breed\":\"Poodle\",\"age\":2,\"imageURL\":\"https://placedog.net/500/400?id=106\"}," +
                "{\"id\":7,\"name\":\"Daisy\",\"type\":\"Dog\",\"breed\":\"Pug\",\"age\":1,\"imageURL\":\"https://placedog.net/500/400?id=107\"}," +
                "{\"id\":8,\"name\":\"Milo\",\"type\":\"Dog\",\"breed\":\"Border Collie\",\"age\":4,\"imageURL\":\"https://placedog.net/500/400?id=108\"}," +
                "{\"id\":9,\"name\":\"Bailey\",\"type\":\"Dog\",\"breed\":\"Siberian Husky\",\"age\":5,\"imageURL\":\"https://placedog.net/500/400?id=109\"}," +
                "{\"id\":10,\"name\":\"Luna\",\"type\":\"Dog\",\"breed\":\"Dalmatian\",\"age\":2,\"imageURL\":\"https://placedog.net/500/400?id=110\"}," +
                "{\"id\":11,\"name\":\"Bella\",\"type\":\"Dog\",\"breed\":\"Shih Tzu\",\"age\":3,\"imageURL\":\"https://placedog.net/500/400?id=111\"}," +
                "{\"id\":12,\"name\":\"Sadie\",\"type\":\"Dog\",\"breed\":\"Cocker Spaniel\",\"age\":4,\"imageURL\":\"https://placedog.net/500/400?id=112\"}," +
                "{\"id\":13,\"name\":\"Toby\",\"type\":\"Dog\",\"breed\":\"Jack Russell Terrier\",\"age\":2,\"imageURL\":\"https://placedog.net/500/400?id=113\"}," +
                "{\"id\":14,\"name\":\"Zoe\",\"type\":\"Dog\",\"breed\":\"Boxer\",\"age\":3,\"imageURL\":\"https://placedog.net/500/400?id=114\"}," +
                "{\"id\":15,\"name\":\"Sophie\",\"type\":\"Dog\",\"breed\":\"Beagle\",\"age\":2,\"imageURL\":\"https://placedog.net/500/400?id=115\"}," +
                "{\"id\":16,\"name\":\"Buddy\",\"type\":\"Dog\",\"breed\":\"Chihuahua\",\"age\":3,\"imageURL\":\"https://placedog.net/500/400?id=116\"}," +
                "{\"id\":17,\"name\":\"Rocky\",\"type\":\"Dog\",\"breed\":\"German Shepherd\",\"age\":5,\"imageURL\":\"https://placedog.net/500/400?id=117\"}," +
                "{\"id\":18,\"name\":\"Buster\",\"type\":\"Dog\",\"breed\":\"Bull Terrier\",\"age\":4,\"imageURL\":\"https://placedog.net/500/400?id=118\"}," +
                "{\"id\":19,\"name\":\"Duke\",\"type\":\"Dog\",\"breed\":\"Doberman Pinscher\",\"age\":6,\"imageURL\":\"https://placedog.net/500/400?id=119\"}," +
                "{\"id\":20,\"name\":\"Harley\",\"type\":\"Dog\",\"breed\":\"Rottweiler\",\"age\":5,\"imageURL\":\"https://placedog.net/500/400?id=120\"}," +
                "{\"id\":21,\"name\":\"Lilly\",\"type\":\"Dog\",\"breed\":\"Yorkshire Terrier\",\"age\":2,\"imageURL\":\"https://placedog.net/500/400?id=121\"}," +
                "{\"id\":22,\"name\":\"Maggie\",\"type\":\"Dog\",\"breed\":\"English Bulldog\",\"age\":4,\"imageURL\":\"https://placedog.net/500/400?id=122\"}," +
                "{\"id\":23,\"name\":\"Jackie\",\"type\":\"Dog\",\"breed\":\"Shih Tzu\",\"age\":3,\"imageURL\":\"https://placedog.net/500/400?id=123\"}," +
                "{\"id\":24,\"name\":\"Rosie\",\"type\":\"Dog\",\"breed\":\"Poodle\",\"age\":1,\"imageURL\":\"https://placedog.net/500/400?id=124\"}," +
                "{\"id\":25,\"name\":\"Samson\",\"type\":\"Dog\",\"breed\":\"Great Dane\",\"age\":6,\"imageURL\":\"https://placedog.net/500/400?id=125\"}," +
                "{\"id\":26,\"name\":\"Rex\",\"type\":\"Dog\",\"breed\":\"Pit Bull\",\"age\":4,\"imageURL\":\"https://placedog.net/500/400?id=126\"}," +
                "{\"id\":27,\"name\":\"Riley\",\"type\":\"Dog\",\"breed\":\"Doberman\",\"age\":3,\"imageURL\":\"https://placedog.net/500/400?id=127\"}," +
                "{\"id\":28,\"name\":\"Lola\",\"type\":\"Dog\",\"breed\":\"French Bulldog\",\"age\":2,\"imageURL\":\"https://placedog.net/500/400?id=128\"}," +
                "{\"id\":29,\"name\":\"Bear\",\"type\":\"Dog\",\"breed\":\"Saint Bernard\",\"age\":4,\"imageURL\":\"https://placedog.net/500/400?id=129\"}," +
                "{\"id\":30,\"name\":\"Finn\",\"type\":\"Dog\",\"breed\":\"Australian Shepherd\",\"age\":2,\"imageURL\":\"https://placedog.net/500/400?id=130\"}," +
                "{\"id\":31,\"name\":\"Chester\",\"type\":\"Dog\",\"breed\":\"Basset Hound\",\"age\":3,\"imageURL\":\"https://placedog.net/500/400?id=131\"}," +
                "{\"id\":32,\"name\":\"Ace\",\"type\":\"Dog\",\"breed\":\"Boxer\",\"age\":4,\"imageURL\":\"https://placedog.net/500/400?id=132\"}," +
                "{\"id\":33,\"name\":\"Koda\",\"type\":\"Dog\",\"breed\":\"Husky\",\"age\":5,\"imageURL\":\"https://placedog.net/500/400?id=133\"}," +
                "{\"id\":34,\"name\":\"Sadie\",\"type\":\"Dog\",\"breed\":\"Beagle\",\"age\":2,\"imageURL\":\"https://placedog.net/500/400?id=134\"}," +
                "{\"id\":35,\"name\":\"Charlie\",\"type\":\"Dog\",\"breed\":\"Labrador Retriever\",\"age\":3,\"imageURL\":\"https://placedog.net/500/400?id=135\"}," +
                "{\"id\":36,\"name\":\"Maggie\",\"type\":\"Dog\",\"breed\":\"Collie\",\"age\":4,\"imageURL\":\"https://placedog.net/500/400?id=136\"}," +
                "{\"id\":37,\"name\":\"Gizmo\",\"type\":\"Dog\",\"breed\":\"Shih Tzu\",\"age\":2,\"imageURL\":\"https://placedog.net/500/400?id=137\"}," +
                "{\"id\":38,\"name\":\"Teddy\",\"type\":\"Dog\",\"breed\":\"Cocker Spaniel\",\"age\":3,\"imageURL\":\"https://placedog.net/500/400?id=138\"}," +
                "{\"id\":39,\"name\":\"Toby\",\"type\":\"Dog\",\"breed\":\"Beagle\",\"age\":4,\"imageURL\":\"https://placedog.net/500/400?id=139\"}," +
                "{\"id\":40,\"name\":\"Fido\",\"type\":\"Dog\",\"breed\":\"Yorkshire Terrier\",\"age\":5,\"imageURL\":\"https://placedog.net/500/400?id=140\"}," +
                "{\"id\":41,\"name\":\"Rover\",\"type\":\"Dog\",\"breed\":\"Dachshund\",\"age\":6,\"imageURL\":\"https://placedog.net/500/400?id=141\"}," +
                "{\"id\":42,\"name\":\"Sasha\",\"type\":\"Dog\",\"breed\":\"Boxer\",\"age\":3,\"imageURL\":\"https://placedog.net/500/400?id=142\"}," +
                "{\"id\":43,\"name\":\"Zara\",\"type\":\"Dog\",\"breed\":\"Pitbull\",\"age\":2,\"imageURL\":\"https://placedog.net/500/400?id=143\"}," +
                "{\"id\":44,\"name\":\"Penny\",\"type\":\"Dog\",\"breed\":\"English Setter\",\"age\":4,\"imageURL\":\"https://placedog.net/500/400?id=144\"}," +
                "{\"id\":45,\"name\":\"Trixie\",\"type\":\"Dog\",\"breed\":\"Miniature Schnauzer\",\"age\":1,\"imageURL\":\"https://placedog.net/500/400?id=145\"}," +
                "{\"id\":46,\"name\":\"Spike\",\"type\":\"Dog\",\"breed\":\"German Shepherd\",\"age\":3,\"imageURL\":\"https://placedog.net/500/400?id=146\"}," +
                "{\"id\":47,\"name\":\"Leo\",\"type\":\"Dog\",\"breed\":\"Bulldog\",\"age\":4,\"imageURL\":\"https://placedog.net/500/400?id=147\"}," +
                "{\"id\":48,\"name\":\"Benny\",\"type\":\"Dog\",\"breed\":\"Cocker Spaniel\",\"age\":2,\"imageURL\":\"https://placedog.net/500/400?id=148\"}," +
                "{\"id\":49,\"name\":\"Nina\",\"type\":\"Dog\",\"breed\":\"Rottweiler\",\"age\":6,\"imageURL\":\"https://placedog.net/500/400?id=149\"}," +
                "{\"id\":50,\"name\":\"Milo\",\"type\":\"Dog\",\"breed\":\"Doberman\",\"age\":5,\"imageURL\":\"https://placedog.net/500/400?id=150\"}," +
                "{\"id\":51,\"name\":\"Baxter\",\"type\":\"Dog\",\"breed\":\"Maltese\",\"age\":3,\"imageURL\":\"https://placedog.net/500/400?id=151\"}," +
                "{\"id\":52,\"name\":\"Ella\",\"type\":\"Dog\",\"breed\":\"Chihuahua\",\"age\":2,\"imageURL\":\"https://placedog.net/500/400?id=152\"}," +
                "{\"id\":53,\"name\":\"Sophie\",\"type\":\"Dog\",\"breed\":\"Rottweiler\",\"age\":4,\"imageURL\":\"https://placedog.net/500/400?id=153\"}," +
                "{\"id\":54,\"name\":\"Gracie\",\"type\":\"Dog\",\"breed\":\"Bichon Frise\",\"age\":3,\"imageURL\":\"https://placedog.net/500/400?id=154\"}," +
                "{\"id\":55,\"name\":\"Coco\",\"type\":\"Dog\",\"breed\":\"Labrador\",\"age\":4,\"imageURL\":\"https://placedog.net/500/400?id=155\"}," +
                "{\"id\":56,\"name\":\"Winston\",\"type\":\"Dog\",\"breed\":\"Poodle\",\"age\":2,\"imageURL\":\"https://placedog.net/500/400?id=156\"}," +
                "{\"id\":57,\"name\":\"Harley\",\"type\":\"Dog\",\"breed\":\"Schnauzer\",\"age\":5,\"imageURL\":\"https://placedog.net/500/400?id=157\"}," +
                "{\"id\":58,\"name\":\"Dolly\",\"type\":\"Dog\",\"breed\":\"Shih Tzu\",\"age\":3,\"imageURL\":\"https://placedog.net/500/400?id=158\"}," +
                "{\"id\":59,\"name\":\"Ollie\",\"type\":\"Dog\",\"breed\":\"Border Collie\",\"age\":2,\"imageURL\":\"https://placedog.net/500/400?id=159\"}," +
                "{\"id\":60,\"name\":\"Riley\",\"type\":\"Dog\",\"breed\":\"German Shepherd\",\"age\":4,\"imageURL\":\"https://placedog.net/500/400?id=160\"}" +
            "]";

            byte[] bytes = json.getBytes("UTF-8");
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Content-Type", "application/json; charset=utf-8");
            exchange.sendResponseHeaders(200, bytes.length);
            try (OutputStream os = exchange.getResponseBody()) { os.write(bytes); }
        }
    }
}
