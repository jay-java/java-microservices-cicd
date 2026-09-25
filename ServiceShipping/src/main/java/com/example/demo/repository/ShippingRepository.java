package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Shipping;

public interface ShippingRepository extends JpaRepository<Shipping, Long> {

}
