package com.murilo.jumia.dao;


import com.murilo.jumia.model.DTO.PhoneDTO;
import com.murilo.jumia.model.entity.PhoneEntity;
import org.hibernate.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.sqlite.Function;
import org.sqlite.SQLiteConnection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;


@Repository
@Transactional(readOnly = true)
public class PhoneRepositoryImpl implements PhoneRepositoryCustom{

    @PersistenceContext
    EntityManager em;

    @Override
    public Page<PhoneEntity> findByStateAndCountry(String regex, PhoneDTO.State state, String countryCode, Pageable pageable){
        List<PhoneEntity> phones = new ArrayList<>();
        AtomicInteger total = new AtomicInteger();

        em.unwrap(Session.class).doWork(conn -> {
            createFunctionRegexp(conn);
            StringBuilder query = getBaseQueryString(regex, state, countryCode);
            total.set(getQueryCount(conn, query));
            ResultSet rs = getResultSetPaginate(conn, query, pageable);
            while (rs.next()) {
                phones.add(new PhoneEntity(rs));
            }
            rs.close();
        });

        return new PageImpl<>(phones, pageable, total.get());
    }

    private void createFunctionRegexp(Connection conn) throws SQLException {
        Function.create(conn.unwrap(SQLiteConnection.class), "regexp", new Function() {
            protected void xFunc() throws SQLException {
                String expression = value_text(0);
                String value = value_text(1);
                if (value == null)
                    value = "";
                Pattern pattern = Pattern.compile(expression);
                result(pattern.matcher(value).find() ? 1 : 0);
            }
        });
    }

    private StringBuilder getBaseQueryString(String regex, PhoneDTO.State state, String countryCode) {
        String whereFilter = state == PhoneDTO.State.NOT_VALID ? "not" : "";
        StringBuilder query = new StringBuilder();
        query.append(" FROM tbl_phones")
                .append(" where (")
                .append(whereFilter)
                .append(" regexp( '").append(regex).append("', phone_number)");

        if(countryCode != null){
            query.append(" and phone_number like '").append(countryCode).append("%'");
        }
        return query.append(" ) ");
    }

    private int getQueryCount(Connection conn, StringBuilder query) throws SQLException {
        ResultSet rsCount = conn.createStatement().executeQuery("SELECT count(*) as count"+ query.toString());
        rsCount.next();
        int total = rsCount.getInt("count");
        rsCount.close();
        return total;
    }

    private ResultSet getResultSetPaginate(Connection conn, StringBuilder query, Pageable pageable) throws SQLException {
        query.append(" limit ").append(pageable.getPageSize())
             .append(" offSet ").append(pageable.getOffset());
        return conn.createStatement().executeQuery("SELECT *" + query.toString());
    }
}
