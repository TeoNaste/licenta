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

    @CrossOrigin
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public void saveMessage(@RequestBody MessageDTO message){
        messageService.saveMessage(message);
    }


}
