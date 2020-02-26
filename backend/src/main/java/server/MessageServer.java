package server;

import modelDTO.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.MessageService;

@RestController
@CrossOrigin
@RequestMapping("/api/messages")
public class MessageServer {

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/{idUser}",method = RequestMethod.GET)
    @ResponseBody
    public Iterable<MessageDTO> getMessagesForUser(@PathVariable Integer idUser){
        return messageService.getMessagesFroUser(idUser);
    }

    @RequestMapping(value = "/0/{idUser}",method = RequestMethod.GET)
    @ResponseBody
    public Iterable<MessageDTO> getMessagesFromUser(@PathVariable Integer idUser){
        return messageService.getMessagesFromUser(idUser);
    }

    @CrossOrigin
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public void saveMessage(@RequestBody MessageDTO message){
        messageService.saveMessage(message);
    }

    @RequestMapping(value = "/details/{idMessage}", method = RequestMethod.GET)
    @ResponseBody
    public MessageDTO getMessage(@PathVariable Integer idMessage){
        return messageService.getMessage(idMessage);
    }


}
