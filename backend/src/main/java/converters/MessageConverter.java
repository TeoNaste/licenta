package converters;

import model.Message;
import model.User;
import modelDTO.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.UserRepository;



@Component
public class MessageConverter {

    @Autowired
    private UserRepository userRepository;

    public MessageDTO convertToDTO(Message message){
        MessageDTO dto = new MessageDTO();
        dto.setId(message.getId());
        dto.setAttachement(message.getAttachement());
        dto.setDate(message.getDate());
        dto.setReceiver(message.getReceiver().getUsername());
        dto.setSender(message.getSender());
        dto.setSubject(message.getSubject());
        dto.setText(message.getText());

        return dto;
    }

    public Message convertFromDTO(MessageDTO messageDTO){
        Message message = new Message();
        message.setAttachement(messageDTO.getAttachement());
        message.setDate(messageDTO.getDate());
        User r = userRepository.getUserByName(messageDTO.getReceiver());
        message.setReceiver(r);
        message.setSender(messageDTO.getSender());
        message.setSubject(messageDTO.getSubject());
        message.setText(messageDTO.getText());

        return message;
    }

}
