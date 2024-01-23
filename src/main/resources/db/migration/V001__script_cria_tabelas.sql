
create table vendas.cliente (
    cd_cliente int auto_increment,
    nm_cliente varchar(60) not null,
    ds_email varchar(50),
    primary key (cd_cliente)
);

create table vendas.produto (
    cd_produto int auto_increment,
    ds_produto varchar(30) not null,
    vl_produto decimal(9,2),
    primary key (cd_produto)
);

create table vendas.pedido (
    cd_pedido int auto_increment,
    cd_cliente int not null,
    st_pedido varchar(1),
    ts_pedido timestamp,
    primary key (cd_pedido),
    foreign key (cd_cliente) references cliente(cd_cliente)
);

create table vendas.pedidoitem (
    cd_pedido int not null,
    cd_produto int not null,
    qt_produto int not null,
    constraint pk_pedidoitem primary key (cd_pedido, cd_produto),
    foreign key (cd_pedido) references pedido(cd_pedido),
    foreign key (cd_produto) references produto(cd_produto)
);