package com.assj5.thien.assj5.repository;

import com.assj5.thien.assj5.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill,Long> {
}
