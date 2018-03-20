package com.hank_01.edu.controller;

import com.hank_01.edu.common.response.EduResponse;
import com.hank_01.edu.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "player" )
public class PlayerController {

    @Autowired
    private PlayerService  playerService;
    public EduResponse findPlayerById(@RequestParam(value = "id") Long id){
        return EduResponse.succResponse(playerService.findPlayerById(id));
    }
}
