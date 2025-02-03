package com.matchmaker.user_mgt.service;

import com.matchmaker.user_mgt.model.dto.AuthenticationRequest;
import com.matchmaker.user_mgt.model.dto.AuthenticationResponse;

public interface AuthenticationService {


    AuthenticationResponse authenticate(AuthenticationRequest request);
}
