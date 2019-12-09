package api;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

public class Reader {

    public static void searchDir(String path) throws IOException {
        try (Stream<Path> filePathStream = Files.walk(Paths.get(path))) {
            filePathStream.forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    try {
                        readData(filePath.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public static void readData(String path) throws IOException {

        byte[] jsonData = Files.readAllBytes(Paths.get(path));
        ObjectMapper objectMapper = new ObjectMapper();
        Conversation conversation = objectMapper.readValue(jsonData, Conversation.class);
//        groupByDate(conversation);

        for (Message msg : conversation.getMessages()) {
            System.out.println(msg.getTimestamp_ms().toLocalDateTime().toLocalDate()+ ":   " + msg.getSender_name() + ":   " + msg.getContent());
        }
    }

//    public static void groupByDate(Conversation conv){
//        HashMap<LocalDate,Message> countMap =  conv.getMessages().stream().collect(groupingBy(conv.getMessages().::/*?*/));
//
//    }

    public static String convertToUTF(String text) throws UnsupportedEncodingException {

        byte[] charset = text.getBytes(StandardCharsets.UTF_8);
        return new String(charset, "UTF-8");
    }

}
