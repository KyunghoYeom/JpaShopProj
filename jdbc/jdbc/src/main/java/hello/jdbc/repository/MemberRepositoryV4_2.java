package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.ex.MyDbException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;

import javax.sql.DataSource;
import java.sql.*;
import java.util.NoSuchElementException;

/**
* SQLExceptionTranslate 추가
 */

@Slf4j
public class MemberRepositoryV4_2 implements MemberRepository{
    private final DataSource dataSource;
    private final SQLExceptionTranslator exTranslator;

    public MemberRepositoryV4_2(DataSource dataSource) {

        this.dataSource = dataSource;
        this.exTranslator = new SQLErrorCodeSQLExceptionTranslator(dataSource);
    }

    @Override
    public Member save(Member member){
        String sql = "insert into member(member_id, money) values (?,?)";

        Connection con = null;
        PreparedStatement pstmt = null;
        try{
            con = getConnection();
            pstmt=  con.prepareStatement(sql);
            pstmt.setString(1,member.getMemberId());
            pstmt.setInt(2,member.getMoney());
            pstmt.executeUpdate();//실제 DB에 저장되게 된다
            return member;
        } catch (SQLException e) {
           throw exTranslator.translate("save",sql,e);
        }finally{

            close(con, pstmt, null);
        }


    }
    @Override
    public Member findById(String memberId){
        String sql = "select * from member where member_id= ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, memberId);
            rs = pstmt.executeQuery();
            if(rs.next()){
                Member member = new Member();
                member.setMemberId(rs.getString("member_id"));
                member.setMoney(rs.getInt("money"));
                return member;
            }
            else{
                throw new NoSuchElementException("member not found memberId = " + memberId);
            }


        }catch (SQLException e) {
            throw exTranslator.translate("findById",sql,e);
        }finally {
            close(con, pstmt, rs);
        }

    }



    @Override
    public void update(String memberId, int money){
        String sql = "update member set money=? where member_id=?";

        Connection con = null;
        PreparedStatement pstmt = null;
        try{
            con = getConnection();
            pstmt=  con.prepareStatement(sql);
            pstmt.setInt(1,money);
            pstmt.setString(2,memberId);
            int resultSize = pstmt.executeUpdate();  //실제 DB에 저장되게 된다
            //쿼리 실행 후 영향받은 row수 반환(0또는 1반환하게됨)
            log.info("resultSize = {}", resultSize);

        } catch (SQLException e) {
            throw exTranslator.translate("update",sql,e);
        }finally{

            close(con, pstmt, null);
        }
    }

    @Override
    public void delete(String memberId){
        String sql = "delete from member where member_id=?";

        Connection con = null;
        PreparedStatement pstmt = null;
        try{
            con = getConnection();
            pstmt=  con.prepareStatement(sql);
            pstmt.setString(1,memberId);
            int resultSize = pstmt.executeUpdate();  //실제 DB에 저장되게 된다
            //쿼리 실행 후 영향받은 row수 반환(0또는 1반환하게됨)
            log.info("resultSize = {}", resultSize);

        } catch (SQLException e) {
            throw exTranslator.translate("delete",sql,e);
        }finally{

            close(con, pstmt, null);
        }


    }

    private void close(Connection con, Statement stat, ResultSet rs){


        JdbcUtils.closeResultSet(rs);
        JdbcUtils.closeStatement(stat);
        //JdbcUtils.closeConnection(con);
        //주의 트랜젝션 동기화를 사용하려면 DataSourceUtils 사용해야한다
        DataSourceUtils.releaseConnection(con, dataSource);

    }

    private Connection getConnection() throws SQLException {
        return DataSourceUtils.getConnection(dataSource);
        //트랜젝션 동기화를 사용하려면 DataSourceUtils 사용해야한다.


    }
}
