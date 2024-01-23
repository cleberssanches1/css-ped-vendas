package br.com.sanches.vendas.adapters.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sanches.vendas.adapters.out.repository.entity.PedidoEntity;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {

}
