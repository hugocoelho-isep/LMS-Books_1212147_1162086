package pt.psoft.g1.psoftg1.suggestedbooks.api;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pt.psoft.g1.psoftg1.suggestedbooks.services.SuggestedBooksService;
import org.springframework.amqp.core.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class SuggestedBookRabbitmqReceiver {

    private final SuggestedBooksService suggestedBooksService;

    @RabbitListener(queues = "#{@autoDeleteQueue_Suggestion_Created.name}")
    public void receiveSuggestedBookCreatedMsg(Message msg) {
        try {
            String jsonReceived = new String(msg.getBody(), StandardCharsets.UTF_8);

            ObjectMapper objectMapper = new ObjectMapper();
            SuggestedBookViewAMQP suggestedBookViewAMQP = objectMapper.readValue(jsonReceived, SuggestedBookViewAMQP.class);

            System.out.println(" [x] Received Suggested Book Created by AMQP: " + msg + ".");
            try {
                suggestedBooksService.create(suggestedBookViewAMQP);
                System.out.println(" [x] New suggested book inserted from AMQP: " + msg + ".");
            } catch (Exception e) {
                System.out.println(" [x] Suggested book already exists. No need to store it.");
            }
        } catch (Exception ex) {
            System.out.println(" [x] Exception receiving suggested book event from AMQP: '" + ex.getMessage() + "'");
        }
    }
}