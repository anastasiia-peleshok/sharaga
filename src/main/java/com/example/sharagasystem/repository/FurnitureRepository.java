package com.example.sharagasystem.repository;

import com.example.sharagasystem.model.Furniture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FurnitureRepository extends JpaRepository<Furniture, UUID> {

}
