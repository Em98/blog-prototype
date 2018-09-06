package com.yc.springinaction.Repository;

import com.yc.springinaction.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long>{
}
