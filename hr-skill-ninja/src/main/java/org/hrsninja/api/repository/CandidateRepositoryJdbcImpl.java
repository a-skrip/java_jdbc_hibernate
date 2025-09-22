package org.hrsninja.api.repository;

import lombok.RequiredArgsConstructor;
import org.hrsninja.api.model.Candidate;
import org.hrsninja.api.model.CandidateStatus;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class CandidateRepositoryJdbcImpl implements CandidateRepository {

    private final static String URL = "jdbc:postgresql://localhost:5433/postgres";
    private final static String USERNAME = "postgres";
    private final static String PASSWORD = "postgres";

    @Override
    public Candidate save(Candidate candidate) {
        String sql = """
                INSERT INTO candidates (id, fio, age, position, cv_info, status)
                VALUES (?, ?, ?, ?, ?, ?)
                RETURNING id, fio, age, position, cv_info, comment, status
                """;

        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setObject(1, candidate.getId());
            stmt.setString(2, candidate.getFio());
            stmt.setShort(3, candidate.getAge());
            stmt.setString(4, candidate.getPosition());
            stmt.setString(5, candidate.getCvInfo());
            stmt.setString(6, candidate.getStatus().name());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
                throw new SQLException("Failed to insert candidate");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error saving candidate", e);
        }
    }

    @Override
    public Candidate update(Candidate candidate) {
        String sql = """
                UPDATE candidates
                SET fio = ?, age = ?, position = ?, cv_info = ?, status = ?, comment = ?
                WHERE id = ?
                RETURNING id, fio, age, position, cv_info, comment, status
                """;

        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, candidate.getFio());
            stmt.setShort(2, candidate.getAge());
            stmt.setString(3, candidate.getPosition());
            stmt.setString(4, candidate.getCvInfo());
            stmt.setString(5, candidate.getStatus().name());
            stmt.setString(6, candidate.getComment());
            stmt.setObject(7, candidate.getId());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
                throw new SQLException("Candidate not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error updating candidate", e);
        }
    }

    @Override
    public Optional<Candidate> findById(UUID id) {
        String sql = """
                SELECT *
                FROM candidates
                WHERE id = ?
                """;

        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setObject(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding candidate", e);
        }
    }

    @Override
    public List<Candidate> findAll() {
        String sql = """
                SELECT *
                FROM candidates
                ORDER BY fio ASC
                """;

        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            List<Candidate> candidates = new ArrayList<>();
            while (rs.next()) {
                candidates.add(mapRow(rs));
            }
            return candidates;
        } catch (SQLException e) {
            throw new RuntimeException("Error finding all candidates", e);
        }
    }

    @Override
    public List<Candidate> search(String fio, Set<CandidateStatus> statuses, String position) {
        StringBuilder sql = new StringBuilder("""
                SELECT id, fio, age, position, cv_info, comment, status
                FROM candidates
                WHERE 1=1
                """);

        List<Object> params = new ArrayList<>();

        if (fio != null && !fio.isBlank()) {
            sql.append(" AND fio ILIKE ?");
            params.add("%" + fio + "%");
        }

        if (statuses != null && !statuses.isEmpty()) {
            sql.append(" AND status = ANY(?)");
            params.add(statuses.stream().map(Enum::name).toArray(String[]::new));
        }

        if (position != null && !position.isBlank()) {
            sql.append(" AND position ILIKE ?");
            params.add("%" + position + "%");
        }

        sql.append(" ORDER BY fio ASC");

        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = stmt.executeQuery()) {
                List<Candidate> candidates = new ArrayList<>();
                while (rs.next()) {
                    candidates.add(mapRow(rs));
                }
                return candidates;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error searching candidates", e);
        }
    }

    private Candidate mapRow(ResultSet rs) throws SQLException {
        Candidate candidate = new Candidate();
        candidate.setId(rs.getObject("id", UUID.class));
        candidate.setFio(rs.getString("fio"));
        candidate.setAge(rs.getShort("age"));
        candidate.setPosition(rs.getString("position"));
        candidate.setCvInfo(rs.getString("cv_info"));
        candidate.setComment(rs.getString("comment"));
        candidate.setStatus(CandidateStatus.valueOf(rs.getString("status")));
        return candidate;
    }
}
