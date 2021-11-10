/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.service.impl;

import com.cloudinary.Cloudinary;
import com.tt.pojos.Status;
import com.tt.service.StatusService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tt.reponsitory.StatusRepository;

/**
 *
 * @author DAVADO
 */
@Service
public class StatusServiceImpl implements StatusService {

    @Override
    public boolean add(Status status, int i) {
        return statusReponsitory.add(status, i);
    }

    @Autowired
    private Cloudinary Cloudinary;
    @Autowired
    private StatusRepository statusReponsitory;

    @Override
    public List<Status> getStatus() {
        return statusReponsitory.getStatus();
    }

    @Override
    public List<Status> getStatusByIduser(int i) {
        return statusReponsitory.getStatusByIduser(i);
    }

    @Override
    public List<Status> getStatusByIdStatus(int id) {
        return statusReponsitory.getStatusByIdStatus(id);
    }

    @Override
    public List<Status> getStatus(int page) {
        return statusReponsitory.getStatus(page);
    }

    @Override
    public boolean deletestt(int id) {
        return statusReponsitory.deletestt(id);
    }
    @Override
    public boolean update(int i, String string, String string1) {
        return statusReponsitory.update(i, string, string1);
    }

}
