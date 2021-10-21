package com.example.donatekaro.dao;

import com.example.donatekaro.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ControllerRepository extends JpaRepository<Category,Long> {
}
