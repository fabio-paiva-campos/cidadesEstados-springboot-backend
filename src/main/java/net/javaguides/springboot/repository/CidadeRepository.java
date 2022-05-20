package net.javaguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.model.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long>{

}
