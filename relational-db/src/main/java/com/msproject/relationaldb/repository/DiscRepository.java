package com.msproject.relationaldb.repository;

import com.msproject.relationaldb.domain.Disc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DiscRepository extends JpaRepository<Disc, UUID> {

}
