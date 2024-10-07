package DiamondShop.Dao;

import org.springframework.stereotype.Repository;

import DiamondShop.Entity.Bill;
import DiamondShop.Entity.BillDetail;

@Repository
public class BillDao extends BaseDao {
	public int AddBill(Bill bill) {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO diamond_shop.bill ");
		sql.append("( ");
		sql.append("    user, phone, display_name, address, quantity, total, note ");
		sql.append(") ");
		sql.append("VALUES ");
		sql.append("( ");
		sql.append("    '"+bill.getUser()+"', ");
		sql.append("    '"+bill.getPhone()+"', ");
		sql.append("    '"+bill.getDisplay_name()+"', ");
		sql.append("    '"+bill.getAddress()+"', ");
		sql.append("    "+bill.getTotal()+", ");
		sql.append("    "+bill.getQuantity()+", ");
		sql.append("    '"+bill.getNote()+"' ");
		sql.append(");");
		int insert = _jdbcTemplate.update(sql.toString());
		return insert;
	}
	
	public int GetIDLastBill() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT MAX(id) FROM diamond_shop.bill;");
		int id = _jdbcTemplate.queryForObject(sql.toString(), new Object[] {}, int.class);
		return id;
	}
	
	public int AddBillDetail(BillDetail billDetail) {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO diamond_shop.billdetail ");
		sql.append("( ");
		sql.append("    id_product, ");
		sql.append("    id_bill, ");
		sql.append("    quantity, ");
		sql.append("    total ");
		sql.append(") ");
		sql.append("VALUES ");
		sql.append("( ");
		sql.append("    "+billDetail.getId_product()+", ");
		sql.append("    "+billDetail.getId_bill()+", ");
		sql.append("    "+billDetail.getQuantity()+", ");
		sql.append("    "+billDetail.getTotal()+" ");
		sql.append(")");
		int insert = _jdbcTemplate.update(sql.toString());
		return insert;
	}
}