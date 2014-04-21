package com.goparty.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.goparty.data.model.MomentComment;

public interface IMomentCommentRepository extends JpaRepository<MomentComment, String>{

}
