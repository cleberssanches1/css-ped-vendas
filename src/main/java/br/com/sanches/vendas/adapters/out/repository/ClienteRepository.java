package br.com.sanches.vendas.adapters.out.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.sanches.vendas.adapters.out.repository.entity.ClienteEntity;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

	@Query(nativeQuery = true, value = "SELECT c.cd_cliente AS codigoCliente, c.nm_cliente AS nomeCliente, c.ds_email AS email, "
			+ "p.cd_pedido AS codigoPedido, p.st_pedido AS statusPedido, "
			+ "pi.cd_pedido AS codigoPedido, pi.cd_produto AS codigoProduto, pi.qt_produto AS quantidade "
			+ "FROM cliente c " + "LEFT JOIN pedido p ON c.cd_cliente = p.cd_cliente "
			+ "LEFT JOIN pedidoitem pi ON p.cd_pedido = pi.cd_pedido " + " WHERE c.cd_cliente = :codigoCliente")
	List<Object[]> findClientePedidosItens(@Param("codigoCliente") Long codigoCliente);

}
