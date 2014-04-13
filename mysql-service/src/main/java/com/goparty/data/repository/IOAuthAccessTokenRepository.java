package com.goparty.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.goparty.data.model.OAuthAccessToken;

public interface IOAuthAccessTokenRepository extends JpaRepository<OAuthAccessToken, String> {

}

