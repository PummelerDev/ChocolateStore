package com.chocolatestore.mappers;

import com.chocolatestore.domain.Storage;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StorageMapper implements RowMapper<Storage> {
    @Override
    public Storage mapRow(ResultSet rs, int rowNum) throws SQLException {
        Storage storage = new Storage();
        storage.setId(rs.getLong("id"));
        storage.setName(rs.getString("name"));
        storage.setCreated(rs.getTimestamp("created"));
        storage.setChanged(rs.getTimestamp("changed"));
        return storage;
    }
}
