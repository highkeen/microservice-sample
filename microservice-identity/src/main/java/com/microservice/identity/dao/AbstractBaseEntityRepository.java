package com.microservice.identity.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.microservice.identity.entity.AbstractBaseEntity;


@NoRepositoryBean
public interface AbstractBaseEntityRepository<T extends AbstractBaseEntity<ID>,ID>  extends JpaRepository<T, ID>{
	
	Page<T> findByActive(boolean status,Pageable pageable);
	Long countByActive(Boolean status);
	Page<T> findByIdInAndActive(List<ID> ids,boolean status,Pageable pageable);
	

}
