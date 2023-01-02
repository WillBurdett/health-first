package com.healthfirst.memberservice.repositories;

import com.healthfirst.memberservice.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepo  extends JpaRepository <Member, Long> {
}
