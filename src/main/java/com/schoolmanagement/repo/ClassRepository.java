package com.schoolmanagement.repo;


import com.schoolmanagement.entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {

    Class findByName(String name);
}
