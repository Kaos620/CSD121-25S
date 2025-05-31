package lab2;

import javax.imageio.ImageIO; // reading and writing image files
import javax.swing.*; //toolkit for creating windows, buttons, labels, and other UI elements
import java.awt.*; // used for developing GUI(Graphic User Interfaces) or Window-Based Applications in Java. Sorry I did not understand how this works in practice
import java.io.IOException; //Input/output operations with files and other things (Streams???)
import java.io.InputStream;
import java.net.URI; // Networking operations like URLs, URIs, etc
import java.net.http.HttpClient; // Handles HTTP requests and responses
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AvatarGenerator {

    public static void main(String[] args) {

        try {
            var avatarStream = AvatarGenerator.getRandomAvatarStream(); /*This is a Class Method that class the method to return a random Avatar*/
            AvatarGenerator.showAvatar(avatarStream); /*This is a Class Method to return the Avatar selected and show it*/
        } catch (IOException | InterruptedException e) {
            e.printStackTrace(); /*This is an Instance Method*/
        }

    }

    public static InputStream getRandomAvatarStream() throws IOException, InterruptedException {
        // Pick a random style
        String[] styles = { "adventurer", "adventurer-neutral", "avataaars", "big-ears", "big-ears-neutral", "big-smile", "bottts", "croodles", "croodles-neutral", "fun-emoji", "icons", "identicon", "initials", "lorelei", "micah", "miniavs", "open-peeps", "personas", "pixel-art", "pixel-art-neutral" };
        var style = styles[(int)(Math.random() * styles.length)]; /* .random() is a Class Method that returns a random number between 0 and 1*/ /* .length is a Instance Variable */

        // Generate a random seed
        var seed = (int)(Math.random() * 10000); //returns a number between 0 and 10000

        // Create an HTTP request for a random avatar
        var uri = URI.create("https://api.dicebear.com/9.x/%s/png?seed=%d".formatted(style, seed)); /* .create() is a Class Method that creates a URI from the provided url */ /* .formatted() is an Instance Method. I could not understand how it works */
        var request = HttpRequest.newBuilder(uri).build(); /* .newBuilder() is a Class Method that initializes a new builder to the given URI*/ /* .build() is an Instance Method that builds the objects resulted from the HttpRequest*/

        // Send the request
        try (var client = HttpClient.newHttpClient()) { /* .newHttpClient() is a Class Method that returns a new HttpClient*/
            var response = client.send(request, HttpResponse.BodyHandlers.ofInputStream()); /* .send() is an Instance Method that sends the request and waits for the response*/
            return response.body(); /* .body() is an Instance Method that generates the body for the returned response*/
        }
    }

    public static void showAvatar(InputStream imageStream)/*The arhument is a reference*/ {
            JFrame frame = new JFrame("PNG Viewer"); // Constructor call to a new JFrame, used to display the avatar inside
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);/* .setDefaultCloseOperation() is an Instance Method*/ /* .EXIT_ON_CLOSE is a Class Variable*/
            frame.setResizable(false); /* .setResizable() is an Instance Method*/
            frame.setSize(200, 200); /* .setSize() is an Instance Method*/
            frame.getContentPane().setBackground(Color.BLACK); /* .getContentPane() is an Instance Method*/ /* .BLACK is a Class Variable*/

            try {
                // Load the PNG image
                Image image = ImageIO.read(imageStream); /* .read() is a Class Method*/

                // Create a JLabel to display the image
                JLabel imageLabel = new JLabel(new ImageIcon(image)); //new JLabel gave me some hard time, could you explain later? ImageIcon too
                frame.add(imageLabel, BorderLayout.CENTER); /* .add() is an Instance Method* that is used to add the JLabel to the center of the frame layout./ /* .CENTER is a Class Variable*/

            } catch (IOException e) {
                e.printStackTrace(); /* .printStackTrace() is an Instance Method*/
            }

            frame.setVisible(true); /* .setVisible() is a Instance Method*/
    }
}

/*
LIST OF VARIABLE NAMES: (Sorry its getting quite hard to fill everything in each variable)

avatarStream (InputStream) is reference

style (String) is reference

seed (int) is primitive

uri (URI) is reference

request (HttpRequest) is reference

client (HttpClient) is reference

response (HttpResponse<InputStream>) is reference

frame (JFrame) is reference

image (Image) is reference

imageLabel (JLabel) is reference

 */
