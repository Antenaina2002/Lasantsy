package org.lasantsy.lasantsy.service;

import org.lasantsy.lasantsy.models.Stock;
import org.lasantsy.lasantsy.repository.StockRepository;

import java.util.List;

public class StockService {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public Stock saveStock(Stock stock) {
        return stockRepository.save(stock);
    }

    public List<Stock> getAllStock() {
        return stockRepository.findAll();
    }

    public Stock getStockById(Long id) {
        return stockRepository.findById(id);
    }

    public void deleteStock(Stock stock) {
        stockRepository.delete(stock);
    }

    public void deleteStockById(Long id) {
        stockRepository.deleteById(id);
    }
}
