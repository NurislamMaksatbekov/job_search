package com.example.hw49.dao;

import com.example.hw49.entity.Image;
import com.example.hw49.entity.Usr;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDao{
    private final JdbcTemplate jdbcTemplate;

    public Optional<Usr> findUserByName(String name) {
        String sql = "select * from users where name = ?";
        return Optional.ofNullable(DataAccessUtils.singleResult(
                jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Usr.class), name)
        ));
    }

    public Optional<Usr> findUserByPhoneNumber(String number) {
        String sql = "select * from users where phone_number = ?";
        return Optional.ofNullable(DataAccessUtils.singleResult(
                jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Usr.class), number)
        ));
    }


    public boolean checkUser(String email) {
        String sql = "select case when exists(" +
                "select * from users where email = ?) " +
                "then true " +
                "else false " +
                "end ";
        return jdbcTemplate.queryForObject(sql, Boolean.class, email);
    }

    public List<Usr> getUserByResponds(Long vacancyId) {
        String sql = "select * from users u " +
                "inner join responds r on u.email = r.RESPONDER_EMAIL " +
                "where RESPONDED_VACANCY_ID = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Usr.class), vacancyId);
    }

    public Usr findApplicant(String email) {
        String sql = "select * from USERS u\n" +
                "where ACCOUNT_TYPE = 'APPLICANT'\n" +
                "and EMAIL = ?";
        Optional<Usr> mayBeUser = Optional.ofNullable(DataAccessUtils.singleResult(
                jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Usr.class), email)
        ));
        return mayBeUser.get();
    }

    public Usr userProfile(String email) {
        String sql = "select email, name, SURNAME, ACCOUNT_TYPE, PHOTO" +
                " from USERS where EMAIL = ?;";
        Optional<Usr> mayBeUser = Optional.ofNullable(DataAccessUtils.singleResult(
                jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Usr.class), email)
        ));
        return mayBeUser.get();
    }

    public Usr findEmployer(String email) {
        String sql = "select * from USERS u\n" +
                "where ACCOUNT_TYPE = 'EMPLOYER'\n" +
                "and EMAIL = ?";
        Optional<Usr> mayBeUser = Optional.ofNullable(DataAccessUtils.singleResult(
                jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Usr.class), email)
        ));
        return mayBeUser.get();
    }

    public void save(Object obj) {
        jdbcTemplate.update(con -> {
        Usr u = (Usr) obj;
        PreparedStatement ps = con.prepareStatement(
                "insert into users(EMAIL, NAME, SURNAME, USERNAME, PASSWORD, PHONE_NUMBER, ACCOUNT_TYPE, ENABLED) " +
                        "values (?, ?, ?, ?, ?, ?, ?,?)"
        );
            ps.setString(1, u.getEmail());
            ps.setString(2, u.getName());
            ps.setString(3, u.getSurname());
            ps.setString(4, u.getUsername());
            ps.setString(5, u.getPassword());
            ps.setString(6, u.getPhoneNumber());
            ps.setString(7, u.getAccountType());
            ps.setBoolean(8,true);
            return ps;
        });
    }
    public void saveImage(Image image) {
        String sql = "update users set photo = ? where email = ?";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setBytes(1, image.getPhoto().getBytes());
            ps.setString(2, image.getEmail());
            return ps;
        });
    }
}
