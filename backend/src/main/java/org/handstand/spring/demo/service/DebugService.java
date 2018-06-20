package org.handstand.spring.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("debug")
public class DebugService {

    @Value("${app.debug}")
    public boolean DEBUG_STATE;

}