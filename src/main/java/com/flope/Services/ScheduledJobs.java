/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flope.Services;

import com.flope.entities.Job;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Florian
 */
@Local
public interface ScheduledJobs {
    
    List<Job> getListofJobs();
    
}
