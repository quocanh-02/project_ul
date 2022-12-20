package com.poly.service.imbl;

import java.time.YearMonth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.repository.StartsRepo;
import com.poly.service.StartService;

@Service
public class StartServiceImpl implements StartService {

	@Autowired
	private StartsRepo repo;
	
	@Override
	public String[][] getTotalPriceLast6Months() {
		String result[][] = new String[2][6];
		YearMonth curentTime = YearMonth.now();
		for(int i=0 ;i <=5;i++) {
			String month = String.valueOf(curentTime.minusMonths((long)i).getMonthValue());
			String year = String.valueOf(curentTime.getYear());
			result[0][5-i] = month + "-" + year;
			result[1][5-i] = repo.getTotalPricePerMonth(month, year);
		}
		return result;
	}

}
