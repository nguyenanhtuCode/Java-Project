package DiamondShop.Controller.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import DiamondShop.Dto.PaginatesDto;
import DiamondShop.Service.User.CategoryServiceImpl;
import DiamondShop.Service.User.PaginatesServiceImpl;

@Controller
public class CategoryController extends BaseController {
	@Autowired
	private CategoryServiceImpl categoryServiceImpl;

	@Autowired
	private PaginatesServiceImpl paginatesServiceImpl;

	private int totalProductsPage = 9;

	@RequestMapping(value = "/san-pham/{id}")
	public ModelAndView Product(@PathVariable String id) {
		_mvShare.setViewName("user/products/category");
		int totalData = categoryServiceImpl.GetAllProductsByID(Integer.parseInt(id)).size();
		PaginatesDto paginatesInfo = paginatesServiceImpl.GetInfoPaginates(totalData, totalProductsPage, 1);
		_mvShare.addObject("idCategory", id);
		_mvShare.addObject("paginatesInfo", paginatesInfo);
		_mvShare.addObject("productsPaginate", categoryServiceImpl.GetDataProductsPaginate(Integer.parseInt(id),
				paginatesInfo.getStart(), totalProductsPage));
		return _mvShare;
	}

	@RequestMapping(value = "/san-pham/{id}/{currentPage}")
	public ModelAndView Product(@PathVariable String id, @PathVariable String currentPage) {
		int totalProductsPage = 9;
		_mvShare.setViewName("user/products/category");
		int totalData = categoryServiceImpl.GetAllProductsByID(Integer.parseInt(id)).size();
		PaginatesDto paginatesInfo = paginatesServiceImpl.GetInfoPaginates(totalData, totalProductsPage,
				Integer.parseInt(currentPage));
		_mvShare.addObject("idCategory", id);
		_mvShare.addObject("paginatesInfo", paginatesInfo);
		_mvShare.addObject("productsPaginate", categoryServiceImpl.GetDataProductsPaginate(Integer.parseInt(id),
				paginatesInfo.getStart(), totalProductsPage));
		return _mvShare;
	}

	@RequestMapping(value = "/san-pham")
	public ModelAndView showProducts(@RequestParam(value = "page", required = false, defaultValue = "1") int currentPage) {
		int totalProductsPage = 9; // Số sản phẩm trên mỗi trang
		String idCategory = "1"; // Giả định idCategory luôn là 1 hoặc giá trị mặc định

		// Lấy tổng số sản phẩm trong danh mục được chọn
		int totalData = categoryServiceImpl.GetAllProductsByID(Integer.parseInt(idCategory)).size();

		// Tính toán thông tin phân trang
		PaginatesDto paginatesInfo = paginatesServiceImpl.GetInfoPaginates(totalData, totalProductsPage, currentPage);

		// Thiết lập thông tin cho trang
		_mvShare.setViewName("user/products/categories");
		_mvShare.addObject("idCategory", idCategory); // Lưu id của danh mục (ở đây mặc định là "1")
		_mvShare.addObject("paginatesInfo", paginatesInfo); // Lưu thông tin phân trang
		_mvShare.addObject("productsPaginate", categoryServiceImpl.GetDataProductsPaginate(Integer.parseInt(idCategory),
				paginatesInfo.getStart(), totalProductsPage)); // Lưu danh sách sản phẩm phân trang
		return _mvShare;
	}
}