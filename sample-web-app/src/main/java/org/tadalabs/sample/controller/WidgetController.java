package org.tadalabs.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tadalabs.sample.domain.Widget;
import org.tadalabs.sample.model.WidgetList;
import org.tadalabs.sample.service.WidgetService;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/rest/widgets")
public class WidgetController {

    private final WidgetService widgetService;

    @Autowired
    public WidgetController(WidgetService widgetService) {
        this.widgetService = widgetService;
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<Widget> addWidget(@Valid @RequestBody Widget request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.widgetService.addWidget(request));
    }

    @GetMapping(value = "/", produces = "application/json")
    @ResponseBody
    public ResponseEntity<WidgetList> getAllWidgets() {
        WidgetList widgetList = this.widgetService.findAll();
        if(widgetList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(widgetList, HttpStatus.OK);
    }

    @GetMapping(value = "/{widgetId}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Widget> getWidgetById(@Valid @NotBlank @PathVariable("widgetId") String widgetId) {

        Widget widget = this.widgetService.getWidgetById(widgetId);

        if (widget == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(widget);
        }
    }

}
