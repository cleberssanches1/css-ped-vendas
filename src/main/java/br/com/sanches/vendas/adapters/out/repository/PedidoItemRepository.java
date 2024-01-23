package br.com.sanches.vendas.adapters.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sanches.vendas.adapters.out.repository.entity.PedidoItemEntity;
import br.com.sanches.vendas.adapters.out.repository.entity.PedidoItemPK;

@Repository
public interface PedidoItemRepository extends JpaRepository<PedidoItemEntity, PedidoItemPK> {   
 
}