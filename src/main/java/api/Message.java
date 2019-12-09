package api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.sql.Timestamp;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {
    private String sender_name;
    private Timestamp timestamp_ms;
    private String content;

}
