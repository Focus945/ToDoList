package org.example.controller;


import org.example.dto.RecordsContainerDto;
import org.example.entity.Record;
import org.example.entity.RecordStatus;
import org.example.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CommonController {
    private final RecordService recordService;

    @Autowired
    public CommonController(RecordService recordService){
        this.recordService = recordService;
    }

    @RequestMapping("/")
    public String redirectToHomePage(){
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String getMainPage(Model model,@RequestParam(value = "filter", required = false) String filterMode){
        RecordsContainerDto container = recordService.findAll(filterMode);
        model.addAttribute("records", container.getRecords());
        model.addAttribute("numberOfDoneRecords", container.getNumberOfDoneRecords());
        model.addAttribute("numberOfActiveRecords", container.getNumberOfActiveRecords());
        return "main-page";
    }

    @PostMapping("/add-record")
    public String addRecord(@RequestParam("title") String title) {
        recordService.saveRecord(title);
        return "redirect:/home";
    }
    @PostMapping("/make-record-done")
    public String makeRecordDone(@RequestParam("id") int id, @RequestParam(value = "filter", required = false) String filter) {
        recordService.updateRecordStatus(id, RecordStatus.DONE);
        return "redirect:/home" + (filter != null && !filter.isBlank() ? "?filter=" + filter : "");
    }
    @PostMapping("/delete-record")
    public String deleteRecord(@RequestParam("id") int id, @RequestParam(value = "filter", required = false) String filter) {
        recordService.deleteRecord(id);
        return "redirect:/home" + (filter != null && !filter.isBlank() ? "?filter=" + filter : "");
    }
}
