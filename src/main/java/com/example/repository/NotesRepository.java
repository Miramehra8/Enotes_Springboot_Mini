package com.example.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Notes;
import com.example.entity.User;

public interface NotesRepository extends JpaRepository<Notes, Integer> {

public Page<Notes> findByUser(User user, Pageable pageable);

}
