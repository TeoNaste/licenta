package service;

import converters.MessageConverter;
import model.Message;
import model.MessageStatus;
import modelDTO.MessageReceivedDTO;
import modelDTO.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.MessageRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private MessageConverter messageConverter;

    public List<MessageDTO> getMessagesFroUser(int idUser){
        List<Message> messages = messageRepository.getMessagesForUser(idUser);
        List<MessageDTO> dtos = new ArrayList<>();
        messages.forEach(message -> {
            dtos.add(messageConverter.convertToDTO(message));
        });
        return dtos;
    }

    public void saveMessage(MessageDTO message){
        Message newMessage = messageConverter.convertFromDTO(message);
        newMessage.setDate(new Date(Calendar.getInstance().getTime().getTime()));
        newMessage.setStatus(MessageStatus.SUCCESFUL);

        messageRepository.save(newMessage);
    }

    public MessageDTO getMessage(int id){
        Message message = messageRepository.findOne(id);
        return messageConverter.convertToDTO(message);
    }

    public List<MessageDTO> getMessagesFromUser(int idUser){
        List<Message> messages = messageRepository.getMessagesByUser(idUser);
        List<MessageDTO> dtos = new ArrayList<>();
        messages.forEach(message -> {
            dtos.add(messageConverter.convertToDTO(message));
        });
        return dtos;
    }
}
