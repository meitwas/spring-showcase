package com.ssn.spring.db;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@SpringBootApplication
public class Application {

    private Server h2Server;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

    @RequestMapping("/start")
    public String start() throws SQLException {
        if (h2Server == null) {
            h2Server = Server.createTcpServer();
        }
        h2Server.start();
        return "started";
    }

    @RequestMapping("/stop")
    public String stop() throws SQLException {
        if (h2Server != null) {
            h2Server.stop();
        }
        return "stopped";
    }
}
