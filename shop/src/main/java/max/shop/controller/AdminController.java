package max.shop.controller;

import lombok.RequiredArgsConstructor;
//import max.shop.dto.request.CreateProductRequest;
import max.shop.response.ResponseService;
import max.shop.service.ProductService;
import org.springframework.web.bind.annotation.*;

/**
 * /item/{id} GET : 상품 조회
 * /item/{id} PUT : 상품 수정
 * /item POST : 상품 등록
 * /items GET : 상품 목록 조회
 */

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final ProductService productService;
    private final ResponseService responseService;

    @GetMapping("/item/{id}")
    public void product(@PathVariable String id){
    }

//    @PostMapping
//    public SingleResult<ProductResponse> postNewProduct(@RequestBody CreateProductRequest product){
//        productService.createProduct(product);
//    }
}
