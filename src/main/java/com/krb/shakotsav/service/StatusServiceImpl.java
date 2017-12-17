package com.krb.shakotsav.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krb.shakotsav.repository.StatusRepository;

@Service("StatusService")
public class StatusServiceImpl implements StatusService {

	@Autowired
	private StatusRepository statusRepository;
}
