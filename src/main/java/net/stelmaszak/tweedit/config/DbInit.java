package net.stelmaszak.tweedit.config;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DbInit {

    private String sqlFile = "tweeditinit.sql";
    @Autowired
    private DataSource dataSource;

    public void runSql() throws IOException, SQLException {
        List<String> sqls = Resources.readLines(Resources.getResource(sqlFile), Charsets.UTF_8);
        try (Connection conn = dataSource.getConnection()) {
            for (String sql : sqls) {
                if (!sql.trim().isEmpty()) {
                    conn.createStatement().executeUpdate(sql);
                }
            }
        }
    }
}
