package DiamondShop.Service.User;

import org.springframework.stereotype.Service;

import DiamondShop.Dto.PaginatesDto;

@Service
public class PaginatesServiceImpl implements IPaginatesService {
	public PaginatesDto GetInfoPaginates(int totalData, int limit, int currentPage) {
		PaginatesDto paginatesDto = new PaginatesDto();
		paginatesDto.setLimit(limit);
		paginatesDto.setTotalPage(InInSetInfoTotalPage(totalData, limit));
		paginatesDto.setCurrentPage(CheckCurrentPage(currentPage, paginatesDto.getTotalPage()));
		int start = FindStart(paginatesDto.getCurrentPage(), limit);
		paginatesDto.setStart(start);
		int end = FindEnd(paginatesDto.getStart(), limit, totalData);
		paginatesDto.setEnd(end);
		return paginatesDto;
	}

	private int FindEnd(int start, int limit, int totalData) {
		return start + limit > totalData ? totalData : (start + limit) - 1;
	}

	private int FindStart(int currentPage, int limit) {
		return ((currentPage - 1) * limit) + 1;
	}

	private int InInSetInfoTotalPage(int totalData, int limit) {
		Integer totalPage = 0;
		totalPage = totalData / limit;
		totalPage = totalPage * limit < totalData ? totalPage + 1 : totalPage;
		return totalPage;
	}
	
	private int CheckCurrentPage(int currentPage, int totalPage) {
		if (currentPage < 1) {
			return 1;
		}
		
		if (currentPage > totalPage) {
			return totalPage;
		}
		return currentPage;
	}
}