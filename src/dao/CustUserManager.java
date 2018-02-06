package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.CustUserBean;

public class CustUserManager {

	public CustUserManager() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getConnection() throws SQLException {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String pass = "hr";
		Connection conn = DriverManager.getConnection(url, user, pass);

		return conn;
	}

	public void close(Connection conn, PreparedStatement psmt, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<CustUserBean> getCustUserList() {

		String sql = " select id, name, address from custuser order by id desc ";
		List<CustUserBean> list = new ArrayList<>();
		
		try {
			Connection conn = getConnection();
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();

			while (rs.next()) {
				CustUserBean bean = new CustUserBean();

				bean.setAddress(rs.getString("address"));
				bean.setId(rs.getString("id"));
				bean.setName(rs.getString("name"));
				
				list.add(bean);
			}
			
			close(conn, ptmt, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return list;
	}
	
	public boolean deleteCustuser(String[] ids) {
		String sql = " delete from custuser where id = ? ";
		
		Connection conn = null;
		PreparedStatement ptmt = null;
		
		System.out.println("deleteCustuser sql : "+sql);
		
		int count[] = new int[ids.length];		
		
		try {
			conn = getConnection();
			
			// 실시간에서 문제가 발생할 수 있기 때문에 
			// 자동으로 커밋하는 기능을 잠시 꺼준다.
			conn.setAutoCommit(false);
			
			ptmt = conn.prepareStatement(sql);
		
			// ptmt addBatch 배치를 돌린다.
			// 데이터를 한번에 여러개 처리해야하는 경우에 사용한다.
			for(int i=0; i<ids.length; i++) {
				// ? 에 아이디 값을 넣어준다.
				System.out.println("ids["+i+"] : "+ids[i] );
				ptmt.setString(1, ids[i].toString());
				ptmt.addBatch();
			}
			
			// 배치를 돌린 값을 배열로 반환한다.
			count = ptmt.executeBatch();
			
			System.out.println("end of batch");
			// auto commit 을 꺼놨기 때문에 commit 을 수동으로 해준다.
			conn.commit();
			System.out.println("end of commit");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				// db 처리를 실패 했을 경우 롤백한다.
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				// auto commit을 다시 켜준다.
				conn.setAutoCommit(true);
				System.out.println("end of auto commit");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			close(conn, ptmt, null);
		}
		
		boolean isSuccess = true;
		
		for(int i=0; i<count.length; i++) {
			System.out.println("count["+i+"] : "+count[i]);
			if(count[i]!=-2) {
				// -2 일때가 정상 처리이다.
				isSuccess = false;
				break;
			}
		}
		
		return isSuccess;
	}
	
	public boolean addCustUser(CustUserBean user) {
		String sql = " insert into custuser values ('"+user.getId()+"', '"+user.getName()+"', '"+user.getAddress()+"') ";
		
		Connection conn = null;
		PreparedStatement ptmt = null;
		
		System.out.println("addCustUser sql : "+sql);
		
		int count = 0;	
		
		try {
			conn = getConnection();
			ptmt = conn.prepareStatement(sql);
			count = ptmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ptmt, null);
		}
		
		return (count > 0)? true : false;
	}
	
	public boolean updateCustUser(CustUserBean user) {
		String sql = " update custuser set name='"+user.getName()+"', address='"+user.getAddress()+"' where id='"+user.getId()+"' ";
		
		Connection conn = null;
		PreparedStatement ptmt = null;
		
		System.out.println("updateCustUser sql : "+sql);
		
		int count = 0;	
		
		try {
			conn = getConnection();
			ptmt = conn.prepareStatement(sql);
			count = ptmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ptmt, null);
		}
		
		return (count > 0)? true : false;
	}
	
	public CustUserBean getUserById(String id) {
		
		
		String sql = " select * from custuser where id='"+id+"'";
		CustUserBean user = new CustUserBean();
		
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			ptmt = conn.prepareStatement(sql);
			rs = ptmt.executeQuery();
			
			if(rs.next()) {
				user.setId(rs.getString("id"));
				user.setAddress(rs.getString("address"));
				user.setName(rs.getString("name"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("user: "+user);
		return user;
	}

}
