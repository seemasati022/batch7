package com.tejait.batch7.detoDesignPattern;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDetailsRepository extends JpaRepository<PersonDetails,Integer> {
}
