package com.ccanto.unbabel.dataacess;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslationRepository extends CrudRepository<TranslationResponse, String>{
}
