package DiamondShop.Service.User;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DiamondShop.Dao.BillDao;
import DiamondShop.Dto.CartDto;
import DiamondShop.Entity.Bill;
import DiamondShop.Entity.BillDetail;

@Service
public class BillServiceImpl implements IBillService {
	@Autowired
	private BillDao billDao;
	
	@Override
	public int AddBill(Bill bill) {
		return billDao.AddBill(bill);
	}

	@Override
	public void AddBillDetail(HashMap<Integer, CartDto> carts) {
		int idBill = billDao.GetIDLastBill();
		for (Map.Entry<Integer, CartDto> itemCart : carts.entrySet()) {
			BillDetail billDetail = new BillDetail();
			billDetail.setId_bill(idBill);
			billDetail.setId_product(itemCart.getValue().getProduct().getId_product());
			billDetail.setQuantity(itemCart.getValue().getQuantity());
			billDetail.setTotal(itemCart.getValue().getTotalPrice());
			billDao.AddBillDetail(billDetail);
		}	
	}
}