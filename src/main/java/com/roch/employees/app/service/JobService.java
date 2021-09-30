package com.roch.employees.app.service;

import com.roch.employees.app.model.Job;
import com.roch.employees.app.repository.JobProxy;
import lombok.Data;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
@Service
@Data
public class JobService {

    @Autowired
    private JobProxy proxy;

    public Job getJob(@NonNull String name) {
        return proxy.getJob(name);
    }

    public boolean delete(@NonNull Job job) {
        return proxy.delete(job);
    }

    public Job save(@NonNull Job job) {
        if (proxy.getJob(job.getName()) == null)
            return proxy.create(job);
        return proxy.update(job);
    }

    public Iterable<Job> getJobs() {
        return proxy.getJobs();
    }

}
