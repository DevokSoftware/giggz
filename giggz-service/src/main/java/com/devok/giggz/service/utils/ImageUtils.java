package com.devok.giggz.service.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import javax.imageio.ImageIO;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ImageUtils {
    public static String fetchThumbnailAsBase64(String imageUrl, boolean requestHighQuality) {
        try {
            String thumbnailUrl = fetchThumbnailUrl(imageUrl);
            if (thumbnailUrl == null) {
                return null;
            }
            if (requestHighQuality) {
                thumbnailUrl = thumbnailUrl.replace("hqdefault.jpg", "maxresdefault.jpg");
            }
            // Fetch the image from the URL
            URL url = new URL(thumbnailUrl);

            BufferedImage image = ImageIO.read(url);

            // Convert the image to Base64
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", outputStream);
            byte[] imageBytes = outputStream.toByteArray();

            return Base64.getEncoder().encodeToString(imageBytes);
        } catch (Exception e) {
            if (requestHighQuality) {
                return fetchThumbnailAsBase64(imageUrl, false);
            }
            return null;
        }
    }

    private static String fetchThumbnailUrl(String imageUrl) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://www.youtube.com/oembed?url=" + imageUrl))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                // Parse JSON using Jackson
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootNode = mapper.readTree(response.body());

                // Extract data (modify keys based on your JSON structure)
                return rootNode.get("thumbnail_url").asText();
            } else {
//                throw new RuntimeException("Failed to fetch JSON: HTTP status code " + response.statusCode());
                return null;
            }
        } catch (Exception e) {
//            throw new RuntimeException("Failed to fetch or convert image", e);
            return null;
        }
    }
}
