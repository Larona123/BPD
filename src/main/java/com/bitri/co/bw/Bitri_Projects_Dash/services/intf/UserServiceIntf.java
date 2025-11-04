package com.bitri.co.bw.Bitri_Projects_Dash.services.intf;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.User;

import java.util.Optional;

public interface UserServiceIntf {
    Optional<User> getById(Long id);
}
