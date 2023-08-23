package com.laboratory.patient.domain;


import org.springframework.data.repository.CrudRepository;

interface PatientRepository extends CrudRepository<Patient, Long> {

}
