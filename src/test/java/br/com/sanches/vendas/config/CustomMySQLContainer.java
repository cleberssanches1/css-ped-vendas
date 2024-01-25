package br.com.sanches.vendas.config;

import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

public class CustomMySQLContainer extends MySQLContainer<CustomMySQLContainer> {

    private static final String IMAGE_VERSION = "mysql:8.0-debian";

    public CustomMySQLContainer() {
        super(DockerImageName.parse(IMAGE_VERSION));
        withDatabaseName("vendas");
        withUsername("user");
        withPassword("user");

        addEnv("MYSQL_ROOT_HOST", "%");
        addEnv("MYSQL_ROOT_PASSWORD", "vendas");
    }

    @Override
    public String getJdbcUrl() {
        return super.getJdbcUrl();
    }
}
