package max.shop.service;

import lombok.RequiredArgsConstructor;
//import max.shop.dto.request.CreateProductRequest;
import max.shop.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

//    public void createProduct(CreateProductRequest dto) {
//        productRepository.save(dto.toEntity());
//    }
}
