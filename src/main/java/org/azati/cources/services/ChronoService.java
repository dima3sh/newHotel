package org.azati.cources.services;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.TimerTask;

@Service
public class ChronoService extends TimerTask {

    @Autowired
    GuestService guestService;

    @Override
    public void run() {
        System.out.println("hello");
        guestService.updateInvoice();
    }
}
