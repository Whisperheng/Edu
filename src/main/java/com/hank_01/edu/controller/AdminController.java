package com.hank_01.edu.controller;

import com.hank_01.edu.common.response.EduResponse;
import com.hank_01.edu.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private PlayerService playerService;

    @RequestMapping(value = "/summary",method = RequestMethod.GET)
    public EduResponse findGameSummary(){
        return EduResponse.succResponse(playerService.findPlayerSummary());
    }
}
